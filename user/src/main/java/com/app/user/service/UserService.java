package com.app.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.app.user.domain.User;
import com.app.user.dto.UserDTO;

public interface UserService {

	public User getUser(Integer id);

	public Page<User> getAllUsers(Pageable pageable);

	public UserDTO addUser(UserDTO userDTO);

	public void updateUser(UserDTO userDTO);

	public void deleteUser(Integer id);

	public Page<User> findByCriteria(Specification<User> specs, Pageable pageable);
}
