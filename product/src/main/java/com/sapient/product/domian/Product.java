package com.sapient.product.domian;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@SequenceGenerator(name = "PRODUCT_GEN", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "PRODUCT_GEN")
	@Column(name = "ID")
	private Integer id;
	@Column(name = "NAME")
	@NaturalId
	private String name;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_TYPE_ID")
	@JsonIgnore
	private ProductType productType;

	public Product() {
		// ignore
	}

	public Product(String name, ProductType productType) {
		this.name = name;
		this.productType = productType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, productType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Product))
			return false;
		Product product = (Product) obj;
		return Objects.equals(name, product.name) && Objects.equals(productType, product.productType);
	}

}
