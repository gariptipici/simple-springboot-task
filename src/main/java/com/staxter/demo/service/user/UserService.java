package com.staxter.demo.service.user;

import com.staxter.demo.dto.user.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto);
	UserDto getUser(String userName, String password);

}
