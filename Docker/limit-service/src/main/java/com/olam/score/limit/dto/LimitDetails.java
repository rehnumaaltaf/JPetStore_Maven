package com.olam.score.limit.dto;

import java.sql.Timestamp;

public class LimitDetails {

	private Integer limitDetailsId;
	private Timestamp validTo;
	private String isTemporary;
	private Timestamp validFrom;
	private BaseLimitAttribute limitVaRAttribute;
	private BaseLimitAttribute limitGammaAttribute;
	private BaseLimitAttribute limitBasisAttribute;
	private BaseLimitAttribute limitDeltaAttribute;
	private BaseLimitAttribute limitDrawDownAttribute;
	private BaseLimitAttribute limitOutRightAttribute;
	private BaseLimitAttribute limitVegaAttribute;
	private BaseLimitAttribute limitStructureAttribute;
	private BaseLimitAttribute currentMonthAttribute;
	private BaseLimitAttribute forwardMonthAttribute;
	private BaseLimitAttribute totalAttribute;
	private BaseLimitAttribute valueAttribute;

	public Integer getLimitDetailsId() {
		return limitDetailsId;
	}

	public void setLimitDetailsId(Integer limitDetailsId) {
		this.limitDetailsId = limitDetailsId;
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

	public String getIsTemporary() {
		return isTemporary;
	}

	public void setIsTemporary(String isTemporary) {
		this.isTemporary = isTemporary;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public BaseLimitAttribute getLimitVaRAttribute() {
		return limitVaRAttribute;
	}

	public void setLimitVaRAttribute(BaseLimitAttribute limitVaRAttribute) {
		this.limitVaRAttribute = limitVaRAttribute;
	}

	public BaseLimitAttribute getLimitGammaAttribute() {
		return limitGammaAttribute;
	}

	public void setLimitGammaAttribute(BaseLimitAttribute limitGammaAttribute) {
		this.limitGammaAttribute = limitGammaAttribute;
	}

	public BaseLimitAttribute getLimitBasisAttribute() {
		return limitBasisAttribute;
	}

	public void setLimitBasisAttribute(BaseLimitAttribute limitBasisAttribute) {
		this.limitBasisAttribute = limitBasisAttribute;
	}

	public BaseLimitAttribute getLimitDeltaAttribute() {
		return limitDeltaAttribute;
	}

	public void setLimitDeltaAttribute(BaseLimitAttribute limitDeltaAttribute) {
		this.limitDeltaAttribute = limitDeltaAttribute;
	}

	public BaseLimitAttribute getLimitDrawDownAttribute() {
		return limitDrawDownAttribute;
	}

	public void setLimitDrawDownAttribute(BaseLimitAttribute limitDrawDownAttribute) {
		this.limitDrawDownAttribute = limitDrawDownAttribute;
	}

	public BaseLimitAttribute getLimitOutRightAttribute() {
		return limitOutRightAttribute;
	}

	public void setLimitOutRightAttribute(BaseLimitAttribute limitOutRightAttribute) {
		this.limitOutRightAttribute = limitOutRightAttribute;
	}

	public BaseLimitAttribute getLimitVegaAttribute() {
		return limitVegaAttribute;
	}

	public void setLimitVegaAttribute(BaseLimitAttribute limitVegaAttribute) {
		this.limitVegaAttribute = limitVegaAttribute;
	}

	public BaseLimitAttribute getLimitStructureAttribute() {
		return limitStructureAttribute;
	}

	public void setLimitStructureAttribute(BaseLimitAttribute limitStructureAttribute) {
		this.limitStructureAttribute = limitStructureAttribute;
	}

	public BaseLimitAttribute getCurrentMonthAttribute() {
		return currentMonthAttribute;
	}

	public void setCurrentMonthAttribute(BaseLimitAttribute currentMonthAttribute) {
		this.currentMonthAttribute = currentMonthAttribute;
	}

	public BaseLimitAttribute getForwardMonthAttribute() {
		return forwardMonthAttribute;
	}

	public void setForwardMonthAttribute(BaseLimitAttribute forwardMonthAttribute) {
		this.forwardMonthAttribute = forwardMonthAttribute;
	}

	public BaseLimitAttribute getTotalAttribute() {
		return totalAttribute;
	}

	public void setTotalAttribute(BaseLimitAttribute totalAttribute) {
		this.totalAttribute = totalAttribute;
	}

	public BaseLimitAttribute getValueAttribute() {
		return valueAttribute;
	}

	public void setValueAttribute(BaseLimitAttribute valueAttribute) {
		this.valueAttribute = valueAttribute;
	}

	/*
	 * private LimitBaseAttribute limitVaRAttribute; private LimitGammaAttribute
	 * limitGammaAttribute; private LimitBasisAttribute limitBasisAttribute;
	 * private LimitDeltaAttribute limitDeltaAttribute; private
	 * LimitDrawDownAttribute limitDrawDownAttribute; private
	 * LimitOutRightAttribute limitOutRightAttribute; private LimitVegaAttribute
	 * limitVegaAttribute; private LimitStructureAttribute
	 * limitStructureAttribute;
	 */

}
