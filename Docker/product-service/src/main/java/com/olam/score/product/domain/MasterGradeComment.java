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
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_GRADE_COMMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterGradeComment.findAll", query = "SELECT m FROM MasterGradeComment m")})
public class MasterGradeComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_GRADE_COMMENT_ID")
    private Integer pkGradeCommentId;
    @Size(max = 200)
    @Column(name = "ACTION")
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
    @JoinColumn(name = "FK_GRADE_ID", referencedColumnName = "PK_GRADE_ID")
    @ManyToOne
    private MasterGrade fkGradeId;

    public MasterGradeComment() {
    }

    public MasterGradeComment(Integer pkGradeCommentId) {
        this.pkGradeCommentId = pkGradeCommentId;
    }

    public Integer getPkGradeCommentId() {
        return pkGradeCommentId;
    }

    public void setPkGradeCommentId(Integer pkGradeCommentId) {
        this.pkGradeCommentId = pkGradeCommentId;
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

    public MasterGrade getFkGradeId() {
        return fkGradeId;
    }

    public void setFkGradeId(MasterGrade fkGradeId) {
        this.fkGradeId = fkGradeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkGradeCommentId != null ? pkGradeCommentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterGradeComment)) {
            return false;
        }
        MasterGradeComment other = (MasterGradeComment) object;
        if ((this.pkGradeCommentId == null && other.pkGradeCommentId != null) || (this.pkGradeCommentId != null && !this.pkGradeCommentId.equals(other.pkGradeCommentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.product.domain.MasterGradeComment[ pkGradeCommentId=" + pkGradeCommentId + " ]";
    }
    
}
