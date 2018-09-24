package com.ibatis.jpetstore.domain;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


public class Category implements Serializable {

  /* Private Fields */

  private String categoryId;
  private String name;
  private String description;
  private List products = new ArrayList();

  /* JavaBeans Properties */

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId.trim();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List getProducts(){
      return products;
  }

  public void setProducts( List products ){
      this.products = products;
  }

  /* Public Methods */

  public String toString() {
    return getCategoryId();
  }

}
