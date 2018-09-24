package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.common.util.CommonUtil;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.party.constants.PartyConstants;
import com.olam.score.party.domain.MasterParty;
import com.olam.score.party.domain.MasterPartyAddress;
import com.olam.score.party.domain.MasterPartyExternalMapping;
import com.olam.score.party.domain.MasterPartyPartyType;
import com.olam.score.party.domain.MasterPartyType;
import com.olam.score.party.dto.ExternalInterfaceDto;
import com.olam.score.party.dto.ResponseDto;
import com.olam.score.party.repository.PartyMasterExternalMappingRepository;
import com.olam.score.party.repository.PartyMasterRepository;
import com.olam.score.party.util.PartyMap;
@Service
public class ExternalInterfaceService {

	@Autowired
	private PartyMasterRepository repository;

	@Autowired
	private WebServiceCallUtil serviceCall;
	
	@Autowired
	private PartyMap partyMap;

	@Transactional
	public ResponseDto create(ExternalInterfaceDto partyDetails) {
		ResponseDto response = null;
		String isValid = validate(partyDetails);
		if (PartyConstants.VALID.equalsIgnoreCase(isValid)) {
			MasterParty party = repository.findByPartyName(partyDetails.getPartyName());
			if (party != null) {
				List<MasterPartyExternalMapping> sapMappingList = party.getMasterPartyExternalMappingList()
						.stream().filter(data -> data.getFkExternalSystemRefId()
								.equals(PartyConstants.EXTERNAL_SYSTEM_SAP_MAPPING))
						.collect(Collectors.toList());
				MasterPartyExternalMapping mappedObject = sapMappingList.get(0);
				if (PartyConstants.SAP_CUSTOMER_TYPE.equals(partyDetails.getType())) {
					if (mappedObject.getCustomerCode() == null) {
						mappedObject.setCustomerCode(partyDetails.getCustomerCode());
						update(party, partyDetails,mappedObject,true);
						response = new ResponseDto(PartyConstants.TRANSACTION_SUCCESS, PartyConstants.SUCCESS_CODE,
								party.getPartyName(), getStatus(party.getFkStatusId()));
					} else if(partyDetails.getCustomerCode().equals(mappedObject.getCustomerCode())){
						update(party, partyDetails,mappedObject,false);
						response = new ResponseDto(PartyConstants.TRANSACTION_SUCCESS, PartyConstants.SUCCESS_CODE,
								party.getPartyName(), getStatus(party.getFkStatusId()));
					}else {
						response = new ResponseDto(PartyConstants.TRANSACTION_FAILURE, PartyConstants.INVALID_DATA,
								PartyConstants.PARTY_CODE_EXISTS_INVALID_DATA);
					}
				} else if (PartyConstants.SAP_VENDOR_TYPE.equals(partyDetails.getType())) {
					if (mappedObject.getVendorCode() == null) {
						mappedObject.setVendorCode(partyDetails.getVendorCode());
						update(party, partyDetails,mappedObject,true);
						response = new ResponseDto(PartyConstants.TRANSACTION_SUCCESS, PartyConstants.SUCCESS_CODE,
								party.getPartyName(), getStatus(party.getFkStatusId()));
					} else if(partyDetails.getVendorCode().equals(mappedObject.getVendorCode())){
						update(party, partyDetails,mappedObject,false);
						response = new ResponseDto(PartyConstants.TRANSACTION_SUCCESS, PartyConstants.SUCCESS_CODE,
								party.getPartyName(), getStatus(party.getFkStatusId()));
					}else {
						response = new ResponseDto(PartyConstants.TRANSACTION_FAILURE, PartyConstants.INVALID_DATA,
								PartyConstants.PARTY_CODE_EXISTS_INVALID_DATA);
					}
				}
			} else {
				MasterParty newParty = createParty(partyDetails);
				response = new ResponseDto(PartyConstants.TRANSACTION_SUCCESS, PartyConstants.SUCCESS_CODE,
						newParty.getPartyName(), getStatus(newParty.getFkStatusId()));
			}
		} else {
			response = new ResponseDto(PartyConstants.TRANSACTION_FAILURE, PartyConstants.INVALID_DATA, isValid);
		}
		return response;
	}

	private String getStatus(Integer fkStatusId) {
		// TODO Auto-generated method stub
		Map<Integer, String> statusMap = serviceCall.getAllStatus();
		return statusMap.get(fkStatusId);

	}

