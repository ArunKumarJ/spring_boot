package com.app.user.domain;

public class UserSearchCriteria {

	private String key;
	private String operation;
	private String value;

	public UserSearchCriteria() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param key
	 * @param operation
	 * @param value
	 */
	public UserSearchCriteria(String key, String operation, String value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
