package com.olam.score.reference.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.olam.score.reference.dto.WrapperDto;
import com.olam.score.reference.service.CurrencyService;
import com.olam.score.reference.service.UOMService;
import com.olam.score.common.dto.ResponseData;
@CrossOrigin 
@RestController
@RequestMapping("/reference/wrapper")
public class WrapperController {
	
	@Autowired
	UOMService uomService;
	@Autowired
	CurrencyService currService;
	
	@RequestMapping(value = "/uomcurrency", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<WrapperDto>> getUomCurrency(Model model){
		ResponseData<WrapperDto> response =new ResponseData<>();
		WrapperDto dto=new WrapperDto();
		dto.setCurrencyList(currService.readAll());
		dto.setUomList(uomService.readAll(model));
		response.setBody(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
}