	private MasterParty createParty(ExternalInterfaceDto partyDetails) {
		// TODO Auto-generated method stub
		MasterParty party = new MasterParty();
		party.setPartyInternalFlag(partyDetails.getInternal());
		party.setPartyName(partyDetails.getPartyName());
		party.setIsGroupParty(partyDetails.getGroup());
		party.setFkStatusId(PartyConstants.STATUS_SAP_DRAFT_ID);
		List<MasterPartyPartyType> masterPartyPartyTypeList = new ArrayList<>();
		List<MasterPartyExternalMapping> masterPartyExternalMappingList = new ArrayList<>();
		MasterPartyExternalMapping externalMapping = new MasterPartyExternalMapping();
		externalMapping.setFkExternalSystemRefId(PartyConstants.EXTERNAL_SYSTEM_SAP_MAPPING);
		if(PartyConstants.SAP_VENDOR_TYPE.equals(partyDetails.getType())){
			party.setPartyCode(partyDetails.getPartyName() + partyDetails.getVendorCode());
			MasterPartyPartyType partyTypeMap = new MasterPartyPartyType(new MasterPartyType(PartyConstants.VENDOR_TYPE_CODE));
			masterPartyPartyTypeList.add(partyTypeMap);
			externalMapping.setVendorCode(partyDetails.getVendorCode());
		}
		if(PartyConstants.SAP_CUSTOMER_TYPE.equals(partyDetails.getType())) {
			party.setPartyCode(partyDetails.getPartyName() + partyDetails.getCustomerCode());
			MasterPartyPartyType partyTypeMap = new MasterPartyPartyType(new MasterPartyType(PartyConstants.CUSTOMER_TYPE_CODE));
			masterPartyPartyTypeList.add(partyTypeMap);
			externalMapping.setCustomerCode(partyDetails.getCustomerCode());
		}
		masterPartyExternalMappingList.add(externalMapping);
		party.setMasterPartyExternalMappingList(masterPartyExternalMappingList);
		party.setMasterPartyPartyTypeList(masterPartyPartyTypeList);
		
		List<MasterPartyAddress> masterPartyAddressList = new ArrayList<>();
		MasterPartyAddress address = new MasterPartyAddress();
		address.setPartyAddressOne(partyDetails.getStreet1());
		address.setPartyAddressTwo(partyDetails.getStreet2());
		address.setPartyAddrZipcode(partyDetails.getZipCode());
		address.setFkGeoCountryId(getCountry(partyDetails.getCountry()));
		masterPartyAddressList.add(address);
		party.setMasterPartyAddressList(masterPartyAddressList);
		repository.saveAndFlush(party);
		return party;
	}

	private Integer getCountry(String country) {
		// TODO Auto-generated method stub
		Integer countryId = null;
		Map<Integer,String> countryMap = partyMap.geoMapData("location-service");
		Optional<Integer> firstKey = countryMap.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(),country))
                .map(Map.Entry::getKey).findFirst();
		if (firstKey.isPresent()) {
           countryId = firstKey.get();
        }
		return countryId;
	}

	private MasterParty update(MasterParty existingParty, ExternalInterfaceDto partyDetails, MasterPartyExternalMapping mappedObject,boolean addPartyType) {
		// TODO Auto-generated method stub
		existingParty.setPartyInternalFlag(partyDetails.getInternal());
		existingParty.setIsGroupParty(partyDetails.getGroup());
		if (PartyConstants.SAP_BLOCKED_VALUE.equals(partyDetails.getBlock())) {
			existingParty.setFkStatusId(PartyConstants.STATUS_INACTIVE_ID);
		}
		MasterPartyPartyType partyTypeMap = null;
		if(addPartyType) {
			if(!CommonUtil.isEmpty(partyDetails.getVendorCode())) {
				partyTypeMap = new MasterPartyPartyType(new MasterPartyType(PartyConstants.VENDOR_TYPE_CODE));
			}else {
				partyTypeMap = new MasterPartyPartyType(new MasterPartyType(PartyConstants.CUSTOMER_TYPE_CODE));
			}
		}
		if(partyTypeMap!=null) {
			if(existingParty.getMasterPartyPartyTypeList()!=null) {
				existingParty.getMasterPartyPartyTypeList().add(partyTypeMap);
			}else {
				List<MasterPartyPartyType> masterPartyPartyTypeList = new ArrayList<>();
				masterPartyPartyTypeList.add(partyTypeMap);
				existingParty.setMasterPartyPartyTypeList(masterPartyPartyTypeList);
			}
		}
		existingParty.getMasterPartyExternalMappingList().add(mappedObject);
		repository.saveAndFlush(existingParty);
		return existingParty;
	}

	private String validate(ExternalInterfaceDto partyDetails) {
		// TODO Auto-generated method stub
		String blockValue = partyDetails.getBlock();
		String isValid = PartyConstants.VALID;
		if (CommonUtil.isEmpty(partyDetails.getInternal()) || CommonUtil.isEmpty(partyDetails.getPartyName())) {
			isValid = PartyConstants.INVALID_DATA_COMING_FROM_SAP;
		}
		else if ((partyDetails.getType().equals(PartyConstants.SAP_CUSTOMER_TYPE)
				&& CommonUtil.isEmpty(partyDetails.getCustomerCode()))
				|| (partyDetails.getType().equals(PartyConstants.SAP_VENDOR_TYPE)
						&& CommonUtil.isEmpty(partyDetails.getVendorCode()))) {
			isValid = PartyConstants.INVALID_DATA_FOR_CUSTOMER_VENDOR_CODE;
		}
		else if (!CommonUtil.isEmpty(blockValue) && !PartyConstants.SAP_BLOCKED_VALUE.equals(blockValue)) {
			isValid = PartyConstants.STATUS_CODE_INVALID;
		} else if (CommonUtil.isEmpty(partyDetails.getPartyName())) {
			isValid = PartyConstants.INVALID_DATA_FROM_SAP_FOR_PARTY_NAME_CANNOT_BE_EMPTY;
		}
		return isValid;
	}



}
