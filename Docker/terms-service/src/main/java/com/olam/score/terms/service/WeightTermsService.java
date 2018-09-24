package com.olam.score.terms.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
//import com.olam.score.product.dto.MasterOutturnDto;
//import com.olam.score.product.domain.MasterOutturnRawGrade;
//import com.olam.score.product.domain.MasterOutturnRawGrade;
//import com.olam.score.product.dto.OutturnRawGradeDto;
import com.olam.score.terms.controller.WeightTermsController;
import com.olam.score.terms.domain.MasterFranchiseValueUnit;
import com.olam.score.terms.domain.MasterPaymentTerm;
import com.olam.score.terms.domain.MasterWeightTerms;
import com.olam.score.terms.dto.FranchiseDto;
//import com.olam.score.terms.dto.MasterWeighttermDto;
import com.olam.score.terms.dto.PaymentTermsDto;
import com.olam.score.terms.dto.WeightTermDto;
import com.olam.score.terms.repository.PaymentTermsRepository;
import com.olam.score.terms.repository.WeightTermsRepository;
import com.olam.score.terms.util.WeightTermsSpecificationsBuilder;

@Service
public class WeightTermsService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private WeightTermsRepository repository;

	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Autowired
	private ListViewUtil listview;

	@Value("${terms.name}")
	private String termsName;

	Map<Integer, String> statusMap;

	// Read All functionality

	public List<WeightTermDto> readAll(ViewDto view) {

		List<WeightTermDto> weightTermsList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		WeightTermsSpecificationsBuilder builder = new WeightTermsSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterWeightTerms> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterWeightTerms> oldList = repository.findAll(spec, pageRequest).getContent();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();


		ResponseEntity<ResponseData> franchiseResponseEntity = webServiceCall.internalServiceCall(termsName , "/terms/franchiseservice/franchise");
		ResponseData<List<Object>> franchiseBody = franchiseResponseEntity.getBody();
		List<Map<Object, Object>> franchiseList = (List<Map<Object, Object>>) franchiseBody.getBody();

		ResponseEntity<ResponseData> toleranceResponseEntity = webServiceCall.internalServiceCall(termsName , "/terms/toleranceservice/tolerance");
		ResponseData<List<Object>> toleranceBody = toleranceResponseEntity.getBody();
		List<Map<Object, Object>> toleranceList = (List<Map<Object, Object>>) toleranceBody.getBody();

		for (MasterWeightTerms obj : oldList) {

			WeightTermDto weighttermDto;
			weighttermDto = obj.convertToWeightTerm(statusMap, franchiseList, toleranceList);
			weightTermsList.add(weighttermDto);
		}

		return weightTermsList;
	}


	// Default View
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
		Class<WeightTermDto> weightTerm = WeightTermDto.class;
		Field[] fields = weightTerm.getDeclaredFields();
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

	// Delete functionality

	public void deleteWeightTerm(List<Map<String, Integer>> body) {

		for (Map<String, Integer> weightTermsId : body) {
			weightTermsId.forEach((name, id) -> {
				MasterWeightTerms masterWeightTerms = new MasterWeightTerms();
				masterWeightTerms = repository.findOne(id);

				if (masterWeightTerms != null) {
					if (masterWeightTerms.getFkStatusId() != 0
							&& (EnumStatus.ACTIVE.getValue()) == (masterWeightTerms.getFkStatusId())) {
						log.info(id.toString());
						log.info("===Changing Active status to Inactive===");
						masterWeightTerms.setFkStatusId(EnumStatus.INACTIVE.getValue());

						repository.saveAndFlush(masterWeightTerms);

					} else if (masterWeightTerms.getFkStatusId() != 0
							&& (EnumStatus.DRAFT.getValue()) == (masterWeightTerms.getFkStatusId())) {

						repository.delete(masterWeightTerms);
					} else if (masterWeightTerms.getFkStatusId() != 0
							&& EnumStatus.INACTIVE.getValue() == masterWeightTerms.getFkStatusId()) {
						masterWeightTerms.setFkStatusId(EnumStatus.ACTIVE.getValue());

						repository.saveAndFlush(masterWeightTerms);
					}
				} else {
					throw new ScoreBaseException(0, "Outturn raw Id Not Found For deletion", HttpStatus.NOT_FOUND);

				}

			});

		}
	}
	// create functionality

	public WeightTermDto create(WeightTermDto weightTermDto) {

		MasterWeightTerms masterWeightTermsEntity;
		WeightTermDto responseDto = null;
		UserBean userBean = new UserBean();
		Locale locale;
		String action = weightTermDto.getStatusName();

		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			weightTermDto.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			weightTermDto.setStatusName(Constants.ACTIVE);
		}
		locale = userBean.getLocale();
		if (locale == null) {
			locale = Locale.getDefault();
		}

		// MasterFranchiseValueUnit franchiseValueUnit = repository.findOne(3);

		Map<Integer, String> statusMap = webServiceCall.getAllStatus();

		// Duplicate check

		MasterWeightTerms weightTermsCode = repository.findByWeightTermsCode(weightTermDto.getWeightTermsCode());
		MasterWeightTerms weightTermsName = repository.findByWeightTermsName(weightTermDto.getWeightTermsName());

		locale = userBean.getLocale();

		if (locale == null) {
			locale = Locale.getDefault();
		}
		String message = CommonUtil.getTranslationMessage(locale, "error_duplicate");

		if (weightTermsCode != null && weightTermsName != null) {
			message = message.replace("$", weightTermsCode.getWeightTermsCode() + " Code:" + " and "
					+ weightTermsName.getWeightTermsName() + " Name ");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);

		} else if ((weightTermsCode != null)) {
			message = message.replace("$", weightTermsCode.getWeightTermsCode() + " Code");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);
		} else if (weightTermsName != null) {
			message = message.replace("$", weightTermsName.getWeightTermsName() + " Name");
			throw new ScoreBaseException(0, message, HttpStatus.CONFLICT);

		} else {
			masterWeightTermsEntity = repository.saveAndFlush(generateMasterWeightTerms(weightTermDto, "create"));
			responseDto = masterWeightTermsEntity.convertToWeightTerm(statusMap, null, null);
		}
		return responseDto;

	}
	
	// for update functionality
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List<WeightTermDto> update(List<WeightTermDto> body) {
			
			List<WeightTermDto> newList = new ArrayList<>();
			MasterWeightTerms masterWeightTermsEntity;
			//WeightTermDto weightTermDto;
			Locale locale;
			UserBean userBean = new UserBean();
			locale = userBean.getLocale();
			if (locale == null) {
				locale = Locale.getDefault();
			}
			
			Map<Integer, String> statusMap = webServiceCall.getAllStatus();
			
			ResponseEntity<ResponseData> franchiseResponseEntity = webServiceCall.internalServiceCall(termsName , "/terms/franchiseservice/franchise");
			ResponseData<List<Object>> franchiseBody = franchiseResponseEntity.getBody();
			List<Map<Object, Object>> franchiseList = (List<Map<Object, Object>>) franchiseBody.getBody();

			ResponseEntity<ResponseData> toleranceResponseEntity = webServiceCall.internalServiceCall(termsName , "/terms/toleranceservice/tolerance");
			ResponseData<List<Object>> toleranceBody = toleranceResponseEntity.getBody();
			List<Map<Object, Object>> toleranceList = (List<Map<Object, Object>>) toleranceBody.getBody();

			
			/*ResponseEntity<ResponseData> franchiseResponseEntity = CommonUtil
					.getServiceCall(termsName + "/terms/franchiseservice/franchise");
			ResponseData<List<Object>> franchiseBody = franchiseResponseEntity.getBody();
			List<Map<Object, Object>> franchiseList = (List<Map<Object, Object>>) franchiseBody.getBody();

			ResponseEntity<ResponseData> toleranceResponseEntity = CommonUtil
					.getServiceCall(termsName + "/terms/toleranceservice/tolerance");
			ResponseData<List<Object>> toleranceBody = franchiseResponseEntity.getBody();
			List<Map<Object, Object>> toleranceList = (List<Map<Object, Object>>) toleranceBody.getBody();
*/
			for (WeightTermDto weightTermDto : body) {
				
		
			MasterWeightTerms weightTermsCode;
			MasterWeightTerms weightTermsName;
			
			
			weightTermsCode = repository.findByWeightTermsCode(weightTermDto.getWeightTermsCode());
			weightTermsName = repository.findByWeightTermsName(weightTermDto.getWeightTermsName());
			
			String defaultErrormessage = CommonUtil.getTranslationMessage(Locale.getDefault(), "error_duplicate");
				
			String message = "";
			Integer errorCount = 0;
			
				// Check for duplicates and throw exception
			if ((weightTermsCode != null && !weightTermsCode.getPkWeightTermsId().equals(weightTermDto.getWeightTermsId()) )
							|| (weightTermsName != null
									&& !weightTermsName.getPkWeightTermsId().equals(weightTermDto.getWeightTermsId()))) {
			 
				Integer resourceId = null;
				if (weightTermsCode != null
						&& !weightTermsCode.getPkWeightTermsId().equals(weightTermDto.getWeightTermsId())) {
					message = "Code:" + weightTermDto.getWeightTermsCode();
					resourceId = weightTermsCode.getPkWeightTermsId();
					errorCount++;
				}
				if (weightTermsName != null
						&& !weightTermsName.getPkWeightTermsId().equals(weightTermDto.getWeightTermsId())) {
					if (errorCount > 0) {
						message = message.concat(" ; ");
					}
					message = message.concat("Name:" + weightTermDto.getWeightTermsName());
					resourceId = weightTermsName.getPkWeightTermsId();
					errorCount++;
				}
			
					message = defaultErrormessage.replace("$", message);
					throw new ScoreBaseException(resourceId, message, HttpStatus.CONFLICT);
				}
			
			
		
				masterWeightTermsEntity = repository.saveAndFlush(generateMasterWeightTerms(weightTermDto, "update"));
				log.info("===Updated the Weight term ID===");

				weightTermDto = masterWeightTermsEntity.convertToWeightTerm(statusMap, franchiseList, toleranceList);
				newList.add(weightTermDto);
			
			}
			return newList;
		
		}

		

		private MasterWeightTerms generateMasterWeightTerms(WeightTermDto weightTermDto, String operation) {

			MasterWeightTerms masterWeightTerm = new MasterWeightTerms();

			Integer statusIdValue = null;
			statusMap = webServiceCall.getAllStatus();
			Optional<Integer> statusId = statusMap.entrySet().stream()
					.filter(e -> e.getValue().equals(weightTermDto.getStatusName())).map(Map.Entry::getKey).findFirst();
			if (statusId.isPresent()) {
				statusIdValue = statusId.get();

				masterWeightTerm.setFkStatusId(statusId.get());

			}

			// trim() to avoid space
			masterWeightTerm.setWeightTermsCode(weightTermDto.getWeightTermsCode().trim());
			masterWeightTerm.setWeightTermsName(weightTermDto.getWeightTermsName().trim());

			masterWeightTerm.setPkWeightTermsId(weightTermDto.getWeightTermsId());
			masterWeightTerm.setWeightTermsCode(weightTermDto.getWeightTermsCode());
			masterWeightTerm.setWeightTermsName(weightTermDto.getWeightTermsName());
			masterWeightTerm.setWeightTermsDesc(weightTermDto.getWeightTermsDesc());
			masterWeightTerm.setWeightTermsDefaultFranchiseValue(weightTermDto.getWeightTermsDefaultFranchiseValue());
			masterWeightTerm.setWeightTermsDefaultToleranceValue(weightTermDto.getWeightTermsDefaultToleranceValue());
			masterWeightTerm.setWeightTermsIsFranchiseApplicable(weightTermDto.getWeightTermsIsFranchiseApplicable());
			masterWeightTerm.setWeightTermsIsToleranceApplicable(weightTermDto.getWeightTermsIsToleranceApplicable());
			masterWeightTerm.setFkFranchiseValueUnitId(weightTermDto.getFranchiseValueUnitId());
			masterWeightTerm.setFkToleranceValueUnitId(weightTermDto.getToleranceValueUnitId());

			if ("create".equalsIgnoreCase(operation)) {
				masterWeightTerm.setCreatedBy("Test");// TODO: Hard Coding
				masterWeightTerm.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				masterWeightTerm.setModifiedBy("TestUser");// TODO: Hard Coding
				masterWeightTerm.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			} else if ("update".equalsIgnoreCase(operation)) {
				masterWeightTerm.setPkWeightTermsId(weightTermDto.getWeightTermsId());
				/*
				 * masterOutturnRawGrade.setCreatedBy("Test");// TODO: Hard Coding
				 * masterOutturnRawGrade.setCreatedDate(new
				 * Timestamp(System.currentTimeMillis()));
				 */
				masterWeightTerm.setModifiedBy("Test");// TODO: Hard Coding
				masterWeightTerm.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			}

			if (weightTermDto.getDeletedIds() != null && (!weightTermDto.getDeletedIds().isEmpty())) {
				for (Integer idToBeDeleted : weightTermDto.getDeletedIds()) {
					repository.deleteByWeightTermsListId(idToBeDeleted);
				}
			}
			return masterWeightTerm;
		}
	
	

	/*private MasterWeightTerms generateMasterWeightTerms(WeightTermDto weightTermDto, String operation) {

		MasterWeightTerms masterWeightTerm = new MasterWeightTerms();

		Integer statusIdValue = null;
		statusMap = webServiceCall.getAllStatus();
		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(weightTermDto.getStatusName())).map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			statusIdValue = statusId.get();

			masterWeightTerm.setFkStatusId(statusId.get());

		}
		masterWeightTerm.setPkWeightTermsId(weightTermDto.getWeightTermsId());
		masterWeightTerm.setWeightTermsCode(weightTermDto.getWeightTermsCode());
		masterWeightTerm.setWeightTermsName(weightTermDto.getWeightTermsName());
		masterWeightTerm.setWeightTermsDesc(weightTermDto.getWeightTermsDesc());
		masterWeightTerm.setWeightTermsDefaultFranchiseValue(weightTermDto.getWeightTermsDefaultFranchiseValue());
		masterWeightTerm.setWeightTermsDefaultToleranceValue(weightTermDto.getWeightTermsDefaultToleranceValue());
		masterWeightTerm.setWeightTermsIsFranchiseApplicable(weightTermDto.getWeightTermsIsFranchiseApplicable());
		masterWeightTerm.setWeightTermsIsToleranceApplicable(weightTermDto.getWeightTermsIsToleranceApplicable());
		

		if ("create".equalsIgnoreCase(operation)) {
			masterWeightTerm.setCreatedBy("Test");// TODO: Hard Coding
			masterWeightTerm.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			masterWeightTerm.setModifiedBy("TestUser");// TODO: Hard Coding
			masterWeightTerm.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}
		
		if( weightTermDto.getDeletedIds()!=null && (!weightTermDto.getDeletedIds().isEmpty())){
			for(Integer idToBeDeleted:weightTermDto.getDeletedIds())
			{
				repository.deleteByWeightTermsListId(idToBeDeleted);
			}
		}

		return masterWeightTerm;
	}
	*/
	
	// get all the details of single weight terms: ViewById
		@Transactional
		public WeightTermDto readById(Integer weightTermsId) {
			// TODO Auto-generated method stub

			MasterWeightTerms masterWeightTerm = repository.findOne(weightTermsId);
			Map<Integer, String> statusMap = webServiceCall.getAllStatus();

			ResponseEntity<ResponseData> franchiseResponseEntity = webServiceCall.internalServiceCall(termsName , "/terms/franchiseservice/franchise");
			ResponseData<List<Object>> franchiseBody = franchiseResponseEntity.getBody();
			List<Map<Object, Object>> franchiseList = (List<Map<Object, Object>>) franchiseBody.getBody();

			ResponseEntity<ResponseData> toleranceResponseEntity = webServiceCall.internalServiceCall(termsName , "/terms/toleranceservice/tolerance");
			ResponseData<List<Object>> toleranceBody = toleranceResponseEntity.getBody();
			List<Map<Object, Object>> toleranceList = (List<Map<Object, Object>>) toleranceBody.getBody();

			return masterWeightTerm.convertToWeightTerm(statusMap, franchiseList, toleranceList);

		}
	
}
