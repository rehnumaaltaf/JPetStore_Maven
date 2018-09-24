package com.olam.score.common.util;

/**
 * 
 * @author sanjay.das
 *
 */
public enum MethodTypeCd {

	CREATE("POST"), UPDATE("PUT"), DELETE("DELETE"), VIEW("GET");

	private String typeCd;

	private MethodTypeCd(String typeCode) {
		this.typeCd = typeCode;
	}

	@Override
	public String toString() {
		return typeCd;
	}
}
