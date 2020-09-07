package com.staxter.demo.resource.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.staxter.demo.dto.user.UserDto;
import com.staxter.demo.service.user.UserService;

@RestController
@RequestMapping(value = "/userservice")
public class UserResource {

	private UserService userService;

	@Autowired
	public UserResource(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/register")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}

	@GetMapping(value = "/login")
	public ResponseEntity<UserDto> getUser(@RequestParam String userName, @RequestParam String password) {

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getUser(userName, password));
	}
}
