package com.olam.score.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.product.domain.MasterIntlCodeTypeRef;
import com.olam.score.product.dto.IntlCodeTypeRefDto;
import com.olam.score.product.repository.IntlCodeTypeRefRepository;

@Service
public class IntlCodeTypeRef {
	@Autowired
	IntlCodeTypeRefRepository codeTypeRefRep;
	
	public List<IntlCodeTypeRefDto> readAll(){
		List<IntlCodeTypeRefDto> codeTypeList=new ArrayList<>();
		List<MasterIntlCodeTypeRef> codeTypeRefList=codeTypeRefRep.findAll();
		for(MasterIntlCodeTypeRef codeTypeRef : codeTypeRefList){
			IntlCodeTypeRefDto codeTypeRefDto =new IntlCodeTypeRefDto();
			codeTypeRefDto.setTypeRefId(codeTypeRef.getPkIntlCodeTypeRefId());
			codeTypeRefDto.setIntlCodeTypeCode(codeTypeRef.getIntlCodeTypeCode());
			codeTypeRefDto.setIntlCodeTypeName(codeTypeRef.getIntlCodeTypeName());
			codeTypeList.add(codeTypeRefDto);
	}
	return codeTypeList;
	}
}
