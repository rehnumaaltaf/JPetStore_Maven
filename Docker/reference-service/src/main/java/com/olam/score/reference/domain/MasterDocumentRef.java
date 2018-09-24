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
@Table(name = "MASTER_DOCUMENT_REF")
@NamedQueries({
    @NamedQuery(name = "MasterDocumentRef.findAll", query = "SELECT m FROM MasterDocumentRef m")})
public @Data class MasterDocumentRef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_DOCUMENT_REF_ID")
    private Integer documentId;
    @Size(max = 20)
    @Column(name = "DOCUMENT_CODE")
    private String documentCode;
    @Size(max = 200)
    @Column(name = "DOCUMENT_NAME")
    private String documentName;
    @Size(max = 1000)
    @Column(name = "DOCUMENT_DESC")
    private String documentDesc;
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
        hash += (documentId != null ? documentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterDocumentRef)) {
            return false;
        }
        MasterDocumentRef other = (MasterDocumentRef) object;
        if ((this.documentId == null && other.documentId != null) || (this.documentId != null && !this.documentId.equals(other.documentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterDocumentRef[ pkDocumentRefId=" + documentId + " ]";
    }
    
}
