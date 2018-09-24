package com.olam.score.terms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.olam.score.common.dto.ResponseData;
import com.olam.score.terms.dto.WrapperDto;
import com.olam.score.terms.service.ContrTrmsAddreduceRefService;
import com.olam.score.terms.service.ContractTermsBaseRefService;
import com.olam.score.terms.service.ContractTermsBasisRefService;

@CrossOrigin
@RestController
@RequestMapping("/terms/wrapper")
public class WrapperController {

	@Autowired
	ContractTermsBaseRefService baseService;
	@Autowired
	ContractTermsBasisRefService basisService;
	@Autowired
	ContrTrmsAddreduceRefService addReduceservice;
	
	@RequestMapping(value = "/basebasisaddreduce", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<WrapperDto>> getProdOrigBrand(){
		ResponseData<WrapperDto> response =new ResponseData<>();
		WrapperDto dto=new WrapperDto();
		dto.setBaseList(baseService.getContractTermsBase());
		dto.setBasisList(basisService.getContractTermsBasis());
		dto.setAddReduceList(addReduceservice.getContrTrmsAddreduceRef());
		response.setBody(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
