package com.olam.score.authorizationconfig.util;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olam.score.authorizationconfig.dto.PartyDto;
import com.olam.score.authorizationconfig.dto.PortfolioDto;
import com.olam.score.authorizationconfig.dto.ProductDto;
import com.olam.score.authorizationconfig.dto.ProfitCenterDto;
import com.olam.score.authorizationconfig.dto.RoleFeatureDto;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.common.util.WebServiceCallUtil;

public class AuthUtil {
	
	@Autowired
	private WebServiceCallUtil webServiceCall;
	
	public String findProduct(Integer fkProdId, String productName) {
		ObjectMapper map = new ObjectMapper();
		ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(productName, "product/productservice/product");
		List<ProductDto> body = map.convertValue(serviceCall.getBody().getBody(), new TypeReference<List<ProductDto>>(){});

		return body.stream().filter(prod -> Optional.of(prod.getProdId().equals(fkProdId)).get()).toString();
	}
	
	public String findRole(Integer roleId, String authorizationName) {
		String productName = null;
		ObjectMapper map = new ObjectMapper();
		ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(authorizationName, "authorization/rolemap/features");
		List<RoleFeatureDto> body = map.convertValue(serviceCall.getBody().getBody(), new TypeReference<List<RoleFeatureDto>>(){});
		for(RoleFeatureDto role:body){
			if(role.getRoleId()==roleId){
				productName =  role.getRoleName();
			}
		}
		return productName;
	}
	
	public String findParty(Integer partyId, String partyName) {
		String productName = null;
		ObjectMapper map = new ObjectMapper();
		ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(partyName, "party/partyservice/party");
		List<PartyDto> body = map.convertValue(serviceCall.getBody().getBody(), new TypeReference<List<PartyDto>>(){});
		for(PartyDto party:body){
			if(party.getPkPartyId()==partyId){
				productName =  party.getPartyName();
			}
		}
		return productName;
	}
	
	public String findUnit(Integer unitId,String partyName) {
		String productName = null;
		ObjectMapper map = new ObjectMapper();
		ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(partyName, "party/unitservice/unit");
		List<ProfitCenterDto> body = map.convertValue(serviceCall.getBody().getBody(), new TypeReference<List<ProfitCenterDto>>(){});
		for(ProfitCenterDto unit:body){
				productName =  unit.getUnitName();
		}
		return productName;
	}
	
	public String findPortfolio(Integer portfolioId,String partyName) {
		String productName = null;
		ObjectMapper map = new ObjectMapper();
		ResponseEntity<ResponseData> serviceCall = webServiceCall.internalServiceCall(partyName, "party/portfolioservice/portfolio");
		List<PortfolioDto> body = map.convertValue(serviceCall.getBody().getBody(), new TypeReference<List<PortfolioDto>>(){});
		for(PortfolioDto portfolio:body){
			if(portfolio.getPortfolioId()==portfolioId){
				productName =  portfolio.getPortfolioName();
			}
		}
		return productName;
	}

}
