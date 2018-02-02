package com.sapient.product.dto;

import javax.validation.constraints.NotNull;

public class ProductDTO {

	private Integer id;
	@NotNull
	private String name;
	@NotNull
	private Integer productType;

	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(Integer id, String name, Integer productType) {
		this.id = id;
		this.name = name;
		this.productType = productType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

}
