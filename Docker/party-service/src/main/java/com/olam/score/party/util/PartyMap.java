package com.olam.score.party.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.party.dto.OriginDto;

@Service
public class PartyMap {

	@Autowired
	private WebServiceCallUtil webServiceCall;

	//geo location data
	@Transactional
	public  Map<Integer, String> geoMapData(String locationName)
	{
		Map<Integer,String> geoMapData = new HashMap<>();
		List<Map<Object,Object>> geoData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(locationName ,"/location/geoservice/geo");
		//Map<Integer,String> geoMapData = new HashMap<>();

		for(Map<Object,Object> obj:geoData){
			Integer geoId = (Integer) obj.get("pkGeoId");
			String geoLocation = (String) obj.get("geoName");
			geoMapData.put(geoId,geoLocation);
		}		
		return geoMapData;
	}

	//commission data
	public  Map<Integer, String> commisionUomData(String url)
	{

		List<Map<Object,Object>> uomData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/reference/uomservice/uom");
		Map<Integer,String> uomMapData = new HashMap<>();

		for(Map<Object,Object> obj:uomData){
			Integer uomId = (Integer) obj.get("uomId");
			String uomName = (String) obj.get("uomName");
			uomMapData.put(uomId,uomName);
		}
		return uomMapData;
	}

	//payment term data
	public  Map<Integer, String> paymentTermData(String url)
	{

		List<Map<Object,Object>> paymentData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/terms/paymenttermservice/paymentterm");
		Map<Integer,String> paymentMapData = new HashMap<>();
		for(Map<Object,Object> obj : paymentData){
			Integer paymentId = (Integer) obj.get("paymentTermId");
			String paymentName = (String) obj.get("payTermName");
			paymentMapData.put(paymentId, paymentName);
		}

		return paymentMapData;
	}

	//document type map
	public  Map<Integer, String> documentTypeMapData(String url){


		List<Map<Object,Object>> docTypeData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/reference/DocumentTypeService/docType");
		Map<Integer,String> docTypeMapData = new HashMap<>();
		for(Map<Object,Object> obj : docTypeData){
			Integer documentTypeId = (Integer) obj.get("documentTypeId");
			String documentTypeName = (String) obj.get("documentTypeName");
			docTypeMapData.put(documentTypeId,documentTypeName);
		}

		return docTypeMapData;
	}

	//document ref map
	public  Map<Integer, String> documentRefMap(String url)
	{

		List<Map<Object,Object>> docData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/reference/DocumentNameService/docName");
		Map<Integer,String> docMapData = new HashMap<>();

		for(Map<Object,Object> obj:docData){
			Integer documentId = (Integer) obj.get("documentId");
			String documentName = (String) obj.get("documentName");
			docMapData.put(documentId,documentName);
		}

		return docMapData;
	}

	//language 
	public  Map<Integer, String> languageTranslationMap(String url){

		List<Map<Object,Object>> langData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/reference/MasterLanguageService/language");
		Map<Integer,String> languageMapData = new HashMap<>();

		for(Map<Object,Object> obj:langData){
			Integer langId = (Integer) obj.get("languageId");
			String langName = (String) obj.get("languageFullName");
			languageMapData.put(langId,langName);
		}
		return languageMapData;
	}

	//department Map
	public  Map<Integer, String> departmentDetails(String url){

		List<Map<Object,Object>> partyDeptData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/party/department");
		Map<Integer,String> partyDeptMap = new HashMap<>();

		partyDeptData.forEach(data -> {
			data.entrySet().forEach(key -> {
				partyDeptMap.put((Integer) data.get("pkDeptId"), (String) data.get("deptCode"));
			});
		});

		return partyDeptMap;		
	}

	//product
	public  Map<Integer, String> productDetails(String url){


		List<Map<Object,Object>> productData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/product/productservice/product/basicdetails");

		Map<Integer,String> productMap = new HashMap<>();

		productData.forEach(data -> {
			data.entrySet().forEach(key -> {
				productMap.put((Integer) data.get("prodId"), (String) data.get("prodName"));
			});
		});

		return productMap;
	}

