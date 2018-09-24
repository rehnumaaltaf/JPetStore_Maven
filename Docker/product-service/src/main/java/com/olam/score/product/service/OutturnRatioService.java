package com.olam.score.product.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.domain.UserBean;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.EnumStatus;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.product.domain.MasterOutturnRatio;
import com.olam.score.product.domain.MasterOutturnRawGrade;
import com.olam.score.product.dto.MasterOutturnDto;
import com.olam.score.product.dto.OutturnRatioDto;
import com.olam.score.product.dto.OutturnRawGradeDto;
import com.olam.score.product.repository.OutturnRatioRepository;
import com.olam.score.product.util.OutturnRatioSpecificationsBuilder;

@Service
public class OutturnRatioService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Autowired
	private ListViewUtil listview;

	@Autowired
	private OutturnRatioRepository repository;

	@Value("${product.name}")
	private String productName;

	Map<Integer, String> statusMap;

	public MasterOutturnDto create(MasterOutturnDto masterOutturnDto) {

		MasterOutturnRawGrade masterOutturnRawGradeEntity;
		MasterOutturnDto responseDto = null;
		UserBean userBean = new UserBean();
		Locale locale;
		String action = masterOutturnDto.getStatusName();

		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			masterOutturnDto.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			masterOutturnDto.setStatusName(Constants.ACTIVE);
		}
		locale = userBean.getLocale();
		if (locale == null) {
			locale = Locale.getDefault();
		}
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		masterOutturnRawGradeEntity = repository.findByProdOriginGrade(
				masterOutturnDto.getOutturnRawGradeDto().getProdId(),
				masterOutturnDto.getOutturnRawGradeDto().getOriginId(),
				masterOutturnDto.getOutturnRawGradeDto().getGradeId());
		String message = CommonUtil.getTranslationMessage(locale, "error_duplicate");

		if (masterOutturnRawGradeEntity != null) {
			log.error("===Duplicate Product,Origin and Grade combination found in Database===");
			message = message.replace("$", "Product,Origin,Grade Combination ");
			throw new ScoreBaseException(masterOutturnRawGradeEntity.getPkOutturnRawGradeId(), message,
					HttpStatus.CONFLICT);

		} else {
			masterOutturnRawGradeEntity = repository
					.saveAndFlush(generateMasterOutturnRawGrade(masterOutturnDto, "create"));
			responseDto = masterOutturnRawGradeEntity.convertToOutturnDto(statusMap, null, null, null);

		}

		return responseDto;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<MasterOutturnDto> update(List<MasterOutturnDto> body) {
		List<MasterOutturnDto> newList = new ArrayList<>();
		MasterOutturnRawGrade masterOutturnRawGrade;
		Locale locale;
		UserBean userBean = new UserBean();
		locale = userBean.getLocale();
		if (locale == null) {
			locale = Locale.getDefault();
		}

		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		ResponseEntity<ResponseData> prodResponseEntity = webServiceCall.internalServiceCall(productName,
				"/product/productservice/product");
		ResponseData<List<Object>> prodBody = prodResponseEntity.getBody();
		List<Map<Object, Object>> prodList = (List<Map<Object, Object>>) prodBody.getBody();

		ResponseEntity<ResponseData> gradeResponseEntity = webServiceCall.internalServiceCall(productName,
				"/product/gradeservice/grade");
		ResponseData<List<Object>> gradeBody = gradeResponseEntity.getBody();
		List<Map<Object, Object>> gradeList = (List<Map<Object, Object>>) gradeBody.getBody();

		ResponseEntity<ResponseData> originResponseEntity = webServiceCall.internalServiceCall(productName,
				"/product/originservice/origin");
		ResponseData<List<Object>> originBody = originResponseEntity.getBody();
		List<Map<Object, Object>> originList = (List<Map<Object, Object>>) originBody.getBody();

		for (MasterOutturnDto masterOutturnDto : body) {
			masterOutturnRawGrade = repository.findByProdOriginGrade(
					masterOutturnDto.getOutturnRawGradeDto().getProdId(),
					masterOutturnDto.getOutturnRawGradeDto().getOriginId(),
					masterOutturnDto.getOutturnRawGradeDto().getGradeId());
			String message = CommonUtil.getTranslationMessage(locale, "error_duplicate");

			if (masterOutturnRawGrade != null && !masterOutturnRawGrade.getPkOutturnRawGradeId()
					.equals(masterOutturnDto.getOutturnRawGradeDto().getOutturnRawGradeId())) {
				log.error("===Duplicate Product,Origin and Grade combination found in Database===");
				message = message.replace("$", "Product,Origin,Grade Combination ");
				throw new ScoreBaseException(masterOutturnRawGrade.getPkOutturnRawGradeId(), message,
						HttpStatus.CONFLICT);

			} else {

				masterOutturnRawGrade = repository
						.saveAndFlush(generateMasterOutturnRawGrade(masterOutturnDto, "update"));
				log.info("===Updated the Outturn Ratio ID===");
			}
			masterOutturnDto = masterOutturnRawGrade.convertToOutturnDto(statusMap, prodList, originList, gradeList);
			newList.add(masterOutturnDto);
		}
		return newList;
	}

	private MasterOutturnRawGrade generateMasterOutturnRawGrade(MasterOutturnDto masterOutturnDto, String operation) {

		MasterOutturnRawGrade masterOutturnRawGrade = new MasterOutturnRawGrade();
		// Integer statusIdValue = null;
		statusMap = webServiceCall.getAllStatus();
		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(masterOutturnDto.getStatusName())).map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			// statusIdValue = statusId.get();

			masterOutturnRawGrade.setFkStatusId(statusId.get());
		}

		masterOutturnRawGrade.setRawGradeCode(masterOutturnDto.getOutturnRawGradeDto().getRawGradeCode());
		masterOutturnRawGrade.setRawGradeName(masterOutturnDto.getOutturnRawGradeDto().getRawGradeName());
		masterOutturnRawGrade.setFkProdId(masterOutturnDto.getOutturnRawGradeDto().getProdId());
		masterOutturnRawGrade.setFkGradeId(masterOutturnDto.getOutturnRawGradeDto().getGradeId());
		masterOutturnRawGrade.setFkOriginId(masterOutturnDto.getOutturnRawGradeDto().getOriginId());

		if ("create".equalsIgnoreCase(operation)) {
			masterOutturnRawGrade.setCreatedBy("Test");// TODO: Hard Coding
			masterOutturnRawGrade.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			masterOutturnRawGrade.setModifiedBy("TestUser");// TODO: Hard Coding
			masterOutturnRawGrade.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		} else if ("update".equalsIgnoreCase(operation)) {
			masterOutturnRawGrade
					.setPkOutturnRawGradeId(masterOutturnDto.getOutturnRawGradeDto().getOutturnRawGradeId());
			masterOutturnRawGrade.setModifiedBy("Test");// TODO: Hard Coding
			masterOutturnRawGrade.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}

		if (masterOutturnDto.getDeletedIds() != null && (!masterOutturnDto.getDeletedIds().isEmpty())) {
			for (Integer idToBeDeleted : masterOutturnDto.getDeletedIds()) {
				repository.deleteByOuturnRatioListId(idToBeDeleted);
			}

		}

		List<MasterOutturnRatio> generateMasterOutturnRatio = generateMasterOutturnRatio(
				masterOutturnDto.getOutturnRatioDto(), masterOutturnRawGrade, operation);
		masterOutturnRawGrade.setMasterOutturnRatioCollection(generateMasterOutturnRatio);
		return masterOutturnRawGrade;

	}

	private List<MasterOutturnRatio> generateMasterOutturnRatio(List<OutturnRatioDto> outturnRatioDto,
			MasterOutturnRawGrade masterOutturnRawGrade, String operation) {

		List<MasterOutturnRatio> masterOutturnRatioList = new LinkedList<MasterOutturnRatio>();

		for (OutturnRatioDto outturnRatio : outturnRatioDto) {
			MasterOutturnRatio masterOutturnRatio = new MasterOutturnRatio();
			masterOutturnRatio.setFkOutturnRawGradeId(masterOutturnRawGrade);
			masterOutturnRatio.setFkFinishedGradeId(outturnRatio.getFinishedGradeId());
			masterOutturnRatio.setQuantityRatio(outturnRatio.getQuantityRatio());
			masterOutturnRatio.setAbilityToBearRatio(outturnRatio.getAbilityToBearRatio());
			masterOutturnRatio.setFkStatusId(masterOutturnRawGrade.getFkStatusId());

			if ("create".equalsIgnoreCase(operation)) {
				masterOutturnRatio.setCreatedBy("Test");// Hard Coding
				masterOutturnRatio.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				masterOutturnRatio.setModifiedBy("TestUser");// Hard Coding
				masterOutturnRatio.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			} else if ("update".equalsIgnoreCase(operation)) {
				if (outturnRatio.getOutturnRatioId() != null) {
					masterOutturnRatio.setPkOutturnRatioId(outturnRatio.getOutturnRatioId());
				} else {
					masterOutturnRatio.setCreatedBy("Test");// Hard Coding needs changes
					masterOutturnRatio.setCreatedDate(new Timestamp(System.currentTimeMillis()));

				}
				masterOutturnRatio.setModifiedBy("Test");// Hard Coding
				masterOutturnRatio.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			}

			masterOutturnRatioList.add(masterOutturnRatio);

		}

		return masterOutturnRatioList;
	}

	// delete functionality of outturn ratio

	public void deleteOutturn(List<Map<String, Integer>> body) {

		for (Map<String, Integer> outturnRawGradeId : body) {
			outturnRawGradeId.forEach((name, id) -> {
				MasterOutturnRawGrade masterOutturnRawGrade = new MasterOutturnRawGrade();
				masterOutturnRawGrade = repository.findOne(id);

				if (masterOutturnRawGrade != null) {
					if (masterOutturnRawGrade.getFkStatusId() != 0
							&& (EnumStatus.ACTIVE.getValue()) == (masterOutturnRawGrade.getFkStatusId())) {
						log.info(id.toString());
						log.info("===Changing Active status to Inactive===");
						masterOutturnRawGrade.setFkStatusId(EnumStatus.INACTIVE.getValue());

						if (masterOutturnRawGrade.getMasterOutturnRatioCollection().size() > 0) {
							for (MasterOutturnRatio outturnRatio : masterOutturnRawGrade
									.getMasterOutturnRatioCollection()) {
								outturnRatio.setFkStatusId(EnumStatus.INACTIVE.getValue());
							}
						}
						repository.saveAndFlush(masterOutturnRawGrade);

					} else if (masterOutturnRawGrade.getFkStatusId() != 0
							&& (EnumStatus.DRAFT.getValue()) == (masterOutturnRawGrade.getFkStatusId())) {

						repository.delete(masterOutturnRawGrade);
					} else if (masterOutturnRawGrade.getFkStatusId() != 0
							&& EnumStatus.INACTIVE.getValue() == masterOutturnRawGrade.getFkStatusId()) {
						masterOutturnRawGrade.setFkStatusId(EnumStatus.ACTIVE.getValue());
						if (!masterOutturnRawGrade.getMasterOutturnRatioCollection().isEmpty()) {
							for (MasterOutturnRatio masterOutturnRatio : masterOutturnRawGrade
									.getMasterOutturnRatioCollection()) {
								masterOutturnRatio.setFkStatusId(EnumStatus.ACTIVE.getValue());
							}
						}
						repository.saveAndFlush(masterOutturnRawGrade);
					}
				} else {
					throw new ScoreBaseException(0, "Outturn raw Id Not Found For deletion", HttpStatus.NOT_FOUND);

				}

			});

		}
	}

	// View Service for outturnratio
	// get view dto

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
		Class<OutturnRawGradeDto> outturnRawGradeDto = OutturnRawGradeDto.class;
		Field[] fields = outturnRawGradeDto.getDeclaredFields();
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

	// Read All for OutturnRatio

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public List<MasterOutturnDto> readAll(ViewDto view) {
		List<MasterOutturnDto> newList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		OutturnRatioSpecificationsBuilder builder = new OutturnRatioSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterOutturnRawGrade> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterOutturnRawGrade> oldList = repository.findAll(spec, pageRequest).getContent();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		ResponseEntity<ResponseData> prodResponseEntity = webServiceCall.internalServiceCall(productName,
				"/product/productservice/product");

		ResponseData<List<Object>> prodBody = prodResponseEntity.getBody();
		List<Map<Object, Object>> prodList = (List<Map<Object, Object>>) prodBody.getBody();

		
		ResponseEntity<ResponseData> originResponseEntity = webServiceCall.internalServiceCall(productName,
				"/product/originservice/origin");
		ResponseData<List<Object>> originBody = originResponseEntity.getBody();
		List<Map<Object, Object>> originList = (List<Map<Object, Object>>) originBody.getBody();

		ResponseEntity<ResponseData> gradeResponseEntity = webServiceCall.internalServiceCall(productName,
				"/product/gradeservice/grade");
		ResponseData<List<Object>> gradeBody = gradeResponseEntity.getBody();
		List<Map<Object, Object>> gradeList = (List<Map<Object, Object>>) gradeBody.getBody();

		for (MasterOutturnRawGrade masterOutturnRawGrade : oldList) {
			MasterOutturnDto masterOutturnDto;
			masterOutturnDto = masterOutturnRawGrade.convertToOutturnDto(statusMap, prodList, originList, gradeList);
			newList.add(masterOutturnDto);
		}

		return newList;
	}

	// get all the details of single outturnrawgrade: ViewById
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public MasterOutturnDto readById(Integer id) {

		MasterOutturnRawGrade masterOutturnRawGrade = repository.findOne(id);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

			
		ResponseEntity<ResponseData> prodResponseEntity = webServiceCall.internalServiceCall(productName,
				"/product/productservice/product");
		ResponseData<List<Object>> prodBody = prodResponseEntity.getBody();
		List<Map<Object, Object>> prodList = (List<Map<Object, Object>>) prodBody.getBody();

		ResponseEntity<ResponseData> originResponseEntity = webServiceCall.internalServiceCall(productName,
				"/product/originservice/origin");
		ResponseData<List<Object>> originBody = originResponseEntity.getBody();
		List<Map<Object, Object>> originList = (List<Map<Object, Object>>) originBody.getBody();

		ResponseEntity<ResponseData> gradeResponseEntity = webServiceCall.internalServiceCall(productName,
				"/product/gradeservice/grade");
		ResponseData<List<Object>> gradeBody = gradeResponseEntity.getBody();
		List<Map<Object, Object>> gradeList = (List<Map<Object, Object>>) gradeBody.getBody();

		return masterOutturnRawGrade.convertToOutturnDto(statusMap, prodList, originList, gradeList);

	}

}
