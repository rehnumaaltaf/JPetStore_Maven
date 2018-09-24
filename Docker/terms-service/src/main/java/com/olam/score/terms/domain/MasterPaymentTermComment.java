/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.terms.domain;

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
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_PAYMENT_TERM_COMMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterPaymentTermComment.findAll", query = "SELECT m FROM MasterPaymentTermComment m")})
public class MasterPaymentTermComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PAYMENT_TERM_COMMENT_ID")
    private Integer pkPaymentTermCommentId;
    @Size(max = 200)
    @Column(name = "COMMENT_ACTION")
    private String action;
    @Size(max = 4000)
    @Column(name = "COMMENT_TEXT")
    private String commentText;
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
    @JoinColumn(name = "PK_PAYMENT_TERM_ID", referencedColumnName = "PK_PAYMENT_TERM_ID")
    @ManyToOne
    private MasterPaymentTerm pkPaymentTermId;

    public MasterPaymentTermComment() {
    }

    public MasterPaymentTermComment(Integer pkPaymentTermCommentId) {
        this.pkPaymentTermCommentId = pkPaymentTermCommentId;
    }

    public Integer getPkPaymentTermCommentId() {
        return pkPaymentTermCommentId;
    }

    public void setPkPaymentTermCommentId(Integer pkPaymentTermCommentId) {
        this.pkPaymentTermCommentId = pkPaymentTermCommentId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
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

    public MasterPaymentTerm getPkPaymentTermId() {
        return pkPaymentTermId;
    }

    public void setPkPaymentTermId(MasterPaymentTerm pkPaymentTermId) {
        this.pkPaymentTermId = pkPaymentTermId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPaymentTermCommentId != null ? pkPaymentTermCommentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPaymentTermComment)) {
            return false;
        }
        MasterPaymentTermComment other = (MasterPaymentTermComment) object;
        if ((this.pkPaymentTermCommentId == null && other.pkPaymentTermCommentId != null) || (this.pkPaymentTermCommentId != null && !this.pkPaymentTermCommentId.equals(other.pkPaymentTermCommentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterPaymentTermComment[ pkPaymentTermCommentId=" + pkPaymentTermCommentId + " ]";
    }
    
}
