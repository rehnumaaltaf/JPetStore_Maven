/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_PRODUCT_ICO_INDEX")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterProductIcoIndex.findAll", query = "SELECT m FROM MasterProductIcoIndex m")})
public class MasterProductIcoIndex implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_PRODUCT_ICO_GENERATOR")
	@SequenceGenerator(name="MASTER_PRODUCT_ICO_GENERATOR", sequenceName="product.PRODUCT_CLASSIFICATION_REF_ID_SEQ",allocationSize=1)
    @NotNull
    @Column(name = "PK_PRODUCT_ICO_INDEX")
    private Integer pkProductIcoIndex;
    @Column(name = "ICO_CODE")
    private String icoCode;
    @Size(max = 200)
    @Column(name = "ICO_NAME")
    private String icoName;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private Double customAttribute8;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "fkProductIcoIndex")
    private Collection<MasterGrade> masterGradeCollection;
    @JoinColumn(name = "FK_PROD_ID", referencedColumnName = "PK_PROD_ID")
    @ManyToOne
    private MasterProduct fkProdId;

    public MasterProductIcoIndex() {
    }

    public MasterProductIcoIndex(Integer pkProductIcoIndex) {
        this.pkProductIcoIndex = pkProductIcoIndex;
    }

    public Integer getPkProductIcoIndex() {
        return pkProductIcoIndex;
    }

    public void setPkProductIcoIndex(Integer pkProductIcoIndex) {
        this.pkProductIcoIndex = pkProductIcoIndex;
    }

    public String getIcoCode() {
        return icoCode;
    }

    public void setIcoCode(String icoCode) {
        this.icoCode = icoCode;
    }

    public String getIcoName() {
        return icoName;
    }

    public void setIcoName(String icoName) {
        this.icoName = icoName;
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

    @XmlTransient
    public Collection<MasterGrade> getMasterGradeCollection() {
        return masterGradeCollection;
    }

    public void setMasterGradeCollection(Collection<MasterGrade> masterGradeCollection) {
        this.masterGradeCollection = masterGradeCollection;
    }

    public MasterProduct getFkProdId() {
        return fkProdId;
    }

    public void setFkProdId(MasterProduct fkProdId) {
        this.fkProdId = fkProdId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkProductIcoIndex != null ? pkProductIcoIndex.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterProductIcoIndex)) {
            return false;
        }
        MasterProductIcoIndex other = (MasterProductIcoIndex) object;
        if ((this.pkProductIcoIndex == null && other.pkProductIcoIndex != null) || (this.pkProductIcoIndex != null && !this.pkProductIcoIndex.equals(other.pkProductIcoIndex))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterProductIcoIndex[ pkProductIcoIndex=" + pkProductIcoIndex + " ]";
    }
    
}
