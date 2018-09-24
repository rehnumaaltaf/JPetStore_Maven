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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "MASTER_QUALITY_GRADE_SPECIFICATION",  schema = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterQualityGradeSpecification.findAll", query = "SELECT m FROM MasterQualityGradeSpecification m")})
public class MasterQualityGradeSpecification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_QLTY_GRADE_SPEC")
    private Integer pkQltyGradeSpec;
    @Size(max = 20)
    @Column(name = "QLTY_GRADE_SPEC_DEFAULT_VALUE")
    private String qltyGradeSpecDefaultValue;
    @Size(max = 20)
    @Column(name = "QLTY_GRADE_SPEC_PARAMETERS_MASTER")
    private String qltyGradeSpecParametersMaster;
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
    @JoinColumn(name = "FK_QLTY_LABEL_REF_ID", referencedColumnName = "PK_QLTY_LABEL_REF_ID")
    @ManyToOne
    private MasterQualityLabelRef fkQltyLabelRefId;
    @JoinColumn(name = "FK_QLTY_NAME_REF_ID", referencedColumnName = "PK_QLTY_NAME_REF_ID")
    @ManyToOne
    private MasterQualityNameRef fkQltyNameRefId;
    @JoinColumn(name = "FK_QLTY_PARAM_REF_ID", referencedColumnName = "PK_QLTY_PARAM_REF_ID")
    @ManyToOne
    private MasterQualityParameterRef fkQltyParamRefId;

    public MasterQualityGradeSpecification() {
    }

    public MasterQualityGradeSpecification(Integer pkQltyGradeSpec) {
        this.pkQltyGradeSpec = pkQltyGradeSpec;
    }

    public Integer getPkQltyGradeSpec() {
        return pkQltyGradeSpec;
    }

    public void setPkQltyGradeSpec(Integer pkQltyGradeSpec) {
        this.pkQltyGradeSpec = pkQltyGradeSpec;
    }

    public String getQltyGradeSpecDefaultValue() {
        return qltyGradeSpecDefaultValue;
    }

    public void setQltyGradeSpecDefaultValue(String qltyGradeSpecDefaultValue) {
        this.qltyGradeSpecDefaultValue = qltyGradeSpecDefaultValue;
    }

    public String getQltyGradeSpecParametersMaster() {
        return qltyGradeSpecParametersMaster;
    }

    public void setQltyGradeSpecParametersMaster(String qltyGradeSpecParametersMaster) {
        this.qltyGradeSpecParametersMaster = qltyGradeSpecParametersMaster;
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

    public MasterQualityLabelRef getFkQltyLabelRefId() {
        return fkQltyLabelRefId;
    }

    public void setFkQltyLabelRefId(MasterQualityLabelRef fkQltyLabelRefId) {
        this.fkQltyLabelRefId = fkQltyLabelRefId;
    }

    public MasterQualityNameRef getFkQltyNameRefId() {
        return fkQltyNameRefId;
    }

    public void setFkQltyNameRefId(MasterQualityNameRef fkQltyNameRefId) {
        this.fkQltyNameRefId = fkQltyNameRefId;
    }

    public MasterQualityParameterRef getFkQltyParamRefId() {
        return fkQltyParamRefId;
    }

    public void setFkQltyParamRefId(MasterQualityParameterRef fkQltyParamRefId) {
        this.fkQltyParamRefId = fkQltyParamRefId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkQltyGradeSpec != null ? pkQltyGradeSpec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterQualityGradeSpecification)) {
            return false;
        }
        MasterQualityGradeSpecification other = (MasterQualityGradeSpecification) object;
        if ((this.pkQltyGradeSpec == null && other.pkQltyGradeSpec != null) || (this.pkQltyGradeSpec != null && !this.pkQltyGradeSpec.equals(other.pkQltyGradeSpec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterQualityGradeSpecification[ pkQltyGradeSpec=" + pkQltyGradeSpec + " ]";
    }
    
}
