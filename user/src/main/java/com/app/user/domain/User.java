package com.app.user.domain;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2065120653400707903L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
	@Id
	@Column(name = "ID")
	private Integer id;
	@Column(name = "EMAIL_ADDRESS", unique = true)
	@NaturalId
	private String emailAddress;
	@Column(name = "USER_ID", unique = true)
	@NaturalId
	private String userId;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "MIDDLE_NAME")
	private String middleName;
	@Column(name = "LAST_NAME")
	private String lastName;

	public User() {
		// ignore
	}

	public User(String emailAddress, String userId, String password, String firstName, String middleName, String lastName) {
		this.emailAddress = emailAddress;
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void modify(String emailAddress, String userId, String password, String firstName, String middleName, String lastName) {
		if (isNotBlank(emailAddress))
			this.emailAddress = emailAddress;
		if (isNotBlank(userId))
			this.userId = userId;
		if (isNotBlank(password))
			this.password = password;
		if (isNotBlank(firstName))
			this.firstName = firstName;
		if (isNotBlank(middleName))
			this.middleName = middleName;
		if (isNotBlank(lastName))
			this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(emailAddress, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User u = (User) obj;
		return Objects.equals(emailAddress, u.emailAddress) || Objects.equals(userId, u.userId);
	}
}
