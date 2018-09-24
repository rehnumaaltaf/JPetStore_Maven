package com.olam.score.reference.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.constants.ErrorConstants;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.ErrorMessageUtil;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.ScoreBaseExceptionResponse;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.reference.controller.UOMContoller;
import com.olam.score.reference.domain.MasterUom;
import com.olam.score.reference.dto.UomDto;
import com.olam.score.reference.repository.UOMRepository;
import com.olam.score.reference.util.UomSpecificationsBuilder;

@Service
public class UOMService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private WebServiceCallUtil webServiceCall;
	@Autowired
	private ListViewUtil listview;
	@Autowired
	private UOMRepository repository;
	@Autowired
	EntityManager entityManager;

	@Transactional
	public UomDto create(UomDto uomDto) {
		MasterUom masterUom;
		UomDto dto;
		String action = uomDto.getAction();
		if (action.equalsIgnoreCase(Constants.DRAFT)) {
			log.info("===draft action to perform for create ===");
			uomDto.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SAVE)) {
			log.info("===save(active) action to perform for create ===");
			uomDto.setStatusName(Constants.ACTIVE);
		}
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		MasterUom masterUomCode = repository.findByUomCode(uomDto.getUomCode());
		MasterUom masterUomName = repository.findByUomName(uomDto.getUomName());

		List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
		if (masterUomCode != null || masterUomName != null) {
			if (masterUomCode != null) {
				log.error("===Duplicate UOM Code found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { uomDto.getUomCode() }, errorBeans,
						Constants.ERROR_DUPLICATE_UOM_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
			}
			if (masterUomName != null) {
				log.error("===Duplicate UOM Name found in Database===");
				ErrorMessageUtil.errorHandler(new Object[] { uomDto.getUomName() }, errorBeans,
						Constants.ERROR_DUPLICATE_UOM_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);
			}
			throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
		} else {
			masterUom = repository.saveAndFlush(generateMasterUom(uomDto, Constants.CREATE, statusMap));
			dto = masterUom.convertToUomDto(statusMap);
		}
		return dto;
	}

	/*
	 * Generate the Master UOM
	 */
	private MasterUom generateMasterUom(UomDto uomDto, String action, Map<Integer, String> statusMap) {

		MasterUom masterUom = new MasterUom();
		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(uomDto.getStatusName())).map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			log.info("===Got Status Id in Status map ===");
			masterUom.setFkStatusId(statusId.get());
		}
		if (uomDto.getParentUomId() != null) {
			log.info("===Base UOM exist for requested uom id ===");
			MasterUom base = repository.findOne(uomDto.getParentUomId());
			masterUom.setFkBaseUomId(base);
		}
		masterUom=uomDto.generateMasterUom(action,masterUom);
		return masterUom;
	}

	@Transactional(readOnly=true)
	public List<UomDto> readAll(Model model) {
		ViewDto view = getViewDetails(UOMContoller.class);
		List<UomDto> newList = new ArrayList<>();
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		List<String> filterList = view.getFiltersArray();
		UomSpecificationsBuilder builder = new UomSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf(Constants.EQUAL)),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf(Constants.EQUAL) + 1));
		}
		if(model!=null){
			Map<String,Object> params=model.asMap();
			if (params.get(Constants.STATUS) != null) {
				Optional<Integer> statusId = statusMap.entrySet().stream().filter(e -> e.getValue().equalsIgnoreCase((String) params.get("status")))
						.map(Map.Entry::getKey).findFirst();
				if (statusId.isPresent()) {
					params.put("fkStatusId", statusId.get());
					params.remove(Constants.STATUS);
				}
				
				}
			params.forEach((attribute, value) -> {
				builder.with(attribute, value);
			});
			}

		Specification<MasterUom> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterUom> oldList = repository.findAll(spec, pageRequest).getContent();
		for (MasterUom masterUom : oldList) {
			UomDto uomDto;
			uomDto = masterUom.convertToUomDto(statusMap);
			newList.add(uomDto);
		}

		return newList;
	}

	@Transactional(readOnly=true)
	public UomDto readById(Integer id) {

		MasterUom masterUom = repository.findOne(id);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		return masterUom.convertToUomDto(statusMap);
	}

	@Transactional
	public Map<Integer, String> deleteUOM(List<Map<String, Integer>> body) {
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		Map<Integer, String> deleteStatusMap = new HashMap<>();
		Optional<Integer> statusId1 = statusMap.entrySet().stream().filter(e -> e.getValue().equals(Constants.INACTIVE))
				.map(Map.Entry::getKey).findFirst();
			Optional<Integer> statusId2 = statusMap.entrySet().stream().filter(e -> e.getValue().equals(Constants.ACTIVE))
				.map(Map.Entry::getKey).findFirst();
		for (Map<String, Integer> i : body) {
			i.forEach((attribute, uomId) -> {
				MasterUom masterUom = new MasterUom();
				masterUom = repository.findOne(uomId);
				if (masterUom == null) {
					String message = CommonUtil.getTranslationMessage(Locale.getDefault(),
							ErrorConstants.RECORD_NOT_FOUND.getErrorConstants());
					deleteStatusMap.put(uomId, message);
				} else {
					String currentStatusName = statusMap.get(masterUom.getFkStatusId());
					deleteStatusMap.put(uomId, performDeleteFunction(masterUom,statusId1,statusId2,currentStatusName));
				}
			});

		}
		return deleteStatusMap;

	}

	private String performDeleteFunction(MasterUom masterUom, Optional<Integer> statusId1, Optional<Integer> statusId2, String currentStatusName) {
		String message="";
		if (masterUom.getFkStatusId() != null) {

			if (currentStatusName.equals(Constants.ACTIVE)) {
				Integer inactiveStatusId = null;
				if (statusId1.isPresent()) {
					inactiveStatusId = statusId1.get();
				}
				masterUom.setFkStatusId(inactiveStatusId);
				repository.saveAndFlush(masterUom);
				message = CommonUtil.getTranslationMessage(Locale.getDefault(),
						ErrorConstants.STATUS_INACTIVATED.getErrorConstants());
			} else if (currentStatusName.equals(Constants.DRAFT)) {
				repository.delete(masterUom);
				message = CommonUtil.getTranslationMessage(Locale.getDefault(),
						ErrorConstants.RECORD_DELETED.getErrorConstants());
			} else if (currentStatusName.equals(Constants.INACTIVE)) {
				Integer activeStatusId = null;
				if (statusId2.isPresent()) {
					activeStatusId = statusId2.get();
				}

				masterUom.setFkStatusId(activeStatusId);
				repository.saveAndFlush(masterUom);
				message = CommonUtil.getTranslationMessage(Locale.getDefault(),
						ErrorConstants.STATUS_ACTIVATED.getErrorConstants());

			}
		} else {
			message = CommonUtil.getTranslationMessage(Locale.getDefault(),
					ErrorConstants.INVALID_STATUS.getErrorConstants());

		}
		return message;
	}

	@Transactional
	public List<UomDto> update(List<UomDto> body) {
		List<UomDto> newList = new ArrayList<>();
		MasterUom masterUom;
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		for (UomDto uomDto : body) {
			MasterUom masterUomCode = repository.findByUomCode(uomDto.getUomCode());
			MasterUom masterUomName = repository.findByUomName(uomDto.getUomName());

			if ((masterUomCode != null && !masterUomCode.getPkUomId().equals(uomDto.getUomId()))
					|| (masterUomName != null && !masterUomName.getPkUomId().equals(uomDto.getUomId()))) {
				List<ScoreBaseExceptionResponse> errorBeans = new ArrayList<>();
				if (masterUomCode != null && !masterUomCode.getPkUomId().equals(uomDto.getUomId())) {
					log.error("===Duplicate UOM Code found in Database===");
					ErrorMessageUtil.errorHandler(new Object[] { uomDto.getUomCode() }, errorBeans,
							Constants.ERROR_DUPLICATE_UOM_CODE, Constants.ERROR_TYPE_DUPLICATE_CODE);
				}
				if (masterUomName != null && !masterUomName.getPkUomId().equals(uomDto.getUomId())) {
					log.error("===Duplicate UOM Name found in Database===");
					ErrorMessageUtil.errorHandler(new Object[] { uomDto.getUomName() }, errorBeans,
							Constants.ERROR_DUPLICATE_UOM_NAME, Constants.ERROR_TYPE_DUPLICATE_NAME);

				}
				throw new ScoreBaseException(errorBeans, HttpStatus.CONFLICT);
			}
			masterUom = repository.saveAndFlush(generateMasterUom(uomDto, Constants.UPDATE, statusMap));
			log.info("===Updated the UOM ID===");
			uomDto = masterUom.convertToUomDto(statusMap);
			newList.add(uomDto);
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
		Class<UomDto> uomDto = UomDto.class;
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

}
