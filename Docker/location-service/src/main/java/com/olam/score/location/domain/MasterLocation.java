/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.location.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.beans.factory.annotation.Autowired;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.util.WebServiceCallUtil;
import com.olam.score.location.dto.LocationDTO;

/**
 *
 * @author ramachandran_n02
 */
@Entity
@Table(name = "MASTER_LOCATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterLocation.findAll", query = "SELECT m FROM MasterLocation m")})
public class MasterLocation implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_LOC_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_LOC_LOCIDSEQ_GENERATOR")
   	@SequenceGenerator(name="MASTER_LOC_LOCIDSEQ_GENERATOR", sequenceName="LOC_ID_SEQ",allocationSize=1)
    private Integer pkLocId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "LOC_NAME")
    private String locName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LOC_CODE")
    private String locCode;
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
    @Size(max = 200)
    @Column(name = "LOC_FULLNAME")
    private String locFullname;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @OneToMany(mappedBy = "pkLocId")
    private Collection<MasterLocLocTypeAssoc> masterLocLocTypeAssocCollection;
    @JoinColumn(name = "FK_GEO_ID", referencedColumnName = "PK_GEO_ID")
    @ManyToOne
    private MasterGeography fkGeoId;
  /*  @OneToMany(cascade = CascadeType.ALL,mappedBy = "pkLocId")
    private List<MasterLocLocTypeAssoc> masterLocLocTypeAssoc;*/
   
    @Column(name = "RATE")
    private Float rate;
    @Column(name = "FK_RATE_BASIS_REF_ID")
    private Integer fkRateBasisRefId;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrencyId;
    @Column(name = "RATE_BASIS_UOM")
    private Integer rateBasisUom;
    @Column(name = "RATE_BASIS_PRIMARY_PACKING")
    private Integer rateBasisPrimaryPacking;
    @Column(name = "RATE_BASIS_SECONDARY_PACKING")
    private Integer rateBasisSecondaryPacking;
    
    @Column(name="FK_LOC_TYPE_ID")
    private Integer fkLocTypeId;
    
    public Integer getRateBasisUom() {
		return rateBasisUom;
	}

	public void setRateBasisUom(Integer rateBasisUom) {
		this.rateBasisUom = rateBasisUom;
	}

	public Integer getFkLocTypeId() {
		return fkLocTypeId;
	}

	public void setFkLocTypeId(Integer fkLocTypeId) {
		this.fkLocTypeId = fkLocTypeId;
	}

	public MasterLocation() {
    }

    public MasterLocation(Integer pkLocId) {
        this.pkLocId = pkLocId;
    }

    public MasterLocation(Integer pkLocId, String locName, String locCode) {
        this.pkLocId = pkLocId;
        this.locName = locName;
        this.locCode = locCode;
    }

    public Integer getPkLocId() {
        return pkLocId;
    }

    public void setPkLocId(Integer pkLocId) {
        this.pkLocId = pkLocId;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
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

    public String getLocFullname() {
        return locFullname;
    }

    public void setLocFullname(String locFullname) {
        this.locFullname = locFullname;
    }
    
    public Integer getFkStatusId() {
        return fkStatusId;
    }

    public void setFkStatusId(Integer fkStatusId) {
        this.fkStatusId = fkStatusId;
    }

    @XmlTransient
    public Collection<MasterLocLocTypeAssoc> getMasterLocLocTypeAssocCollection() {
        return masterLocLocTypeAssocCollection;
    }

    public void setMasterLocLocTypeAssocCollection(Collection<MasterLocLocTypeAssoc> masterLocLocTypeAssocCollection) {
        this.masterLocLocTypeAssocCollection = masterLocLocTypeAssocCollection;
    }

    public MasterGeography getFkGeoId() {
        return fkGeoId;
    }

    public void setFkGeoId(MasterGeography fkGeoId) {
        this.fkGeoId = fkGeoId;
    }
    
    
    /*public List<MasterLocLocTypeAssoc> getMasterLocLocTypeAssoc() {
		return masterLocLocTypeAssoc;
	}

	public void setMasterLocLocTypeAssoc(List<MasterLocLocTypeAssoc> masterLocLocTypeAssoc) {
		this.masterLocLocTypeAssoc = masterLocLocTypeAssoc;
	}*/
    
	


	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Integer getFkRateBasisRefId() {
		return fkRateBasisRefId;
	}

	public void setFkRateBasisRefId(Integer fkRateBasisRefId) {
		this.fkRateBasisRefId = fkRateBasisRefId;
	}

	public Integer getFkCurrencyId() {
		return fkCurrencyId;
	}

	public void setFkCurrencyId(Integer fkCurrencyId) {
		this.fkCurrencyId = fkCurrencyId;
	}

	/*public String getRateBasisUom() {
		return rateBasisUom;
	}

	public void setRateBasisUom(String rateBasisUom) {
		this.rateBasisUom = rateBasisUom;
	}*/

	public Integer getRateBasisPrimaryPacking() {
		return rateBasisPrimaryPacking;
	}

	public void setRateBasisPrimaryPacking(Integer rateBasisPrimaryPacking) {
		this.rateBasisPrimaryPacking = rateBasisPrimaryPacking;
	}

	public Integer getRateBasisSecondaryPacking() {
		return rateBasisSecondaryPacking;
	}

	public void setRateBasisSecondaryPacking(Integer rateBasisSecondaryPacking) {
		this.rateBasisSecondaryPacking = rateBasisSecondaryPacking;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLocId != null ? pkLocId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasterLocation)) {
            return false;
        }
        MasterLocation other = (MasterLocation) object;
        if ((this.pkLocId == null && other.pkLocId != null) || (this.pkLocId != null && !this.pkLocId.equals(other.pkLocId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.olam.score.location.domain.MasterLocation[ pkLocId=" + pkLocId + " ]";
    }
    
	public MasterLocation getMappingLocation(LocationDTO inputData, MasterLocation masterLocation,Map<Integer, String> statusMap) {
		String action = inputData.getAction();
		MasterGeography masterGeography = new MasterGeography();
		List<MasterLocLocTypeAssoc> list = new ArrayList<>();
		if (action.equalsIgnoreCase(Constants.SAVE)) {
			inputData.setStatusName(Constants.DRAFT);
		} else if (action.equalsIgnoreCase(Constants.SUBMIT)) {
			inputData.setStatusName(Constants.ACTIVE);
		} else if (action.equalsIgnoreCase(Constants.DEACTIVE)) {

			inputData.setStatusName(Constants.INACTIVE);
		}

		Optional<Integer> statusId = statusMap.entrySet().stream()
				.filter(e -> e.getValue().equals(inputData.getStatusName())).map(Map.Entry::getKey).findFirst();

		masterLocation.setFkStatusId(statusId.get());
		masterLocation.setFkLocTypeId(inputData.getFkLocTypeId());
		masterLocation.setCreatedBy("");
		masterLocation.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		masterLocation.setPkLocId(inputData.getPkLocId());
		masterLocation.setLocName(inputData.getLocName());
		masterLocation.setLocCode(inputData.getLocCode());
		masterLocation.setLocFullname(inputData.getLocFullName());
		masterGeography.setPkGeoId(inputData.getFkGeoId());
		masterLocation.setFkGeoId(masterGeography);

		masterLocation.setFkCurrencyId(inputData.getFkCurrencyId());
		masterLocation.setRate(inputData.getRate());
		masterLocation.setFkRateBasisRefId(inputData.getFkRateBasisRefId());
		masterLocation.setRateBasisUom(inputData.getRateBasisUomId());
		masterLocation.setRateBasisPrimaryPacking(inputData.getRateBasisPrimaryPackingId());
		masterLocation.setRateBasisSecondaryPacking(inputData.getRateBasisSecondaryPackingId());
		return masterLocation;
	}
    
	
}
