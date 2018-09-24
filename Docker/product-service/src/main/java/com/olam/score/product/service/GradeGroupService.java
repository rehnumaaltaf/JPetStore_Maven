package com.olam.score.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.product.repository.GradeGroupingRefRepository;
import com.olam.score.product.domain.MasterGradeGroupRef;
import com.olam.score.product.dto.GradeGroupDto;
@Service
public class GradeGroupService {
	@Autowired
	GradeGroupingRefRepository groupingRefRepository;

	
	public List<GradeGroupDto> readAll(){
		List<GradeGroupDto> gradeGroupList=new ArrayList<>();
		List<MasterGradeGroupRef> gradeGroupRefList=groupingRefRepository.findAll();
		for(MasterGradeGroupRef gradeGroupRef : gradeGroupRefList){
		GradeGroupDto gradeGroupDto =new GradeGroupDto();
		gradeGroupDto.setGradeGroupId(gradeGroupRef.getPkGradeGroupRefId());
		gradeGroupDto.setGradeGroupCode(gradeGroupRef.getGradeGroupCode());
		gradeGroupDto.setGradeGroupName(gradeGroupRef.getGradeGroupName());
		gradeGroupList.add(gradeGroupDto);
	}
	return gradeGroupList;
	}
	
	public GradeGroupDto readById(Integer gradeGroupId){
		MasterGradeGroupRef gradeGroupRef=groupingRefRepository.findOne(gradeGroupId);
		GradeGroupDto gradeGroupDto =new GradeGroupDto();
		gradeGroupDto.setGradeGroupId(gradeGroupRef.getPkGradeGroupRefId());
		gradeGroupDto.setGradeGroupCode(gradeGroupRef.getGradeGroupCode());
		gradeGroupDto.setGradeGroupName(gradeGroupRef.getGradeGroupName());
		return gradeGroupDto;
	}

}