	//Origin
	@SuppressWarnings("unchecked")
	public  Map<Integer, String> originValues(String url){

		Map<Integer,String> originMap = new HashMap<>();

		/*List<Map<Object,Object>> productData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/product/originservice/origin");

		List<OriginDto> dtoList = new ArrayList<>();

		productData.forEach(data -> {
			data.entrySet().forEach(key -> {
				dtoList.add((OriginDto)data.get("originDto"));
			});
		});

		Map<Integer,String> originMap = new HashMap<>();
		dtoList.forEach(data -> originMap.put((Integer) data.getPkOriginId(), (String) data.getOriginName()));

		System.out.println("Origin");
		System.out.println(originMap);*/

		/*ObjectMapper objMapper = new ObjectMapper();
		ModelMapper modelMapper = new ModelMapper();

		ResponseEntity<ResponseData> originData = (ResponseEntity<ResponseData>) webServiceCall.getInternalServiceData(url ,"/product/originservice/origin");
		List<Object> op = (List<Object>) originData.getBody().getBody();
		//List<OriginDto> dtoList = new ArrayList<>();
		op.forEach(p -> { 
			System.out.println(modelMapper.map(p, OriginDto.class).getOriginFullName());
			
			
		});
		OriginDto orgDto = objMapper.convertValue(originData.getBody().getBody(), new TypeReference<OriginDto>(){});
		System.out.println(orgDto);*/


		return originMap;
	}

	//address map
	public  Map<Integer, String> addressMap(String url){


		List<Map<Object,Object>> addressData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/location/addressType");
		Map<Integer,String> addressMapData = new HashMap<>();

		for(Map<Object,Object> obj: addressData){
			Integer addrId = (Integer) obj.get("addressTypeId");
			String addrName = (String) obj.get("name");
			addressMapData.put(addrId,addrName);
		}

		return addressMapData;
	}

	//account type
	public  Map<Integer, String> accountTypeMap(String url){


		List<Map<Object,Object>> accountTypeData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/party/accountType");
		Map<Integer,String> accountTypeMap = new HashMap<>();

		accountTypeData.forEach(data -> {
			data.entrySet().forEach(key -> {
				accountTypeMap.put((Integer) data.get("pkAccountTypeId"), (String) data.get("accountTypeName"));
			});
		});

		return accountTypeMap;
	}

	//WareHouse Location
	public  Map<Integer, String> wareHouseMap(String url){


		List<Map<Object,Object>> warehouseData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/location/warehouseLocation");
		Map<Integer,String> warehouseMap = new HashMap<>();

		warehouseData.forEach(data -> {
			data.entrySet().forEach(key -> {
				warehouseMap.put((Integer) data.get("pkLocId"), (String) data.get("locCode"));
			});
		});

		System.out.println("warehouseMap");
		System.out.println(warehouseMap);
		return warehouseMap;
	}

	//master Party
	public  Map<Integer, String> masterPartyValues(String url){


		List<Map<Object,Object>> masterPartyData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/party/partyCode");
		Map<Integer,String> masterPartyMap = new HashMap<>();

		masterPartyData.forEach(data -> {
			data.entrySet().forEach(key -> {
				masterPartyMap.put((Integer) data.get("internalPartyId"), (String) data.get("internalPartyCode"));
			});
		});

		return masterPartyMap;		
	}

	//master unit
	public  Map<Integer, String> masterUnitValues(String url){


		List<Map<Object,Object>> masterUnitData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/party/unitservice/unit");
		Map<Integer,String> masterUnitMap = new HashMap<>();

		masterUnitData.forEach(data -> {
			data.entrySet().forEach(key -> {
				masterUnitMap.put((Integer) data.get("unitId"), (String) data.get("unitName"));
			});
		});

		return masterUnitMap;
	}

	//currency
	public  Map<Integer, String> masterCurrency(String url){


		List<Map<Object,Object>> currData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/reference/currencyservice/currency");
		Map<Integer,String> currMap = new HashMap<>();

		currData.forEach(data -> {
			data.entrySet().forEach(key -> {
				currMap.put((Integer) data.get("pkCurrencyId"), (String) data.get("currencyName"));
			});
		});

		return currMap; 
	}

