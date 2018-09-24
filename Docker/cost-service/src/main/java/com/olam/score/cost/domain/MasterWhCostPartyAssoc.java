
package com.olam.score.cost.domain;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Prabhakar_Koyi
 */
@Entity
@Table(name = "MASTER_WH_COST_PARTY_ASSOC",schema = "cost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MasterWhCostPartyAssoc.findAll", query = "SELECT m FROM MasterWhCostPartyAssoc m"),
    @NamedQuery(name = "MasterWhCostPartyAssoc.findByCustomAttribute1", query = "SELECT m FROM MasterWhCostPartyAssoc m WHERE m.customAttribute1 = :customAttribute1"),
    @NamedQuery(name = "MasterWhCostPartyAssoc.findByCustomAttribute2", query = "SELECT m FROM MasterWhCostPartyAssoc m WHERE m.customAttribute2 = :customAttribute2"),
    @NamedQuery(name = "MasterWhCostPartyAssoc.findByCustomAttribute3", query = "SELECT m FROM MasterWhCostPartyAssoc m WHERE m.customAttribute3 = :customAttribute3"),
    @NamedQuery(name = "MasterWhCostPartyAssoc.findByCustomAttribute4", query = "SELECT m FROM MasterWhCostPartyAssoc m WHERE m.customAttribute4 = :customAttribute4"),
    @NamedQuery(name = "MasterWhCostPartyAssoc.findByCustomAttribute5", query = "SELECT m FROM MasterWhCostPartyAssoc m WHERE m.customAttribute5 = :customAttribute5"),
    @NamedQuery(name = "MasterWhCostPartyAssoc.findByCustomAttribute6", query = "SELECT m FROM MasterWhCostPartyAssoc m WHERE m.customAttribute6 = :customAttribute6"),
    @NamedQuery(name = "MasterWhCostPartyAssoc.findByCustomAttribute7", query = "SELECT m FROM MasterWhCostPartyAssoc m WHERE m.customAttribute7 = :customAttribute7"),
    @NamedQuery(name = "MasterWhCostPartyAssoc.findByCustomAttribute8", query = "SELECT m FROM MasterWhCostPartyAssoc m WHERE m.customAttribute8 = :customAttribute8")})
public class MasterWhCostPartyAssoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WH_COST_PARTY_ASSOC_ID_SEQ")
   	@SequenceGenerator(name="WH_COST_PARTY_ASSOC_ID_SEQ", sequenceName="cost.WH_COST_PARTY_ASSOC_ID_SEQ",initialValue=1,allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_WH_COST_PARTY_ASSOC_ID")
    private Integer pkWhCostPartyAssocId;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_1")
    private String customAttribute1;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_2")
    private String customAttribute2;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_3")
    private String customAttribute3;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_4")
    private String customAttribute4;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_5")
    private String customAttribute5;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CUSTOM_ATTRIBUTE_6")
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7")
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8")
    private Double customAttribute8;

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
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    @Column(name = "FK_PARTY_ID")
    private Integer fkPartyId;
    
    @JoinColumn(name = "FK_WH_COST_ID")
    @ManyToOne(cascade = CascadeType.ALL)
    private MasterWhCost fkWhCostId;
    
    public Integer getPkWhCostPartyAssocId() {
		return pkWhCostPartyAssocId;
	}



	public void setPkWhCostPartyAssocId(Integer pkWhCostPartyAssocId) {
		this.pkWhCostPartyAssocId = pkWhCostPartyAssocId;
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



	public Integer getFkStatusId() {
		return fkStatusId;
	}



	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}



	public Integer getFkPartyId() {
		return fkPartyId;
	}



	public void setFkPartyId(Integer fkPartyId) {
		this.fkPartyId = fkPartyId;
	}



	public MasterWhCost getFkWhCostId() {
		return fkWhCostId;
	}



	public void setFkWhCostId(MasterWhCost fkWhCostId) {
		this.fkWhCostId = fkWhCostId;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public MasterWhCostPartyAssoc() {
    }

  

    public String getCustomAttribute1() {
        return customAttribute1;
    }

    public void setCustomAttribute1(String customAttribute1) {
        this.customAttribute1 = customAttribute1;
    }

    public String getCustomAttribute2() {
        return customAttribute2;
    }

    public void setCustomAttribute2(String customAttribute2) {
        this.customAttribute2 = customAttribute2;
    }

    public String getCustomAttribute3() {
        return customAttribute3;
    }

    public void setCustomAttribute3(String customAttribute3) {
        this.customAttribute3 = customAttribute3;
    }

    public String getCustomAttribute4() {
        return customAttribute4;
    }

    public void setCustomAttribute4(String customAttribute4) {
        this.customAttribute4 = customAttribute4;
    }

    public String getCustomAttribute5() {
        return customAttribute5;
    }

    public void setCustomAttribute5(String customAttribute5) {
        this.customAttribute5 = customAttribute5;
    }

    public Double getCustomAttribute6() {
        return customAttribute6;
    }

    public void setCustomAttribute6(Double customAttribute6) {
        this.customAttribute6 = customAttribute6;
    }

    public Double getCustomAttribute7() {
        return customAttribute7;
    }

    public void setCustomAttribute7(Double customAttribute7) {
        this.customAttribute7 = customAttribute7;
    }

    public Double getCustomAttribute8() {
        return customAttribute8;
    }

    public void setCustomAttribute8(Double customAttribute8) {
        this.customAttribute8 = customAttribute8;
    }

   
  
    
}
