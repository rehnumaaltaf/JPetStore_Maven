package com.olam.score.location.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olam.score.common.constants.Constants;
import com.olam.score.common.constants.ResponseConstants;
import com.olam.score.location.domain.MasterGeography;
import com.olam.score.location.domain.MasterLocation;
import com.olam.score.location.domain.MasterLocationType;

import com.olam.score.location.dto.LocationDTO;
import com.olam.score.common.domain.DropDownModel;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.location.repository.GeoRepository;
import com.olam.score.location.repository.LocTypeAssocRepository;
import com.olam.score.location.repository.LocationRepository;
import com.olam.score.location.repository.LocationTypeRepository;

import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;

@Service
public class LocationService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private GeoRepository geoRepository;

	@Autowired
	private LocationTypeRepository locTypeRepository;
	
	@Autowired
	private LocTypeAssocRepository locTypeAssocRepository;
	
	@Value("${reference.name}")
	private String referenceName;
	
	@Value("${packing.name}")
	private String packingName;
	@Autowired
	private WebServiceCallUtil serviceCall;
	private int chkValue=0;
	@Transactional
	public String create(LocationDTO inputData) {
		String addStatus = ResponseConstants.LOCATION_ADD_SUCESS;
		MasterLocation masterLocation = new MasterLocation();
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		masterLocation =masterLocation.getMappingLocation(inputData, masterLocation,statusMap);
		chkValue=0;
	    validate(0,masterLocation);
		locationRepository.saveAndFlush(masterLocation);
		return addStatus;
	}
	
	private void validate(Integer locID,MasterLocation masterLocation){
		
		if(locationRepository.uniqueLocCode(masterLocation.getLocCode()) > chkValue && locationRepository.uniqueLocName(masterLocation.getLocName()) > chkValue)
		{
			throw new ScoreBaseException(locID, "Location Name and Code exist in database",HttpStatus.CONFLICT);
		}
		if (locationRepository.uniqueLocCode(masterLocation.getLocCode()) > chkValue) {
			throw new ScoreBaseException(locID, "Location Code exist in database",HttpStatus.CONFLICT);
		}
		if (locationRepository.uniqueLocName(masterLocation.getLocName()) > chkValue) {
			throw new ScoreBaseException(locID, "Location Name exist in database",HttpStatus.CONFLICT);
		}

	}

	@Transactional
	public String update(LocationDTO inputData) {
		String addStatus = ResponseConstants.LOCATION_UPDATED_SUCESS;
		MasterLocation masterLocation = new MasterLocation();
		masterLocation.setPkLocId(inputData.getPkLocId());
		locTypeAssocRepository.deleteLocTypeAssocId(masterLocation);
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		masterLocation = masterLocation.getMappingLocation(inputData, masterLocation, statusMap);
		chkValue = 1;
		if (!locationRepository.getByLocCodeAndPkLocIdNotIn(masterLocation.getLocCode(), masterLocation.getPkLocId()).isEmpty()&&! locationRepository.getByLocNameAndPkLocIdNotIn(masterLocation.getLocName(), masterLocation.getPkLocId()).isEmpty()) {
			 throw new ScoreBaseException(masterLocation.getPkLocId(),"Location Name and Code exist in database", HttpStatus.CONFLICT);
		}
		if (!locationRepository.getByLocCodeAndPkLocIdNotIn(masterLocation.getLocCode(), masterLocation.getPkLocId()).isEmpty()) {
			throw new ScoreBaseException(masterLocation.getPkLocId(), "Location Code exist in database",HttpStatus.CONFLICT);
		}
		if (!locationRepository.getByLocNameAndPkLocIdNotIn(masterLocation.getLocName(), masterLocation.getPkLocId()).isEmpty()) {
			throw new ScoreBaseException(masterLocation.getPkLocId(), "Location Name exist in database",HttpStatus.CONFLICT);
		}
		masterLocation.setModifiedBy("");
		masterLocation.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		locationRepository.saveAndFlush(masterLocation);
		return addStatus;
	}


	
	public String returnStatusNameById(int id)
	{
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		return statusMap.get(id);
	}
	
	public LocationDTO getDropdownList() {
		LocationDTO locationDTO = new LocationDTO();
		List<MasterGeography> locCountryList = geoRepository.findAll();
		List<MasterLocationType> locationTypeList = locTypeRepository.findAll();
		List<DropDownModel> countryList = new ArrayList<>();
		List<DropDownModel> locTypeList = new ArrayList<>();

		for (MasterGeography status : locCountryList) {
			DropDownModel dropDownModel = new DropDownModel();
			dropDownModel.setCode(String.valueOf(status.getPkGeoId()));
			dropDownModel.setValue(status.getGeoName());
			countryList.add(dropDownModel);
		}
		for (MasterLocationType status : locationTypeList) {
			DropDownModel dropDownModel = new DropDownModel();
			dropDownModel.setCode(String.valueOf(status.getPkLocTypeId()));
			dropDownModel.setValue(status.getLocTypeName());
			locTypeList.add(dropDownModel);
		}

		locationDTO.setLocCountryList(countryList);
		locationDTO.setLocationTypeList(locTypeList);

		return locationDTO;

	}
	private List<LocationDTO> modelToDto(List<MasterLocation> masterLocationList){
		List<LocationDTO> locationDTOList = new ArrayList<>();
		for (MasterLocation masterLoc : masterLocationList) {
			LocationDTO locationDTO = new LocationDTO();
			locationDTO.setPkLocId(masterLoc.getPkLocId());
			locationDTO.setLocName(masterLoc.getLocName());
			locationDTO.setLocFullname(masterLoc.getLocFullname());
			locationDTO.setLocCode(masterLoc.getLocCode());
			locationDTO.setFkGeoId(masterLoc.getFkGeoId().getPkGeoId());
			locationDTO.setFkGeoName(masterLoc.getFkGeoId().getGeoName());
			locationDTO.setFkStatusId(masterLoc.getFkStatusId());
			locationDTO.setFkLocTypeId(masterLoc.getFkLocTypeId());
			locationDTO.setFkLocTypeName(locTypeRepository.findOne(masterLoc.getFkLocTypeId()).getLocTypeName());
			locationDTO.setStatusName(returnStatusNameById(masterLoc.getFkStatusId()));
		    modelToDtoIndiv(masterLoc,locationDTO);
			locationDTOList.add(locationDTO);
		}
		return locationDTOList;
	}
	private LocationDTO modelToDtoIndiv(MasterLocation masterLoc,LocationDTO locationDTO){
		
			if(masterLoc.getRateBasisUom()!=null) {
			locationDTO.setRateBasisUomId(masterLoc.getRateBasisUom());
			locationDTO.setRateBasisUom(getUomName(masterLoc.getRateBasisUom()));
			}
			locationDTO.setFkLocTypeId(masterLoc.getFkLocTypeId());
			if(masterLoc.getFkLocTypeId()!=null){
			locationDTO.setFkLocTypeName(locTypeRepository.findByLocTypeName(masterLoc.getFkLocTypeId()));
			}
			if(masterLoc.getRate()!=null){
			locationDTO.setRate(masterLoc.getRate());
			locationDTO.setFkRateBasisRefId(masterLoc.getFkRateBasisRefId());
			}
			if(masterLoc.getRateBasisPrimaryPacking()!=null) {
			locationDTO.setRateBasisPrimaryPackingId(masterLoc.getRateBasisPrimaryPacking());
			locationDTO.setRateBasisPrimaryPacking(getPrimaryPackingName(masterLoc.getRateBasisPrimaryPacking()));
			}
			if(masterLoc.getRateBasisSecondaryPacking()!=null) {
			locationDTO.setRateBasisSecondaryPackingId(masterLoc.getRateBasisSecondaryPacking());
			locationDTO.setRateBasisSecondaryPacking(getSecondaryPackingName(masterLoc.getRateBasisSecondaryPacking()));
			}
			if(masterLoc.getFkCurrencyId()!=null && masterLoc.getFkCurrencyId()>0) {
				locationDTO.setFkCurrencyId(masterLoc.getFkCurrencyId());
				locationDTO.setFkCurrencyName(getCurrencyName(masterLoc.getFkCurrencyId()));
			}
			HashMap<Integer, String> rateBasisMap = new HashMap<>(); 
			
			rateBasisMap.put(1, "List UOM");
			rateBasisMap.put(2, "Primary Packing");
			rateBasisMap.put(3, "Secondary Packing");
			Set set = (Set) rateBasisMap.entrySet();
			//Create iterator on Set 
			Iterator iterator = set.iterator();
			
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				if (masterLoc.getFkRateBasisRefId() != null && masterLoc.getFkRateBasisRefId() != 0) {
					int costType = masterLoc.getFkRateBasisRefId();

					int keyValue = (int) mapEntry.getKey();
					if (costType == keyValue) {
						// Get Value
						String value = (String) mapEntry.getValue();
						locationDTO.setFkRateBasisRefName(value);
					}
				}
			}
		return locationDTO;
	}
	
    private String getCurrencyName(Integer currencyId) {
       
        if(currencyId!=null && currencyId>0) {
              ResponseEntity<ResponseData> responseEntityCurrency=serviceCall.internalServiceCall(referenceName,"/reference/currencyservice/currency/"+currencyId.toString());
              Map<Object, Object> currencys = (Map<Object, Object>) responseEntityCurrency.getBody().getBody();
              return (String) currencys.get("currencyName");
        }else {
              return null;
        }
  }
  
  private String getUomName(Integer uomId) {
       
        if(uomId!=null && uomId>0) {
              ResponseEntity<ResponseData> responseEntityUom=serviceCall.internalServiceCall(referenceName,"/reference/uomservice/uom/"+uomId.toString());
              Map<Object, Object> uoms = (Map<Object, Object>) responseEntityUom.getBody().getBody();
              return (String) uoms.get("uomName");
        }else {
              return null;
        }
  }
  
  private String getPrimaryPackingName(Integer ppId) {
       
        if(ppId!=null && ppId>0) {
              ResponseEntity<ResponseData> responseEntityPrimaryPacking=serviceCall.internalServiceCall(packingName,"/packing/PrimaryPackingType/viewPrimaryPackingType/"+ppId.toString());
              Map<Object, Object> pps = (Map<Object, Object>) responseEntityPrimaryPacking.getBody().getBody();
              return (String) pps.get("internalPackingTypeName");
        }else {
              return null;
        }
  }
  
  private String getSecondaryPackingName(Integer spId) {

        String secondaryPackingName=null;
        if(spId!=null && spId>0) {
              ObjectMapper map=new ObjectMapper();
              ResponseEntity<ResponseData> responseEntitySecondaryPacking=serviceCall.internalServiceCall(packingName,"/packing/secPackTypeService/secPackType/"+spId.toString());
              List<Map<Object, Object>> sps = (List<Map<Object, Object>>) responseEntitySecondaryPacking.getBody().getBody();
  			if (!sps.isEmpty()) {
  				secondaryPackingName=(String) sps.get(0).get("secondaryPackingTypeName");
  				
  			}
  			return secondaryPackingName;
        }else {
              return null;
        }
        
  }
	
    @Transactional(readOnly=true)
	public List<LocationDTO> readAll() {
		List<MasterLocation> masterLocationList = locationRepository.findAll(sortByIdDsc());
		return modelToDto(masterLocationList);
	}
	
    @Transactional(readOnly=true)
	public List<LocationDTO> getLocationById(Integer locationId) {
		List<MasterLocation> masterLocationList = locationRepository.getLocationById(locationId);
		return modelToDto(masterLocationList);
	}

	
	@Transactional
	public String delete(Integer locationId) {

		String deleteStatus = ResponseConstants.CURRENCY_DELETE_SUCESS;
		try {
			MasterLocation location= locationRepository.findOne(locationId);
			if(location.getFkStatusId()==returnStatusIdByName(Constants.DRAFT)){
			locationRepository.delete(locationId);
			}
			if(location.getFkStatusId()==returnStatusIdByName(Constants.ACTIVE)) {
				locationRepository.deactivateOrReactivateLocation(returnStatusIdByName(Constants.INACTIVE), locationId);
			} 
			if(location.getFkStatusId()==returnStatusIdByName(Constants.DRAFT)){
				locationRepository.deactivateOrReactivateLocation(returnStatusIdByName(Constants.ACTIVE), locationId);
			}
		} catch (Exception e) {
			deleteStatus = ResponseConstants.CURRENCY_DELETE_FAILURE;
			log.error("Delete:", e);

		}
		return deleteStatus;
	}
    
	private Sort sortByIdDsc() {
		return new Sort(Direction.DESC, Arrays.asList("createdDate","modifiedDate"));
	}
	
	public int returnStatusIdByName(String name)
    {
          Map<Integer, String> statusMap = serviceCall.getAllStatus();
          Optional<Integer> statusId = statusMap.entrySet().stream()
                      .filter(e -> e.getValue().equals(name)).map(Map.Entry::getKey).findFirst();
          if (statusId.isPresent()) {
                log.info("===Got Status Id in Status map ===");
                return statusId.get();
          }
          return 0;
    }

}
