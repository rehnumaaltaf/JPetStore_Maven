package com.olam.score.party.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.party.dto.DepartmentDto;
import com.olam.score.party.repository.PartyDepartmentRepository;

@Service
public class PartyDepartmentService {

	@Autowired
	private PartyDepartmentRepository documentsRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public List<DepartmentDto> fetchDepartment() {

		List<DepartmentDto> department = new ArrayList<>();

		documentsRepository.findAll().forEach(data -> {
			department.add(modelMapper.map(data, DepartmentDto.class));
		});
		return department;
	}


}
