package com.olam.score.terms.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.dto.PageDto;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.util.ListViewUtil;
import com.olam.score.common.util.Mapping;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.terms.controller.ToleranceController;
import com.olam.score.terms.domain.MasterFranchiseValueUnit;
import com.olam.score.terms.domain.MasterToleranceValueUnit;
import com.olam.score.terms.domain.MasterWeightTerms;
import com.olam.score.terms.dto.FranchiseDto;
import com.olam.score.terms.dto.ToleranceDto;
import com.olam.score.terms.repository.FranchiseRepository;
import com.olam.score.terms.repository.ToleranceRepository;
import com.olam.score.terms.util.WeightTermsSpecificationsBuilder;

@Service
public class ToleranceService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired 
	private ToleranceRepository repository;
	
	@Autowired
	private WebServiceCallUtil webServiceCall;
	
	@Autowired
	private ListViewUtil listview;
	
	public List<ToleranceDto> readAll1(ViewDto view) {
		List<ToleranceDto> toleranceList = new ArrayList<>();
		/*List<String> filterList = view.getFiltersArray();
		WeightTermsSpecificationsBuilder builder = new WeightTermsSpecificationsBuilder();
		for (int filterSize = 0; filterSize < filterList.size(); filterSize++) {
			builder.with(filterList.get(filterSize).substring(0, filterList.get(filterSize).indexOf("=")),
					filterList.get(filterSize).substring(filterList.get(filterSize).indexOf("=") + 1));
		}
		Specification<MasterWeightTerms> spec = builder.build();
		Pageable pageRequest = ListViewUtil.createPageRequest(view);
		log.info("===Pageable object created from view object===");
		List<MasterToleranceValueUnit> oldList = repository.findAll(pageRequest).getContent();*/
		
		List<MasterToleranceValueUnit> oldList = repository.findAll();
		
		Map<Integer, String> statusMap = webServiceCall.getAllStatus();
		
		for (MasterToleranceValueUnit obj : oldList) {
			
			ToleranceDto tolerance = new ToleranceDto();
			tolerance.setToleranceValueUnitId(obj.getPkToleranceValueUnitId());
			tolerance.setToleranceValueUnitCode(obj.getToleranceValueUnitCode());
			tolerance.setToleranceValueUnitName(obj.getToleranceValueUnitName());
			tolerance.setToleranceValueUnitDesc(obj.getToleranceValueUnitDesc());
			toleranceList.add(tolerance);
		}
	
		return toleranceList;
}

	public ViewDto getViewDetails(Class<? extends Object> currentfeature) {
		String featureId = currentfeature.getAnnotation(Mapping.class).featureId();
		ViewDto viewdto = listview.getView(featureId);
		log.info("===viewdto from common has been generated for JSON===");
		PageDto pageDto = new PageDto();
		pageDto.setPageNumber(0);
		pageDto.setPageSize(Constants.PAGESIZE);
		pageDto.setTotalCount(repository.count());
		pageDto.setOperation("Client");//To be set from Database
		viewdto.setPage(pageDto);
		Class<ToleranceDto> tolerance = ToleranceDto.class;
		Field[] fields = tolerance.getDeclaredFields();
		Map<String, String> dataType = new HashMap<>();
		for (int i = 0; i < fields.length; i++) {
			String type = fields[i].getType().toString();
			type = type.substring(type.lastIndexOf('.') + 1, type.length());
			dataType.put(fields[i].getName(), type);
		}
		viewdto.setDataType(dataType);
		log.info("===dataType is set in view according to entity===");

		return viewdto;
	}
	
	}
