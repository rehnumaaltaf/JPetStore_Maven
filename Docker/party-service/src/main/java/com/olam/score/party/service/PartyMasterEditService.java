package com.olam.score.party.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.party.constants.PartyConstants;
import com.olam.score.party.domain.MasterParty;
import com.olam.score.party.domain.MasterPartyAddress;
import com.olam.score.party.domain.MasterPartyAddressTranslation;
import com.olam.score.party.domain.MasterPartyBank;
import com.olam.score.party.domain.MasterPartyBroker;
import com.olam.score.party.domain.MasterPartyClassification;
import com.olam.score.party.domain.MasterPartyContact;
import com.olam.score.party.domain.MasterPartyCreditLimit;
import com.olam.score.party.domain.MasterPartyDocument;
import com.olam.score.party.domain.MasterPartyExternalMapping;
import com.olam.score.party.domain.MasterPartyGrade;
import com.olam.score.party.domain.MasterPartyGradeAssoc;
import com.olam.score.party.domain.MasterPartyPartyClass;
import com.olam.score.party.domain.MasterPartyPartyType;
import com.olam.score.party.domain.MasterPartyPaymentTerm;
import com.olam.score.party.domain.MasterPartyPlant;
import com.olam.score.party.domain.MasterPartyRatingRef;
import com.olam.score.party.domain.MasterPartyTranslation;
import com.olam.score.party.domain.MasterPartyType;
import com.olam.score.party.domain.MasterPartyWhStockLocation;
import com.olam.score.party.dto.PartyBrokerDetailsDto;
import com.olam.score.party.dto.PartyGradeDefinitionDto;
import com.olam.score.party.dto.PartyMasterDto;
import com.olam.score.party.dto.PaymentTermsDto;
import com.olam.score.party.repository.PartyMasterRepository;
import com.olam.score.party.util.PartyUtil;

@Service
public class PartyMasterEditService {
	
	@Autowired
	private PartyMasterRepository repository;
	
	@Autowired
	private WebServiceCallUtil webServiceCall;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PartyUtil partyUtil;
	
