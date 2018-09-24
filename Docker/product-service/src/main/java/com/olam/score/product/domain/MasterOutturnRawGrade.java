/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olam.score.product.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

import org.modelmapper.ModelMapper;

import com.olam.score.common.util.ScoreBaseException;
import com.olam.score.product.dto.MasterOutturnDto;
import com.olam.score.product.dto.OutturnRatioDto;
import com.olam.score.product.dto.OutturnRawGradeDto;

/**
 *
 * @author Ramachandran_N02
 */
@Entity
@Table(name = "MASTER_OUTTURN_RAW_GRADE")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "MasterOutturnRawGrade.findAll", query = "SELECT m FROM MasterOutturnRawGrade m") })
public class MasterOutturnRawGrade implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_OUTTURN_OUTTURNRAWGRADEIDSEQ_GENERATOR")
	@SequenceGenerator(name = "PRODUCT_OUTTURN_OUTTURNRAWGRADEIDSEQ_GENERATOR", sequenceName = "OUTTURN_RAW_GRADE_ID_SEQ", initialValue = 1, allocationSize = 1)
	@Column(name = "PK_OUTTURN_RAW_GRADE_ID")
	private Integer pkOutturnRawGradeId;
	@Size(max = 20)
	@Column(name = "RAW_GRADE_CODE")
	private String rawGradeCode;
	@Size(max = 200)
	@Column(name = "RAW_GRADE_NAME")
	private String rawGradeName;
	@Size(max = 1000)
	@Column(name = "RAW_GRADE_FULLNAME")
	private String rawGradeFullname;
	@Size(max = 100)
	@Column(name = "CREATED_BY", updatable = false)
	private String createdBy;
	@Column(name = "CREATED_DATE", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Size(max = 100)
	@Column(name = "MODIFIED_BY")
	private String modifiedBy;
	@Column(name = "MODIFIED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
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
	@Column(name = "CUSTOM_ATTRIBUTE_6")
	private BigInteger customAttribute6;
	@Column(name = "CUSTOM_ATTRIBUTE_7")
	private BigInteger customAttribute7;
	@Column(name = "CUSTOM_ATTRIBUTE_8")
	private BigInteger customAttribute8;
	@Column(name = "FK_STATUS_ID")
	private Integer fkStatusId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkOutturnRawGradeId")
	private Collection<MasterOutturnRatio> masterOutturnRatioCollection;
	/*@JoinColumn(name = "FK_GRADE_ID", referencedColumnName = "PK_GRADE_ID")
	 @ManyToOne*/
	@NotNull
	@Column(name = "FK_GRADE_ID")
	private Integer fkGradeId;
	/*@JoinColumn(name = "FK_ORIGIN_ID", referencedColumnName = "PK_ORIGIN_ID")
	@ManyToOne*/
	@NotNull
	@Column(name = "FK_ORIGIN_ID")
	private Integer fkOriginId;
	/*@JoinColumn(name = "FK_PROD_ID", referencedColumnName = "PK_PROD_ID")
	 @ManyToOne*/
	@NotNull
	@Column(name = "FK_PROD_ID")
	private Integer fkProdId;

	public MasterOutturnRawGrade() {
	}

	public MasterOutturnRawGrade(Integer pkOutturnRawGradeId) {
		this.pkOutturnRawGradeId = pkOutturnRawGradeId;
	}

	public Integer getPkOutturnRawGradeId() {
		return pkOutturnRawGradeId;
	}

	public void setPkOutturnRawGradeId(Integer pkOutturnRawGradeId) {
		this.pkOutturnRawGradeId = pkOutturnRawGradeId;
	}

	public String getRawGradeCode() {
		return rawGradeCode;
	}

	public void setRawGradeCode(String rawGradeCode) {
		this.rawGradeCode = rawGradeCode;
	}

	public String getRawGradeName() {
		return rawGradeName;
	}

	public void setRawGradeName(String rawGradeName) {
		this.rawGradeName = rawGradeName;
	}

	public String getRawGradeFullname() {
		return rawGradeFullname;
	}

	public void setRawGradeFullname(String rawGradeFullname) {
		this.rawGradeFullname = rawGradeFullname;
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

	public BigInteger getCustomAttribute6() {
		return customAttribute6;
	}

	public void setCustomAttribute6(BigInteger customAttribute6) {
		this.customAttribute6 = customAttribute6;
	}

	public BigInteger getCustomAttribute7() {
		return customAttribute7;
	}

	public void setCustomAttribute7(BigInteger customAttribute7) {
		this.customAttribute7 = customAttribute7;
	}

	public BigInteger getCustomAttribute8() {
		return customAttribute8;
	}

	public void setCustomAttribute8(BigInteger customAttribute8) {
		this.customAttribute8 = customAttribute8;
	}

	public Integer getFkStatusId() {
		return fkStatusId;
	}

	public void setFkStatusId(Integer fkStatusId) {
		this.fkStatusId = fkStatusId;
	}

	@XmlTransient
	public Collection<MasterOutturnRatio> getMasterOutturnRatioCollection() {
		return masterOutturnRatioCollection;
	}

	public void setMasterOutturnRatioCollection(Collection<MasterOutturnRatio> masterOutturnRatioCollection) {
		this.masterOutturnRatioCollection = masterOutturnRatioCollection;
	}

	/*
	 * public MasterGrade getFkGradeId() { return fkGradeId; }
	 * 
	 * public void setFkGradeId(MasterGrade fkGradeId) { this.fkGradeId =
	 * fkGradeId; }
	 */

	public Integer getFkGradeId() {
		return fkGradeId;
	}

	public void setFkGradeId(Integer fkGradeId) {
		this.fkGradeId = fkGradeId;
	}

	/*public MasterOrigin getFkOriginId() {
		return fkOriginId;
	}

	public void setFkOriginId(MasterOrigin fkOriginId) {
		this.fkOriginId = fkOriginId;
	}*/
	public Integer getFkOriginId() {
		return fkOriginId;
	}

	public void setFkOriginId(Integer fkOriginId) {
		this.fkOriginId = fkOriginId;
	}

	/*
	 * public MasterProduct getFkProdId() { return fkProdId; }
	 * 
	 * public void setFkProdId(MasterProduct fkProdId) { this.fkProdId =
	 * fkProdId; }
	 */

	public Integer getFkProdId() {
		return fkProdId;
	}

	

	public void setFkProdId(Integer fkProdId) {
		this.fkProdId = fkProdId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (pkOutturnRawGradeId != null ? pkOutturnRawGradeId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof MasterOutturnRawGrade)) {
			return false;
		}
		MasterOutturnRawGrade other = (MasterOutturnRawGrade) object;
		if ((this.pkOutturnRawGradeId == null && other.pkOutturnRawGradeId != null)
				|| (this.pkOutturnRawGradeId != null && !this.pkOutturnRawGradeId.equals(other.pkOutturnRawGradeId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.olam.score.product.domain.MasterOutturnRawGrade[ pkOutturnRawGradeId=" + pkOutturnRawGradeId + " ]";
	}
	
	public MasterOutturnDto convertToOutturnDto(Map<Integer, String> statusMap,List<Map<Object, Object>> prodList,List<Map<Object, Object>> originList,  List<Map<Object, Object>> gradeList) {
		MasterOutturnDto dto = new MasterOutturnDto();
		OutturnRawGradeDto outturnRawGradeDto;
        ModelMapper modelMapper = new ModelMapper();
        outturnRawGradeDto = modelMapper.map(this, OutturnRawGradeDto.class);
		
        if(prodList!=null)
		{
			String prodName = null;
			String prodCode = null;
			 Integer prodId = null;
			 for (int x = 0; x < prodList.size(); x++) {
				prodId = (Integer) prodList.get(x).get("prodId");
				
				if (prodId != null && prodId.equals(this.getFkProdId())) {
					prodName = (String) prodList.get(x).get("prodName");
					prodCode = (String) prodList.get(x).get("prodCode");
					outturnRawGradeDto.setProdName(prodName);
					outturnRawGradeDto.setProdCode(prodCode);
					outturnRawGradeDto.setProdId(prodId);
					//break;
				}
			}
		} 
		if(originList!=null){
			String originName = null;
			String originCode = null;
			 Integer originId = 0;
			 for (int x = 0; x < originList.size(); x++) {
			 	//System.out.println("originid value "+originList.get(x));
				
				
			 	Map<Object,Object> originDto =  originList.get(x);
			 	
				Map<Object,Object> originDtoMap =  (Map<Object, Object>) originDto.get("originDto");
				if(originDtoMap!=null){
				originId = (Integer) originDtoMap.get("pkOriginId");
						//get("pkOriginId");
				//System.out.println(originId);
				if (originId != null && originId.equals(this.getFkOriginId())) {
					originName = (String) originDtoMap.get("originName");
					originCode = (String) originDtoMap.get("originCode");
					outturnRawGradeDto.setOriginName(originName);
					outturnRawGradeDto.setOriginCode(originCode);
					outturnRawGradeDto.setOriginId(originId);
					break;
				}
				}
			}
		} 
		
		if(gradeList!=null)
		{
			String rawGradeName = null;
			String rawGradeCode = null;
			 Integer gradeId = 0;
			 for (int x = 0; x < gradeList.size(); x++) {
				 gradeId = (Integer) gradeList.get(x).get("gradeId");
				if (gradeId != 0 && gradeId.equals(this.getFkGradeId())	 ) {
					rawGradeName = (String) gradeList.get(x).get("gradeName");
					rawGradeCode = (String) gradeList.get(x).get("gradeCode");
					
					outturnRawGradeDto.setRawGradeName(rawGradeName);
					outturnRawGradeDto.setRawGradeCode(rawGradeCode);
					outturnRawGradeDto.setGradeId(gradeId);
					
					break;
				}
			}
		} 
		
		
		
    	dto.setOutturnRawGradeDto(outturnRawGradeDto);
    	
    	
    	if(!this.getMasterOutturnRatioCollection().isEmpty())
    	{
    		List<OutturnRatioDto> outturnRatioDtoList = new ArrayList<>();
    		
    		for(MasterOutturnRatio masterOutturnRatio:this.getMasterOutturnRatioCollection()){
    			OutturnRatioDto outturnRatioDto = new OutturnRatioDto();
    			
    			outturnRatioDto.setFinishedGradeId(masterOutturnRatio.getFkFinishedGradeId());
    			if(gradeList!=null)
    			{
    				String finishedGradeName = null;
    				String finishedGradeCode = null;
    				 Integer gradeId = 0;
    				 for (int x = 0; x < gradeList.size(); x++) {
    					 gradeId = (int) gradeList.get(x).get("gradeId");
    					//if (gradeId != 0 && this.getFkGradeId() == gradeId) {
    					 if (gradeId != 0 && masterOutturnRatio.getFkFinishedGradeId() == gradeId) {
    						finishedGradeName = (String) gradeList.get(x).get("gradeName");
    						finishedGradeCode = (String) gradeList.get(x).get("gradeCode");
    						//outturnRatioDto.setFinishedGradeId(masterOutturnRatio.getFkFinishedGradeId());
    						outturnRatioDto.setFinishedGradeName(finishedGradeName);
    						outturnRatioDto.setFinishedGradeCode(finishedGradeCode);
    						
    						break;
    					}
    				}
    			} 
    			
    		
    			    			
    			
    			outturnRatioDto.setQuantityRatio(masterOutturnRatio.getQuantityRatio());
    			outturnRatioDto.setAbilityToBearRatio(masterOutturnRatio.getAbilityToBearRatio());
    			outturnRatioDto.setOutturnRatioId(masterOutturnRatio.getPkOutturnRatioId());
    			outturnRatioDto.setOutturnRawGradeId(masterOutturnRatio.getFkOutturnRawGradeId().getPkOutturnRawGradeId());
    			//outturnRatioDto.setStatusId(statusMap.get(key));
    			if(statusMap!=null)
    			outturnRatioDto.setStatusName(statusMap.get(getFkStatusId()));
    			outturnRatioDtoList.add(outturnRatioDto);
    		}
    		dto.setOutturnRatioDto(outturnRatioDtoList);
    	}
    	
    	//dto.setOutturnRatioDto((List<OutturnRatioDto>) modelMapper.map(this.getMasterOutturnRatioCollection(), MasterOutturnDto.class));
    	//dto.setOutturnRatioDto(modelMapper.map(this.getMasterOutturnRatioCollection(), dto.getOutturnRatioDto()));
    	//dto.setOutturnRatioDto((List<>)this.getMasterOutturnRatioCollection());
    	dto.setStatusName(statusMap.get(getFkStatusId()));
		return dto;
	} 
	
	
	
	public String getGradeDetails(){
		return "test";
		
	}
	
	
	

}
