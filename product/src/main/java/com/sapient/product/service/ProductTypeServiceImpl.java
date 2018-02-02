package com.sapient.product.service;

import static java.lang.String.format;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sapient.product.common.ProductException;
import com.sapient.product.domian.ProductType;
import com.sapient.product.domian.ProductTypeRepository;

@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Override
	public Page<ProductType> getProductTypes(Pageable pageable) {
		return productTypeRepository.findAllByOrderByIdAsc(pageable);
	}

	@Override
	public ProductType addProductType(String type) throws ProductException {
		ProductType productType = new ProductType(type);
		return productTypeRepository.save(productType);
	}

	@Override
	public void removeProductType(Integer id) throws ProductException {
		ProductType productType = productTypeRepository.findById(id);
		if (productType == null)
			throw new ProductException(format("product type not found for id:%d", id));
		productTypeRepository.delete(id);
	}

	@Override
	public ProductType getProductType(String type) throws ProductException {
		ProductType productType = productTypeRepository.findByType(type);
		if (productType == null)
			throw new ProductException(format("product type not found for type:%d", type));
		return productType;
	}

}
