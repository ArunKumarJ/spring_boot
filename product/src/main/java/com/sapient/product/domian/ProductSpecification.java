package com.sapient.product.domian;

import static org.springframework.util.StringUtils.isEmpty;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification implements Specification<Product> {

	private SearchCriteria criteria;

	public ProductSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		if (!isEmpty(criteria.getProductName())) {
			return builder.equal(root.get("name"), criteria.getValue().toString());
		} else if (!isEmpty(criteria.getProductType())) {
			return builder.equal(root.get("productType").get("type"), criteria.getValue().toString());
		}
		return null;
	}
}