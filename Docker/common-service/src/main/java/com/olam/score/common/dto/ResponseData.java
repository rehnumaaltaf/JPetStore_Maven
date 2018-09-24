package com.olam.score.common.dto;

import java.util.List;

import org.springframework.data.domain.Page;


public class ResponseData<T> {
	private T body;
	private List<Link<?>> links;
	private ViewDto view;
	
	public ResponseData() {
		this.body = null;
	}
	
	public ResponseData(T body, List<Link<?>> links){
		this.body=body;
		this.links=links;
	}
	
	public Object getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	public List<Link<?>> getLinks() {
		return links;
	}
	public void setLinks(List<Link<?>> links) {
		this.links = links;
	}

	public ViewDto getView() {
		return view;
	}

	public void setView(ViewDto view) {
		this.view = view;
	}

}
