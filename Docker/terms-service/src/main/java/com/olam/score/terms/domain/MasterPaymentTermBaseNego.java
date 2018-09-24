/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_PAYMENT_TERM_BASE_NEGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPaymentTermBaseNego.findAll", query = "SELECT m FROM MasterPaymentTermBaseNego m")})
public class MasterPaymentTermBaseNego implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PAYMENT_TERM_BASE_NEGO_GENERATOR")
   	@SequenceGenerator(name="MASTER_PAYMENT_TERM_BASE_NEGO_GENERATOR", sequenceName="terms.PAYMENT_TERM_BASE_NEGO_ID_SEQ",allocationSize=1)
     @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PAYMENT_TERM_BASE_NEGO_ID")
    private Integer pkPaymentTermBaseNegoId;
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
    @JoinColumn(name = "FK_PAYMENT_NEGOTIATION_ID", referencedColumnName = "PK_PAYMENT_NEGOTIATION_ID")
    @ManyToOne
    private MasterPaymentNegotiation fkPaymentNegotiationId;
    @JoinColumn(name = "FK_PAYMENT_TERM_BASE_ID", referencedColumnName = "PK_PAYMENT_TERM_BASE_ID")
    @ManyToOne
    private MasterPaymentTermBase fkPaymentTermBaseId;

    public MasterPaymentTermBaseNego() {
    }

    public MasterPaymentTermBaseNego(Integer pkPaymentTermBaseNegoId) {
        this.pkPaymentTermBaseNegoId = pkPaymentTermBaseNegoId;
    }

    public Integer getPkPaymentTermBaseNegoId() {
        return pkPaymentTermBaseNegoId;
    }

    public void setPkPaymentTermBaseNegoId(Integer pkPaymentTermBaseNegoId) {
        this.pkPaymentTermBaseNegoId = pkPaymentTermBaseNegoId;
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

    public MasterPaymentNegotiation getFkPaymentNegotiationId() {
        return fkPaymentNegotiationId;
    }

    public void setFkPaymentNegotiationId(MasterPaymentNegotiation fkPaymentNegotiationId) {
        this.fkPaymentNegotiationId = fkPaymentNegotiationId;
    }

    public MasterPaymentTermBase getFkPaymentTermBaseId() {
        return fkPaymentTermBaseId;
    }

    public void setFkPaymentTermBaseId(MasterPaymentTermBase fkPaymentTermBaseId) {
        this.fkPaymentTermBaseId = fkPaymentTermBaseId;
    }

    
    
}
