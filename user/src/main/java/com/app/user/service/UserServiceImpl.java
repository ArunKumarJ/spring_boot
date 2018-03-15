package com.app.user.service;

import static com.app.user.dto.UserDTOMapper.mapper;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.app.user.domain.User;
import com.app.user.domain.UserRepository;
import com.app.user.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUser(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		User user = mapper(userDTO);
		userRepository.save(user);
		return mapper(user);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	@Override
	public void updateUser(UserDTO userDTO) {
		User user = userRepository.findById(userDTO.getId());
		mapper(user, userDTO);
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.delete(userRepository.findById(id));
	}

	@Override
	public Page<User> findByCriteria(Specification<User> specs, Pageable pageable) {
		return userRepository.findAll(specs, pageable);
	}
}
