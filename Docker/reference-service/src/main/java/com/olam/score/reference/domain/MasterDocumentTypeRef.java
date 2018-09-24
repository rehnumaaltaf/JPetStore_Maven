/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_DOCUMENT_TYPE_REF")
@NamedQueries({
    @NamedQuery(name = "MasterDocumentTypeRef.findAll", query = "SELECT m FROM MasterDocumentTypeRef m")})
public @Data class MasterDocumentTypeRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_DOCUMENT_TYPE_REF_ID")
    private Integer documentTypeId;
    @Size(max = 20)
    @Column(name = "DOCUMENT_TYPE_CODE")
    private String documentTypeCode;
    @Size(max = 200)
    @Column(name = "DOCUMENT_TYPE_NAME")
    private String documentTypeName;
    @Size(max = 1000)
    @Column(name = "DOCUMENT_TYPE_DESC")
    private String documentTypeDesc;
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

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documentTypeId != null ? documentTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterDocumentTypeRef)) {
            return false;
        }
        MasterDocumentTypeRef other = (MasterDocumentTypeRef) object;
        if ((this.documentTypeId == null && other.documentTypeId != null) || (this.documentTypeId != null && !this.documentTypeId.equals(other.documentTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterDocumentTypeRef[ pkDocumentTypeRefId=" + documentTypeId + " ]";
    }
    
}
