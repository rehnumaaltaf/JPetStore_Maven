package com.olam.score.packing.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.packing.controller.PrimaryPackingTypeController;
import com.olam.score.packing.domain.MasterPrimaryPackingType;
import com.olam.score.packing.dto.PrimaryPackingTypeDto;
import com.olam.score.packing.dto.UomDto;
import com.olam.score.packing.repository.PrimaryPackingTypeRepository;
import com.olam.score.packing.util.PrimaryPackingSpecificationsBuilder;

import antlr.TokenStreamRetryException;

@Service
public class PrimaryPackingTypeService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PrimaryPackingTypeRepository repository;

	@Value("${reference.name}")
	private String referenceName;

	@Autowired
	private WebServiceCallUtil webServiceCall;
	
	@Autowired
	private ListViewUtil listview;

	@SuppressWarnings("unused")
	public String create(PrimaryPackingTypeDto dto) {
		String toReturn = null;
		
			MasterPrimaryPackingType primaryPackingType = new MasterPrimaryPackingType();
			int status = returnStatusIdByName(Constants.ACTIVE);
			if (Constants.SAVE_CAPS.equalsIgnoreCase(dto.getAction())) {
				status = returnStatusIdByName(Constants.DRAFT);
			}
			if (!repository.findByPrimaryPackingTypeCode(dto.getInternalPackingTypeCode()).isEmpty()) {
				toReturn = Constants.IP_CODE_EXISTS;
				toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeCode());

			}
			if (!repository.findByPrimaryPackingTypeName(dto.getInternalPackingTypeName()).isEmpty()) {
				if (toReturn != null) {
					toReturn = Constants.IP_NAME_CODE_EXISTS;
					toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeName());
					toReturn = toReturn.replace(Constants.IP_CODE_MESSAGE, dto.getInternalPackingTypeCode());
				} else {
					toReturn = Constants.IP_NAME_EXISTS;
					toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeName());
				}
			}
			if (toReturn != null) {
				throw new ScoreBaseException(0, toReturn, HttpStatus.CONFLICT);
			}
			primaryPackingType = this.createAndReturnEntity(dto, primaryPackingType, status);

			primaryPackingType = repository.saveAndFlush(primaryPackingType);
			if (primaryPackingType.getPkPrimaryPackingTypeId() <= 0)
				toReturn = Constants.IP_ADD_FAILURE;
			else {
				if (Constants.SAVE_CAPS.equalsIgnoreCase(dto.getAction())) {
					toReturn = Constants.IP_SAVE_SUCCESS_DRAFT;
					toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE,
							primaryPackingType.getPrimaryPackingTypeName());
				} else {
					toReturn = Constants.IP_SAVE_SUCCESS_ACTIVE;
					toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE,
							primaryPackingType.getPrimaryPackingTypeName());
				}
			}
		
		return toReturn;
	}

	public String update(PrimaryPackingTypeDto dto) {
		String toReturn = null;
		
			int status = 0;
			MasterPrimaryPackingType primaryPackingType = new MasterPrimaryPackingType();
			if (Constants.IP_DELETE.equalsIgnoreCase(dto.getAction())) {
				repository.delete(dto.getInternalPackingTypeId());
				toReturn = Constants.IP_DELETE_SUCCESS;
				toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeName());
				return toReturn;
			}
				if (!repository.getByPrimaryPackingTypeCodeAndPkPrimaryPackingTypeIdNotIn(dto.getInternalPackingTypeCode(), dto.getInternalPackingTypeId()).isEmpty()) {
					toReturn = Constants.IP_CODE_EXISTS;
					toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeCode());
				}
				if (!repository.getByPrimaryPackingTypeNameAndPkPrimaryPackingTypeIdNotIn(dto.getInternalPackingTypeName(),
						dto.getInternalPackingTypeId()).isEmpty()) {
					if (toReturn != null) {
						toReturn = Constants.IP_NAME_CODE_EXISTS;
						toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeName());
						toReturn = toReturn.replace(Constants.IP_CODE_MESSAGE, dto.getInternalPackingTypeCode());
					} else {
						toReturn = Constants.IP_NAME_EXISTS;
						toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeName());
					}
				}
				if (toReturn != null) {
					throw new ScoreBaseException(0, toReturn, HttpStatus.CONFLICT);
				}
				if (Constants.IP_UPDATE_REACTIVATE.equalsIgnoreCase(dto.getAction())) {
					status = returnStatusIdByName(Constants.ACTIVE);
					toReturn = Constants.IP_UPDATE_REACTIVE_SUCCESS;
					toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeName());
				} else if (Constants.IP_UPDATE_DEACTIVATE.equalsIgnoreCase(dto.getAction())) {
					status = returnStatusIdByName(Constants.INACTIVE);
					toReturn = Constants.IP_UPDATE_DEACTIVE_SUCCESS;
					toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeName());
				} else if (Constants.IP_UPDATE.equalsIgnoreCase(dto.getAction())) {
					status = repository.findOne(dto.getInternalPackingTypeId()).getFkStatusId();
					toReturn = Constants.IP_UPDATE_SUCCESS;
					toReturn = toReturn.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeName());
				}
				primaryPackingType = createAndReturnEntity(dto, primaryPackingType, status);
				repository.saveAndFlush(primaryPackingType);
			
		return toReturn;
	}

	public MasterPrimaryPackingType createAndReturnEntity(PrimaryPackingTypeDto dto,
			MasterPrimaryPackingType primaryPackingType, int status) {
		if (dto.getInternalPackingTypeId() != null) {
			primaryPackingType.setPkPrimaryPackingTypeId(dto.getInternalPackingTypeId());
			primaryPackingType.setModifiedBy("Test");
			primaryPackingType.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			MasterPrimaryPackingType type = repository.findOne(dto.getInternalPackingTypeId());
			primaryPackingType.setCreatedBy(type.getCreatedBy());
			primaryPackingType.setCreatedDate(type.getCreatedDate());
		} else {
			primaryPackingType.setCreatedBy("Test");
			primaryPackingType.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		}
		if (dto.getInternalPackingUomCode() != null) {
			primaryPackingType.setFkPackageUomId(Integer.parseInt(dto.getInternalPackingUomCode()));
		}
		primaryPackingType.setFkStatusId(status);
		if (dto.getInternalPackingTypeCode() != null) {
			primaryPackingType.setPrimaryPackingTypeCode(dto.getInternalPackingTypeCode());
		}
		if (dto.getInternalPackingTypeName() != null) {
			primaryPackingType.setPrimaryPackingTypeName(dto.getInternalPackingTypeName());
		}
		if (dto.getInternalPackingTypeDesc() != null) {
			primaryPackingType.setPrimaryPackingTypeDesc(dto.getInternalPackingTypeDesc());
		}
		primaryPackingType.setPrimaryPackingTypeWeight(dto.getInternalPackingWeight());

		if (dto.getIsBulkCode() != null)
			primaryPackingType.setPrimaryPackingTypeIsBulk(dto.getIsBulkCode());
		return primaryPackingType;
	}

	public String validatePrimaryPacking(PrimaryPackingTypeDto dto) {
		String response = null;
		if (Constants.IP_NAME.equalsIgnoreCase(dto.getToValidate())) {
			int count = repository.getByName(dto.getInternalPackingTypeName());
			if (count != Constants.ZERO) {
				response = Constants.IP_NAME_EXISTS;
				response = response.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeName());
			}
		} else if (Constants.IP_CODE.equals(dto.getToValidate())) {
			int count = repository.getByCode(dto.getInternalPackingTypeCode());
			if (count != Constants.ZERO) {
				response = Constants.IP_CODE_EXISTS;
				response = response.replace(Constants.IP_NAME_MESSAGE, dto.getInternalPackingTypeCode());
			}
		}

		return response;
	}

	public String validatePrimaryPackingBeforeSave(String toValidate, String toBeValidated) {
		String response = null;
		if (Constants.IP_NAME.equalsIgnoreCase(toValidate)) {
			int count = repository.getByName(toBeValidated);
			if (count != Constants.ZERO) {
				response = Constants.IP_NAME_EXISTS;
				response = response.replace(Constants.IP_NAME_MESSAGE, toBeValidated);
			}
		} else if (Constants.IP_CODE.equals(toValidate)) {
			int count = repository.getByCode(toBeValidated);
			if (count != Constants.ZERO) {
				response = Constants.IP_CODE_EXISTS;
				response = response.replace(Constants.IP_NAME_MESSAGE, toBeValidated);
			}
		}

		return response;
	}

	public String deletePrimaryPacking(String id) {
		String response = Constants.IP_DELETE_FAILURE;
		MasterPrimaryPackingType ipType = repository.findOne(Integer.parseInt(id));
		if (ipType.getPkPrimaryPackingTypeId() > 0) {
			String name = ipType.getPrimaryPackingTypeName();
			if (ipType.getFkStatusId() == returnStatusIdByName(Constants.DRAFT)) {
				repository.delete(Integer.parseInt(id));
				response = Constants.IP_DELETE_SUCCESS;
				response = response.replace(Constants.IP_NAME_MESSAGE, name);
			} else if (ipType.getFkStatusId() == returnStatusIdByName(Constants.ACTIVE)) {
				repository.deactivateOrReactivatePrimaryPackingType(returnStatusIdByName(Constants.INACTIVE),
						ipType.getPkPrimaryPackingTypeId());
				response = Constants.IP_DEACTIVE_SUCCESS;
				response = response.replace(Constants.IP_NAME_MESSAGE, name);
			} else {
				repository.deactivateOrReactivatePrimaryPackingType(returnStatusIdByName(Constants.ACTIVE),
						ipType.getPkPrimaryPackingTypeId());
				response = Constants.IP_REACTIVE_SUCCESS;
				response = response.replace(Constants.IP_NAME_MESSAGE, name);
			}
		}
		return response;
	}

	public List<PrimaryPackingTypeDto> createAndReturnPrimaryPackingViewModelList() {
		List<MasterPrimaryPackingType> ipTypeList = repository.findAllByOrderByCreatedDateDesc();
		List<PrimaryPackingTypeDto> dtoList = new ArrayList<>();
		for (MasterPrimaryPackingType ipType : ipTypeList) {
			dtoList.add(returnPrimaryPackingTypeDto(ipType));
		}
		return dtoList;
	}

	public PrimaryPackingTypeDto createAndReturnPrimaryPackingViewModelListById(int id) {
		return returnPrimaryPackingTypeDto(repository.findOne(id));
	}

	public PrimaryPackingTypeDto returnPrimaryPackingTypeDto(MasterPrimaryPackingType ipType) {
		PrimaryPackingTypeDto dto = new PrimaryPackingTypeDto();
		dto.setInternalPackingTypeId(ipType.getPkPrimaryPackingTypeId());
		dto.setInternalPackingTypeCode(ipType.getPrimaryPackingTypeCode());
		dto.setInternalPackingTypeName(ipType.getPrimaryPackingTypeName());
		dto.setInternalPackingTypeDesc(ipType.getPrimaryPackingTypeDesc());
		dto.setInternalPackingWeight(ipType.getPrimaryPackingTypeWeight());
		if (ipType.getPrimaryPackingTypeIsBulk() != null) {
			if (Constants.YES_VIEW.equalsIgnoreCase(ipType.getPrimaryPackingTypeIsBulk()))
				dto.setIsBulk(Constants.IP_BULK);
			else
				dto.setIsBulk(Constants.IP_NOT_BULK);

			dto.setIsBulkCode(ipType.getPrimaryPackingTypeIsBulk());
		}
		dto.setStatus(returnStatusNameById(ipType.getFkStatusId()));
		try {
			dto.setSelectedUomId(returnUomName(ipType));
			dto.setInternalPackingUomCode(ipType.getFkPackageUomId().toString());
		} catch (Exception msg) {
			 log.info("UOM in view", msg);
		}

		return dto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String returnUomName(MasterPrimaryPackingType masterPrimaryPackingType) {
		String uomName = null;
		ResponseEntity<ResponseData> responseEntity1 = webServiceCall.internalServiceCall(referenceName,
				Constants.UOM_URL);
		ResponseData<List<UomDto>> body1 = responseEntity1.getBody();
		List<Map<Object, Object>> list1 = (List<Map<Object, Object>>) body1.getBody();

		Integer uomId;
		for (int x = 0; x < list1.size(); x++) {
			uomId = (Integer) list1.get(x).get(Constants.UOM_PKID);
			if (uomId != null && masterPrimaryPackingType.getFkPackageUomId() == uomId) {
				uomName = (String) list1.get(x).get(Constants.UOM_NAME);
			}
		}
		return uomName;

	}

	public String viewPrimaryPackingAction(PrimaryPackingTypeDto dto) {
		String response = Constants.IP_DELETE_FAILURE;
		MasterPrimaryPackingType ipType = repository
				.getByPkPrimaryPackingTypeId(dto.getInternalPackingTypeId());
		if (ipType.getPkPrimaryPackingTypeId() > 0) {
			String name = ipType.getPrimaryPackingTypeName();
			if (Constants.IP_VIEW_DELETE.equalsIgnoreCase(dto.getAction())) {
				repository.delete(dto.getInternalPackingTypeId());
				response = Constants.IP_DELETE_SUCCESS;
				response = response.replace(Constants.IP_NAME_MESSAGE, name);
			} else if (Constants.IP_VIEW_DEACTIVATE.equalsIgnoreCase(dto.getAction())) {
				repository.deactivateOrReactivatePrimaryPackingType(returnStatusIdByName(Constants.INACTIVE),
						ipType.getPkPrimaryPackingTypeId());
				response = Constants.IP_DEACTIVE_SUCCESS;
				response = response.replace(Constants.IP_NAME_MESSAGE, name);
			} else if (Constants.IP_VIEW_ACTIVATE.equalsIgnoreCase(dto.getAction())) {
				repository.deactivateOrReactivatePrimaryPackingType(returnStatusIdByName(Constants.ACTIVE),
						ipType.getPkPrimaryPackingTypeId());
				response = Constants.IP_REACTIVE_SUCCESS;
				response = response.replace(Constants.IP_NAME_MESSAGE, name);
				if (ipType.getFkStatusId() == returnStatusIdByName(Constants.DRAFT))
					response=response.replace(Constants.REACTIVATED, Constants.ACTIVATED);
			}
		}
		return response;
	}

	public List<String> autoSuggestion(PrimaryPackingTypeDto dto) {
		List<String> toReturn = new ArrayList<>();
		Pageable pageable = new PageRequest(0, 20);
		if (Constants.IP_NAME.equalsIgnoreCase(dto.getToValidate())) {
			List<MasterPrimaryPackingType> ipList = repository.findByPrimaryPackingTypeNameContaining(dto.getInternalPackingTypeName(), pageable);
			for (MasterPrimaryPackingType ipType : ipList) {
				toReturn.add(ipType.getPrimaryPackingTypeName());
			}
		}
		if (Constants.IP_CODE.equalsIgnoreCase(dto.getToValidate())) {
			List<MasterPrimaryPackingType> ipList = repository.findByPrimaryPackingTypeCodeContaining(dto.getInternalPackingTypeCode(), pageable);
			for (MasterPrimaryPackingType ipType : ipList) {
				toReturn.add(ipType.getPrimaryPackingTypeCode());
			}
		}
		return toReturn;
	}

	public int returnStatusIdByName(String name) {
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		Optional<Integer> statusId = statusMap.entrySet().stream().filter(e -> e.getValue().equals(name))
				.map(Map.Entry::getKey).findFirst();
		if (statusId.isPresent()) {
			log.info("===Got Status Id in Status map ===");
			return statusId.get();
		}
		return 0;
	}

	public String returnStatusNameById(int id) {
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		return statusMap.get(id);
	}

	public ViewDto getViewDetails(Class<? extends Object> currentfeature) {
		String featureId = currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		log.info("===viewdto from common has been generated for JSON===");
		PageDto pageDto = new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		pageDto.setTotalCount(repository.count());
		pageDto.setOperation("Client");//To be set from Database
		viewdto.setPage(pageDto);
		Class<PrimaryPackingTypeDto> primaryPackingTypeDto = PrimaryPackingTypeDto.class;
		Field[] fields = primaryPackingTypeDto.getDeclaredFields();
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
	
	public List<PrimaryPackingTypeDto> readBasicDetails() {

		ViewDto view = getViewDetails(PrimaryPackingTypeController.class);
		List<PrimaryPackingTypeDto> newList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		PrimaryPackingSpecificationsBuilder builder = new PrimaryPackingSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf('=')),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf('=') + 1));
		}
		Specification<MasterPrimaryPackingType> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterPrimaryPackingType> allProducts = repository.findAll(spec, pageRequest).getContent();
		for (MasterPrimaryPackingType masterPrimaryPackingType : allProducts) {
			PrimaryPackingTypeDto primaryPackingTypeDto = masterPrimaryPackingType.convertBasicEntityToDto();
			newList.add(primaryPackingTypeDto);
		}

		return newList;
	}
}
