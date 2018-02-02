package com.sapient.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sapient.product.common.ProductException;
import com.sapient.product.domian.ProductType;

public interface ProductTypeService {

	public Page<ProductType> getProductTypes(Pageable pageable);

	public ProductType addProductType(String type) throws ProductException;

	public void removeProductType(Integer id) throws ProductException;

	public ProductType getProductType(String name) throws ProductException;

}
