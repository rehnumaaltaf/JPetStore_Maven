package com.olam.score.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.olam.score.common.dto.ResponseData;
import com.olam.score.product.domain.MasterOriginGradeGroup;
import com.olam.score.product.dto.OriginGradeDto;
import com.olam.score.product.dto.WrapperDto;
import com.olam.score.product.repository.GradeGroupOriginAssoc;
import com.olam.score.product.repository.GradeGroupingRefRepository;
import com.olam.score.product.service.BrandService;
import com.olam.score.product.service.GradeGroupService;
import com.olam.score.product.service.IntlCodeTypeRef;
import com.olam.score.product.service.ProductService;
@CrossOrigin
@RestController
@RequestMapping("/product/wrapper")
public class WrapperController {
	
	@Autowired
	ProductService prodService;
	
	@Autowired
	BrandService brandService;
	
	@Autowired
	GradeGroupService gradeGrpService;
	@Autowired
	GradeGroupingRefRepository groupingRefRepository;
	@Autowired
	GradeGroupOriginAssoc gradeGroupOrgRep;
	@Autowired
	IntlCodeTypeRef codeTypeRef;
	@RequestMapping(value = "/prodorigbrand", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<WrapperDto>> getProdOrigBrand(){
		ResponseData<WrapperDto> response =new ResponseData<>();
		WrapperDto dto=new WrapperDto();
		dto.setBrandList(brandService.viewBrandList());
		dto.setProductList(prodService.readAll());
		dto.setGradeGroupList(gradeGrpService.readAll());
		dto.setIntlCodeTypeList(codeTypeRef.readAll());
		response.setBody(dto);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//To Be done after Origin service is available
	@RequestMapping(value = "/origin/{gradeGroupId}", method = RequestMethod.GET,produces = { "application/json" })
	public ResponseEntity<ResponseData<List<OriginGradeDto>>> getOriginGrade(@PathVariable("gradeGroupId") Integer gradeGroupId){
			List<MasterOriginGradeGroup> masterOriginList=gradeGroupOrgRep.findByFkGradeGroupRefId(groupingRefRepository.findOne(gradeGroupId));
			List<OriginGradeDto> originList=new ArrayList<>();
			for(MasterOriginGradeGroup masterOrigin:masterOriginList){
				OriginGradeDto originGradeDto = new OriginGradeDto();
				originGradeDto.setOriginId(masterOrigin.getFkOriginId().getPkOriginId());
				originGradeDto.setOriginCode(masterOrigin.getFkOriginId().getOriginCode());
				originGradeDto.setOriginName(masterOrigin.getFkOriginId().getOriginName());
				originList.add(originGradeDto);
			}
		ResponseData<List<OriginGradeDto>> response=new ResponseData<>();
		response.setBody(originList);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
