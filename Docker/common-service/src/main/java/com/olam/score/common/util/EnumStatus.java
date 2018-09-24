package com.olam.score.common.util;

public enum EnumStatus {
	DRAFT(2), ACTIVE(1), INACTIVE(3);
	
	int value;
	EnumStatus(int val) {
		value = val;
	}
	
	public Integer getValue() {
		return value;
	}
}
