package com.app.user.dto;

import com.app.user.domain.User;

public class UserDTOMapper {

	public static User mapper(UserDTO userDTO) {
		return new User(userDTO.getEmailAddress(), userDTO.getUserId(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getMiddleName(),
				userDTO.getLastName());
	}

	public static UserDTO mapper(User user) {
		return new UserDTO(user.getId(), user.getEmailAddress(), user.getUserId(), user.getPassword(), user.getFirstName(), user.getMiddleName(),
				user.getLastName());
	}

	public static User mapper(User user, UserDTO userDTO) {
		user.modify(userDTO.getEmailAddress(), userDTO.getUserId(), userDTO.getPassword(), userDTO.getFirstName(), userDTO.getMiddleName(),
				userDTO.getLastName());
		return user;
	}
}
