package com.olam.score.common.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ViewDto {
	
	private List<String> sort;
	private String column;
	private String groupBy;
	private PageDto page;
	private Map<String, String> dataType;
	private String filters;
	@JsonIgnore
	private List<String> filtersArray;
	
	public List<String> getSort() {
		return sort;
	}
	public void setSort(List<String> sort) {
		this.sort = sort;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public PageDto getPage() {
		return page;
	}
	public void setPage(PageDto page) {
		this.page = page;
	}
	public Map<String, String> getDataType() {
		return dataType;
	}
	public void setDataType(Map<String, String> dataType2) {
		this.dataType = dataType2;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	public List<String> getFiltersArray() {
		return filtersArray;
	}
	public void setFiltersArray(List<String> filtersArray) {
		this.filtersArray = filtersArray;
	}

}
