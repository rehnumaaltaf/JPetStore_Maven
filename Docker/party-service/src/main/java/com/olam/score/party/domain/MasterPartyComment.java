/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.party.domain;

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

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_PARTY_COMMENT")
@NamedQueries({
    @NamedQuery(name = "MasterPartyComment.findAll", query = "SELECT m FROM MasterPartyComment m")})
public class MasterPartyComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PARTY_COMMENT_ID")
    private Integer pkPartyCommentId;
    @Size(max = 200)
    @Column(name = "COMMENT_ACTION")
    private String commentAction;
    @Size(max = 4000)
    @Column(name = "COMMENT_TEXT")
    private String commentText;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE", updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Size(max = 100)
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_DATE",  updatable = true, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "FK_PARTY_ID", referencedColumnName = "PK_PARTY_ID")
    @ManyToOne
    private MasterParty fkPartyId;

    public MasterPartyComment() {
    }

    public MasterPartyComment(Integer pkPartyCommentId) {
        this.pkPartyCommentId = pkPartyCommentId;
    }

    public Integer getPkPartyCommentId() {
        return pkPartyCommentId;
    }

    public void setPkPartyCommentId(Integer pkPartyCommentId) {
        this.pkPartyCommentId = pkPartyCommentId;
    }

    public String getCommentAction() {
        return commentAction;
    }

    public void setCommentAction(String commentAction) {
        this.commentAction = commentAction;
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

    public MasterParty getFkPartyId() {
        return fkPartyId;
    }

    public void setFkPartyId(MasterParty fkPartyId) {
        this.fkPartyId = fkPartyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPartyCommentId != null ? pkPartyCommentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterPartyComment)) {
            return false;
        }
        MasterPartyComment other = (MasterPartyComment) object;
        if ((this.pkPartyCommentId == null && other.pkPartyCommentId != null) || (this.pkPartyCommentId != null && !this.pkPartyCommentId.equals(other.pkPartyCommentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.party.domain.MasterPartyComment[ pkPartyCommentId=" + pkPartyCommentId + " ]";
    }
    
}
