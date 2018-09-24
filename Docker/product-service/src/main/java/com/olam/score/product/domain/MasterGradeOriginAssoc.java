/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_GRADE_ORIGIN_ASSOC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGradeOriginAssoc.findAll", query = "SELECT m FROM MasterGradeOriginAssoc m")})
public class MasterGradeOriginAssoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="MASTER_GRADE_ORIGIN_ASSOC_IDSEQ_GENERATOR", sequenceName="GRADE_ORIGIN_ASSOC_ID_SEQ",allocationSize=1)
 	@GeneratedValue(generator="MASTER_GRADE_ORIGIN_ASSOC_IDSEQ_GENERATOR")
      @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GRADE_ORIGIN_ASSOC_ID")
    private Integer pkGradeOriginAssocId;
    @Size(max = 20)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 20)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "FK_GRADE_ID", referencedColumnName = "PK_GRADE_ID")
    @ManyToOne
    private MasterGrade fkGradeId;
    @JoinColumn(name = "FK_ORIGIN_ID", referencedColumnName = "PK_ORIGIN_ID")
    @ManyToOne
    private MasterOrigin fkOriginId;

    public MasterGradeOriginAssoc() {
    }

    public MasterGradeOriginAssoc(Integer pkGradeOriginAssocId) {
        this.pkGradeOriginAssocId = pkGradeOriginAssocId;
    }

    public Integer getPkGradeOriginAssocId() {
        return pkGradeOriginAssocId;
    }

    public void setPkGradeOriginAssocId(Integer pkGradeOriginAssocId) {
        this.pkGradeOriginAssocId = pkGradeOriginAssocId;
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

    public MasterGrade getFkGradeId() {
        return fkGradeId;
    }

    public void setFkGradeId(MasterGrade fkGradeId) {
        this.fkGradeId = fkGradeId;
    }

    public MasterOrigin getFkOriginId() {
        return fkOriginId;
    }

    public void setFkOriginId(MasterOrigin fkOriginId) {
        this.fkOriginId = fkOriginId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGradeOriginAssocId != null ? pkGradeOriginAssocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGradeOriginAssoc)) {
            return false;
        }
        MasterGradeOriginAssoc other = (MasterGradeOriginAssoc) object;
        if ((this.pkGradeOriginAssocId == null && other.pkGradeOriginAssocId != null) || (this.pkGradeOriginAssocId != null && !this.pkGradeOriginAssocId.equals(other.pkGradeOriginAssocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterGradeOriginAssoc[ pkGradeOriginAssocId=" + pkGradeOriginAssocId + " ]";
    }
    
}
