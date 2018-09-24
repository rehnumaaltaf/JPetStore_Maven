package com.olam.score.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.olam.score.common.domain.MasterCustomViewDetail;
import com.olam.score.common.dto.ViewDto;
import com.olam.score.common.repository.CommonRepository;

@Component
public class ListViewUtil {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private  CommonRepository commonRepository;
	@Autowired
	EntityManager entity; 
	
	public  ViewDto getView(String featureId){
		List<MasterCustomViewDetail> customView = null;
		String isDefault="Y";
		customView = commonRepository.findDefaultView(Integer.parseInt(featureId),isDefault);
		log.info("====Custom view retrieved from database=====");
		ViewDto viewDto;
		viewDto=setView(customView);
		return viewDto;
	}
	public ViewDto setView(List<MasterCustomViewDetail> customView){
		
		List<String> sort=new ArrayList<>();
		StringBuilder columnList = new StringBuilder();
		StringBuilder groupByList=new StringBuilder();
		StringBuilder filterList=new StringBuilder();
		List<String> filter=new ArrayList<>();
		for (MasterCustomViewDetail masterCustomView : customView) {
		if(masterCustomView.getCustomViewDetailAttributeType()!=null && "Sort".equalsIgnoreCase(masterCustomView.getCustomViewDetailAttributeType())){
			sort.add(masterCustomView.getCustomViewDetailAttributeValue());
		}
		if(masterCustomView.getCustomViewDetailAttributeType()!=null && "Display Field".equalsIgnoreCase(masterCustomView.getCustomViewDetailAttributeType())){
			columnList.append(",").append(masterCustomView.getCustomViewDetailAttributeValue());
		}
		if(masterCustomView.getCustomViewDetailAttributeType()!=null && "Group By".equalsIgnoreCase(masterCustomView.getCustomViewDetailAttributeType())){
			groupByList.append(",").append(masterCustomView.getCustomViewDetailAttributeValue());
		}
		if(masterCustomView.getCustomViewDetailAttributeType()!=null && "Filter".equalsIgnoreCase(masterCustomView.getCustomViewDetailAttributeType())){
			filterList.append(",").append(masterCustomView.getCustomViewDetailAttributeValue());
			filter.add(masterCustomView.getCustomViewDetailAttributeValue());
		}
		}
		ViewDto viewDto=new ViewDto();
		viewDto.setSort(sort);
		viewDto.setColumn(columnList.substring(columnList.indexOf(",")+1));
		viewDto.setGroupBy(groupByList.substring(groupByList.indexOf(",")+1));
		viewDto.setFilters(filterList.substring(filterList.indexOf(",")+1));
		viewDto.setFiltersArray(filter);
		log.info("====viewDto is set in listViewutil=====");
		return viewDto;
		
	}
	public static Pageable createPageRequest(ViewDto viewdto) {
		Pageable pageRequest;
		List<String> sortingColumn=viewdto.getSort();
		Direction direction;
		Sort sort =null;
		for (int i = 0; i < sortingColumn.size(); i++) {
			if(sortingColumn.get(i)!=null && sortingColumn.get(i).indexOf(':')!=0)
			{
				if(sortingColumn.get(i).substring(sortingColumn.get(i).indexOf(':')+1).equalsIgnoreCase("DESC"))
				direction=Sort.Direction.DESC;
				else
					direction=Sort.Direction.ASC;
				if(i==0)
				sort=new Sort(direction,sortingColumn.get(i).substring(0,sortingColumn.get(i).indexOf(':')));
				else if(i>0 && sort!=null){
				sort=sort.and(new Sort(direction,sortingColumn.get(i).substring(0,sortingColumn.get(i).indexOf(':'))));
			}}

		}
		 pageRequest=new PageRequest(viewdto.getPage().getPageNumber(), 
				viewdto.getPage().getPageSize(),sort);
		 return pageRequest;
    }
}
