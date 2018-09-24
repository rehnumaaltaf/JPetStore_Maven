/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.authorizationconfig.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "AUTH_PERMISSION_LEVEL_GROUP")
@NamedQueries({
    @NamedQuery(name = "AuthPermissionLevelGroup.findAll", query = "SELECT a FROM AuthPermissionLevelGroup a")})
public class AuthPermissionLevelGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PERMISSION_LEVEL_GROUP_ID")
    private Integer pkPermissionLevelGroupId;
    @JoinColumn(name = "FK_PERMISSION_LEVEL_ID", referencedColumnName = "PK_PERMISSION_LEVEL_ID")
    @ManyToOne
    private AuthPermissionLevel fkPermissionLevelId;
    @JoinColumn(name = "FK_INHERITED_PERMISSION_LEVEL_ID", referencedColumnName = "PK_PERMISSION_LEVEL_ID")
    @ManyToOne
    private AuthPermissionLevel fkInheritedPermissionLevelId;

    public AuthPermissionLevelGroup() {
    }

    public AuthPermissionLevelGroup(Integer pkPermissionLevelGroupId) {
        this.pkPermissionLevelGroupId = pkPermissionLevelGroupId;
    }

    public Integer getPkPermissionLevelGroupId() {
        return pkPermissionLevelGroupId;
    }

    public void setPkPermissionLevelGroupId(Integer pkPermissionLevelGroupId) {
        this.pkPermissionLevelGroupId = pkPermissionLevelGroupId;
    }

    public AuthPermissionLevel getFkPermissionLevelId() {
        return fkPermissionLevelId;
    }

    public void setFkPermissionLevelId(AuthPermissionLevel fkPermissionLevelId) {
        this.fkPermissionLevelId = fkPermissionLevelId;
    }

    public AuthPermissionLevel getFkInheritedPermissionLevelId() {
        return fkInheritedPermissionLevelId;
    }

    public void setFkInheritedPermissionLevelId(AuthPermissionLevel fkInheritedPermissionLevelId) {
        this.fkInheritedPermissionLevelId = fkInheritedPermissionLevelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPermissionLevelGroupId != null ? pkPermissionLevelGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthPermissionLevelGroup)) {
            return false;
        }
        AuthPermissionLevelGroup other = (AuthPermissionLevelGroup) object;
        if ((this.pkPermissionLevelGroupId == null && other.pkPermissionLevelGroupId != null) || (this.pkPermissionLevelGroupId != null && !this.pkPermissionLevelGroupId.equals(other.pkPermissionLevelGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.authconfig.domain.AuthPermissionLevelGroup[ pkPermissionLevelGroupId=" + pkPermissionLevelGroupId + " ]";
    }
    
}
