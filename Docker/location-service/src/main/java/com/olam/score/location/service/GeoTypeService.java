package com.olam.score.location.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.location.domain.MasterGeographyLevel;
import com.olam.score.location.dto.GeoTypeDto;
import com.olam.score.location.repository.GeoLevelRepository;

@Service
public class GeoTypeService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	GeoLevelRepository repository;
	

	
	public List<GeoTypeDto> readAll() 
	{	
		List<GeoTypeDto> geoTypeList = new ArrayList<>();
		repository.findAll().forEach(data ->
			geoTypeList.add(generateDtoFromEntity(data)));
		log.info("==In geo Level Type method List is retrieved==");
		return geoTypeList;
	}

	private GeoTypeDto generateDtoFromEntity(MasterGeographyLevel data) {
		GeoTypeDto dto=new GeoTypeDto();
		dto.setGeoTypeId(data.getPkGeoLevelId());
		dto.setGeoTypeCode(data.getGeoLevelCode());
		dto.setGeoTypeName(data.getGeoLevelName());
		return dto;
	}
	
	
}
