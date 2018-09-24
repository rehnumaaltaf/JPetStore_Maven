package com.olam.score.party.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.party.constants.PartyConstants;
import com.olam.score.party.domain.MasterAccountType;
import com.olam.score.party.domain.MasterDepartment;
import com.olam.score.party.domain.MasterParty;
import com.olam.score.party.domain.MasterPartyBroker;
import com.olam.score.party.domain.MasterPartyClassification;
import com.olam.score.party.domain.MasterPartyGrade;
import com.olam.score.party.domain.MasterPartyPaymentTerm;
import com.olam.score.party.domain.MasterPartyRatingRef;
import com.olam.score.party.domain.MasterPartyType;
import com.olam.score.party.dto.AddressDto;
import com.olam.score.party.dto.ContactsDto;
import com.olam.score.party.dto.DocumentsDto;
import com.olam.score.party.dto.ExchangeDetailsDto;
import com.olam.score.party.dto.ExternalSystemMappingDto;
import com.olam.score.party.dto.InternalGradeMappingDto;
import com.olam.score.party.dto.PartyBankAccountDetailsDto;
import com.olam.score.party.dto.PartyBasicDetailsDto;
import com.olam.score.party.dto.PartyBasicTranslationDto;
import com.olam.score.party.dto.PartyBrokerDetailsDto;
import com.olam.score.party.dto.PartyCreditLimitDto;
import com.olam.score.party.dto.PartyGradeDefinitionDto;
import com.olam.score.party.dto.PartyLimitDto;
import com.olam.score.party.dto.PartyMasterDto;
import com.olam.score.party.dto.OriginDto;
import com.olam.score.party.dto.PartyPlantDetailsDto;
import com.olam.score.party.dto.PartyTranslatedAddress;
import com.olam.score.party.dto.PartyWarehouseLocationDto;
import com.olam.score.party.dto.PaymentTermsDto;
import com.olam.score.party.repository.MasterAccountTypeRepository;
import com.olam.score.party.repository.MasterUnitRepository;
import com.olam.score.party.repository.PartyClassificationRepository;
import com.olam.score.party.repository.PartyDepartmentRepository;
import com.olam.score.party.repository.PartyMasterRepository;
import com.olam.score.party.repository.PartyRatingRepository;
import com.olam.score.party.repository.PartyTypeRepository;


@Component
public class PartyUtil {

	@Autowired
	private PartyTypeRepository partyTypeRepository;

	@Autowired
	private PartyClassificationRepository partyClassificationRepository;

	@Autowired
	private PartyMasterRepository repository;

	@Autowired
	private PartyRatingRepository partyRatingRepository;

	@Autowired
	private	MasterAccountTypeRepository partyAccountTypeRepository;

	@Autowired
	private PartyDepartmentRepository departmentRepository;

	@Autowired
	private MasterUnitRepository unitRepository;

	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Autowired
	private PartyMap partyMap;

	public PartyUtil(){}

