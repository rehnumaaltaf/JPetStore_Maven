package com.olam.score.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.dto.StatusDto;
import com.olam.score.common.service.EurekaInternalService;
import com.olam.score.common.service.StatusService;

@Component
public class WebServiceCallUtil {

	@Autowired
	private StatusService statusService;

	@Autowired
	private EurekaInternalService eurekaInternalService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	public Map<Integer, String> getAllStatus() {

		List<StatusDto> listObj = statusService.readAll();

		Map<Integer, String> statusMap = new HashMap<>();
		for (StatusDto statusDto : listObj) {
			statusMap.put(statusDto.getPkStatusId(), statusDto.getStatusName());
		}
		return statusMap;

	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity<ResponseData> internalServiceCall(String serviceName, String resourceName) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity(
				            eurekaInternalService.callInternalService(serviceName, resourceName), 
				            ResponseData.class);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public List<Map<Object, Object>> getInternalServiceData(String serviceName, String resourcesName) {
		ResponseEntity<ResponseData> responseEntity = null;
		responseEntity = internalServiceCall(serviceName, resourcesName);
		return (List<Map<Object, Object>>) responseEntity.getBody().getBody();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public Map postInternalServiceData(String serviceName,String resourcesName, String obj) {
		
		RestTemplate restTemplate = new RestTemplate();
		JSONParser parser = new JSONParser(); 
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(obj);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MultiValueMap<String, String> headers  = new LinkedMultiValueMap<String, String>();     
		headers.add("HeaderName", "value");
		headers.add("Content-Type", "application/json");
		HttpEntity<JSONObject> request = new HttpEntity<>(json,headers);
		ResponseEntity<ResponseData> responseEntity = null ;
		try {
		 responseEntity = restTemplate
		  .exchange(eurekaInternalService.callInternalService(serviceName, resourcesName), 
				  HttpMethod.POST, request, ResponseData.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.println(responseEntity.getBody().getBody());
		return (Map)responseEntity.getBody().getBody();
	}
}