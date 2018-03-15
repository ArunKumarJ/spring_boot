package com.app.user.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<User> {

	private UserSearchCriteria criteria;

	public UserSpecification(UserSearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (criteria.getOperation().equals(">")) {
			return cb.greaterThan(root.<String>get(criteria.getKey()), criteria.getValue());
		} else if (criteria.getOperation().equals("<")) {
			return cb.lessThan(root.<String>get(criteria.getKey()), criteria.getValue());
		} else if (criteria.getOperation().equals(":")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return cb.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
			} else {
				return cb.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		}
		return null;
	}

}
