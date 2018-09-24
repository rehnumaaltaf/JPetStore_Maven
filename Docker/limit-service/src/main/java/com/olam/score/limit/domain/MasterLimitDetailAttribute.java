/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.limit.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rashmi.kedlaya
 */
@Entity
@Table(name = "MASTER_LIMIT_DETAIL_ATTRIBUTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLimitDetailAttribute.findAll", query = "SELECT m FROM MasterLimitDetailAttribute m")})
public class MasterLimitDetailAttribute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="MASTER_LIMIT_DETAIL_ATTR_LIMITDETAILATTRIDSEQ_GENERATOR", sequenceName="LIMIT_DETAIL_ATTRIBUTE_ID_SEQ",allocationSize=1)
	@GeneratedValue(generator="MASTER_LIMIT_DETAIL_ATTR_LIMITDETAILATTRIDSEQ_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LIMIT_DETAIL_ATTRIBUTE_ID")
    private Integer pkLimitDetailAttributeId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LIMIT_DATA")
    private BigDecimal limitData;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrencyId;
    @Column(name = "FK_UOM_ID")
    private Integer fkUomId;
    @JoinColumn(name = "FK_LIMIT_ATTRIBUTE_REF_ID", referencedColumnName = "PK_LIMIT_ATTRIBUTE_REF_ID")
    @ManyToOne
    private MasterLimitAttributeRef fkLimitAttributeRefId;
    @JoinColumn(name = "FK_LIMIT_DETAIL_ID", referencedColumnName = "PK_LIMIT_DETAIL_ID")
    @ManyToOne
    private MasterLimitDetail fkLimitDetailId;
    @JoinColumn(name = "FK_LIMIT_VOL_VALUE_REF_ID", referencedColumnName = "PK_LIMIT_VOL_VALUE_REF_ID")
    @ManyToOne
    private MasterLimitVolValueRef fkLimitVolValueRefId;

    public MasterLimitDetailAttribute() {
    }

    public MasterLimitDetailAttribute(Integer pkLimitDetailAttributeId) {
        this.pkLimitDetailAttributeId = pkLimitDetailAttributeId;
    }

    public Integer getPkLimitDetailAttributeId() {
        return pkLimitDetailAttributeId;
    }

    public void setPkLimitDetailAttributeId(Integer pkLimitDetailAttributeId) {
        this.pkLimitDetailAttributeId = pkLimitDetailAttributeId;
    }

    public BigDecimal getLimitData() {
        return limitData;
    }

    public void setLimitData(BigDecimal limitData) {
        this.limitData = limitData;
    }

    public Integer getFkCurrencyId() {
        return fkCurrencyId;
    }

    public void setFkCurrencyId(Integer fkCurrencyId) {
        this.fkCurrencyId = fkCurrencyId;
    }

    public Integer getFkUomId() {
        return fkUomId;
    }

    public void setFkUomId(Integer fkUomId) {
        this.fkUomId = fkUomId;
    }

    public MasterLimitAttributeRef getFkLimitAttributeRefId() {
        return fkLimitAttributeRefId;
    }

    public void setFkLimitAttributeRefId(MasterLimitAttributeRef fkLimitAttributeRefId) {
        this.fkLimitAttributeRefId = fkLimitAttributeRefId;
    }

    public MasterLimitDetail getFkLimitDetailId() {
        return fkLimitDetailId;
    }

    public void setFkLimitDetailId(MasterLimitDetail fkLimitDetailId) {
        this.fkLimitDetailId = fkLimitDetailId;
    }

    public MasterLimitVolValueRef getFkLimitVolValueRefId() {
        return fkLimitVolValueRefId;
    }

    public void setFkLimitVolValueRefId(MasterLimitVolValueRef fkLimitVolValueRefId) {
        this.fkLimitVolValueRefId = fkLimitVolValueRefId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLimitDetailAttributeId != null ? pkLimitDetailAttributeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLimitDetailAttribute)) {
            return false;
        }
        MasterLimitDetailAttribute other = (MasterLimitDetailAttribute) object;
        if ((this.pkLimitDetailAttributeId == null && other.pkLimitDetailAttributeId != null) || (this.pkLimitDetailAttributeId != null && !this.pkLimitDetailAttributeId.equals(other.pkLimitDetailAttributeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.terms.domain.MasterLimitDetailAttribute[ pkLimitDetailAttributeId=" + pkLimitDetailAttributeId + " ]";
    }
    
}
