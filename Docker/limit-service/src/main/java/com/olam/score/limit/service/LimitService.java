package com.olam.score.limit.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.ErrorMessageUtil;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.ScoreBaseExceptionResponse;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.limit.controller.LimitController;
import com.olam.score.limit.domain.MasterLimit;
import com.olam.score.limit.domain.MasterLimitAttributeRef;
import com.olam.score.limit.domain.MasterLimitComment;
import com.olam.score.limit.domain.MasterLimitDetail;
import com.olam.score.limit.domain.MasterLimitDetailAttribute;
import com.olam.score.limit.domain.MasterLimitVolValueRef;
import com.olam.score.limit.dto.BaseLimitAttribute;
import com.olam.score.limit.dto.LimitBasisTypeDto;
import com.olam.score.limit.dto.LimitDetails;
import com.olam.score.limit.dto.LimitDto;
import com.olam.score.limit.dto.LimitVolumeValueTypeDto;
import com.olam.score.limit.repository.LimitAttributeDetailRepository;
import com.olam.score.limit.repository.LimitAttributeRefRepository;
import com.olam.score.limit.repository.LimitDetailRepository;
import com.olam.score.limit.repository.LimitRepository;
import com.olam.score.limit.repository.LimitVolumeValueTypeRepository;
import com.olam.score.limit.util.LimitSpecificationsBuilder;

