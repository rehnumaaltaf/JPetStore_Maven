/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.olam.score.terms.dto.BasePaymentDto;
import com.olam.score.terms.dto.NegotiationDto;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_PAYMENT_TERM_BASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPaymentTermBase.findAll", query = "SELECT m FROM MasterPaymentTermBase m")})
public class MasterPaymentTermBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PAYMENT_TERM_BASE_GENERATOR")
   	@SequenceGenerator(name="MASTER_PAYMENT_TERM_BASE_GENERATOR", sequenceName="terms.PAY_TERM_ID_SEQ",allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PAYMENT_TERM_BASE_ID")
    private Integer pkPaymentTermBaseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TERM_BASE_CODE")
    private String termBaseCode;
    @Size(max = 200)
    @Column(name = "TERM_BASE_NAME")
    private String termBaseName;
    @Size(max = 1000)
    @Column(name = "TERM_BASE_DESC")
    private String termBaseDesc;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    private Timestamp modifiedDate;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_1")
    private String customAttribute1;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_2")
    private String customAttribute2;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_3")
    private String customAttribute3;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_4")
    private String customAttribute4;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_5")
    private String customAttribute5;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private Double customAttribute8;
    @Size(max = 1)
    @Column(name = "IS_CREDIT_RISK")
    private String isCreditRisk;
    @Size(max = 1)
    @Column(name = "IS_LC_BASED")
    private String isLcBased;
  
    public MasterPaymentTermBase() {
    }

    public MasterPaymentTermBase(Integer pkPaymentTermBaseId) {
        this.pkPaymentTermBaseId = pkPaymentTermBaseId;
    }

    public MasterPaymentTermBase(Integer pkPaymentTermBaseId, String termBaseCode) {
        this.pkPaymentTermBaseId = pkPaymentTermBaseId;
        this.termBaseCode = termBaseCode;
    }

    public Integer getPkPaymentTermBaseId() {
        return pkPaymentTermBaseId;
    }

    public void setPkPaymentTermBaseId(Integer pkPaymentTermBaseId) {
        this.pkPaymentTermBaseId = pkPaymentTermBaseId;
    }

    public String getTermBaseCode() {
        return termBaseCode;
    }

    public void setTermBaseCode(String termBaseCode) {
        this.termBaseCode = termBaseCode;
    }

    public String getTermBaseName() {
        return termBaseName;
    }

    public void setTermBaseName(String termBaseName) {
        this.termBaseName = termBaseName;
    }

    public String getTermBaseDesc() {
        return termBaseDesc;
    }

    public void setTermBaseDesc(String termBaseDesc) {
        this.termBaseDesc = termBaseDesc;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public String getCustomAttribute1() {
        return customAttribute1;
    }

    public void setCustomAttribute1(String customAttribute1) {
        this.customAttribute1 = customAttribute1;
    }

    public String getCustomAttribute2() {
        return customAttribute2;
    }

    public void setCustomAttribute2(String customAttribute2) {
        this.customAttribute2 = customAttribute2;
    }

    public String getCustomAttribute3() {
        return customAttribute3;
    }

    public void setCustomAttribute3(String customAttribute3) {
        this.customAttribute3 = customAttribute3;
    }

    public String getCustomAttribute4() {
        return customAttribute4;
    }

    public void setCustomAttribute4(String customAttribute4) {
        this.customAttribute4 = customAttribute4;
    }

    public String getCustomAttribute5() {
        return customAttribute5;
    }

    public void setCustomAttribute5(String customAttribute5) {
        this.customAttribute5 = customAttribute5;
    }

    public Double getCustomAttribute6() {
        return customAttribute6;
    }

    public void setCustomAttribute6(Double customAttribute6) {
        this.customAttribute6 = customAttribute6;
    }

    public Double getCustomAttribute7() {
        return customAttribute7;
    }

    public void setCustomAttribute7(Double customAttribute7) {
        this.customAttribute7 = customAttribute7;
    }

    public Double getCustomAttribute8() {
        return customAttribute8;
    }

    public void setCustomAttribute8(Double customAttribute8) {
        this.customAttribute8 = customAttribute8;
    }

    public String getIsCreditRisk() {
        return isCreditRisk;
    }

    public void setIsCreditRisk(String isCreditRisk) {
        this.isCreditRisk = isCreditRisk;
    }

    public String getIsLcBased() {
        return isLcBased;
    }

    public void setIsLcBased(String isLcBased) {
        this.isLcBased = isLcBased;
    }

 

public BasePaymentDto convertEntityToDto(Map<Integer, String> statusMap,List<NegotiationDto> reponseNegoList)
{
	BasePaymentDto dto = new BasePaymentDto();
	dto.setBaseNagotiationTerm(reponseNegoList);
	dto.setBaseTermId(pkPaymentTermBaseId);
	dto.setBaseTermCode(termBaseCode);
	dto.setBaseTermCreditRisk(isCreditRisk);
	dto.setBaseTermDescription(termBaseDesc);
	dto.setBaseTermLCBased(isLcBased);
	dto.setBaseTermName(termBaseName);
	dto.setStatusName(statusMap.get(fkStatusId));
	dto.setCreatedBy(createdBy);
	dto.setCreatedDate(createdDate);
	dto.setModifiedBy(modifiedBy);
	dto.setModifiedDate(modifiedDate);
	return dto;
}
    
}