	@Transactional
	public PartyMasterDto editPartyMaster(PartyMasterDto editPartyDetails) {
		try {
			
		MasterParty editedParty	;
		//MasterParty fkParty = new MasterParty(editPartyDetails.getPkPartyId());
		
		//Basic Details
		editedParty = modelMapper.map(editPartyDetails.getPartyBasicDetails(), MasterParty.class);
		editedParty.setPkPartyId(editPartyDetails.getPkPartyId());
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		Optional<Integer> firstKey = statusMap.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), editPartyDetails.getPartyBasicDetails().getStatus()))
                .map(Map.Entry::getKey).findFirst();
		if (firstKey.isPresent()) {
			editedParty.setFkStatusId(firstKey.get());
        }
		
		List<Integer> partyTypeIds = editPartyDetails.getPartyBasicDetails().getPartyTypeIds();
		List<String> partyType = partyUtil.getPartyTypeValues(partyTypeIds);
		
		List<Integer> partyClassificationIds = editPartyDetails.getPartyBasicDetails().getPartyClassificationIds();
		List<String> partyClassification = partyUtil.getPartyClassificationValues(partyClassificationIds);
		
		//PartyRating
		if(editPartyDetails.getPartyBasicDetails().getPartyRKey()!=null) {
			MasterPartyRatingRef partyRating = new MasterPartyRatingRef(editPartyDetails.getPartyBasicDetails().getPartyRKey());
			editedParty.setFkPartyRatingRefId(partyRating);
		}
		
		//Address
		List<MasterPartyAddress> addressRows = new ArrayList<>();
		editPartyDetails.getPartyAddress().forEach(data -> {
			MasterPartyAddress updateAddressRow = modelMapper.map(data,MasterPartyAddress.class);
			if(data.getUniqueMapping()!=null) {
				updateAddressRow.setPkPartyAddrId(data.getUniqueMapping());
				updateAddressRow.setFkPartyId(editedParty);
			}
			addressRows.add(updateAddressRow);
		});
		editedParty.setMasterPartyAddressList(addressRows);
		
		//Bank Accounts 
		List<MasterPartyBank> bankRows = new ArrayList<>();
		editPartyDetails.getPartyBankAccountDetails().forEach(data -> {
			MasterPartyBank updatedBankRow = modelMapper.map(data,MasterPartyBank.class);
			if(data.getUniqueMapping()!=null) {
				updatedBankRow.setPkPartyBankId(data.getUniqueMapping());
				updatedBankRow.setFkParentPartyId(editedParty);
			}
			bankRows.add(updatedBankRow);
		});
		editedParty.setMasterPartyBankList(bankRows);
		
		//Contacts(not required if external party is exchange)
		List<MasterPartyContact> contactRows = new ArrayList<>();
		editPartyDetails.getPartyContacts().forEach(data -> {
			MasterPartyContact updatedContactRow = modelMapper.map(data,MasterPartyContact.class);
			if(data.getUniqueMapping()!=null) {
				updatedContactRow.setPkPartyContactId(data.getUniqueMapping());
				updatedContactRow.setFkPartyId(editedParty);
			}
			contactRows.add(updatedContactRow);
		});
		editedParty.setMasterPartyContactList(contactRows);
				
		
		//External System Mapping(multiple)
		List<MasterPartyExternalMapping> externalMappingRows = new ArrayList<>();
		editPartyDetails.getExternalSystemMapping().forEach(data -> {
			MasterPartyExternalMapping updatedExternalMappingRow = modelMapper.map(data,MasterPartyExternalMapping.class);
			if(data.getUniqueMapping()!=null) {
				updatedExternalMappingRow.setPkPartyExternalMappingId(data.getUniqueMapping());
				updatedExternalMappingRow.setFkPartyId(editedParty);
			}
			externalMappingRows.add(updatedExternalMappingRow);
		});
		editedParty.setMasterPartyExternalMappingList(externalMappingRows);
		
		//Name in alternative language(multiple?)
		List<MasterPartyTranslation> partyTranslationRows = new ArrayList<>();
		editPartyDetails.getPartyTranslations().forEach(data -> {
			MasterPartyTranslation updatedTranslationRow = modelMapper.map(data,MasterPartyTranslation.class);
			if(data.getUniqueMapping()!=null) {
				updatedTranslationRow.setPkPartyTranslId(data.getUniqueMapping());
				updatedTranslationRow.setFkPartyId(editedParty);
			}
			partyTranslationRows.add(updatedTranslationRow);
		});
		editedParty.setMasterPartyTranslationList(partyTranslationRows);
		
		//Address in alternative language(multiple)
		List<MasterPartyAddressTranslation> addressTranslationRows = new ArrayList<>();
		editPartyDetails.getTranslatedAddress().forEach(data -> {
			MasterPartyAddressTranslation updatedAddressTranslationRow = modelMapper.map(data,MasterPartyAddressTranslation.class);
			if(data.getUniqueMapping()!=null) {
				updatedAddressTranslationRow.setPkPartyAddrTranslId(data.getUniqueMapping());
			}
			addressTranslationRows.add(updatedAddressTranslationRow);
		});
		
		//Party Type
		List<MasterPartyPartyType> partyTypeList = new ArrayList<>();
		partyTypeIds.forEach(data -> {MasterPartyType type = new MasterPartyType(data);
				partyTypeList.add(new MasterPartyPartyType(type));});
		editedParty.setMasterPartyPartyTypeList(partyTypeList);
		
		//Party Classification
		List<MasterPartyPartyClass> partyClassList = new ArrayList<>();
		partyClassificationIds.forEach(data -> {MasterPartyClassification classification = new MasterPartyClassification(data);
		partyClassList.add(new MasterPartyPartyClass(classification));});
		editedParty.setMasterPartyPartyClassList(partyClassList);
		
		//If party classification = Warehouse ,be it internal or external
			//Warehouse Location(multiple)
		if(partyClassification.contains(PartyConstants.CLASSIFICATION_WAREHOUSE)) {
			List<MasterPartyWhStockLocation> warehouseRows = new ArrayList<>();
			editPartyDetails.getWarehouseLocation().forEach(data -> {
				MasterPartyWhStockLocation updatedWarehouseRow = modelMapper.map(data,MasterPartyWhStockLocation.class);
				if(data.getUniqueMapping()!=null) {
					updatedWarehouseRow.setPkPartyWhStockLocationId(data.getUniqueMapping());
					updatedWarehouseRow.setFkPartyId(editedParty);
				}
				warehouseRows.add(updatedWarehouseRow);
			});
			editedParty.setMasterPartyWhStockLocationList(warehouseRows);
		}
		
		//Party type = Customer
			//Documents(multiple)
		if(partyType.contains(PartyConstants.TYPE_CUSTOMER)) {
			List<MasterPartyDocument> documentRows = new ArrayList<>();
			editPartyDetails.getPartyDocuments().forEach(data -> {
				MasterPartyDocument updatedDocumentRow = modelMapper.map(data,MasterPartyDocument.class);
				if(data.getUniqueMapping()!=null) {
					updatedDocumentRow.setPkPartyDocumentId(data.getUniqueMapping());
					updatedDocumentRow.setFkPartyId(editedParty);
				}
				documentRows.add(updatedDocumentRow);
			});
			editedParty.setMasterPartyDocumentList(documentRows);
		}
		
		//External 
		if(PartyConstants.FLAG_NO.equals(editPartyDetails.getPartyBasicDetails().getPartyInternalFlag())){
		
			//Default Payment Terms
			PaymentTermsDto paymentTermDetails = editPartyDetails.getPaymentTermDetails();
			if(paymentTermDetails!=null) {
				List<MasterPartyPaymentTerm> paymentTermList = new ArrayList<>();
				MasterPartyPaymentTerm updatedPaymentTermRow = modelMapper.map(paymentTermDetails,MasterPartyPaymentTerm.class);
				if(paymentTermDetails.getUniqueMapping()!=null) {
					updatedPaymentTermRow.setPkPartyPaymentTermId(paymentTermDetails.getUniqueMapping());
					updatedPaymentTermRow.setFkPartyId(editedParty);
				}
				paymentTermList.add(updatedPaymentTermRow);
				editedParty.setMasterPartyPaymentTermList(paymentTermList);
			}
			//and Party Classification = Roaster
				//Plant Details
			if(partyClassification.contains(PartyConstants.CLASSIFICATION_ROASTER)) {
				List<MasterPartyPlant> plantRows = new ArrayList<>();
				editPartyDetails.getPlantDetails().forEach(data -> {
					MasterPartyPlant updatedPlantRow = modelMapper.map(data, MasterPartyPlant.class);
					if(data.getUniqueMapping()!=null) {
						updatedPlantRow.setPkPartyPlantId(data.getUniqueMapping());
						updatedPlantRow.setFkPartyId(editedParty);
					}
					plantRows.add(updatedPlantRow);
				});
				editedParty.setMasterPartyPlantList(plantRows);
			}
			//and Party Classification = Broker
				//Broker Details
			PartyBrokerDetailsDto brokerDetails = editPartyDetails.getBrokerDetails();
			if (partyClassification.contains(PartyConstants.CLASSIFICATION_BROKER)&&brokerDetails!=null) {
				List<MasterPartyBroker> updatedBrokerList = new ArrayList<>();
				MasterPartyBroker brokerRow = modelMapper.map(brokerDetails, MasterPartyBroker.class);
				if(brokerDetails.getUniqueMapping()!=null) {
					brokerRow.setPkPartyBrokerId(brokerDetails.getUniqueMapping());
					brokerRow.setFkPartyId(editedParty);
				}
				updatedBrokerList.add(brokerRow);
				editedParty.setMasterPartyBrokerList(updatedBrokerList);
			}
			//and Party Classification = Exchange
				//Exchange Details
			if(partyClassification.contains(PartyConstants.CLASSIFICATION_EXCHANGE)) {/*
				ObjectMapper mapper = new ObjectMapper();
				String requestJson = mapper.writeValueAsString(editPartyDetails.getExchangeDetails());
				String jsonInString = requestJson.substring(1,requestJson.length()-1);
				Map postInternalServiceData = webServiceCall.postInternalServiceData("marketdata-service",PartyConstants.EXCHANGE_DETAILS_URI,jsonInString);
				System.out.println(postInternalServiceData);
			*/}
		
			//and Party type = Customer
			if(partyType.contains(PartyConstants.TYPE_CUSTOMER)) {
				//PartyGradeDefinition
				List<MasterPartyGrade> gradeList = new ArrayList<>();
				PartyGradeDefinitionDto partyGradeDefinition = editPartyDetails.getPartyGradeDefinition();
				MasterPartyGrade updatedGradeRow = modelMapper.map(partyGradeDefinition, MasterPartyGrade.class);
				if(partyGradeDefinition.getUniqueMapping()!=null) {
					updatedGradeRow.setPkPartyGradeId(partyGradeDefinition.getUniqueMapping());
					updatedGradeRow.setFkPartyId(editedParty);
				}
				//InternalGradeMapping(multiple)
				List<MasterPartyGradeAssoc> gradeAssociationRows = new ArrayList<>();
				editPartyDetails.getInternalGradeMapping().forEach(data -> {
					MasterPartyGradeAssoc updatedInternalGradeMappingRow = modelMapper.map(data, MasterPartyGradeAssoc.class);
					if(data.getUniqueMapping()!=null) {
						updatedInternalGradeMappingRow.setPkPartyGradeAssocId(data.getUniqueMapping());
					}
					gradeAssociationRows.add(updatedInternalGradeMappingRow);
				});
				updatedGradeRow.setMasterPartyGradeAssocCollection(gradeAssociationRows);
				gradeList.add(updatedGradeRow);
				editedParty.setMasterPartyGradeList(gradeList);
				//CreditLimits
				List<MasterPartyCreditLimit> creditLimitRows = new ArrayList<>();
				editPartyDetails.getCreditLimit().forEach(data -> {
					MasterPartyCreditLimit updatedCreditLimitRow = modelMapper.map(data, MasterPartyCreditLimit.class);
					if(data.getUniqueMapping()!=null) {
						updatedCreditLimitRow.setPkCreditLimitId(data.getUniqueMapping());
						updatedCreditLimitRow.setFkInternalPartyId(editedParty);
					}
					creditLimitRows.add(updatedCreditLimitRow);
				});
				editedParty.setMasterPartyCreditLimitList(creditLimitRows);
				/*//PartyLimits
				ObjectMapper mapper = new ObjectMapper();
				String jsonInString = mapper.writeValueAsString(editPartyDetails.getPartyLimit().get(0));
				//String jsonInString = requestJson.substring(1,requestJson.length()-1);
				Map postInternalServiceData = webServiceCall.postInternalServiceData("limit-service",PartyConstants.PARTY_LIMITS_URI,jsonInString );
				System.out.println(postInternalServiceData);*/
			}
			
			
		}
		//Remove this code before merging to Sprint4
		/*ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(editedParty);
		System.out.println(jsonInString);*/
		//Remove code ends
		editedParty.setCreatedBy(PartyConstants.DEV);
		editedParty.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		editedParty.setModifiedBy(PartyConstants.DEV);
		editedParty.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		repository.saveAndFlush(editedParty);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return editPartyDetails;
	}

}