@Service
public class LimitService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private LimitRepository repository;
	@Autowired
	private ListViewUtil listview;
	@Autowired
	private WebServiceCallUtil webServiceCall;
	@Autowired
	private LimitVolumeValueTypeRepository limitVolumeValueTypeRepository;
	@Autowired
	private LimitAttributeRefRepository limitAttributeRefRepository;
	@Autowired
	private LimitDetailRepository limitDetailRepository;
	@Autowired
	private LimitAttributeDetailRepository limitAttributeDetailRepository;
	@Autowired
	EntityManager entityManager;
	@Value("${reference.name}")
	private String referenceName;

	@Transactional
	public LimitDto readById(Integer limitId) {

		MasterLimit limit = repository.findOne(limitId);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		List<Object[]> entityList = getEntityList();
		List<Map<Object, Object>> uomList = webServiceCall.getInternalServiceData(referenceName,
				"/reference/uomservice/uom");
		List<Map<Object, Object>> currencyList = webServiceCall.getInternalServiceData(referenceName,
				"/reference/currencyservice/currency");

		return limit.convertEntityToDto(statusMap, entityList, uomList, currencyList, webServiceCall);
	}

	@Transactional
	public List<LimitDto> readAll() {
		ViewDto view = getViewDetails(LimitController.class);
		List<String> filterList = view.getFiltersArray();
		LimitSpecificationsBuilder builder = new LimitSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterLimit> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<LimitDto> limitDtoList = new ArrayList<>();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		List<Object[]> entityList = getEntityList();
		List<MasterLimit> masterLimitList = repository.findAll(spec, pageRequest).getContent();
		List<Map<Object, Object>> uomList = webServiceCall.getInternalServiceData(referenceName,
				"/reference/uomservice/uom");
		List<Map<Object, Object>> currencyList = webServiceCall.getInternalServiceData(referenceName,
				"/reference/currencyservice/currency");
		for (MasterLimit masterLimit : masterLimitList) {
			limitDtoList
					.add(masterLimit.convertEntityToDto(statusMap, entityList, uomList, currencyList, webServiceCall));
		}
		return limitDtoList;

	}

	private List<Object[]> getEntityList() {
		Query query;
		query = entityManager.createNativeQuery(
				"select ae.PK_ENTITY_ID, ENTITY_NAME, ENTITY_URL, LIMIT_IND from auth.AUTH_ENTITY ae where "
						+ "ae.LIMIT_IND = :limitInd");
		query.setParameter("limitInd", Constants.YES);
		List<Object[]> authEntitys = query.getResultList();

		return authEntitys;

	}

	@Transactional
	public LimitDto create(LimitDto limitDto) {
		MasterLimit masterLimit = null;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		String action = limitDto.getAction();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			log.info("===draft action to perform for create ===");
			limitDto.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===save(active) action to perform for create ===");
			limitDto.setStatusName(Constants.ACTIVE);
		}

		masterLimit = repository.saveAndFlush(generateLimitEntity(limitDto, "create", statusMap));
		masterLimit.setLimitNumber(Constants.PREFIX_LIMIT_NO + masterLimit.getPkLimitId());
		masterLimit.setPkLimitId(masterLimit.getPkLimitId());
		masterLimit = repository.saveAndFlush(masterLimit);

		List<Object[]> entityList = getEntityList();
		List<Map<Object, Object>> uomList = webServiceCall.getInternalServiceData(referenceName,
				"/reference/uomservice/uom");
		List<Map<Object, Object>> currencyList = webServiceCall.getInternalServiceData(referenceName,
				"/reference/currencyservice/currency");

		return masterLimit.convertEntityToDto(statusMap, entityList, uomList, currencyList, webServiceCall);
	}

	private MasterLimit generateLimitEntity(LimitDto limitDto, String action, Map<Integer, String> statusMap) {

		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();

		if (limitDto.getLimitBasisTypeId().equals(limitDto.getAdditionallimitBasisTypeId())) {
			log.error("===Limit Basis and Additional Basis are same.===");
			ErrorMessageUtil.errorHandler(new Object[] {}, errorBeans, Constants.ERROR_SAME_BASIS_ADDNLBASIS,
					Constants.ERROR_TYPE_SAME_BASIS_ADDNLBASIS);
			throw new ScoreBaseException(errorBeans, HttpStatus.BAD_REQUEST);
		}

		MasterLimit masterLimit = new MasterLimit();
		if ("create".equalsIgnoreCase(action)) {

			// get limit based on basis type and basis
			MasterLimit limitBasis = repository.findByBasis(limitDto.getLimitBasisTypeId(), limitDto.getLimitBasisId());
			if (limitBasis != null) {
				ErrorMessageUtil.errorHandler(new Object[] {}, errorBeans, Constants.ERROR_BASIS_BASISNAME_DUPLICATE,
						Constants.ERROR_TYPE_BASIS_BASISNAME_DUPLICATE);
				throw new ScoreBaseException(errorBeans, HttpStatus.BAD_REQUEST);
			}

			// get limit based on basis type, basis, additional basis type,
			// additional basis
			MasterLimit limitBasisAdditionalbasis = repository.findByBasisAdditionalbasis(
					limitDto.getLimitBasisTypeId(), limitDto.getLimitBasisId(),
					limitDto.getAdditionallimitBasisTypeId(), limitDto.getAdditionallimitBasisId());
			if (limitBasisAdditionalbasis != null) {
				ErrorMessageUtil.errorHandler(new Object[] {}, errorBeans,
						Constants.ERROR_BASIS_BASISNAME_ADDNLBASIS_ADDNLBASISNAME_DUPLICATE,
						Constants.ERROR_TYPE_BASIS_BASISNAME_ADDNLBASIS_ADDNLBASISNAME_DUPLICATE);
				throw new ScoreBaseException(errorBeans, HttpStatus.BAD_REQUEST);
			}

			masterLimit.setCreatedBy("Test");
			masterLimit.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		} else if ("update".equalsIgnoreCase(action)) {

			// get limit based on basis type and basis
			MasterLimit limitBasis = repository.findByBasis(limitDto.getLimitBasisTypeId(), limitDto.getLimitBasisId());
			if (limitBasis != null && !limitDto.getLimitHeaderId().equals(limitBasis.getPkLimitId())) {
				ErrorMessageUtil.errorHandler(new Object[] {}, errorBeans, Constants.ERROR_BASIS_BASISNAME_DUPLICATE,
						Constants.ERROR_TYPE_BASIS_BASISNAME_DUPLICATE);
				throw new ScoreBaseException(errorBeans, HttpStatus.BAD_REQUEST);
			}

			// get limit based on basis type, basis, additional basis type,
			// additional basis
			MasterLimit limitBasisAdditionalbasis = repository.findByBasisAdditionalbasis(
					limitDto.getLimitBasisTypeId(), limitDto.getLimitBasisId(),
					limitDto.getAdditionallimitBasisTypeId(), limitDto.getAdditionallimitBasisId());
			if (limitBasisAdditionalbasis != null && !limitDto.getLimitHeaderId().equals(limitBasis.getPkLimitId())) {
				ErrorMessageUtil.errorHandler(new Object[] {}, errorBeans,
						Constants.ERROR_BASIS_BASISNAME_ADDNLBASIS_ADDNLBASISNAME_DUPLICATE,
						Constants.ERROR_TYPE_BASIS_BASISNAME_ADDNLBASIS_ADDNLBASISNAME_DUPLICATE);
				throw new ScoreBaseException(errorBeans, HttpStatus.BAD_REQUEST);
			}
			MasterLimit limits = repository.findOne(limitDto.getLimitHeaderId());
			masterLimit = limits;
			// delete all attribute details
			for (MasterLimitDetail limitDetail : limits.getMasterLimitDetailCollection()) {
				limitAttributeDetailRepository.deleteByFkLimitDetailId(limitDetail);
			}
			// delete all limit details
			limitDetailRepository.deleteByFkLimitId(limits);
		}
		
		masterLimit.setModifiedBy("Test");
		masterLimit.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		for (LimitDetails limitDetail : limitDto.getLimitDetails()) {

			if (limitDetail.getIsTemporary() == null || limitDetail.getValidFrom() == null
					|| limitDetail.getValidTo() == null) {
				if (limitDetail.getIsTemporary() == null) {
					ErrorMessageUtil.errorHandler(new Object[] { limitDetail.getIsTemporary() }, errorBeans,
							Constants.ERROR_LIMIT_IS_TEMPORARY_NOTNULL,
							Constants.ERROR_TYPE_LIMIT_IS_TEMPORARY_NOTNULL);
				}
				if (limitDetail.getValidFrom() == null) {
					ErrorMessageUtil.errorHandler(new Object[] { limitDetail.getValidFrom() }, errorBeans,
							Constants.ERROR_LIMIT_FROMDATE_NOTNULL, Constants.ERROR_TYPE_LIMIT_FROMDATE_NOTNULL);
				}
				if (limitDetail.getValidTo() == null) {
					ErrorMessageUtil.errorHandler(new Object[] { limitDetail.getValidTo() }, errorBeans,
							Constants.ERROR_LIMIT_TODATE_NOTNULL, Constants.ERROR_TYPE_LIMIT_TODATE_NOTNULL);
				}
				throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
			}
		}
		Collection<LimitDetails> tempY = limitDto.getLimitDetails().stream()
				.filter(f -> Constants.YES.equals(f.getIsTemporary())).collect(Collectors.toList());

		LimitDetails[] arrayY = tempY.toArray(new LimitDetails[tempY.size()]);

		for (int i = 0; i < arrayY.length; i++) {
			Timestamp from = arrayY[i].getValidFrom();
			Timestamp to = arrayY[i].getValidTo();
			for (int j = i + 1; j < arrayY.length; j++) {
				Timestamp newFrom = arrayY[j].getValidFrom();
				Timestamp newTo = arrayY[j].getValidTo();
				if ((newFrom.after(from) && newFrom.before(to)) || (newTo.after(from) && newFrom.before(to))
						|| (newFrom.equals(from)) || (newFrom.equals(to)) || (newTo.equals(from))
						|| (newTo.equals(to))) {
					log.error("===Temporary Date overlaps.===");
					ErrorMessageUtil.errorHandler(new Object[] {}, errorBeans, Constants.ERROR_TEMPORARY_DATE_OVERLAP,
							Constants.ERROR_TYPE_TEMPORARY_DATE_OVERLAP);
					throw new ScoreBaseException(errorBeans, HttpStatus.BAD_REQUEST);
				}
			}
		}

		Collection<LimitDetails> tempN = limitDto.getLimitDetails().stream()
				.filter(f -> Constants.NO.equals(f.getIsTemporary())).collect(Collectors.toList());
		LimitDetails[] arrayN = tempN.toArray(new LimitDetails[tempN.size()]);

		for (int i = 0; i < arrayN.length; i++) {
			Timestamp from = arrayN[i].getValidFrom();
			Timestamp to = arrayN[i].getValidTo();
			for (int j = i + 1; j < arrayN.length; j++) {
				Timestamp newFrom = arrayN[j].getValidFrom();
				Timestamp newTo = arrayN[j].getValidTo();
				if ((newFrom.after(from) && newFrom.before(to)) || (newTo.after(from) && newFrom.before(to))
						|| (newFrom.equals(from)) || (newFrom.equals(to)) || (newTo.equals(from))
						|| (newTo.equals(to))) {
					ErrorMessageUtil.errorHandler(new Object[] {}, errorBeans, Constants.ERROR_PERMANENT_DATE_OVERLAP,
							Constants.ERROR_TYPE_PERMANENT_DATE_OVERLAP);
					throw new ScoreBaseException(errorBeans, HttpStatus.BAD_REQUEST);
				}
			}
		}

		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(limitDto.getStatusName())).map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			log.info("===Got Status Id in Status map ===");
			masterLimit.setFkStatusId(statusId.get());
		}

		masterLimit.setFkPrimaryLimitBasisTypeId(limitDto.getLimitBasisTypeId());
		masterLimit.setPrimaryLimitBasisId(limitDto.getLimitBasisId());
		masterLimit.setFkAdditionalLmtBasisTypeId(limitDto.getAdditionallimitBasisTypeId());
		masterLimit.setAdditionalLimitBasisId(limitDto.getAdditionallimitBasisId());

		if (limitDto.getComments() != null && !limitDto.getComments().isEmpty()) {
			MasterLimitComment limitComment = new MasterLimitComment();
			limitComment.setCommentText(limitDto.getComments().get(0).getCommentText());
			limitComment.setCreatedBy("Test");
			limitComment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			limitComment.setModifiedBy("Test");
			limitComment.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			List<MasterLimitComment> masterLimitCommentCollection = new ArrayList<>();
			masterLimitCommentCollection.add(limitComment);
			masterLimit.setMasterLimitCommentCollection(masterLimitCommentCollection);
			limitComment.setFkLimitId(masterLimit);
		}

		Integer primaryBasisTypeId = limitDto.getLimitBasisTypeId();
		Query query;
		query = entityManager.createNativeQuery(
				"select ae.PK_ENTITY_ID, ENTITY_NAME, ENTITY_URL, LIMIT_IND from auth.AUTH_ENTITY ae where "
						+ "ae.PK_ENTITY_ID = :primaryBasisTypeId");
		query.setParameter("primaryBasisTypeId", primaryBasisTypeId);
		Object[] authEntity = (Object[]) query.getSingleResult();
		String entityName = (String) authEntity[1];

		List<MasterLimitDetail> masterLimitDetails = new ArrayList<>();
		for (LimitDetails limitDetail : limitDto.getLimitDetails()) {

			// set common limitdetails
			MasterLimitDetail masterLimitDetail = new MasterLimitDetail();
			masterLimitDetail.setIsTemporary(limitDetail.getIsTemporary());
			masterLimitDetail.setLimitValidFrom(limitDetail.getValidFrom());
			masterLimitDetail.setLimitValidTo(limitDetail.getValidTo());
			masterLimitDetail.setFkLimitId(masterLimit);

			// set limit attribute details
			List<MasterLimitDetailAttribute> limitDetailAttributes = new ArrayList<>();

			if (entityName.equalsIgnoreCase(Constants.FOREX_BASISTYPE)) {
				// value
				attributeDetailDtoToModel(limitDetail.getValueAttribute(), masterLimitDetail, limitDetailAttributes,
						Constants.VALUE);
			} else if (entityName.equalsIgnoreCase(Constants.EXCHANGE_BASISTYPE)) {
				// current month
				attributeDetailDtoToModel(limitDetail.getCurrentMonthAttribute(), masterLimitDetail,
						limitDetailAttributes, Constants.CURRENT_MONTH);
				// forward month
				attributeDetailDtoToModel(limitDetail.getForwardMonthAttribute(), masterLimitDetail,
						limitDetailAttributes, Constants.FORWARD_MONTH);
				// total
				attributeDetailDtoToModel(limitDetail.getTotalAttribute(), masterLimitDetail, limitDetailAttributes,
						Constants.TOTAL);
			} else {
				// VaR
				attributeDetailDtoToModel(limitDetail.getLimitVaRAttribute(), masterLimitDetail, limitDetailAttributes,
						Constants.VAR_LIMIT);

				// STRUCTURE
				attributeDetailDtoToModel(limitDetail.getLimitStructureAttribute(), masterLimitDetail,
						limitDetailAttributes, Constants.STRUCTURE_LIMIT);

				// Drawdown Limit
				attributeDetailDtoToModel(limitDetail.getLimitDrawDownAttribute(), masterLimitDetail,
						limitDetailAttributes, Constants.DRAWDOWN_LIMIT);
				// Delta Limit
				attributeDetailDtoToModel(limitDetail.getLimitDeltaAttribute(), masterLimitDetail,
						limitDetailAttributes, Constants.DELTA_LIMIT);
				// Gamma Limit
				attributeDetailDtoToModel(limitDetail.getLimitGammaAttribute(), masterLimitDetail,
						limitDetailAttributes, Constants.GAMMA_LIMIT);
				// Vega Limit
				attributeDetailDtoToModel(limitDetail.getLimitVegaAttribute(), masterLimitDetail, limitDetailAttributes,
						Constants.VEGA_LIMIT);
				// Basis Limit
				attributeDetailDtoToModel(limitDetail.getLimitBasisAttribute(), masterLimitDetail,
						limitDetailAttributes, Constants.BASIS_LIMIT);
				// OutRight
				attributeDetailDtoToModel(limitDetail.getLimitOutRightAttribute(), masterLimitDetail,
						limitDetailAttributes, Constants.OUTRIGHT_LIMIT);
			}

			masterLimitDetail.setMasterLimitDetailAttributeCollection(limitDetailAttributes);

			masterLimitDetails.add(masterLimitDetail);

		}
		masterLimit.setMasterLimitDetailCollection(masterLimitDetails);

		return masterLimit;
	}

	private BaseLimitAttribute attributeDetailDtoToModel(BaseLimitAttribute limitVaRAttribute,
			MasterLimitDetail masterLimitDetail, List<MasterLimitDetailAttribute> limitDetailAttributes,
			String limitAttributeType) {

		MasterLimitDetailAttribute limitDetailAttribute = new MasterLimitDetailAttribute();

		limitDetailAttribute.setLimitData(limitVaRAttribute.getLimit());
		MasterLimitVolValueRef limitVolValueRef = limitVolumeValueTypeRepository
				.findOne(limitVaRAttribute.getLimitVolumeValueRefId());
		limitDetailAttribute.setFkLimitVolValueRefId(limitVolValueRef);

		MasterLimitAttributeRef limitAttributeRef = limitAttributeRefRepository
				.findByLimitAttributeName(limitAttributeType);
		limitDetailAttribute.setFkLimitAttributeRefId(limitAttributeRef);

		limitDetailAttribute.setFkUomId(limitVaRAttribute.getLimitVolumeUomId());
		limitDetailAttribute.setFkCurrencyId(limitVaRAttribute.getLimitCurrencyId());
		limitDetailAttribute.setFkLimitDetailId(masterLimitDetail);

		limitDetailAttributes.add(limitDetailAttribute);
		return limitVaRAttribute;
	}

	public ViewDto getViewDetails(Class<? extends Object> currentfeature) {
		String featureId = currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		log.info("===viewdto from common has been generated for JSON===");
		PageDto pageDto = new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		pageDto.setTotalCount(repository.count());
		pageDto.setOperation("Client");// To be set from Database
		viewdto.setPage(pageDto);
		Class<LimitDto> uomDto = LimitDto.class;
		Field[] fields = uomDto.getDeclaredFields();
		Map<String, String> dataType = new HashMap<>();
		for (int i = 0; i < fields.length; i++) {
			String type = fields[i].getType().toString();
			type = type.substring(type.lastIndexOf('.') + 1, type.length());
			dataType.put(fields[i].getName(), type);
		}
		viewdto.setDataType(dataType);
		log.info("===dataType is set in view according to entity===");

		return viewdto;
	}

	public List<LimitBasisTypeDto> readLimitBasisType() {
		List<LimitBasisTypeDto> limitBasisTypeList = new ArrayList<>();

		Query query;
		query = entityManager.createNativeQuery(
				"select ae.PK_ENTITY_ID, ENTITY_NAME, ENTITY_URL, LIMIT_IND from auth.AUTH_ENTITY ae where "
						+ "ae.LIMIT_IND = :limitInd");
		query.setParameter("limitInd", Constants.YES);
		List<Object[]> authEntitys = query.getResultList();

		for (Object[] authEntity : authEntitys) {
			LimitBasisTypeDto limitBasisTypeDto = new LimitBasisTypeDto();
			limitBasisTypeDto.setLimitBasisTypeId((Integer) authEntity[0]);
			limitBasisTypeDto.setLimitBasisTypeName((String) authEntity[1]);
			limitBasisTypeDto.setUrl((String) authEntity[2]);
			limitBasisTypeList.add(limitBasisTypeDto);
		}

		return limitBasisTypeList;
	}

	public List<LimitVolumeValueTypeDto> readLimitVolumeValueType() {

		List<MasterLimitVolValueRef> limitVolValueTypes = limitVolumeValueTypeRepository.findAll();
		List<LimitVolumeValueTypeDto> limitVolValueTypeList = new ArrayList<>();

		for (MasterLimitVolValueRef limitVolValueType : limitVolValueTypes) {
			LimitVolumeValueTypeDto limitVolumeValueTypeDto = new LimitVolumeValueTypeDto();
			limitVolumeValueTypeDto.setLimitVolumeValueRefId(limitVolValueType.getPkLimitVolValueRefId());
			limitVolumeValueTypeDto.setLimitVolumeValueRefCode(limitVolValueType.getLimitVolValueCode());
			limitVolumeValueTypeDto.setLimitVolumeValueRefName(limitVolValueType.getLimitVolValueName());
			limitVolValueTypeList.add(limitVolumeValueTypeDto);
		}

		return limitVolValueTypeList;
	}

	public LimitDto update(LimitDto limitDto) {
		MasterLimit masterGrade = null;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		// deleteExtOriginIntlMapping(limitDto);
		masterGrade = repository.saveAndFlush(generateLimitEntity(limitDto, "update", statusMap));
		/*
		 * List<MasterLimitDetail> gradeOriginAssocList =
		 * gradeOriginAssocRepository.findAll(); List<Map<Object, Object>>
		 * brands = getDtoListMap(productUrl + "/product/brand/viewBrand");
		 * List<MasterLimitDetailAttribute> subTypeRef =
		 * processSubTypeRefRepository.findAll();
		 */

		List<Object[]> entityList = getEntityList();
		List<Map<Object, Object>> uomList = webServiceCall.getInternalServiceData(referenceName,
				"/reference/uomservice/uom");
		List<Map<Object, Object>> currencyList = webServiceCall.getInternalServiceData(referenceName,
				"/reference/currencyservice/currency");

		// limitDto.setExternalSystemList(generateExternalSystem(masterGrade));

		return masterGrade.convertEntityToDto(statusMap, entityList, uomList, currencyList, webServiceCall);
	}

	public void delete(List<Map<String, Integer>> body) {
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		Optional<Integer> statusId1 = statusMap.entrySet().stream().filter(e -> e.getValue().equals(Constants.INACTIVE))
				.map(Map.Entry::getKey).findFirst();
		Integer inactiveStatusId = null;
		if (statusId1.isPresent()) {
			inactiveStatusId = statusId1.get();
		}

		Optional<Integer> statusId2 = statusMap.entrySet().stream().filter(e -> e.getValue().equals(Constants.ACTIVE))
				.map(Map.Entry::getKey).findFirst();
		Integer activeStatusId = null;
		if (statusId2.isPresent()) {
			activeStatusId = statusId2.get();
		}

		for (Map<String, Integer> map : body) {
			MasterLimit limit;
			limit = repository.findOne(map.get("limitHeaderId"));

			if (limit.getFkStatusId() != null) {
				String currentStatusName = null;

				currentStatusName = statusMap.get(limit.getFkStatusId());
				log.info("Curerent Status Name : ");
				log.info(currentStatusName);
				if (currentStatusName.equals(Constants.ACTIVE)) {
					limit.setFkStatusId(inactiveStatusId);
					repository.saveAndFlush(limit);

				} else if (currentStatusName.equals(Constants.INACTIVE)) {

					limit.setFkStatusId(activeStatusId);
					repository.saveAndFlush(limit);
				} else if (currentStatusName.equals(Constants.DRAFT)) {
					repository.delete(limit);
				}

			}
		}
	}
}