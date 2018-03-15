package com.app.user.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class UserSpecificationBuider {
	private List<UserSearchCriteria> criterias;

	public UserSpecificationBuider() {
		criterias = new ArrayList<>();
	}

	public UserSpecificationBuider with(String key, String operation, String value) {
		criterias.add(new UserSearchCriteria(key, operation, value));
		return this;
	}

	public Specification<User> build() {
		if (criterias == null && criterias.size() > 0)
			return null;

		List<Specification<User>> specs = new ArrayList<>();
		for (UserSearchCriteria criteria : criterias) {
			specs.add(new UserSpecification(criteria));
		}
		Specification<User> result = null;
		if (specs.size() > 0) {
			result = specs.get(0);
			for (int i = 0; i < specs.size(); i++) {
				result = Specifications.where(result).and(specs.get(i));
			}
		}

		return result;
	}
}
