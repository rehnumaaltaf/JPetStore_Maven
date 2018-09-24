package com.olam.score.terms.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
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
import com.olam.score.common.domain.UserBean;
import com.olam.score.common.dto.PageDto;

import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.EnumStatus;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.terms.domain.MasterContractTerms;
import com.olam.score.terms.domain.MasterContractTermsPurchase;
import com.olam.score.terms.domain.MasterContractTermsSales;
import com.olam.score.terms.dto.ContractTermsDto;
import com.olam.score.terms.dto.ContractTermsPurchaseDto;
import com.olam.score.terms.dto.ContractTermsSalesDto;
import com.olam.score.terms.repository.ContractTermsRepository;
import com.olam.score.terms.util.ContractTermsSpecificationsBuilder;

@Service
public class ContractTermsService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private WebServiceCallUtil webServiceCall;
	@Autowired
	private ListViewUtil listview;
	@Autowired
	private ContractTermsRepository repository;
	@Autowired
	EntityManager entityManager;

	@Value("${reference.name}")
	private String referenceName;

	@Value("${product.name}")
	private String productName;

	@Value("${cost.name}")
	private String costName;

	@Transactional
	public ContractTermsDto create(ContractTermsDto contractTermsDto) {
		MasterContractTerms masterContractTerms;
		ContractTermsDto dto;
		UserBean userBean = new UserBean();
		Locale locale;
		String action = contractTermsDto.getStatusName();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			log.info("===draft action to perform for create ===");
			contractTermsDto.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===save(active) action to perform for create ===");
			contractTermsDto.setStatusName(Constants.ACTIVE);
		}
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		String contractTermsName;
		String contractTermsCode;
		contractTermsName = repository.findByContractTermsName(contractTermsDto.getContractTermsName());
		contractTermsCode = repository.findByContractTermsCode(contractTermsDto.getContractTermsCode());
		locale = userBean.getLocale();
		if (locale == null) {
			locale = Locale.getDefault();
		}
		String message = CommonUtil.getTranslationMessage(locale, "error_duplicate");
		if ((contractTermsName != null) && (contractTermsCode != null)) {
			message = message.replace("$", contractTermsCode + " Code" + " and " + contractTermsName + " Name");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		} else if ((contractTermsName != null)) {
			message = message.replace("$", contractTermsName + " Name");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		} else if ((contractTermsCode != null)) {
			message = message.replace("$", contractTermsCode + " Code");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		} else {
			masterContractTerms = repository
					.saveAndFlush(generateMastercontractTerms(contractTermsDto, "create", statusMap));
			dto = masterContractTerms.convertToContractTermsDto(statusMap, null, null, null);
		}
		return dto;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ContractTermsDto> update(List<ContractTermsDto> body) {
		List<ContractTermsDto> newList = new ArrayList<>();
		MasterContractTerms masterContractTerms;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		for (ContractTermsDto contractTermsDto : body) {
			UserBean userBean = new UserBean();
			Locale locale;
			locale = userBean.getLocale();
			String action = contractTermsDto.getStatusName();
			if (action.equalsIgnoreCase(Constants.DRAFT)) {
				log.info("===draft action to perform for create ===");
				contractTermsDto.setStatusName(Constants.DRAFT);
			} else if (action.equalsIgnoreCase(Constants.SAVE)) {
				log.info("===save(active) action to perform for create ===");
				contractTermsDto.setStatusName(Constants.ACTIVE);
			}

			MasterContractTerms contractTermsNameId = null;
			MasterContractTerms contractTermsCodeId = null;
			List<MasterContractTerms> nameList = repository
					.findByContractTermsNameandId(contractTermsDto.getContractTermsName());
			List<MasterContractTerms> codeList = repository
					.findByContractTermsCodeandId(contractTermsDto.getContractTermsCode());
			if (!nameList.isEmpty())
				contractTermsNameId = nameList.get(0);
			if (!codeList.isEmpty())
				contractTermsCodeId = codeList.get(0);
			if (locale == null) {
				locale = Locale.getDefault();
			}
			String message = CommonUtil.getTranslationMessage(locale, "error_duplicate");
			if ((contractTermsNameId != null
					&& !contractTermsNameId.getPkContractTermsId().equals(contractTermsDto.getContractTermsId()))
					&& (contractTermsCodeId != null && !contractTermsCodeId.getPkContractTermsId()
							.equals(contractTermsDto.getContractTermsId()))) {
				message = message.replace("$", contractTermsCodeId.getContractTermsCode() + " Code" + " and "
						+ contractTermsNameId.getContractTermsName() + " Name");
				throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			} else if (contractTermsNameId != null
					&& !contractTermsNameId.getPkContractTermsId().equals(contractTermsDto.getContractTermsId())) {
				message = message.replace("$", contractTermsNameId.getContractTermsName() + " Name");
				throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			} else if (contractTermsCodeId != null
					&& !contractTermsCodeId.getPkContractTermsId().equals(contractTermsDto.getContractTermsId())) {
				message = message.replace("$", contractTermsCodeId.getContractTermsCode() + " Code");
				throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
			} else {
				masterContractTerms = repository
						.saveAndFlush(generateMastercontractTerms(contractTermsDto, "update", statusMap));
				log.info("===Updated the ContractTerms ID===");
				contractTermsDto = masterContractTerms.convertToContractTermsDto(statusMap, null, null, null);
				newList.add(contractTermsDto);
			}
		}
		return newList;
	}

	private MasterContractTerms generateMastercontractTerms(ContractTermsDto contractTermsDto, String action,
			Map<Integer, String> statusMap) {
		MasterContractTerms masterContractTerms = new MasterContractTerms();
		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(contractTermsDto.getStatusName())).map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			log.info("===Got Status Id in Status map ===");
			masterContractTerms.setFkStatusId(statusId.get());
		}

		// trim() to avoid space
		masterContractTerms.setContractTermsCode(contractTermsDto.getContractTermsCode().trim());
		masterContractTerms.setContractTermsName(contractTermsDto.getContractTermsName().trim());
		if (contractTermsDto.getContractTermsDesc() != null)
			masterContractTerms.setContractTermsDesc(contractTermsDto.getContractTermsDesc().trim());
		masterContractTerms.setFkProdId(contractTermsDto.getProdId());

		masterContractTerms
				.setFkContractTermsBaseRefId(repository.findBaseRef(contractTermsDto.getContractTermsBaseRefId()));
		masterContractTerms
				.setFkContractTermsBasisRefId(repository.findBasisRef(contractTermsDto.getContractTermsBasisRefId()));

		if ("create".equalsIgnoreCase(action)) {
			masterContractTerms.setCreatedBy("Test");
			masterContractTerms.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			masterContractTerms.setModifiedBy("Test");
			masterContractTerms.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		} else if ("update".equalsIgnoreCase(action)) {
			masterContractTerms.setPkContractTermsId(contractTermsDto.getContractTermsId());
			masterContractTerms.setModifiedBy("Test");
			masterContractTerms.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}

		Collection<MasterContractTermsPurchase> masterContractTermsPurchaseCollection = new ArrayList<>();
		if ((contractTermsDto.getContractTermsPurchaseDto() != null)
				&& (!contractTermsDto.getContractTermsPurchaseDto().isEmpty())) {
			for (ContractTermsPurchaseDto contractTermsPurchaseDto : contractTermsDto.getContractTermsPurchaseDto()) {
				MasterContractTermsPurchase masterContractTermsPurchase = new MasterContractTermsPurchase();

				masterContractTermsPurchase.setFkStatusId(statusId.get());
				masterContractTermsPurchase.setBudgetInd(contractTermsPurchaseDto.getBudgetInd());
				masterContractTermsPurchase.setFkCostId(contractTermsPurchaseDto.getCostId());
				masterContractTermsPurchase.setFkCostGroupId(contractTermsPurchaseDto.getCostGroupId());
				masterContractTermsPurchase.setFkContrTrmsAddreduceRefId(
						repository.findAddreduce(contractTermsPurchaseDto.getContrTrmsAddreduceRefId()));
				masterContractTermsPurchase.setFkContractTermsId(masterContractTerms);
				masterContractTermsPurchase.setFkCostGroupId(contractTermsPurchaseDto.getCostGroupId());

				if ("create".equalsIgnoreCase(action)) {
					masterContractTermsPurchase.setCreatedBy("Test");
					masterContractTermsPurchase.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				} else if ("update".equalsIgnoreCase(action)) {
					if (contractTermsPurchaseDto.getContractTermsPurchaseId() != null) {
						masterContractTermsPurchase
								.setPkContractTermsPurchaseId(contractTermsPurchaseDto.getContractTermsPurchaseId());
					} else {
						masterContractTermsPurchase.setCreatedBy("Test");
						masterContractTermsPurchase.setCreatedDate(new Timestamp(System.currentTimeMillis()));
					}
					masterContractTermsPurchase.setModifiedBy("Test");
					masterContractTermsPurchase.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				}
				masterContractTermsPurchaseCollection.add(masterContractTermsPurchase);
			}
		}
		Collection<MasterContractTermsSales> masterContractTermsSalesCollection = new ArrayList<>();
		if ((contractTermsDto.getContractTermsSalesDto() != null)
				&& (!contractTermsDto.getContractTermsSalesDto().isEmpty())) {
			for (ContractTermsSalesDto contractTermsSalesDto : contractTermsDto.getContractTermsSalesDto()) {
				MasterContractTermsSales masterContractTermsSales = new MasterContractTermsSales();

				masterContractTermsSales.setFkStatusId(statusId.get());
				masterContractTermsSales.setBudgetInd(contractTermsSalesDto.getBudgetInd());
				masterContractTermsSales.setFkCostId(contractTermsSalesDto.getCostId());
				masterContractTermsSales.setFkCostGroupId(contractTermsSalesDto.getCostGroupId());
				masterContractTermsSales.setFkContrTrmsAddreduceRefId(
						repository.findAddreduce(contractTermsSalesDto.getContrTrmsAddreduceRefId()));
				masterContractTermsSales.setFkContractTermsId(masterContractTerms);
				masterContractTermsSales.setFkCostGroupId(contractTermsSalesDto.getCostGroupId());

				if ("create".equalsIgnoreCase(action)) {
					masterContractTermsSales.setCreatedBy("Test");
					masterContractTermsSales.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				} else if ("update".equalsIgnoreCase(action)) {
					if (contractTermsSalesDto.getContractTermsSalesId() != null) {
						masterContractTermsSales
								.setPkContractTermsSalesId(contractTermsSalesDto.getContractTermsSalesId());
					} else {
						masterContractTermsSales.setCreatedBy("Test");
						masterContractTermsSales.setCreatedDate(new Timestamp(System.currentTimeMillis()));
					}
					masterContractTermsSales.setModifiedBy("Test");
					masterContractTermsSales.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				}
				masterContractTermsSalesCollection.add(masterContractTermsSales);
			}
		}

		if (contractTermsDto.getDeletedPurchaseIds() != null && !contractTermsDto.getDeletedPurchaseIds().isEmpty()) {
			for (Integer idToBeDeleted : contractTermsDto.getDeletedPurchaseIds()) {
				repository.deleteByPurchaseId(idToBeDeleted);
			}

		}
		if (contractTermsDto.getDeletedSalesIds() != null && !contractTermsDto.getDeletedSalesIds().isEmpty()) {
			for (Integer idToBeDeleted : contractTermsDto.getDeletedSalesIds()) {
				repository.deleteBySalesId(idToBeDeleted);
			}

		}
		masterContractTerms.setMasterContractTermsPurchaseCollection(masterContractTermsPurchaseCollection);
		masterContractTerms.setMasterContractTermsSalesCollection(masterContractTermsSalesCollection);

		return masterContractTerms;
	}

	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ContractTermsDto readById(Integer id) {

		MasterContractTerms masterContractTerms = repository.findOne(id);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		// rest calls
		List<Map<Object, Object>> prodList = null;
		List<Map<Object, Object>> costList = null;
		try {
			/*
			 * commented as CommonUtil.getServiceCall changed to
			 * webServiceCall.internalServiceCall(msaName,"endpoint")
			 * ResponseEntity<ResponseData> prodResponseEntity =
			 * webServiceCall.internalServiceCall(productName,
			 * "/product/productservice/product");//(
			 * "http://ctrmapp:8042/product/productservice/product");
			 * ResponseData<List<Object>> prodBody =
			 * prodResponseEntity.getBody(); prodList = (List<Map<Object,
			 * Object>>) prodBody.getBody();
			 */
			prodList = (List<Map<Object, Object>>) webServiceCall
					.internalServiceCall(productName, "/product/productservice/product").getBody().getBody();

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			/*
			 * ResponseEntity<ResponseData> costResponseEntity =
			 * CommonUtil.getServiceCall(costName, "/cost/costService/cost");
			 * ResponseData<List<Object>> costBody =
			 * costResponseEntity.getBody(); costList = (List<Map<Object,
			 * Object>>) costBody.getBody();
			 */
			costList = (List<Map<Object, Object>>) webServiceCall
					.internalServiceCall(costName, "/cost/costService/basicCost").getBody().getBody();
		} catch (Exception e) {
			// TODO: handle exception
		}
		/*
		 * ResponseEntity<ResponseData> costGroupResponseEntity =
		 * webServiceCall.internalServiceCall(costName
		 * ,"/cost/costgroupservice/costgroup"); ResponseData<List<Object>>
		 * costGroupBody = costGroupResponseEntity.getBody(); List<Map<Object,
		 * Object>> costGroupList = (List<Map<Object, Object>>)
		 * costGroupBody.getBody();
		 */
		List<Map<Object, Object>> costGroupList = (List<Map<Object, Object>>) webServiceCall
				.internalServiceCall(costName, "/cost/costgroupservice/costgroup").getBody().getBody();

		return masterContractTerms.convertToContractTermsDto(statusMap, prodList, costList, costGroupList);
	}

	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ContractTermsDto> readAll(ViewDto view) {
		List<ContractTermsDto> newList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		ContractTermsSpecificationsBuilder builder = new ContractTermsSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterContractTerms> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");

		List<MasterContractTerms> oldList = repository.findAll(spec, pageRequest).getContent();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		// rest calls
		List<Map<Object, Object>> prodList = null;
		List<Map<Object, Object>> costList = null;
		List<Map<Object, Object>> costGroupList = null;
		try {

			prodList = (List<Map<Object, Object>>) webServiceCall
					.internalServiceCall(productName, "/product/productservice/product").getBody().getBody();
		} catch (Exception e) {

			prodList = null;
		}
		try {

			costList = (List<Map<Object, Object>>) webServiceCall
					.internalServiceCall(costName, "/cost/costService/basicCost").getBody().getBody();
		} catch (Exception e) {

			costList = null;
		}
		try {

			costGroupList = (List<Map<Object, Object>>) webServiceCall
					.internalServiceCall(costName, "/cost/costgroupservice/costgroup").getBody().getBody();

		} catch (Exception e) {

			costGroupList = null;
		}
		for (MasterContractTerms masterContractTerms : oldList) {
			ContractTermsDto contractTermsDto;
			contractTermsDto = masterContractTerms.convertToContractTermsDto(statusMap, prodList, costList,
					costGroupList);
			newList.add(contractTermsDto);
		}

		return newList;
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
		Class<ContractTermsDto> contractTermsDto = ContractTermsDto.class;
		Field[] fields = contractTermsDto.getDeclaredFields();
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

	@Transactional
	public void deleteContractTerms(List<Map<String, Integer>> body) {
		for (Map<String, Integer> i : body) {
			i.forEach((attribute, contractTermsId) -> {
				MasterContractTerms masterContractTerms = new MasterContractTerms();
				masterContractTerms = repository.findOne(contractTermsId);
				if (masterContractTerms.getFkStatusId() != 0
						&& EnumStatus.ACTIVE.getValue() == masterContractTerms.getFkStatusId()) {
					log.info(contractTermsId.toString());
					log.info("===Changing Active status to Inactive===");
					masterContractTerms.setFkStatusId(EnumStatus.INACTIVE.getValue());
					if (!masterContractTerms.getMasterContractTermsPurchaseCollection().isEmpty()) {
						for (MasterContractTermsPurchase purchaseList : masterContractTerms
								.getMasterContractTermsPurchaseCollection()) {
							purchaseList.setFkStatusId(EnumStatus.INACTIVE.getValue());
						}
					}
					if (!masterContractTerms.getMasterContractTermsSalesCollection().isEmpty()) {
						for (MasterContractTermsSales salesList : masterContractTerms
								.getMasterContractTermsSalesCollection()) {
							salesList.setFkStatusId(EnumStatus.INACTIVE.getValue());
						}
					}
					repository.saveAndFlush(masterContractTerms);
				} else if (masterContractTerms.getFkStatusId() != 0
						&& EnumStatus.DRAFT.getValue() == masterContractTerms.getFkStatusId()) {
					log.info(contractTermsId.toString());
					log.info("===Deleting the draft HolidayCalendar===");
					repository.delete(masterContractTerms);
				} else if (masterContractTerms.getFkStatusId() != 0
						&& EnumStatus.INACTIVE.getValue() == masterContractTerms.getFkStatusId()) {
					masterContractTerms.setFkStatusId(EnumStatus.ACTIVE.getValue());
					if (!masterContractTerms.getMasterContractTermsPurchaseCollection().isEmpty()) {
						for (MasterContractTermsPurchase purchaseList : masterContractTerms
								.getMasterContractTermsPurchaseCollection()) {
							purchaseList.setFkStatusId(EnumStatus.ACTIVE.getValue());
						}
					}
					if (!masterContractTerms.getMasterContractTermsSalesCollection().isEmpty()) {
						for (MasterContractTermsSales salesList : masterContractTerms
								.getMasterContractTermsSalesCollection()) {
							salesList.setFkStatusId(EnumStatus.ACTIVE.getValue());
						}
					}
					repository.saveAndFlush(masterContractTerms);
				}
			});

		}

	}
}
