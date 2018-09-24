package com.olam.score.terms.dto;

public class ContractTermsSalesDto {

	//validations removed as it will be handled from UI
	private Integer contractTermsSalesId;
	private Integer costGroupId;
	private String costGroupName;
	private String costGroupCode;
	/*@NotNull(message = "notNull_budgetInd")
    @NotEmpty(message = "mandatory_budgetInd")*/
	private String budgetInd;
	/*@NotNull(message = "notNull_costId")*/
	private Integer costId;
	private String costName;
	private String costCode;
	/*@NotNull(message = "notNull_contrTrmsAddreduceRefId")*/
	private Integer contrTrmsAddreduceRefId;
	private String addReduceCode;
	private String addReduceName;
	private Integer contractTermsId;
	private Integer statusId;
	private String statusName;
	public Integer getContractTermsSalesId() {
		return contractTermsSalesId;
	}
	public void setContractTermsSalesId(Integer contractTermsSalesId) {
		this.contractTermsSalesId = contractTermsSalesId;
	}
	public Integer getCostGroupId() {
		return costGroupId;
	}
	public void setCostGroupId(Integer costGroupId) {
		this.costGroupId = costGroupId;
	}
	public String getBudgetInd() {
		return budgetInd;
	}
	public void setBudgetInd(String budgetInd) {
		this.budgetInd = budgetInd;
	}
	public Integer getCostId() {
		return costId;
	}
	public void setCostId(Integer costId) {
		this.costId = costId;
	}
	public Integer getContrTrmsAddreduceRefId() {
		return contrTrmsAddreduceRefId;
	}
	public void setContrTrmsAddreduceRefId(Integer contrTrmsAddreduceRefId) {
		this.contrTrmsAddreduceRefId = contrTrmsAddreduceRefId;
	}
	public Integer getContractTermsId() {
		return contractTermsId;
	}
	public void setContractTermsId(Integer contractTermsId) {
		this.contractTermsId = contractTermsId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public String getCostCode() {
		return costCode;
	}
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}
	public String getAddReduceCode() {
		return addReduceCode;
	}
	public void setAddReduceCode(String addReduceCode) {
		this.addReduceCode = addReduceCode;
	}
	public String getAddReduceName() {
		return addReduceName;
	}
	public void setAddReduceName(String addReduceName) {
		this.addReduceName = addReduceName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getCostGroupName() {
		return costGroupName;
	}
	public void setCostGroupName(String costGroupName) {
		this.costGroupName = costGroupName;
	}
	public String getCostGroupCode() {
		return costGroupCode;
	}
	public void setCostGroupCode(String costGroupCode) {
		this.costGroupCode = costGroupCode;
	}
	
	
	
}
