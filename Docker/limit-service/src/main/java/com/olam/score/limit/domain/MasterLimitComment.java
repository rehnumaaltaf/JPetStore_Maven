/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

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
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_LIMIT_COMMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLimitComment.findAll", query = "SELECT m FROM MasterLimitComment m")})
public class MasterLimitComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="MASTER_LIMIT_COMMENTIDSEQ_GENERATOR", sequenceName="LIMIT_COMMENT_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_LIMIT_COMMENTIDSEQ_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LIMIT_COMMENT_ID")
    private Integer pkLimitCommentId;
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
    @JoinColumn(name = "FK_LIMIT_ID", referencedColumnName = "PK_LIMIT_ID")
    @ManyToOne
    private MasterLimit fkLimitId;

    public MasterLimitComment() {
    }

    public MasterLimitComment(Integer pkLimitCommentId) {
        this.pkLimitCommentId = pkLimitCommentId;
    }

    public Integer getPkLimitCommentId() {
        return pkLimitCommentId;
    }

    public void setPkLimitCommentId(Integer pkLimitCommentId) {
        this.pkLimitCommentId = pkLimitCommentId;
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

    public MasterLimit getFkLimitId() {
        return fkLimitId;
    }

    public void setFkLimitId(MasterLimit fkLimitId) {
        this.fkLimitId = fkLimitId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLimitCommentId != null ? pkLimitCommentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLimitComment)) {
            return false;
        }
        MasterLimitComment other = (MasterLimitComment) object;
        if ((this.pkLimitCommentId == null && other.pkLimitCommentId != null) || (this.pkLimitCommentId != null && !this.pkLimitCommentId.equals(other.pkLimitCommentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterLimitComment[ pkLimitCommentId=" + pkLimitCommentId + " ]";
    }
    
}