	//master Origin
	public  Map<Integer, String> masterOrigin(String url){


		List<Map<Object,Object>> originData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/product/originservice/origin");
		Map<Integer,String> originMap = new HashMap<>();

		originData.forEach(data -> {
			data.entrySet().forEach(key -> {
				originMap.put((Integer) data.get("pkOriginId"), (String) data.get("originName"));
			});
		});
		System.out.println("Origin");
		System.out.println(originMap);
		return originMap; 
	}

	//master Grade
	public  Map<Integer, String> masterGrade(String url){


		List<Map<Object,Object>> gradeData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/product/gradeservice/grade");
		Map<Integer,String> gradeMap = new HashMap<>();

		gradeData.forEach(data -> {
			data.entrySet().forEach(key -> {
				gradeMap.put((Integer) data.get("gradeId"), (String) data.get("gradeName"));
			});
		});
		System.out.println("gradeMap");
		System.out.println(gradeMap);

		return gradeMap; 
	}

	//Party Select Group
	public  Map<Integer, String> partySelectGroup(String url){


		List<Map<Object,Object>> partyGroupData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/party/partyType/{partyInternalFlag}");
		Map<Integer,String> groupMap = new HashMap<>();

		partyGroupData.forEach(data -> {
			data.entrySet().forEach(key -> {
				groupMap.put((Integer) data.get("fkParentPartyId"), (String) data.get("isGroupParty"));
			});
		});

		return groupMap; 
	}

	//Market Desk
	public  Map<Integer, String> marketDesk(String url){


		List<Map<Object,Object>> marketDeskData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/reference/marketDesk");
		Map<Integer,String> marketDeskMap = new HashMap<>();

		marketDeskData.forEach(data -> {
			data.entrySet().forEach(key -> {
				marketDeskMap.put((Integer) data.get("pkMarketDeskId"), (String) data.get("marketDeskName"));
			});
		});
		System.out.println("marketDeskMap");
		System.out.println(marketDeskMap);
		return marketDeskMap; 
	}

	//limit alert level
	public  Map<Integer, String> limitAlertLevelValues(String url){


		List<Map<Object,Object>> limitData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/limit/MasterLimitAlertLevelService/masterLimitAlertLevel");
		Map<Integer,String> limitMap = new HashMap<>();

		limitData.forEach(data -> {
			data.entrySet().forEach(key -> {
				limitMap.put((Integer) data.get("pkLimitAlertLevelId"), (String) data.get("limitAlertLevelName"));
			});
		});

		return limitMap;
	}

	//Party external system mapping
	public  Map<Integer,String> partyExternalMapping(String url){


		List<Map<Object,Object>> extrenalDataMap = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/finance/glservice/gl/externalsystemref");
		Map<Integer,String> externalMap = new HashMap<>();

		extrenalDataMap.forEach(data -> {
			data.entrySet().forEach(key -> {
				externalMap.put((Integer) data.get("externalSystemRefId"), (String) data.get("externalSystemRefName"));
			});
		});

		return externalMap; 
	}

	//Holiday calender
	public  Map<Integer,String> holidayCalender(String url){


		List<Map<Object,Object>> holidayData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/reference/holidaycalendarservice/holidaycalendar");
		Map<Integer,String> holidayMap = new HashMap<>();

		holidayData.forEach(data -> {
			data.entrySet().forEach(key -> {
				holidayMap.put((Integer) data.get("holidayCalId"), (String) data.get("holidayCalName"));
			});
		});

		return holidayMap; 
	}

	//Counter Limit Type
	public  Map<Integer,String> masterCounterLimitType(String url){

		List<Map<Object,Object>> counterLimitData = (List<Map<Object,Object>>) webServiceCall.getInternalServiceData(url ,"/limit/MasterCounterPartyLimitTypeService/counterPartyLimitType");
		Map<Integer,String> counterLimitMap = new HashMap<>();

		counterLimitData.forEach(data -> {
			data.entrySet().forEach(key -> {
				counterLimitMap.put((Integer) data.get("pkCounterPartyLimitTypeId"), (String) data.get("counterPartyLimitTypeName"));
			});
		});

		return counterLimitMap; 
	}
}
