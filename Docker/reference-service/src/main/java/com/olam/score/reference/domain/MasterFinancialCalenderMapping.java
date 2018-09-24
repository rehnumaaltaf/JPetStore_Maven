/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.reference.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_FINANCIAL_CALENDER_MAPPING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterFinancialCalenderMapping.findAll", query = "SELECT m FROM MasterFinancialCalenderMapping m")})
public class MasterFinancialCalenderMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
   	@SequenceGenerator(name="MASTER_FINANCIAL_CALENDER_MAPPING_PK_FIN_CAL_ID_GENERATOR", sequenceName="FIN_CAL_MAPPING_ID_SEQ",initialValue = 10, allocationSize = 1)
   	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_FINANCIAL_CALENDER_MAPPING_PK_FIN_CAL_ID_GENERATOR")
     
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_FIN_CAL_MAPPING_ID")
    private Integer pkFinCalMappingId;
    @Size(max = 200)
    @Column(name = "FIN_CAL_MAPPING_NAME")
    private String finCalMappingName;
    @Size(max = 200)
    @Column(name = "FIN_CAL_MAPPING_MONTH_NAME")
    private String finCalMappingMonthName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FIN_CAL_MAPPING_START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finCalMappingStartDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FIN_CAL_MAPPING_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finCalMappingEndDate;
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
   /* @JoinColumn(name = "FK_ERP_STATUS_ID", referencedColumnName = "PK_STATUS_ID")
    @ManyToOne(optional = false)
    private MasterStatus fkErpStatusId;
    @JoinColumn(name = "FK_STATUS_ID", referencedColumnName = "PK_STATUS_ID")
    @ManyToOne(optional = false)
    private MasterStatus fkStatusId;
*/
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Column(name = "FK_ERP_STATUS_ID")
    private Integer fkErpStatusId;
    @Column(name = "FK_FIN_CAL_ID")
    private Integer fkFinCalId;
    @OneToMany(mappedBy="fkFinCalMappingtId", cascade=CascadeType.ALL)
    private Collection<MasterFincalProduct> masterFincalProductList;
    
    public Collection<MasterFincalProduct> getMasterFincalProductList() {
		return masterFincalProductList;
	}

	public void setMasterFincalProductList(Collection<MasterFincalProduct> masterFincalProductList) {
		this.masterFincalProductList = masterFincalProductList;
	}

	public Integer getFkFinCalId() {
		return fkFinCalId;
	}

	public void setFkFinCalId(Integer fkFinCalId) {
		this.fkFinCalId = fkFinCalId;
	}

	public Integer getFkErpStatusId() {
		return fkErpStatusId;
	}

	public void setFkErpStatusId(Integer fkErpStatusId) {
		this.fkErpStatusId = fkErpStatusId;
	}

	public Integer getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

	public MasterFinancialCalenderMapping() {
    }

    public MasterFinancialCalenderMapping(Integer pkFinCalMappingId) {
        this.pkFinCalMappingId = pkFinCalMappingId;
    }

    public MasterFinancialCalenderMapping(Integer pkFinCalMappingId, Date finCalMappingStartDate, Date finCalMappingEndDate) {
        this.pkFinCalMappingId = pkFinCalMappingId;
        this.finCalMappingStartDate = finCalMappingStartDate;
        this.finCalMappingEndDate = finCalMappingEndDate;
    }

    public Integer getPkFinCalMappingId() {
        return pkFinCalMappingId;
    }

    public void setPkFinCalMappingId(Integer pkFinCalMappingId) {
        this.pkFinCalMappingId = pkFinCalMappingId;
    }

    public String getFinCalMappingName() {
        return finCalMappingName;
    }

    public void setFinCalMappingName(String finCalMappingName) {
        this.finCalMappingName = finCalMappingName;
    }

    public String getFinCalMappingMonthName() {
        return finCalMappingMonthName;
    }

    public void setFinCalMappingMonthName(String finCalMappingMonthName) {
        this.finCalMappingMonthName = finCalMappingMonthName;
    }

    public Date getFinCalMappingStartDate() {
        return finCalMappingStartDate;
    }

    public void setFinCalMappingStartDate(Date finCalMappingStartDate) {
        this.finCalMappingStartDate = finCalMappingStartDate;
    }

    public Date getFinCalMappingEndDate() {
        return finCalMappingEndDate;
    }

    public void setFinCalMappingEndDate(Date finCalMappingEndDate) {
        this.finCalMappingEndDate = finCalMappingEndDate;
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

   /* public MasterStatus getFkErpStatusId() {
        return fkErpStatusId;
    }

    public void setFkErpStatusId(MasterStatus fkErpStatusId) {
        this.fkErpStatusId = fkErpStatusId;
    }

    public MasterStatus getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(MasterStatus fkStatusId) {
        this.fkStatusId = fkStatusId;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkFinCalMappingId != null ? pkFinCalMappingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterFinancialCalenderMapping)) {
            return false;
        }
        MasterFinancialCalenderMapping other = (MasterFinancialCalenderMapping) object;
        if ((this.pkFinCalMappingId == null && other.pkFinCalMappingId != null) || (this.pkFinCalMappingId != null && !this.pkFinCalMappingId.equals(other.pkFinCalMappingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.reference.domain.MasterFinancialCalenderMapping[ pkFinCalMappingId=" + pkFinCalMappingId + " ]";
    }
    
}
