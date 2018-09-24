package com.olam.score.marketdata.constants;

	public enum ErrorConstants{
		 NOTNULL("notnull_");

		 private String errorConstants;

		private ErrorConstants(String errorConstants){
		    this.errorConstants = errorConstants;
		  }

		  public String getErrorConstants(){
		    return this.errorConstants;
		  }
		}
