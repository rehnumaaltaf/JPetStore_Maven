package com.olam.score.cost.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Prabhakar_Koyi
 */
@Entity
@Table(name = "MASTER_WH_COST_DETAIL", catalog = "SCORE_DEV_002", schema = "cost")
@XmlRootElement
public class MasterWhCostDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WH_COST_DETAIL_ID_SEQ")
   	@SequenceGenerator(name="WH_COST_DETAIL_ID_SEQ", sequenceName="cost.WH_COST_DETAIL_ID_SEQ",initialValue=1,allocationSize=1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_WH_COST_DETAIL_ID")
    private Integer pkWhCostDetailId;
    
    
    @JoinColumn(name = "FK_WH_COST_ID")
    @ManyToOne
    private MasterWhCost fkWhCostId;
    
    @JoinColumn(name="FK_WH_COST_TYPE_REF_ID")
    @ManyToOne
    private MasterWhCostTypeRef fkWhCostTypeRefId;
    
    
    
    public MasterWhCostTypeRef getFkWhCostTypeRefId() {
		return fkWhCostTypeRefId;
	}



	public void setFkWhCostTypeRefId(MasterWhCostTypeRef fkWhCostTypeRefId) {
		this.fkWhCostTypeRefId = fkWhCostTypeRefId;
	}

	@Column(name="RATE")
    private BigDecimal rate;
    
    @Column(name = "FK_RATE_UOM_ID")
    private Integer fkUomId;
    @Column(name = "FK_CURRENCY_ID")
    private Integer fkCurrencyId;
    @Column(name = "FK_STATUS_ID")
    private Integer fkStatusId;
    
    
	@Column(name="FK_RATE_BASIS_REF_ID")
	private int fkRateBasisRefId;
	@Column(name="FK_RATE_PRIMARY_PACKING_ID")
	private int fkRatePrimaryPackingId;
	@Column(name="FK_RATE_SECONDARY_PACKING_ID")
	private int fkRateSecondaryPacking;
    
    
   



	public int getFkRateBasisRefId() {
		return fkRateBasisRefId;
	}



	public void setFkRateBasisRefId(int fkRateBasisRefId) {
		this.fkRateBasisRefId = fkRateBasisRefId;
	}



	



	public int getFkRateSecondaryPacking() {
		return fkRateSecondaryPacking;
	}



	public void setFkRateSecondaryPacking(int fkRateSecondaryPacking) {
		this.fkRateSecondaryPacking = fkRateSecondaryPacking;
	}



	public void setFkRatePrimaryPackingId(int fkRatePrimaryPackingId) {
		this.fkRatePrimaryPackingId = fkRatePrimaryPackingId;
	}

	/* @JoinColumn(name = "FK_WH_RENT_TIME_BASIS_REF_ID", referencedColumnName = "PK_WH_RENT_TIME_BASIS_REF_ID")
    @ManyToOne
    private MasterWhRentTimeBasisRef fkWhRentztimeBasisRefId;*/
    @JoinColumn(name = "FK_WH_RENT_TIME_BASIS_REF_ID")
	@ManyToOne
    private MasterWhRentTimeBasisRef fkWhRentztimeBasisRefId;
    
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_1", length = 500)
    private String customAttribute1;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_2", length = 500)
    private String customAttribute2;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_3", length = 500)
    private String customAttribute3;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_4", length = 500)
    private String customAttribute4;
    @Size(max = 500)
    @Column(name = "CUSTOM_ATTRIBUTE_5", length = 500)
    private String customAttribute5;
    @Column(name = "CUSTOM_ATTRIBUTE_6", precision = 53)
    private Double customAttribute6;
    @Column(name = "CUSTOM_ATTRIBUTE_7", precision = 53)
    private Double customAttribute7;
    @Column(name = "CUSTOM_ATTRIBUTE_8", precision = 53)
    private Double customAttribute8;
    @Size(max = 500)
    @Column(name="REMARKS")
    private String remarks;
    
    

    



	public MasterWhRentTimeBasisRef getFkWhRentztimeBasisRefId() {
		return fkWhRentztimeBasisRefId;
	}



	public void setFkWhRentztimeBasisRefId(MasterWhRentTimeBasisRef fkWhRentztimeBasisRefId) {
		this.fkWhRentztimeBasisRefId = fkWhRentztimeBasisRefId;
	}



	public Integer getPkWhCostDetailId() {
		return pkWhCostDetailId;
	}



	public void setPkWhCostDetailId(Integer pkWhCostDetailId) {
		this.pkWhCostDetailId = pkWhCostDetailId;
	}



	public MasterWhCost getFkWhCostId() {
		return fkWhCostId;
	}



	public void setFkWhCostId(MasterWhCost fkWhCostId) {
		this.fkWhCostId = fkWhCostId;
	}




	public Integer getFkUomId() {
		return fkUomId;
	}



	public void setFkUomId(Integer fkUomId) {
		this.fkUomId = fkUomId;
	}



	public Integer getFkCurrencyId() {
		return fkCurrencyId;
	}



	public void setFkCurrencyId(Integer fkCurrencyId) {
		this.fkCurrencyId = fkCurrencyId;
	}



	public Integer getFkStatusId() {
		return fkStatusId;
	}



	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}






	public Integer getFkRatePrimaryPackingId() {
		return fkRatePrimaryPackingId;
	}



	public void setFkRatePrimaryPackingId(Integer fkRatePrimaryPackingId) {
		this.fkRatePrimaryPackingId = fkRatePrimaryPackingId;
	}



	/*public MasterWhRentTimeBasisRef getFkWhRentztimeBasisRefId() {
		return fkWhRentztimeBasisRefId;
	}



	public void setFkWhRentztimeBasisRefId(MasterWhRentTimeBasisRef fkWhRentztimeBasisRefId) {
		this.fkWhRentztimeBasisRefId = fkWhRentztimeBasisRefId;
	}*/



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public MasterWhCostDetail() {
    }

   

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    
    
}