	@SuppressWarnings({ "unlikely-arg-type", "unused", "rawtypes" })
	@Transactional
	public PartyMasterDto convertToDto(MasterParty party, Map<String,String> serviceNames) 
	{
		//Foreign key mappings for all the services
		//Map<Integer,String> countryMap = partyMap.geoMapData(locationName);
		Map<Integer,String> countryMap = partyMap.geoMapData(serviceNames.get("locationName"));
		Map<Integer,String> marketDesk = partyMap.marketDesk(serviceNames.get("referenceName"));
		Map<Integer,String> currency = partyMap.masterCurrency(serviceNames.get("referenceName"));
		Map<Integer,String> uomMapData = partyMap.commisionUomData(serviceNames.get("referenceName"));
		Map<Integer,String> docMapData = partyMap.documentRefMap(serviceNames.get("referenceName"))
				;
		Map<Integer,String> docTypeMapData = partyMap.documentTypeMapData(serviceNames.get("referenceName"));
		Map<Integer,String> warehouseMap = partyMap.wareHouseMap(serviceNames.get("locationName"));
		Map<Integer,String> productMap = partyMap.productDetails(serviceNames.get("productName"));
		Map<Integer,String> originMap = partyMap.originValues(serviceNames.get("productName"));
		Map<Integer,String> externalMapping = partyMap.partyExternalMapping(serviceNames.get("financeName"));
		Map<Integer,String> languageMapData = partyMap.languageTranslationMap(serviceNames.get("referenceName"));
		Map<Integer,String> addressMap =  partyMap.addressMap(serviceNames.get("locationName"));
		Map<Integer,String>  masterLimit = partyMap.limitAlertLevelValues(serviceNames.get("limitName"));
		Map<Integer,String>  termsMap = partyMap.paymentTermData(serviceNames.get("termsName"));
		Map<Integer,String>  productGrade = partyMap.masterGrade(serviceNames.get("productName"));
		Map<Integer,String>  holidayCalenderMap = partyMap.holidayCalender(serviceNames.get("referenceName"));

		Map<Integer,String> unitMap = new HashMap<Integer,String>();
		unitRepository.findAll().forEach(unit -> {
			unitMap.put(unit.getPkUnitId(),unit.getUnitName());
		});


		ObjectMapper objMapper = new ObjectMapper();
		PartyMasterDto partyMasterDto = new PartyMasterDto();		
		ModelMapper modelMapper = new ModelMapper();

		//Party Basic Details
		partyMasterDto.setPkPartyId(party.getPkPartyId());

		PartyBasicDetailsDto basicDto = modelMapper.map(party, PartyBasicDetailsDto.class);
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		basicDto.setStatus(statusMap.get(party.getFkStatusId()));
		//To set party rating records in basic details
		if(Optional.ofNullable(party.getFkPartyRatingRefId()).isPresent()) 
		{
			MasterPartyRatingRef partyRating = partyRatingRepository.findOne(party.getFkPartyRatingRefId().getPkPartyRateRefId());
			basicDto.setPartyRShort(partyRating.getRCode());
			basicDto.setPartyRating(partyRating.getPartyRatingName());
		}

		basicDto.setPartyCode(party.getPartyCode());
		basicDto.setIsGroupParty(party.getIsGroupParty());	

		if(Optional.ofNullable(party.getFkParentPartyId()).isPresent())
		{
			basicDto.setParentParty(repository.findOne(party.getFkParentPartyId()).getPartyName());
		}
		basicDto.setGeoCName(countryMap.get(party.getFkGeoCountryId()));
		basicDto.setMarketingDesk(marketDesk.get(party.getFkMarketingDeskId()));
		basicDto.setFunctionalCurrency(currency.get(party.getFkFunctionalCurrencyId()));
		basicDto.setOperationalCurrency(currency.get(party.getFkOperationalCurrencyId()));
		basicDto.setDefaultReportingUOM(uomMapData.get(party.getFkDefaultReportingUomId()));

		partyMasterDto.setPartyBasicDetails(basicDto);

		//Documents mapping from reference
		//Documents(multiple)
		List<DocumentsDto> documentList =  new ArrayList<>();

		party.getMasterPartyDocumentList().forEach(data -> {
			DocumentsDto mappedData = modelMapper.map(data, DocumentsDto.class);
			mappedData.setUniqueMapping(data.getPkPartyDocumentId());
			mappedData.setDName(docMapData.get(mappedData.getFkDocumentRefId()));
			mappedData.setDocumentTypeName(docTypeMapData.get(mappedData.getFkDocumentTypeRefId()));
			documentList.add(mappedData);
		});
		partyMasterDto.setPartyDocuments(documentList);


		//If party classification = Warehouse ,be it internal or external
		//Warehouse Location(multiple)	

		List<PartyWarehouseLocationDto> whLocationList = new ArrayList<>();
		party.getMasterPartyWhStockLocationList().forEach(data -> {
			PartyWarehouseLocationDto warehouseDto = modelMapper.map(data, PartyWarehouseLocationDto.class);
			warehouseDto.setUniqueMapping(data.getPkPartyWhStockLocationId());
			warehouseDto.setPortLocationCode(warehouseMap.get(warehouseDto.getFkLocId()));
			warehouseDto.setCountry(countryMap.get(warehouseDto.getFkGeoId()));
			whLocationList.add(warehouseDto);
		});
		partyMasterDto.setWarehouseLocation(whLocationList);


		//Party Address mapping from location & party
		List<AddressDto> addressDtoList = new ArrayList<>();

		party.getMasterPartyAddressList().forEach(data -> {
			AddressDto addrDto = modelMapper.map(data, AddressDto.class);
			addrDto.setUniqueMapping(data.getPkPartyAddrId());
			addrDto.setAddressType(addressMap.get(addrDto.getFkAddrTypeRefId()));
			addrDto.setCountry(countryMap.get(addrDto.getFkGeoCountryId()));
			addressDtoList.add(addrDto);
		});
		partyMasterDto.setPartyAddress(addressDtoList);


		//Bank Accounts mapping from location & party
		List<PartyBankAccountDetailsDto> bankDtoList = new ArrayList<>();

		party.getMasterPartyBankList().forEach(data -> {
			PartyBankAccountDetailsDto bankDto = modelMapper.map(data, PartyBankAccountDetailsDto.class);
			if(Optional.ofNullable(data.getFkAccountTypeId()).isPresent()) {
				MasterAccountType acn = partyAccountTypeRepository.findOne(data.getFkAccountTypeId().getPkAccountTypeId());
				bankDto.setAccountTypeCode(acn.getAccountTypeCode());
				bankDto.setAccountTypeName(acn.getAccountTypeName());
			}

			bankDto.setUniqueMapping(data.getPkPartyBankId());
			bankDto.setCountry(countryMap.get(bankDto.getFkBankBranchCountryGeoId()));
			bankDtoList.add(bankDto);
		});
		partyMasterDto.setPartyBankAccountDetails(bankDtoList);


		//Contacts(not required if external party is exchange)
		//contacts mapping from party
		List<ContactsDto> contactList = new ArrayList<>();

		party.getMasterPartyContactList().forEach(data -> {
			ContactsDto contactDto = modelMapper.map(data, ContactsDto.class);

			if(Optional.ofNullable(contactDto.getPkDeptId()).isPresent()) {
				MasterDepartment dept = departmentRepository.findOne(contactDto.getPkDeptId());
				contactDto.setDeptCode(dept.getDeptCode());
			}
			contactDto.setProductName(productMap.get(contactDto.getProdId()));
			contactDto.setUniqueMapping(data.getPkPartyContactId());
			contactList.add(contactDto);
		});
		partyMasterDto.setPartyContacts(contactList);


		//External System Mapping(multiple)
		//External System Mapping from finance
		List<ExternalSystemMappingDto> externalMappingList = new ArrayList<>();

		party.getMasterPartyExternalMappingList().forEach(data -> {
			ExternalSystemMappingDto externalMapDto = modelMapper.map(data, ExternalSystemMappingDto.class);
			externalMapDto.setUniqueMapping(data.getPkPartyExternalMappingId());
			externalMapDto.setExternalSystemRefName(externalMapping.get(externalMapDto.getFkExternalSystemRefId()));
			externalMappingList.add(externalMapDto);
		});
		partyMasterDto.setExternalSystemMapping(externalMappingList);


		//Name in alternative language(multiple?)
		//Language translation mapping from reference
		List<PartyBasicTranslationDto> translatedList = new ArrayList<>();

		party.getMasterPartyTranslationList().forEach(data -> {
			PartyBasicTranslationDto langDto = modelMapper.map(data, PartyBasicTranslationDto.class);
			langDto.setUniqueMapping(data.getPkPartyTranslId());
			langDto.setLanguage(languageMapData.get(data.getFkLanguageId()));
			translatedList.add(langDto);
		});
		partyMasterDto.setPartyTranslations(translatedList);


		//Address in alternative language(multiple)
		//Address translation mapping from party
		List<PartyTranslatedAddress> translatedAddressList = new ArrayList<>();

		party.getMasterPartyAddressList().forEach(address -> address.getMasterPartyAddressTranslationCollection().forEach(data -> {
			PartyTranslatedAddress translationDto = modelMapper.map(data, PartyTranslatedAddress.class);
			translationDto.setUniqueMapping(data.getPkPartyAddrTranslId());
			translationDto.setAddressName(addressMap.get(translationDto.getAddressId())); //check
			translationDto.setLanguage(languageMapData.get(translationDto.getLanguageId())); //check
			translatedAddressList.add(translationDto);
		}));

		partyMasterDto.setTranslatedAddress(translatedAddressList);


		//Party classification mapping from party
		List<String> partyClassificationNames = new ArrayList<>();
		List<Integer> partyClassificationIds = new ArrayList<>();
		party.getMasterPartyPartyClassList().forEach(data -> {partyClassificationNames.add
			(data.getFkPartyClassId().getPartyClassName());});
		party.getMasterPartyPartyClassList().forEach(data -> {partyClassificationIds.add
			(data.getFkPartyClassId().getPkPartyClassId());});
		partyMasterDto.getPartyBasicDetails().setPartyClassifications(partyClassificationNames);
		partyMasterDto.getPartyBasicDetails().setPartyClassificationIds(partyClassificationIds);

		//Party Type Classification from Party
		List<String> partyTypeNames = new ArrayList<>();
		List<Integer> partyTypeIds = new ArrayList<>();

		party.getMasterPartyPartyTypeList().forEach(data -> {partyTypeNames.add
			(data.getFkPartyTypeId().getPartyTypeName());});
		party.getMasterPartyPartyTypeList().forEach(data -> {partyTypeIds.add
			(data.getFkPartyTypeId().getPkPartyTypeId());});
		partyMasterDto.getPartyBasicDetails().setPartyTypes(partyTypeNames);
		partyMasterDto.getPartyBasicDetails().setPartyTypeIds(partyTypeIds);

		//and Party Classification = Roaster
		//Plant Details
		if(partyClassificationNames.contains(PartyConstants.CLASSIFICATION_ROASTER)) {
			List<PartyPlantDetailsDto> plantList = new ArrayList<>();
			party.getMasterPartyPlantList().forEach(data -> {
				PartyPlantDetailsDto plantDto = modelMapper.map(data, PartyPlantDetailsDto.class);
				plantDto.setUniqueMapping(data.getPkPartyPlantId());
				plantDto.setCountry(countryMap.get(data.getFkGeoId()));
				plantList.add(plantDto);
			});
			partyMasterDto.setPlantDetails(plantList);
		}

		//and Party Classification = Broker
		//Broker Details mapping from reference
		if(party.getMasterPartyBrokerList().size()>0) {
			MasterPartyBroker brokerRow = party.getMasterPartyBrokerList().get(0);
			if(partyClassificationNames.contains(PartyConstants.CLASSIFICATION_BROKER)&& Optional.ofNullable(brokerRow).isPresent()) {
				PartyBrokerDetailsDto broker = modelMapper.map(brokerRow, PartyBrokerDetailsDto.class);
				broker.setUniqueMapping(brokerRow.getPkPartyBrokerId());
				broker.setCommissionUOM(uomMapData.get(brokerRow.getFkCommissionUomId()));
				partyMasterDto.setBrokerDetails(broker);
			}
		}
		


		//Party type = Customer
		//External 
		//Default Payment Terms from party
		if(PartyConstants.FLAG_NO.equals(party.getPartyInternalFlag())) {
			if(party.getMasterPartyPaymentTermList().size()>0) {
				MasterPartyPaymentTerm paymentTermRow = party.getMasterPartyPaymentTermList().get(0);
				if(Optional.ofNullable(paymentTermRow).isPresent()) {
					PaymentTermsDto paymentTerm = modelMapper.map(paymentTermRow, PaymentTermsDto.class);
					paymentTerm.setUniqueMapping(paymentTermRow.getPkPartyPaymentTermId());
					paymentTerm.setPaymentTerm(termsMap.get(paymentTerm.getPaymentTermId()));
					partyMasterDto.setPaymentTermDetails(paymentTerm);
				}
			}
			//and Party Classification = Exchange
			//Exchange Details
			if(partyClassificationNames.contains(PartyConstants.CLASSIFICATION_EXCHANGE)) //check for DTO Objs
			{
				//Need to get this value from other service calls party.get
				List<ExchangeDetailsDto> exchangeDetailList = new ArrayList<>();
				ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(serviceNames.get("marketdataName") , PartyConstants.EXCHANGE_DETAILS_URI+party.getPkPartyId());

				ExchangeDetailsDto exchangeDetail = objMapper.convertValue(serviceCall.getBody().getBody(), new TypeReference<ExchangeDetailsDto>(){});

				exchangeDetail.setProduct(productMap.get(exchangeDetail.getProductUnique()));
				exchangeDetail.setLotOrContractSizeName(uomMapData.get(exchangeDetail.getLotOrContractSize()));
				exchangeDetail.setHolidayCalendarText(holidayCalenderMap.get(exchangeDetail.getHolidayCalendar()));
				//trading calender drop down details has to be added

				exchangeDetail.setPriceQuotation(currency.get(exchangeDetail.getPriceQuotationCurrency()));
				exchangeDetail.setPriceQuotationUomKey(uomMapData.get(exchangeDetail.getPriceQuotationUoM())); //key is for string value (i.e price quotataion UOm name) to be changed in UOM DTO & Domain
				exchangeDetail.setMinimumPriceMovementCurrency(currency.get(exchangeDetail.getMinimumPriceMovementCurrencyId()));
				exchangeDetail.setMinimumPriceMovementUoM(uomMapData.get(exchangeDetail.getMinimumPriceMovementUoMId()));

				exchangeDetailList.add(exchangeDetail);
				partyMasterDto.setExchangeDetails(exchangeDetailList);
			}


			//and Party type = Customer
			//PartyGradeDefinition
			if(party.getMasterPartyGradeList().size()>0) {
				MasterPartyGrade gradeDefinitionRow = party.getMasterPartyGradeList().get(0);
				if (partyTypeNames.contains(PartyConstants.TYPE_CUSTOMER)&& Optional.ofNullable(gradeDefinitionRow).isPresent()) {
					PartyGradeDefinitionDto gradeDefinition = modelMapper.map(gradeDefinitionRow,
							PartyGradeDefinitionDto.class);
					gradeDefinition.setProductName(productMap.get(gradeDefinitionRow.getFkProdId()));
					gradeDefinition.setUniqueMapping(gradeDefinitionRow.getPkPartyGradeId());
					partyMasterDto.setPartyGradeDefinition(gradeDefinition);

					List<InternalGradeMappingDto> internalMappingList = new ArrayList<>();
					gradeDefinitionRow.getMasterPartyGradeAssocCollection().forEach(data -> {
						InternalGradeMappingDto internalMappingDto = modelMapper.map(data, InternalGradeMappingDto.class);
						internalMappingDto.setUniqueMapping(data.getPkPartyGradeAssocId());
						internalMappingDto.setOriginName(originMap.get(data.getFkOriginId()));
						internalMappingDto.setInternalGradeName(productGrade.get(data.getFkGradeId()));
						internalMappingList.add(internalMappingDto);
					});
					partyMasterDto.setInternalGradeMapping(internalMappingList);
				}
			}
			
		}


		//CreditLimits mapping from party, limit
		List<PartyCreditLimitDto> creditLimitList = new ArrayList<>();

		party.getMasterPartyCreditLimitList().forEach(data -> {
			PartyCreditLimitDto creditDto = modelMapper.map(data, PartyCreditLimitDto.class);
			if(data.getFkUnitId()!=null) {
				creditDto.setShortUn(data.getFkUnitId().getPkUnitId());
				creditDto.setUnitName(unitMap.get(data.getFkUnitId()));
			}
			if(data.getFkInternalPartyId()!=null) {
				creditDto.setInternalPartyCode(data.getFkInternalPartyId().getPartyCode());
				creditDto.setInternalPartyName(data.getFkInternalPartyId().getPartyName());
			}
			creditDto.setProduct(productMap.get(data.getFkProd()));
			creditDto.setLimitAlertLevelName(masterLimit.get(data.getFkLimitAlertLevel()));
			creditDto.setCurrency(currency.get(data.getFkCurrency()));
			creditLimitList.add(creditDto);
		});
		partyMasterDto.setCreditLimit(creditLimitList);

		//PartyLimits mapping from party //check for DTO objs
		ResponseEntity<ResponseData> serviceCall =  webServiceCall.internalServiceCall("limit-service" , PartyConstants.PARTY_LIMITS_URI+party.getPkPartyId());
		List<PartyLimitDto> partyLimitsList = new ArrayList<>();

		PartyLimitDto partyLimit = objMapper.convertValue(serviceCall.getBody().getBody(), new TypeReference<PartyLimitDto>(){});
		
		if(partyLimit!=null) {
			partyLimit.setUnit(unitMap.get(partyLimit.getUnitId()));
			partyLimit.setProduct(productMap.get(partyLimit.getFkProdId()));		
			partyLimit.setLimitAlertLevelName(masterLimit.get(partyLimit.getFkLimitAlertLevelKey()));
			partyLimit.setCurrency(currency.get(partyLimit.getCurrUnique()));
			partyLimit.setUom(uomMapData.get(partyLimit.getUomId()));
			partyLimit.setLimitOnName(masterLimit.get(partyLimit.getLimitOnId()));
			partyLimitsList.add(partyLimit);
		}
		partyMasterDto.setPartyLimit(partyLimitsList);

		return partyMasterDto;
	}

	public List<String> getPartyTypeValues(List<Integer> partyTypeIds) {
		// TODO Auto-generated method stub
		// Write a service to get the PartyTypeValues
		List<MasterPartyType> partyTypes = partyTypeRepository.findAll();
		List<String> partyTypeList = new ArrayList<>();
		partyTypes.forEach(data -> {
			partyTypeList.add(data.getPartyTypeName());
		});
		return partyTypeList;
	}

	public List<String> getPartyClassificationValues(List<Integer> partyClassificationIds) {
		// TODO Auto-generated method stub
		List<MasterPartyClassification> partyClassifications = partyClassificationRepository.findAll();
		List<String> partyClassificationList = new ArrayList<>();
		partyClassifications.forEach(data -> {
			partyClassificationList.add(data.getPartyClassName());
		});
		return partyClassificationList;
	}

}
