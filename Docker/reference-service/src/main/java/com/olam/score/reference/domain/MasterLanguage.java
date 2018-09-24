/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_LANGUAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLanguage.findAll", query = "SELECT m FROM MasterLanguage m")})
public @Data class MasterLanguage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LANGUAGE_ID")
    private Integer languageId;
    @Size(max = 20)
    @Column(name = "LANGUAGE_CODE")
    private String languageCode;
    @Size(max = 20)
    @Column(name = "LANGUAGE_FULLNAME")
    private String languageFullname;
    @Column(name = "FK_STATUS_ID")
    private int fkStatusId;

       @Override
    public int hashCode() {
        int hash = 0;
        hash += (languageId != null ? languageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLanguage)) {
            return false;
        }
        MasterLanguage other = (MasterLanguage) object;
        if ((this.languageId == null && other.languageId != null) || (this.languageId != null && !this.languageId.equals(other.languageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterLanguage[ pkLanguageId=" + languageId + " ]";
    }
    
}
