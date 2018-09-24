/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.common.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_CUSTOM_VIEW")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterCustomView.findAll", query = "SELECT m FROM MasterCustomView m")
    , @NamedQuery(name = "MasterCustomView.findByPkCustomViewId", query = "SELECT m FROM MasterCustomView m WHERE m.pkCustomViewId = :pkCustomViewId")
    , @NamedQuery(name = "MasterCustomView.findByCustomViewName", query = "SELECT m FROM MasterCustomView m WHERE m.customViewName = :customViewName")
    , @NamedQuery(name = "MasterCustomView.findByCustomViewDesc", query = "SELECT m FROM MasterCustomView m WHERE m.customViewDesc = :customViewDesc")
    , @NamedQuery(name = "MasterCustomView.findByFkEntityId", query = "SELECT m FROM MasterCustomView m WHERE m.fkEntityId = :fkEntityId")
    , @NamedQuery(name = "MasterCustomView.findByCustomViewIsDefault", query = "SELECT m FROM MasterCustomView m WHERE m.customViewIsDefault = :customViewIsDefault")
    , @NamedQuery(name = "MasterCustomView.findByFkViewScopeId", query = "SELECT m FROM MasterCustomView m WHERE m.fkViewScopeId = :fkViewScopeId")
    , @NamedQuery(name = "MasterCustomView.findByCustomViewUser", query = "SELECT m FROM MasterCustomView m WHERE m.customViewUser = :customViewUser")
    , @NamedQuery(name = "MasterCustomView.findByFkStatusId", query = "SELECT m FROM MasterCustomView m WHERE m.fkStatusId = :fkStatusId")})
public class MasterCustomView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_CUSTOM_VIEW_ID")
    private Integer pkCustomViewId;
    @Size(max = 100)
    @Column(name = "CUSTOM_VIEW_NAME")
    private String customViewName;
    @Size(max = 500)
    @Column(name = "CUSTOM_VIEW_DESC")
    private String customViewDesc;
    @Column(name = "FK_ENTITY_ID")
    private Integer fkEntityId;
    @Size(max = 1)
    @Column(name = "CUSTOM_VIEW_IS_DEFAULT")
    private String customViewIsDefault;
    @Column(name = "FK_VIEW_SCOPE_ID")
    private Integer fkViewScopeId;
    @Size(max = 100)
    @Column(name = "CUSTOM_VIEW_USER")
    private String customViewUser;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;


    public MasterCustomView() {
    }

    public MasterCustomView(Integer pkCustomViewId) {
        this.pkCustomViewId = pkCustomViewId;
    }

    public Integer getPkCustomViewId() {
        return pkCustomViewId;
    }

    public void setPkCustomViewId(Integer pkCustomViewId) {
        this.pkCustomViewId = pkCustomViewId;
    }

    public String getCustomViewName() {
        return customViewName;
    }

    public void setCustomViewName(String customViewName) {
        this.customViewName = customViewName;
    }

    public String getCustomViewDesc() {
        return customViewDesc;
    }

    public void setCustomViewDesc(String customViewDesc) {
        this.customViewDesc = customViewDesc;
    }

    public Integer getFkEntityId() {
        return fkEntityId;
    }

    public void setFkEntityId(Integer fkEntityId) {
        this.fkEntityId = fkEntityId;
    }

    public String getCustomViewIsDefault() {
        return customViewIsDefault;
    }

    public void setCustomViewIsDefault(String customViewIsDefault) {
        this.customViewIsDefault = customViewIsDefault;
    }

    public Integer getFkViewScopeId() {
        return fkViewScopeId;
    }

    public void setFkViewScopeId(Integer fkViewScopeId) {
        this.fkViewScopeId = fkViewScopeId;
    }

    public String getCustomViewUser() {
        return customViewUser;
    }

    public void setCustomViewUser(String customViewUser) {
        this.customViewUser = customViewUser;
    }

    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCustomViewId != null ? pkCustomViewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterCustomView)) {
            return false;
        }
        MasterCustomView other = (MasterCustomView) object;
        if ((this.pkCustomViewId == null && other.pkCustomViewId != null) || (this.pkCustomViewId != null && !this.pkCustomViewId.equals(other.pkCustomViewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.MasterCustomView[ pkCustomViewId=" + pkCustomViewId + " ]";
    }
    
}
