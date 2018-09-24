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
@Table(name = "MASTER_UNIT_COMMENT")
@NamedQueries({
    @NamedQuery(name = "MasterUnitComment.findAll", query = "SELECT m FROM MasterUnitComment m")})
public class MasterUnitComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_UNIT_COMMENT_ID")
    private Integer pkUnitCommentId;
    @Size(max = 200)
    @Column(name = "COMMENT_ACTION")
    private String commentAction;
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
    @JoinColumn(name = "FK_UNIT_ID", referencedColumnName = "PK_UNIT_ID")
    @ManyToOne
    private MasterUnit fkUnitId;

    public MasterUnitComment() {
    }

    public MasterUnitComment(Integer pkUnitCommentId) {
        this.pkUnitCommentId = pkUnitCommentId;
    }

    public Integer getPkUnitCommentId() {
        return pkUnitCommentId;
    }

    public void setPkUnitCommentId(Integer pkUnitCommentId) {
        this.pkUnitCommentId = pkUnitCommentId;
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

    public MasterUnit getFkUnitId() {
        return fkUnitId;
    }

    public void setFkUnitId(MasterUnit fkUnitId) {
        this.fkUnitId = fkUnitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUnitCommentId != null ? pkUnitCommentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterUnitComment)) {
            return false;
        }
        MasterUnitComment other = (MasterUnitComment) object;
        if ((this.pkUnitCommentId == null && other.pkUnitCommentId != null) || (this.pkUnitCommentId != null && !this.pkUnitCommentId.equals(other.pkUnitCommentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.party.domain.MasterUnitComment[ pkUnitCommentId=" + pkUnitCommentId + " ]";
    }
    
}
