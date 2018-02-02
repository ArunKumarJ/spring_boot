package com.sapient.product.domian;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface ProductTypeRepository extends Repository<ProductType, Integer> {

	ProductType save(ProductType productType);

	ProductType findById(Integer id);

	ProductType findByType(String type);

	Page<ProductType> findAllByOrderByIdAsc(Pageable pageable);

	void delete(Integer id);
}
