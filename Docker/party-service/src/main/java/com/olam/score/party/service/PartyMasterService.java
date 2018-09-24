package com.olam.score.party.service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.party.constants.PartyConstants;
import com.olam.score.party.controller.PartyMasterController;
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
import com.olam.score.party.domain.MasterUnit;
import com.olam.score.party.dto.ExchangeDetailsDto;
import com.olam.score.party.dto.ExternalSystemMappingDto;
import com.olam.score.party.dto.PartyBasicDetailsDto;
import com.olam.score.party.dto.PartyLimitDto;
import com.olam.score.party.dto.PartyMasterDto;
import com.olam.score.party.repository.PartyMasterRepository;
import com.olam.score.party.util.PartyMap;
import com.olam.score.party.util.PartySpecificationsBuilder;
import com.olam.score.party.util.PartyUtil;

@Service
public class PartyMasterService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PartyMasterRepository repository;

	@Autowired
	private ListViewUtil listview;

	@Autowired
	private WebServiceCallUtil webServiceCall;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PartyUtil partyUtil;

	@Autowired
	private PartyMap partyMap;

	@Transactional
	public PartyMasterDto create(PartyMasterDto partyDetails) {

			MasterParty party;
			party = repository.findByPartyName(partyDetails.getPartyBasicDetails().getPartyName());
			if (party != null) {
				throw new ScoreBaseException(party.getPkPartyId(), "Already exist in database", HttpStatus.CONFLICT);
			}
			// Basic Details
			party = modelMapper.map(partyDetails.getPartyBasicDetails(), MasterParty.class);

			// Set Party Status
			Map<Integer, String> statusMap = webServiceCall.getAllStatus();
			Optional<Integer> firstKey = statusMap.entrySet().stream()
					.filter(entry -> Objects.equals(entry.getValue(), partyDetails.getPartyBasicDetails().getStatus()))
					.map(Map.Entry::getKey).findFirst();
			if (firstKey.isPresent()) {
				party.setFkStatusId(firstKey.get());
			}

			List<Integer> partyTypeIds = partyDetails.getPartyBasicDetails().getPartyTypeIds();
			List<String> partyType = partyUtil.getPartyTypeValues(partyTypeIds);

			List<Integer> partyClassificationIds = partyDetails.getPartyBasicDetails().getPartyClassificationIds();
			List<String> partyClassification = partyUtil.getPartyClassificationValues(partyClassificationIds);

			// PartyRating
			if (partyDetails.getPartyBasicDetails().getPartyRKey() != null) {
				MasterPartyRatingRef partyRating = new MasterPartyRatingRef(
						partyDetails.getPartyBasicDetails().getPartyRKey());
				party.setFkPartyRatingRefId(partyRating);
			}

			// Address
			List<MasterPartyAddress> addressRows = new ArrayList<>();
			Optional.of(partyDetails.getPartyAddress()).get().forEach(data -> {
				addressRows.add(modelMapper.map(data, MasterPartyAddress.class));
			});
			party.setMasterPartyAddressList(addressRows);

			// Bank Accounts
			List<MasterPartyBank> bankRows = new ArrayList<>();
			partyDetails.getPartyBankAccountDetails().forEach(data -> {
				bankRows.add(modelMapper.map(data, MasterPartyBank.class));
			});
			party.setMasterPartyBankList(bankRows);

			// Contacts(not required if external party is exchange)
			List<MasterPartyContact> contactRows = new ArrayList<>();
			partyDetails.getPartyContacts().forEach(data -> {
				contactRows.add(modelMapper.map(data, MasterPartyContact.class));
			});
			party.setMasterPartyContactList(contactRows);

			// External System Mapping(multiple)
			List<MasterPartyExternalMapping> externalMappingRows = new ArrayList<>();
			TypeMap<ExternalSystemMappingDto, MasterPartyExternalMapping> typeMap = modelMapper
					.getTypeMap(ExternalSystemMappingDto.class, MasterPartyExternalMapping.class);
			partyDetails.getExternalSystemMapping().forEach(data -> {
				// typeMap.addMappings( modelMapper ->
				// modelMapper.skip(MasterPartyExternalMapping::setPkPartyExternalMappingId));
				externalMappingRows.add(modelMapper.map(data, MasterPartyExternalMapping.class));
			});
			party.setMasterPartyExternalMappingList(externalMappingRows);

			// Name in alternative language(multiple?)
			List<MasterPartyTranslation> partyTranslationRows = new ArrayList<>();
			partyDetails.getPartyTranslations().forEach(data -> {
				partyTranslationRows.add(modelMapper.map(data, MasterPartyTranslation.class));
			});
			party.setMasterPartyTranslationList(partyTranslationRows);

			// Address in alternative language(multiple)
			List<MasterPartyAddressTranslation> addressTranslationRows = new ArrayList<>();
			partyDetails.getTranslatedAddress().forEach(data -> {
				addressTranslationRows.add(modelMapper.map(data, MasterPartyAddressTranslation.class));
			});

			// Party Type
			List<MasterPartyPartyType> partyTypeList = new ArrayList<>();
			partyTypeIds.forEach(data -> {
				MasterPartyType type = new MasterPartyType(data);
				partyTypeList.add(new MasterPartyPartyType(type));
			});
			party.setMasterPartyPartyTypeList(partyTypeList);

			// Party Classification
			List<MasterPartyPartyClass> partyClassList = new ArrayList<>();
			partyClassificationIds.forEach(data -> {
				MasterPartyClassification classification = new MasterPartyClassification(data);
				partyClassList.add(new MasterPartyPartyClass(classification));
			});
			party.setMasterPartyPartyClassList(partyClassList);

			// If party classification = Warehouse ,be it internal or external
			// Warehouse Location(multiple)
			if (partyClassification.contains(PartyConstants.CLASSIFICATION_WAREHOUSE)) {
				List<MasterPartyWhStockLocation> warehouseRows = new ArrayList<>();
				partyDetails.getWarehouseLocation().forEach(data -> {
					warehouseRows.add(modelMapper.map(data, MasterPartyWhStockLocation.class));
				});
				party.setMasterPartyWhStockLocationList(warehouseRows);
			}

			// Party type = Customer
			// Documents(multiple)
			if (partyType.contains(PartyConstants.TYPE_CUSTOMER)) {
				List<MasterPartyDocument> documentRows = new ArrayList<>();
				partyDetails.getPartyDocuments().forEach(data -> {
					documentRows.add(modelMapper.map(data, MasterPartyDocument.class));
				});
				party.setMasterPartyDocumentList(documentRows);
			}

			// External
			if (PartyConstants.FLAG_NO.equals(partyDetails.getPartyBasicDetails().getPartyInternalFlag())) {

				// Default Payment Terms
				if (partyDetails.getPaymentTermDetails() != null) {
					List<MasterPartyPaymentTerm> paymentTermList = new ArrayList<>();
					MasterPartyPaymentTerm paymentTermRow = modelMapper.map(partyDetails.getPaymentTermDetails(),
							MasterPartyPaymentTerm.class);
					paymentTermList.add(paymentTermRow);
					party.setMasterPartyPaymentTermList(paymentTermList);
				}
				// and Party Classification = Roaster
				// Plant Details
				if (partyClassification.contains(PartyConstants.CLASSIFICATION_ROASTER)) {
					List<MasterPartyPlant> plantRows = new ArrayList<>();
					partyDetails.getPlantDetails().forEach(data -> {
						plantRows.add(modelMapper.map(data, MasterPartyPlant.class));
					});
					party.setMasterPartyPlantList(plantRows);
				}
				// and Party Classification = Broker
				// Broker Details
				if (partyClassification.contains(PartyConstants.CLASSIFICATION_BROKER)
						&& partyDetails.getBrokerDetails() != null) {
					List<MasterPartyBroker> brokerList = new ArrayList<>();
					MasterPartyBroker brokerRow = modelMapper.map(partyDetails.getBrokerDetails(),
							MasterPartyBroker.class);
					brokerList.add(brokerRow);
					party.setMasterPartyBrokerList(brokerList);
				}

				// and Party type = Customer
				if (partyType.contains(PartyConstants.TYPE_CUSTOMER)) {
					// PartyGradeDefinition
					List<MasterPartyGrade> gradeList = new ArrayList<>();
					MasterPartyGrade gradeRow = modelMapper.map(partyDetails.getPartyGradeDefinition(),
							MasterPartyGrade.class);
					// InternalGradeMapping(multiple)
					List<MasterPartyGradeAssoc> gradeAssociationRows = new ArrayList<>();
					partyDetails.getInternalGradeMapping().forEach(data -> {
						gradeAssociationRows.add(modelMapper.map(data, MasterPartyGradeAssoc.class));
					});
					gradeRow.setMasterPartyGradeAssocCollection(gradeAssociationRows);
					gradeList.add(gradeRow);
					party.setMasterPartyGradeList(gradeList);
					// CreditLimits
					List<MasterPartyCreditLimit> creditLimitRows = new ArrayList<>();
					partyDetails.getCreditLimit().forEach(data -> {
						MasterPartyCreditLimit creditLimitRow = modelMapper.map(data, MasterPartyCreditLimit.class);
						if(data.getShortIntPar()!=null) {
							creditLimitRow.setFkInternalPartyId(new MasterParty(data.getShortIntPar()));
						}
						if(data.getShortUn()!=null) {
							creditLimitRow.setFkUnitId(new MasterUnit(data.getShortUn()));
						}
						creditLimitRows.add(creditLimitRow);
					});
					party.setMasterPartyCreditLimitList(creditLimitRows);

				}

			}
			// Remove this code before merging to Sprint4
			ObjectMapper finalMapper = new ObjectMapper();
			//String jsonFinalInString = finalMapper.writerWithDefaultPrettyPrinter().writeValueAsString(party);
			//System.out.println(jsonFinalInString);
			// Remove code ends
			party.setCreatedBy(PartyConstants.DEV);
			party.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			party.setModifiedBy(PartyConstants.DEV);
			party.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			MasterParty savedParty = repository.saveAndFlush(party);

			
			 //Making webservice call for partylimits and exchange after saving the party
			 //to the repository to get the pkPartyId; //and Party Classification = Exchange
			 //Exchange Details
			try {
				 if(partyClassification.contains(PartyConstants.CLASSIFICATION_EXCHANGE)) {
					 ObjectMapper mapper = new ObjectMapper();
					 List<ExchangeDetailsDto> exchangeDetails = partyDetails.getExchangeDetails();
					 if(exchangeDetails.size()>0) {
						 ExchangeDetailsDto exchangeDetailsDto = exchangeDetails.get(0);
						 exchangeDetailsDto.setPartyNumber(savedParty.getPkPartyId());
						 String requestJson = mapper.writeValueAsString(exchangeDetailsDto); //String
						 //String jsonInString = requestJson.substring(1,requestJson.length()-1); 
						 System.out.println("Exchange Request:" + requestJson);
						 Map postInternalServiceData = webServiceCall.postInternalServiceData("marketdata-service",PartyConstants.EXCHANGE_DETAILS_URI,requestJson);
						 System.out.println("Exchange Response :" +postInternalServiceData);
					 }
				 }
				 //External if
				 if(PartyConstants.FLAG_NO.equals(partyDetails.getPartyBasicDetails().getPartyInternalFlag()))
				 { 
					 if(partyType.contains(PartyConstants.TYPE_CUSTOMER)) { 
						 // PartyLimits
						 ObjectMapper mapper = new ObjectMapper(); 
						 List<PartyLimitDto> partyLimit = partyDetails.getPartyLimit();
						 if(partyLimit.size()>0) {
							 PartyLimitDto partyLimitDto = partyLimit.get(0);
							 partyLimitDto.setInternalPartyId(savedParty.getPkPartyId());
							String jsonInString = mapper.writeValueAsString(partyLimitDto); // String
							System.out.println("Limits Request:" + jsonInString);
							 //String jsonInString = requestJson.substring(1,requestJson.length()-1); 
							 Map postInternalServiceData = webServiceCall.postInternalServiceData("limit-service",PartyConstants.PARTY_LIMITS_URI, jsonInString);
							 System.out.println("Party Limits Response :" + postInternalServiceData);
						 }
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			 

		return partyDetails;
	}

	@Transactional
	public List<PartyMasterDto> readAll() {
		ViewDto view = getViewDetails(PartyMasterController.class);
		List<PartyMasterDto> newList = new ArrayList<>();
		try {
			List<String> filterList = view.getFiltersArray();
			PartySpecificationsBuilder builder = new PartySpecificationsBuilder();
			for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
				builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
						filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
			}
			Specification<MasterParty> spec = builder.build();
			Pageable pageRequest = ListViewUtil.createPageRequest(view);
			log.info("===Pageable object created from view object===");
			List<MasterParty> allParties = repository.findAll(spec, pageRequest).getContent();
			Map<Integer, String> statusMap = webServiceCall.getAllStatus();
			// Map<Integer, String> marketingDeskMap = serviceCall.
			for (MasterParty party : allParties) {
				PartyMasterDto partyDto = new PartyMasterDto();
				partyDto.setPkPartyId(party.getPkPartyId());
				PartyBasicDetailsDto partyBasicDetails = new PartyBasicDetailsDto();
				partyBasicDetails.setPartyInternalFlag(party.getPartyInternalFlag());
				partyBasicDetails.setPartyName(party.getPartyName());
				partyBasicDetails.setFkGeoCountryId(party.getFkGeoCountryId());
				// Need to make a service for getting geo
				Map<Integer, String> geoMapData = partyMap.geoMapData("location-service");
				partyBasicDetails.setGeoCName(geoMapData.get(party.getFkGeoCountryId()));
				partyBasicDetails.setFkMarketingDeskId(party.getFkMarketingDeskId());
				Map<Integer, String> marketingDeskMapData = partyMap.marketDesk("reference-service");
				partyBasicDetails.setMarketingDesk(marketingDeskMapData.get(party.getFkMarketingDeskId()));

				partyBasicDetails.setFkStatusId(party.getFkStatusId());
				partyBasicDetails.setStatus(statusMap.get(party.getFkStatusId()));
				partyDto.setPartyBasicDetails(partyBasicDetails);
				newList.add(partyDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		Class<PartyMasterDto> uomDto = PartyMasterDto.class;
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

	@Transactional
	public PartyMasterDto readById(Integer partyId) {
		// PartyMasterDto dto = new PartyMasterDto();
		MasterParty party = repository.findOne(partyId);
		// Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		Map<String, String> serviceNames = new HashMap<>();
		serviceNames.put("locationName", "location-service");
		serviceNames.put("referenceName", "reference-service");
		serviceNames.put("marketdataName", "marketdata-service");
		serviceNames.put("partyName", "party-service");
		serviceNames.put("financeName", "finance-service");
		serviceNames.put("limitName", "limit-service");
		serviceNames.put("productName", "product-service");
		serviceNames.put("termsName", "terms-service");
		PartyMasterDto dto = partyUtil.convertToDto(party, serviceNames);

		return dto;
	}

}
