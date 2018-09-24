package com.olam.score.product.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.WebServiceCallUtil;
//import com.olam.score.product.domain.MasterGradeGroupRef;
import com.olam.score.product.domain.MasterOrigin;
import com.olam.score.product.dto.MasterOriginDto;
import com.olam.score.product.dto.OriginDto;
//import com.olam.score.product.dto.OriginRegionDto;
import com.olam.score.product.repository.OriginRepository;
import com.olam.score.product.util.OriginSpecificationsBuilder;

@Service
public class OriginService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WebServiceCallUtil serviceCall;
	
	@Autowired
	private ListViewUtil listview;

	@Autowired
	private OriginRepository repository;

	int statusId = 0;

	/**
	 * @param inputData
	 * @return
	 */
	/*@Transactional
	public MasterOriginDto create(MasterOriginDto masterOriginDto, String status) {
		MasterOriginDto responseDto = null;

		if (status.equalsIgnoreCase(Constants.DRAFT)) {
			masterOriginDto.setStatus(Constants.DRAFT);
		} else if (status.equalsIgnoreCase(Constants.SAVE)) {
			masterOriginDto.setStatus(Constants.ACTIVE);
		}

		// Duplicate check

		String masterOriginCode = repository.findByOriginCode(masterOriginDto.getOriginDto().getOriginCode());
		String masterOriginName = repository.findByOriginName(masterOriginDto.getOriginDto().getOriginName());

		if (masterOriginCode != null && masterOriginName != null) {
			throw new ScoreBaseException(0,
					"Origin Code: " + masterOriginCode + " & Origin Name: " + masterOriginName + " exists in database",
					HttpStatus.CONFLICT);

		} else if (masterOriginCode != null) {

			throw new ScoreBaseException(0, "Origin Code " + masterOriginCode + " exists in database",
					HttpStatus.CONFLICT);

		} else if (masterOriginName != null) {

			throw new ScoreBaseException(0, "Origin Name " + masterOriginName + " exists in database",
					HttpStatus.CONFLICT);

		} else {
			MasterOrigin masterOriginEntity = repository.saveAndFlush(generateMasterOrigin(masterOriginDto, "create"));
			// responseDto = generatemasterOriginDto(masterOriginEntity);
		}

		return responseDto;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private MasterOrigin generateMasterOrigin(MasterOriginDto masterOriginDto, String operation) {
		MasterOrigin masterOrigin = new MasterOrigin();

		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(locationurl + "/statusservice/status");
		ResponseData<List<StatusDto>> body = responseEntity.getBody();
		List<Map<Object, Object>> list = (List<Map<Object, Object>>) body.getBody();

		for (int x = 0; x < list.size(); x++) {
			String statusname = (String) list.get(x).get("statusName");
			if (statusname != null && masterOriginDto.getStatus() != null
					&& masterOriginDto.getStatus().equalsIgnoreCase(statusname)) {
				statusId = (int) list.get(x).get("pkStatusId");
			}
		}

		ResponseEntity<ResponseData> geoResponseEntity = webServiceCall.internalServiceCall(locationurl + "/geoservice/geo");
		ResponseData<List<Object>> geoBody = geoResponseEntity.getBody();
		List<Map<Object, Object>> geoList = (List<Map<Object, Object>>) geoBody.getBody();

		String geoName = null;
		Integer geoId = 0;
		for (int x = 0; x < geoList.size(); x++) {
			geoName = (String) geoList.get(x).get("geoName");
			if (masterOriginDto.getOriginDto().getGeoName() != null && geoName != null
					&& geoName.equals(masterOriginDto.getOriginDto().getGeoName())) {
				geoId = (Integer) geoList.get(x).get("pkGeoId");

			}
		}

		masterOrigin.setOriginFullname(masterOriginDto.getOriginDto().getOriginFullName());
		masterOrigin.setOriginName(masterOriginDto.getOriginDto().getOriginName());
		masterOrigin.setOriginCode(masterOriginDto.getOriginDto().getOriginCode());
		// getting geoId from geo-service call
		masterOrigin.setFkGeoId(geoId);
		// getting status id from status-service call
		masterOrigin.setFkStatusId(statusId);
		if ("create".equalsIgnoreCase(operation)) {
			masterOrigin.setCreatedBy("Test");// TODO: Hard Coding needs
												// changes
			masterOrigin.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		} else if ("update".equalsIgnoreCase(operation)) {
			masterOrigin.setCreatedBy("Test");// TODO: Hard Coding needs
												// changes
			masterOrigin.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			// masterOrigin.setPkOriginId(pkOriginId)
			masterOrigin.setModifiedBy("Test");// TODO: Hard Coding needs
												// changes
			masterOrigin.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		}
		return masterOrigin;
	}

	List<MasterOriginRegion> generateMasterOriginRegion = generateMasterOriginRegion(
			masterOriginDto.getOriginRegionDto(), masterOrigin,
			masterOriginDto);masterOrigin.setMasterOriginRegionCollection(generateMasterOriginRegion);
	List<MasterOriginCupProfile> generateMasterOriginCupProfile = generateMasterOriginCupProfile(
			masterOriginDto.getOriginCupProfileDto(), masterOrigin,
			masterOriginDto);masterOrigin.setMasterOriginCupProfileCollection(generateMasterOriginCupProfile);

	List<MasterGradeGroupRef> generateMasterGradeGroupRef = generateMasterGradeGroupRef(
			masterOriginDto.getOriginGradeGroupDto());

	masterOrigin.setMasterGradeGroupRefCollection(generateMasterGradeGroupRef);

	List<MasterOriginPackingAssoc> generateMasterOriginPackingAssoc = generateMasterOriginPackingAssoc(masterOriginDto
			.getDefaultPackingDto());masterOrigin.setMasterOriginPackingAssoc(generateMasterOriginPackingAssoc);return masterOrigin;}
	List<MasterOriginExternalMapping> generateMasterOriginExternalMapping = generateMasterOriginExternalMapping(
			masterOriginDto.getExternalSystemMappingDto());

	masterOrigin.setMasterGradeGroupRefCollection(generateMasterGradeGroupRef);
	}

	private List<MasterOriginExternalMapping> generateMasterOriginExternalMapping(
			List<ExternalSystemMappingDto> externalSystemMappingDto) {
		
		MasterOriginExternalMapping masterOriginExternalMapping = new MasterOriginExternalMapping();
		for(ExternalSystemMappingDto externalmapping : externalSystemMappingDto){
		
		//masterOriginExternalMapping.setDestinationSystem(externalSystemMapping.getDestinationSystem());
		masterOriginExternalMapping.setMappingType(externalmapping.getType());
		masterOriginExternalMapping.setMappingType(externalmapping.getMapping());
		
	
	return masterOriginExternalMapping;
}

		// TODO Auto-generated method stub
		return null;
	}

	private List<MasterGradeGroupRef> generateMasterGradeGroupRef(List<OriginGradeGroupDto> originGradeGroupDto) {
		List<MasterGradeGroupRef> masterGradeGroupRefList = new LinkedList<MasterGradeGroupRef>();
		MasterGradeGroupRef masterGradeGroupRef = new MasterGradeGroupRef();

		for (OriginGradeGroupDto originGradeGroup : originGradeGroupDto) {

			masterGradeGroupRef.setGradeGroupName(originGradeGroup.getOriginGradeGroupName());
			masterGradeGroupRef.setGradeGroupCode(originGradeGroup.getOriginGradeGroupCode());
			masterGradeGroupRefList.add(masterGradeGroupRef);

		}

		// TODO Auto-generated method stub
		return masterGradeGroupRefList;
	}

	private List<MasterOriginPackingAssoc> generateMasterOriginPackingAssoc(List<DefaultPackingDto> defaultPackingDto) {
		List<MasterOriginPackingAssoc> obj = new ArrayList<>();
		for (DefaultPackingDto dto : defaultPackingDto) {

			MasterOriginPackingAssoc mopa = new MasterOriginPackingAssoc();

			mopa.setProductId(dto.getProductId());
			mopa.setFkPrimaryPackingTypeId(dto.getPackingId());
			// mopa.setProductId(new MasterProduct(dto.getProductId()));

			// mopa.setFkPrimaryPackingTypeId(dto.getProductId());
			// mopa.setPackingId(dto.getPackingId());
			// mopa.setFkPrimaryPackingTypeId(dto.getPackingId());

		}
		// TODO Auto-generated method stub
		return obj;
	}

	private List<MasterOriginRegion> generateMasterOriginRegion(List<OriginRegionDto> originRegionDto,
			MasterOrigin masterOrigin, MasterOriginDto masterOriginDto) {
		List<MasterOriginRegion> masterOriginRegionList = new LinkedList<MasterOriginRegion>();
		MasterOriginRegion masterOriginRegion = new MasterOriginRegion();

		for (OriginRegionDto originRegion : originRegionDto) {

			masterOriginRegion.setCreatedBy(originRegion.getCreatedBy());
			masterOriginRegion.setCreatedDate(originRegion.getCreatedDate());
			masterOriginRegion.setModifiedBy(originRegion.getModifiedBy());
			masterOriginRegion.setModifiedDate(originRegion.getModifiedDate());

			masterOriginRegion.setOriginRegionName(originRegion.getOriginRegionName());
			masterOriginRegion.setOriginRegionCode(originRegion.getOriginRegionCode());
			masterOriginRegion.setFkStatusId(masterOrigin.getFkStatusId());
			masterOriginRegion.setOriginRegionMeanAboveSeaLevel(originRegion.getOriginRegionMeanAboveSeaLevel());
			masterOriginRegion.setRegionEstate(originRegion.getRegionEstate());
			masterOriginRegion.setFkOriginId(masterOrigin);
			masterOriginRegionList.add(masterOriginRegion);
		}
		return masterOriginRegionList;
	}

	private List<MasterOriginCupProfile> generateMasterOriginCupProfile(List<OriginCupProfileDto> originCupProfileDto,
			MasterOrigin masterOrigin, MasterOriginDto masterOriginDto) {
		List<MasterOriginCupProfile> masterOriginCupProfileList = new LinkedList<MasterOriginCupProfile>();
		MasterOriginCupProfile masterOriginCupProfile = new MasterOriginCupProfile();
		for (OriginCupProfileDto originCupProfile : originCupProfileDto) {

			masterOriginCupProfile.setOriginCupProfileName(originCupProfile.getOriginCupProfileName());
			masterOriginCupProfile.setOriginCupProfileCode(originCupProfile.getOriginCupProfileCode());
			masterOriginCupProfile.setFkOriginId(masterOrigin);
			masterOriginCupProfile.setFkStatusId(masterOrigin.getFkStatusId());
			masterOriginCupProfileList.add(masterOriginCupProfile);
		}
		return masterOriginCupProfileList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private MasterOriginDto generatemasterOriginDto(MasterOrigin masterOriginEntity) {

		MasterOriginDto masterOriginDto = new MasterOriginDto();
		OriginDto origin = new OriginDto();

		List<OriginRegionDto> originRegionList = new LinkedList<OriginRegionDto>();
		List<OriginCupProfileDto> originCupList = new LinkedList<OriginCupProfileDto>();
		List<ProductDto> productDtoList = new LinkedList<ProductDto>();
		// List<ExternalMappingDto> mappingDtoList = new
		// LinkedList<ExternalMappingDto>();

		// geo-service rest call
		ResponseEntity<ResponseData> geoResponseEntity = webServiceCall.internalServiceCall(url + "/geoservice/geo");
		ResponseData<List<Object>> geoBody = geoResponseEntity.getBody();
		List<Map<Object, Object>> geoList = (List<Map<Object, Object>>) geoBody.getBody();

		String geoName = null;
		// Integer geoId = 0;

		for (int x = 0; x < geoList.size(); x++) {
			int geoId = (int) geoList.get(x).get("pkGeoId");
			if (geoId != 0 && masterOriginEntity.getFkGeoId() == geoId) {
				geoName = (String) geoList.get(x).get("geoName");
				origin.setGeoName(geoName);

			}
		}
		// status-service rest call
		ResponseEntity<ResponseData> responseEntity = webServiceCall.internalServiceCall(url + "/statusservice/status");
		ResponseData<List<StatusDto>> body = responseEntity.getBody();
		List<Map<Object, Object>> list = (List<Map<Object, Object>>) body.getBody();
		int statusId = 0;
		String statusname = null;
		for (int x = 0; x < list.size(); x++) {
			statusId = (int) list.get(x).get("pkStatusId");
			if (statusId != 0 && masterOriginEntity.getFkStatusId() == statusId) {
				statusname = (String) list.get(x).get("statusName");
				origin.setStatusName(statusname);
			}
		}

		// setting basic details for originDto
		origin.setPkOriginId(masterOriginEntity.getPkOriginId());
		origin.setOriginCode(masterOriginEntity.getOriginCode());
		origin.setOriginName(masterOriginEntity.getOriginName());
		origin.setOriginFullName(masterOriginEntity.getOriginFullname());
		// commented in master working for prod
		// origin.setFkGeoId(masterOriginEntity.getFkGeoId());

		origin.setFkGeoId(masterOriginEntity.getFkGeoId());
		origin.setGeoName(geoName);
		origin.setFkStatusId(masterOriginEntity.getFkStatusId());
		// getting statusname from status id fetched from status rest call

		// origin.setFkStatusId(masterOriginEntity.getFkStatusId());

		// setting origin region details for OriginRegionDto
		OriginRegionDto regionDto = new OriginRegionDto();
		for (MasterOriginRegion region : masterOriginEntity.getMasterOriginRegionCollection()) {
			regionDto.setPkOriginRegionId(region.getPkOriginRegionId());
			regionDto.setOriginRegionCode(region.getOriginRegionCode());
			regionDto.setOriginRegionName(region.getOriginRegionName());
			regionDto.setOriginRegionMeanAboveSeaLevel(region.getOriginRegionMeanAboveSeaLevel());
			regionDto.setRegionEstate(region.getRegionEstate());
			regionDto.setStatusName(statusname);

			originRegionList.add(regionDto);

		}

		// setting origin cup profile details for OriginCupProfileDto
		OriginCupProfileDto cupProfileDto = new OriginCupProfileDto();
		for (MasterOriginCupProfile cupProfile : masterOriginEntity.getMasterOriginCupProfileCollection()) {
			cupProfileDto.setPkOriginCupProfileId(cupProfile.getPkOriginCupProfileId());
			cupProfileDto.setOriginCupProfileCode(cupProfile.getOriginCupProfileCode());
			cupProfileDto.setOriginCupProfileName(cupProfile.getOriginCupProfileName());
			cupProfileDto.setStatusName(statusname);
			originCupList.add(cupProfileDto);
		}

		// setting Product details for ProductDto
		ProductDto productDto = new ProductDto();
		for (MasterProduct product : masterOriginEntity.getMasterProductCollection()) {
			productDto.setPkProdId(product.getPkProdId());
			productDto.setProdCode(product.getProdCode());
			productDto.setProdName(product.getProdName());
			// productDto.setFkStatusId(statusname);
			productDtoList.add(productDto);
		}

		// setting External system details for ExternalMappingDto
		ExternalMappingDto mappingDto = new ExternalMappingDto();
		for (MasterOriginExternalMapping mapping : masterOriginEntity.getMasterOriginExternalMappingCollection()) {
			mappingDto.setPkOriginExternalMappingId(mapping.getPkOriginExternalMappingId());
			mappingDto.setExternalCode(mapping.getExternalCode());
			mappingDto.setMappingType(mapping.getMappingType());
			// productDto.setFkStatusId(statusname);
			mappingDtoList.add(mappingDto);
		}

		masterOriginDto.setOriginDto(origin);
		// masterOriginDto.setOriginRegionDto(originRegionList);
		// masterOriginDto.setOriginCupProfileDto(originCupList);
		masterOriginDto.setStatus(statusname);
		// masterOriginDto.setProductDto(productDtoList);
		// masterOriginDto.setExternalMappingDto(mappingDtoList);

		return masterOriginDto;
	}

	
	 
	@Transactional
	public List<MasterOriginDto> readAll() {

		List<MasterOriginDto> masterOriginDtoList = new ArrayList<>();
		List<String> sortColumns = new ArrayList<>();

		sortColumns.add("createdDate");
		sortColumns.add("modifiedDate");
		Sort sort = new Sort(Direction.DESC, sortColumns);
		List<MasterOrigin> oldList = repository.findAll(sort);
		System.out.println("oldList" + oldList);
		if (!(oldList.isEmpty())) {
			for (MasterOrigin masterOrigin : oldList) {
				MasterOriginDto masterOriginDto;
				masterOriginDto = generatemasterOriginDto(masterOrigin);
				masterOriginDtoList.add(masterOriginDto);
			}

			return masterOriginDtoList;

		} else {

			throw new ScoreBaseException(0, " No Record Found in DB ", HttpStatus.NOT_FOUND);

		}
	}

	
	
	
	@Transactional
	public MasterOriginDto readById(Integer originId) {

		MasterOriginDto originDetails = null;

		if (originId != null) {
			MasterOrigin originDetailsDb = repository.findOne(originId);
			if (originDetailsDb != null) {
				//originDetails = generatemasterOriginDto(originDetailsDb);
				return originDetails;
			} else {

				throw new ScoreBaseException(0, " Origin Id: " + originId + " not found in DB", HttpStatus.NOT_FOUND);
			}

		} else {

			throw new ScoreBaseException(0, " Origin Id: " + originId, HttpStatus.NOT_FOUND);
		}

	}

	public List<MasterOriginDto> update(List<MasterOriginDto> body) {

		masterOriginDto dto;
		List<masterOriginDto> newList = new ArrayList<>();
		for (masterOriginDto masterOriginDto : body) {
			MasterGl masterGl = repository.saveAndFlush(generateMasterGL(masterOriginDto, "update"));
			dto = generatemasterOriginDto(masterGl);
			newList.add(dto);
		}
		return newList;

		MasterOriginDto dto;
		List<MasterOriginDto> newList = new ArrayList<>();
		for (MasterOriginDto masterOriginDto : body) {

			Integer originIdFromName = repository.findOriginIdByName(masterOriginDto.getOriginDto().getOriginName());
			Integer originIdFromCode = repository.findOriginIdByCode(masterOriginDto.getOriginDto().getOriginCode());
			// System.out.println("check
			// "+masterOriginDto.getGlBasicDto().getGlId()+"
			// check "+originIdFromName);
			// System.out.println("check
			// "+masterOriginDto.getGlBasicDto().getGlId()+"
			// check "+originIdFromCode);
			if ((originIdFromName != null
					&& masterOriginDto.getOriginDto().getPkOriginId() != originIdFromName.intValue())
					&& (originIdFromCode != null
							&& masterOriginDto.getOriginDto().getPkOriginId() != originIdFromCode.intValue())) {
				throw new ScoreBaseException(0, "originId and originCode already exists in database",
						HttpStatus.CONFLICT);
			} else if (originIdFromName != null
					&& masterOriginDto.getOriginDto().getPkOriginId() != originIdFromName.intValue()) {
				throw new ScoreBaseException(0, "originId exists in database", HttpStatus.CONFLICT);
			} else if ((originIdFromCode != null
					&& masterOriginDto.getOriginDto().getPkOriginId() != originIdFromCode.intValue())) {
				throw new ScoreBaseException(0, "originCode exists in database", HttpStatus.CONFLICT);
			} else {

				MasterOrigin masterOrigin = repository.findOne(masterOriginDto.getOriginDto().getPkOriginId());
				masterOriginDto.getOriginDto().setCreatedBy(masterOrigin.getCreatedBy());
				masterOriginDto.getOriginDto().setCreatedDate((Timestamp) masterOrigin.getCreatedDate());
				if (!(masterOrigin.getMasterOriginRegionCollection().isEmpty())) {
					for (MasterOriginRegion masterOriginRegion : masterOrigin.getMasterOriginRegionCollection()) {
						((MasterOrigin) masterOriginDto.getOriginRegionDto()).setCreatedBy(masterOrigin.getCreatedBy());
						((MasterOrigin) masterOriginDto.getOriginRegionDto())
								.setCreatedDate(masterOrigin.getCreatedDate());
					}
				}

				masterOrigin = repository.saveAndFlush(generateMasterOrigin(masterOriginDto, "update"));
				dto = generatemasterOriginDto(masterOrigin);
				newList.add(dto);
			}
		}
		return newList;
	}

	public void deleteOrigin(List<Map<String, Integer>> body) {

		for (Map<String, Integer> originId : body) {
			originId.forEach((name, id) -> {
				MasterOrigin masterOrigin = new MasterOrigin();
				masterOrigin = repository.findOne(id);
				if (masterOrigin != null) {
					if (masterOrigin.getFkStatusId() != 0
							&& (EnumStatus.ACTIVE.getValue()) == (masterOrigin.getFkStatusId())) {

						masterOrigin.setFkStatusId(EnumStatus.INACTIVE.getValue());
						if (!(masterOrigin.getMasterOriginRegionCollection().isEmpty())) {
							for (MasterOriginRegion originRegion : masterOrigin.getMasterOriginRegionCollection()) {
								originRegion.setFkStatusId(EnumStatus.INACTIVE.getValue());
							}
						}
						if (!(masterOrigin.getMasterOriginCupProfileCollection().isEmpty())) {
							for (MasterOriginCupProfile originCupProfile : masterOrigin
									.getMasterOriginCupProfileCollection()) {
								originCupProfile.setFkStatusId(EnumStatus.INACTIVE.getValue());
							}
						}

						repository.saveAndFlush(masterOrigin);
					} else if (masterOrigin.getFkStatusId() != 0
							&& (EnumStatus.DRAFT.getValue()) == (masterOrigin.getFkStatusId())) {

						repository.delete(masterOrigin);

					}
				} else {
					throw new ScoreBaseException(0, "Origin Id Not Found For deletion", HttpStatus.NOT_FOUND);

				}
			});

		}

	}*/
	
	
	@Transactional
	public List<MasterOriginDto> readAll(ViewDto view) {
		List<MasterOriginDto> newList = new ArrayList<>();
		List<String> filterList = view.getFiltersArray();
		OriginSpecificationsBuilder builder = new OriginSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterOrigin> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterOrigin> oldList = repository.findAll(spec, pageRequest).getContent();
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		
		/*ResponseEntity<ResponseData> geoResponseEntity = CommonUtil.getServiceCall(locationurl + "/location/geoservice/geo");
		ResponseData<List<Object>> geoBody = geoResponseEntity.getBody();
		List<Map<Object, Object>> geoList = (List<Map<Object, Object>>) geoBody.getBody();*/
		
		
		
		
		for (MasterOrigin masterOrigin : oldList) {
			MasterOriginDto masterOriginDto;
			masterOriginDto = masterOrigin.convertToMasterOriginDto(statusMap,null);//geoList);
			newList.add(masterOriginDto);
		}

		return newList;
	}
	
	//View Service for outturnratio
	// get view dto
	
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
		Class<OriginDto> originDto = OriginDto.class;
		Field[] fields = originDto.getDeclaredFields();
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
