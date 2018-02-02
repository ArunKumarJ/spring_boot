package com.sapient.product.domian;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Integer>, JpaSpecificationExecutor<Product> {

	Page<Product> findAll(Pageable pageable);

	Product save(Product product);

	Product findById(Integer id);

	void delete(Integer id);
}
