package com.app.user.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.user.domain.User;
import com.app.user.domain.UserSpecificationBuider;
import com.app.user.dto.UserDTO;
import com.app.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") Integer id) {
		return userService.getUser(id);
	}

	@GetMapping("/users")
	public Page<User> findByCriteria(@RequestParam("criteria") String criteria) {
		UserSpecificationBuider builder = new UserSpecificationBuider();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		Matcher matcher = pattern.matcher(criteria + ",");
		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		return userService.findByCriteria(builder.build(), new PageRequest(0, 20, Sort.Direction.DESC, "firstName"));
	}

	@PostMapping
	public UserDTO addUser(@RequestBody UserDTO userDTO) {
		return userService.addUser(userDTO);
	}

	@PutMapping
	public void updateUser(@RequestBody UserDTO userDTO) {
		userService.updateUser(userDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}
}
