package com.olam.score.limit.dto;

import java.sql.Timestamp;

public class CommentsDto {
	
	  private String commentText;
	  private Integer limitCommentId;
	  private Timestamp createdDate;
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public Integer getLimitCommentId() {
		return limitCommentId;
	}
	public void setLimitCommentId(Integer limitCommentId) {
		this.limitCommentId = limitCommentId;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	  

}
