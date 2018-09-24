package com.olam.score.party.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public @Data class PartyMasterDto {
	
	
	private Integer pkPartyId;
	//Basic Details
	private PartyBasicDetailsDto partyBasicDetails = new PartyBasicDetailsDto();

	//Address(Multiple)
	private List<AddressDto> partyAddress = new ArrayList<>() ;
	
	//Bank Account Details(Multiple)
	private List<PartyBankAccountDetailsDto> partyBankAccountDetails = new ArrayList<>();
	
	//Contacts(Multiple)
	private List<ContactsDto> partyContacts = new ArrayList<>();;

	//	Party Grade Definition and Internal Grade Mapping(Multiple)
	private PartyGradeDefinitionDto partyGradeDefinition= new PartyGradeDefinitionDto();;
	private List<InternalGradeMappingDto> internalGradeMapping = new ArrayList<>(); 
	
	//	Default Payment Terms
	private PaymentTermsDto paymentTermDetails = new PaymentTermsDto();
	
	//	Plant Details (Multiple)
	private List<PartyPlantDetailsDto> plantDetails = new ArrayList<>();
	
	//	External System Mapping(Multiple)
	private List<ExternalSystemMappingDto> externalSystemMapping = new ArrayList<>() ;
	
	//	Credit Limit (Multiple)
	private List<PartyCreditLimitDto> creditLimit = new ArrayList<>() ;
	
	//	Party Limits (Multiple)
	private List<PartyLimitDto> partyLimit = new ArrayList<>() ;
	
	//	Name in Alternate Language(multiple)
	//private PartyBasicTranslationDto partyTranslation;//Check regarding this..make it as class for now
	private List<PartyBasicTranslationDto> partyTranslations = new ArrayList<>();
	
	//	Address in Alternative Language(multiple)
	private List<PartyTranslatedAddress> translatedAddress = new ArrayList<>() ;
	
	//	Warehouse Location (Multiple)
	private List<PartyWarehouseLocationDto> warehouseLocation = new ArrayList<>()  ; 
	
	//	Exchange Details (Multiple)
	private List<ExchangeDetailsDto> exchangeDetails  = new ArrayList<>();
	
	//	Broker Details
	private PartyBrokerDetailsDto brokerDetails= new PartyBrokerDetailsDto();
	
	//	Documents (Multiple)
	private List<DocumentsDto> partyDocuments = new ArrayList<>() ;
	
	


}
