package com.sapient.product.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.product.common.ProductException;
import com.sapient.product.domian.Product;
import com.sapient.product.domian.ProductSpecification;
import com.sapient.product.domian.SearchCriteria;
import com.sapient.product.dto.ProductDTO;
import com.sapient.product.service.ProductService;

@RestController
@RequestMapping("product")
@Validated
@Transactional
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/products")
	public Page<Product> getProducts(Pageable pageable) {
		return productService.getProducts(pageable);
	}

	@GetMapping(value = "/search")
	public List<Product> searchByCriteria(@RequestParam(value = "productType", required = false) String productType,
			@RequestParam(value = "productName", required = false) String productName) throws ProductException {
		SearchCriteria sc = new SearchCriteria();
		if (productName != null) {
			sc.setProductName(productName);
			sc.setValue(productName);

		}
		if (productType != null) {
			sc.setProductType(productType);
			sc.setValue(productType);

		}
		return productService.searchByCriteria(new ProductSpecification(sc));
	}

	@RequestMapping(method = RequestMethod.POST)
	public Product addProduct(@Valid @RequestBody ProductDTO productDTO) throws ProductException {
		return productService.addProduct(productDTO);
	}

	@DeleteMapping("/{id}")
	public void removeProduct(@Valid @PathVariable("id") Integer productId) throws ProductException {
		productService.removeProduct(productId);
	}
}
