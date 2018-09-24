package com.olam.score.common.constants;

	public enum ErrorConstants{
		 NOTNULL("notnull_"),
		 RECORD_NOT_FOUND("error_record_not_found"),
		 STATUS_INACTIVATED("record_status_inactivated"),
		 STATUS_ACTIVATED("record_status_activated"),
		 RECORD_DELETED("record_deleted"),
		 INVALID_STATUS("error_invalid_status");

		 private String errorConstants;

		private ErrorConstants(String errorConstants){
		    this.errorConstants = errorConstants;
		  }

		  public String getErrorConstants(){
		    return this.errorConstants;
		  }
		}
