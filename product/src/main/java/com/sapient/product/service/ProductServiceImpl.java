package com.sapient.product.service;

import static java.lang.String.format;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sapient.product.common.ProductException;
import com.sapient.product.domian.Product;
import com.sapient.product.domian.ProductRepository;
import com.sapient.product.domian.ProductSpecification;
import com.sapient.product.domian.ProductType;
import com.sapient.product.domian.ProductTypeRepository;
import com.sapient.product.dto.ProductDTO;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Override
	public Page<Product> getProducts(Pageable pageable) {
		return productRepository.findAll(pageable);

	}

	@Override
	public Product addProduct(ProductDTO productDTO) throws ProductException {
		ProductType productType = productTypeRepository.findById(productDTO.getProductType());
		if (productType == null)
			throw new ProductException(format("product type not found for id:%d", productDTO.getProductType()));
		Product product = new Product(productDTO.getName(), productType);
		return productRepository.save(product);
	}

	@Override
	public void removeProduct(Integer productId) throws ProductException {
		Product product = productRepository.findById(productId);
		if (product == null)
			throw new ProductException(format("product not found for id:%d", productId));
		productRepository.delete(productId);
	}

	@Override
	public List<Product> searchByCriteria(ProductSpecification productSpecification) throws ProductException {
		return productRepository.findAll(productSpecification);
	}

}
