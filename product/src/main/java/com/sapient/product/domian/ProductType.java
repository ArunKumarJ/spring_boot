package com.sapient.product.domian;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "PRODUCT_TYPE")
public class ProductType {

	@Id
	@SequenceGenerator(name = "PRODUCT_TYPE_GEN", sequenceName = "PRODUCT_TYPE_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "PRODUCT_TYPE_GEN")
	@Column(name = "ID")
	private Integer id;
	@Column(name = "TYPE")
	@NaturalId
	private String type;
	@OneToMany(mappedBy = "productType", fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet<>();

	public ProductType() {
		// ignore
	}

	public ProductType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductType other = (ProductType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
