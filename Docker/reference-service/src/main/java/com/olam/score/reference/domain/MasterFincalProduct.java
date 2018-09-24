package com.olam.score.reference.domain;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MASTER_FINANCIAL_CAL_PRODUCT")
@XmlRootElement
public class MasterFincalProduct {
	
	
	 private static final long serialVersionUID = 1L;
	    @Id
	   	@SequenceGenerator(name="MASTER_FINANCIAL_CALENDER_MAPPING_PK_FINANCIAL_CAL_PRODUCT_ID_SEQ_GENERATOR", sequenceName="reference.FINANCIAL_CAL_PRODUCT_ID_SEQ",initialValue = 10, allocationSize = 1)
	   	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASTER_FINANCIAL_CALENDER_MAPPING_PK_FINANCIAL_CAL_PRODUCT_ID_SEQ_GENERATOR")
	    @Basic(optional = false)
	    @NotNull
	    @Column(name = "PK_FINANCIAL_CAL_PRODUCT_ID")
	    private Integer pkFinCalProductId;
	    
	   @JoinColumn(name="FK_FIN_CAL_MAPPING_ID", referencedColumnName="PK_FIN_CAL_MAPPING_ID")
	   @ManyToOne(cascade=CascadeType.ALL)
	   private MasterFinancialCalenderMapping fkFinCalMappingtId;
	  
	    @Size(max = 100)
	    @Column(name = "CREATED_BY")
	    private String createdBy;
	    public Integer getPkFinCalProductId() {
			return pkFinCalProductId;
		}
		public void setPkFinCalProductId(Integer pkFinCalProductId) {
			this.pkFinCalProductId = pkFinCalProductId;
		}
		public MasterFinancialCalenderMapping getFkFinCalMappingtId() {
			return fkFinCalMappingtId;
		}
		public void setFkFinCalMappingtId(MasterFinancialCalenderMapping fkFinCalMappingtId) {
			this.fkFinCalMappingtId = fkFinCalMappingtId;
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
		/*public Integer getFkErpStatusId() {
			return fkErpStatusId;
		}
		public void setFkErpStatusId(Integer fkErpStatusId) {
			this.fkErpStatusId = fkErpStatusId;
		}*/
		public Integer getFkProdId() {
			return fkProdId;
		}
		public void setFkProdId(Integer fkProdId) {
			this.fkProdId = fkProdId;
		}
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
	    /*@Column(name = "FK_ERP_STATUS_ID")
	    private Integer fkErpStatusId;*/
	    @Column(name = "FK_PROD_ID")
	    private Integer fkProdId;
	
	

}
