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
@Table(name = "MASTER_ORIGIN_CUP_PROFILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterOriginCupProfile.findAll", query = "SELECT m FROM MasterOriginCupProfile m")})
public class MasterOriginCupProfile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ORIGIN_ORIGINCUPPROFILEIDSEQ_GENERATOR")
	@SequenceGenerator(name = "PRODUCT_ORIGIN_ORIGINCUPPROFILEIDSEQ_GENERATOR", sequenceName = "ORIGIN_CUP_PROFILE_ID_SEQ", initialValue = 1, allocationSize = 1)

    @Column(name = "PK_ORIGIN_CUP_PROFILE_ID")
    private Integer pkOriginCupProfileId;
    @Size(max = 200)
    @Column(name = "ORIGIN_CUP_PROFILE_NAME")
    private String originCupProfileName;
    @Size(max = 20)
    @Column(name = "ORIGIN_CUP_PROFILE_CODE")
    private String originCupProfileCode;
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
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @JoinColumn(name = "FK_ORIGIN_ID", referencedColumnName = "PK_ORIGIN_ID")
    @ManyToOne
    private MasterOrigin fkOriginId;
    @OneToMany(mappedBy = "fkOriginCupProfileId")
    private Collection<MasterGrade> masterGradeCollection;

    public MasterOriginCupProfile() {
    }

    public MasterOriginCupProfile(Integer pkOriginCupProfileId) {
        this.pkOriginCupProfileId = pkOriginCupProfileId;
    }

    public Integer getPkOriginCupProfileId() {
        return pkOriginCupProfileId;
    }

    public void setPkOriginCupProfileId(Integer pkOriginCupProfileId) {
        this.pkOriginCupProfileId = pkOriginCupProfileId;
    }

    public String getOriginCupProfileName() {
        return originCupProfileName;
    }

    public void setOriginCupProfileName(String originCupProfileName) {
        this.originCupProfileName = originCupProfileName;
    }

    public String getOriginCupProfileCode() {
        return originCupProfileCode;
    }

    public void setOriginCupProfileCode(String originCupProfileCode) {
        this.originCupProfileCode = originCupProfileCode;
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

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    public MasterOrigin getFkOriginId() {
        return fkOriginId;
    }

    public void setFkOriginId(MasterOrigin fkOriginId) {
        this.fkOriginId = fkOriginId;
    }

    @XmlTransient
    public Collection<MasterGrade> getMasterGradeCollection() {
        return masterGradeCollection;
    }

    public void setMasterGradeCollection(Collection<MasterGrade> masterGradeCollection) {
        this.masterGradeCollection = masterGradeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkOriginCupProfileId != null ? pkOriginCupProfileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterOriginCupProfile)) {
            return false;
        }
        MasterOriginCupProfile other = (MasterOriginCupProfile) object;
        if ((this.pkOriginCupProfileId == null && other.pkOriginCupProfileId != null) || (this.pkOriginCupProfileId != null && !this.pkOriginCupProfileId.equals(other.pkOriginCupProfileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterOriginCupProfile[ pkOriginCupProfileId=" + pkOriginCupProfileId + " ]";
    }
    
}
