package com.olam.score.product.dto;

import java.util.List;


public class WrapperDto {
	private List<BrandDto> brandList;
	private List<ProductDto> productList;
	private List<GradeGroupDto> gradeGroupList;
	private List<IntlCodeTypeRefDto> intlCodeTypeList;
	public List<BrandDto> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<BrandDto> brandList) {
		this.brandList = brandList;
	}
	public List<ProductDto> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}
	public List<GradeGroupDto> getGradeGroupList() {
		return gradeGroupList;
	}
	public void setGradeGroupList(List<GradeGroupDto> gradeGroupList) {
		this.gradeGroupList = gradeGroupList;
	}
	public List<IntlCodeTypeRefDto> getIntlCodeTypeList() {
		return intlCodeTypeList;
	}
	public void setIntlCodeTypeList(List<IntlCodeTypeRefDto> intlCodeTypeList) {
		this.intlCodeTypeList = intlCodeTypeList;
	}

}
