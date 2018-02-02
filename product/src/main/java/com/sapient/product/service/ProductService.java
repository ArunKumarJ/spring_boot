package com.sapient.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sapient.product.common.ProductException;
import com.sapient.product.domian.Product;
import com.sapient.product.domian.ProductSpecification;
import com.sapient.product.dto.ProductDTO;

public interface ProductService {

	public Page<Product> getProducts(Pageable pageable);

	public Product addProduct(ProductDTO productDTO) throws ProductException;

	public void removeProduct(Integer productId) throws ProductException;

	List<Product> searchByCriteria(ProductSpecification productSpecification) throws ProductException;

}
