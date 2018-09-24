package com.olam.score.cost.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MASTER_WH_RENT_TIME_BASIS_REF", catalog = "SCORE_DEV_002", schema = "cost")
@XmlRootElement
public class MasterWhRentTimeBasisRef {
	
	
	 @Id
	 @Basic(optional = false)
	 @NotNull
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WH_RENT_TIME_BASIS_REF_ID_SEQ")
	   	@SequenceGenerator(name="WH_RENT_TIME_BASIS_REF_ID_SEQ", sequenceName="cost.WH_RENT_TIME_BASIS_REF_ID_SEQ",initialValue=1,allocationSize=1)
	 @Column(name = "PK_WH_RENT_TIME_BASIS_REF_ID")
	 private Integer pkWhRentTimeBasisRefId;
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
	    @Column(name = "WH_RENT_TIME_BASIS_CODE")
	    private String whRentTimeBasisCode;
	    @Column(name = "WH_RENT_TIME_BASIS_NAME")
	    private String whRentTimeBasisName;  
	    @Column(name = "WH_RENT_TIME_BASIS_DESC")
	    private String whRentTimeBasisDesc;   
	   
		public Integer getPkWhRentTimeBasisRefId() {
			return pkWhRentTimeBasisRefId;
		}
		public void setPkWhRentTimeBasisRefId(Integer pkWhRentTimeBasisRefId) {
			this.pkWhRentTimeBasisRefId = pkWhRentTimeBasisRefId;
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
		public String getWhRentTimeBasisCode() {
			return whRentTimeBasisCode;
		}
		public void setWhRentTimeBasisCode(String whRentTimeBasisCode) {
			this.whRentTimeBasisCode = whRentTimeBasisCode;
		}
		public String getWhRentTimeBasisName() {
			return whRentTimeBasisName;
		}
		public void setWhRentTimeBasisName(String whRentTimeBasisName) {
			this.whRentTimeBasisName = whRentTimeBasisName;
		}
		public String getWhRentTimeBasisDesc() {
			return whRentTimeBasisDesc;
		}
		public void setWhRentTimeBasisDesc(String whRentTimeBasisDesc) {
			this.whRentTimeBasisDesc = whRentTimeBasisDesc;
		}
		
		    
}
