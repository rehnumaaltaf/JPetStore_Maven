/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "MASTER_OUTTURN_RATIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterOutturnRatio.findAll", query = "SELECT m FROM MasterOutturnRatio m")})
public class MasterOutturnRatio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_OUTTURN_OUTTURNRATIOIDSEQGENERATOR")
   	@SequenceGenerator(name = "PRODUCT_OUTTURN_OUTTURNRATIOIDSEQGENERATOR", sequenceName = "OUTTURN_RATIO_ID_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "PK_OUTTURN_RATIO_ID")
    private Integer pkOutturnRatioId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QUANTITY_RATIO")
    private Double quantityRatio;
    @Column(name = "VALUE_RATIO")
    private Double abilityToBearRatio;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 100)
    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;
    @Column(name = "CREATED_DATE",  updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
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
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private BigInteger customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private BigInteger customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private BigInteger customAttribute8;
    /*@JoinColumn(name = "FK_FINISHED_GRADE_ID", referencedColumnName = "PK_GRADE_ID")
    @ManyToOne*/
    @Column(name = "FK_FINISHED_GRADE_ID")
    private Integer fkFinishedGradeId;
    @JoinColumn(name = "FK_OUTTURN_RAW_GRADE_ID", referencedColumnName = "PK_OUTTURN_RAW_GRADE_ID")
    @ManyToOne
    private MasterOutturnRawGrade fkOutturnRawGradeId;

    public MasterOutturnRatio() {
    }

    public MasterOutturnRatio(Integer pkOutturnRatioId) {
        this.pkOutturnRatioId = pkOutturnRatioId;
    }

    public Integer getPkOutturnRatioId() {
        return pkOutturnRatioId;
    }

    public void setPkOutturnRatioId(Integer pkOutturnRatioId) {
        this.pkOutturnRatioId = pkOutturnRatioId;
    }

    public Double getQuantityRatio() {
        return quantityRatio;
    }

    public void setQuantityRatio(Double quantityRatio) {
        this.quantityRatio = quantityRatio;
    }

    public Double getAbilityToBearRatio() {
        return abilityToBearRatio;
    }

    public void setAbilityToBearRatio(Double abilityToBearRatio) {
        this.abilityToBearRatio = abilityToBearRatio;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
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

    public BigInteger getCustomAttribute6() {
        return customAttribute6;
    }

    public void setCustomAttribute6(BigInteger customAttribute6) {
        this.customAttribute6 = customAttribute6;
    }

    public BigInteger getCustomAttribute7() {
        return customAttribute7;
    }

    public void setCustomAttribute7(BigInteger customAttribute7) {
        this.customAttribute7 = customAttribute7;
    }

    public BigInteger getCustomAttribute8() {
        return customAttribute8;
    }

    public void setCustomAttribute8(BigInteger customAttribute8) {
        this.customAttribute8 = customAttribute8;
    }

    
    
    /*public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}*/

	public Integer getFkFinishedGradeId() {
		return fkFinishedGradeId;
	}

	public void setFkFinishedGradeId(Integer fkFinishedGradeId) {
		this.fkFinishedGradeId = fkFinishedGradeId;
	}

	public MasterOutturnRawGrade getFkOutturnRawGradeId() {
        return fkOutturnRawGradeId;
    }

    public void setFkOutturnRawGradeId(MasterOutturnRawGrade fkOutturnRawGradeId) {
        this.fkOutturnRawGradeId = fkOutturnRawGradeId;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkOutturnRatioId != null ? pkOutturnRatioId.hashCode() : 0);
        return hash;
    }

    

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterOutturnRatio)) {
            return false;
        }
        MasterOutturnRatio other = (MasterOutturnRatio) object;
        if ((this.pkOutturnRatioId == null && other.pkOutturnRatioId != null) || (this.pkOutturnRatioId != null && !this.pkOutturnRatioId.equals(other.pkOutturnRatioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterOutturnRatio[ pkOutturnRatioId=" + pkOutturnRatioId + " ]";
    }
    
}
