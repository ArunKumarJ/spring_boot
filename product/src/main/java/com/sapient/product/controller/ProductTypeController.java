package com.sapient.product.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.product.common.ProductException;
import com.sapient.product.domian.ProductType;
import com.sapient.product.service.ProductTypeService;

@RestController
@RequestMapping("producttype")
@Validated
public class ProductTypeController {

	@Autowired
	private ProductTypeService productTypeService;

	@GetMapping("/types")
	public Page<ProductType> getProductTypes(Pageable pageable) {
		return productTypeService.getProductTypes(pageable);
	}

	@GetMapping("/{name}")
	public ProductType getProductType(@Valid @PathVariable("name") String name) throws ProductException {
		return productTypeService.getProductType(name);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ProductType addProductType(@Valid @RequestBody String type) throws ProductException {
		return productTypeService.addProductType(type);
	}

	@DeleteMapping("/{id}")
	public void removeProductType(@Valid @PathVariable("id") Integer id) throws ProductException {
		productTypeService.removeProductType(id);
	}
}
