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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_ORIGIN_REGION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterOriginRegion.findAll", query = "SELECT m FROM MasterOriginRegion m")})
public class MasterOriginRegion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ORIGIN_ORIGINREGIONIDSEQ_GENERATOR")
	@SequenceGenerator(name = "PRODUCT_ORIGIN_ORIGINREGIONIDSEQ_GENERATOR", sequenceName = "ORIGIN_REGION_ID_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "PK_ORIGIN_REGION_ID")
    private Integer pkOriginRegionId;
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
    @Size(max = 200)
    @Column(name = "ORIGIN_REGION_NAME")
    private String originRegionName;
    @Size(max = 20)
    @Column(name = "ORIGIN_REGION_CODE")
    private String originRegionCode;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Size(max = 200)
    @Column(name = "ORIGIN_REGION_MEAN_ABOVE_SEA_LEVEL")
    private String originRegionMeanAboveSeaLevel;
    @JoinColumn(name = "FK_ORIGIN_ID", referencedColumnName = "PK_ORIGIN_ID")
    @ManyToOne
    private MasterOrigin fkOriginId;
    @OneToMany(mappedBy = "fkOriginRegionId")
    private Collection<MasterGradeOriginRegAssoc> masterGradeOriginRegAssocCollection;

    public MasterOriginRegion() {
    }

    public MasterOriginRegion(Integer pkOriginRegionId) {
        this.pkOriginRegionId = pkOriginRegionId;
    }

    public Integer getPkOriginRegionId() {
        return pkOriginRegionId;
    }

    public void setPkOriginRegionId(Integer pkOriginRegionId) {
        this.pkOriginRegionId = pkOriginRegionId;
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

    public String getOriginRegionName() {
        return originRegionName;
    }

    public void setOriginRegionName(String originRegionName) {
        this.originRegionName = originRegionName;
    }

    public String getOriginRegionCode() {
        return originRegionCode;
    }

    public void setOriginRegionCode(String originRegionCode) {
        this.originRegionCode = originRegionCode;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public String getOriginRegionMeanAboveSeaLevel() {
        return originRegionMeanAboveSeaLevel;
    }

    public void setOriginRegionMeanAboveSeaLevel(String originRegionMeanAboveSeaLevel) {
        this.originRegionMeanAboveSeaLevel = originRegionMeanAboveSeaLevel;
    }

    public MasterOrigin getFkOriginId() {
        return fkOriginId;
    }

    public void setFkOriginId(MasterOrigin fkOriginId) {
        this.fkOriginId = fkOriginId;
    }

    @XmlTransient
    public Collection<MasterGradeOriginRegAssoc> getMasterGradeOriginRegAssocCollection() {
        return masterGradeOriginRegAssocCollection;
    }

    public void setMasterGradeOriginRegAssocCollection(Collection<MasterGradeOriginRegAssoc> masterGradeOriginRegAssocCollection) {
        this.masterGradeOriginRegAssocCollection = masterGradeOriginRegAssocCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkOriginRegionId != null ? pkOriginRegionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterOriginRegion)) {
            return false;
        }
        MasterOriginRegion other = (MasterOriginRegion) object;
        if ((this.pkOriginRegionId == null && other.pkOriginRegionId != null) || (this.pkOriginRegionId != null && !this.pkOriginRegionId.equals(other.pkOriginRegionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterOriginRegion[ pkOriginRegionId=" + pkOriginRegionId + " ]";
    }
    
}
