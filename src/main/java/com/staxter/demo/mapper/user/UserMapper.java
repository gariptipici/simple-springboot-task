package com.staxter.demo.mapper.user;

import com.staxter.demo.data.user.User;
import com.staxter.demo.dto.user.UserDto;

public class UserMapper {
	
	public static User userDtoToUser(UserDto userDto) {
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setUserName(userDto.getUserName());
		user.setPlainTextPassword(userDto.getPassword());
		return user;
	}
	
	public static UserDto userDtoToUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setUserName(user.getUserName());
		userDto.setId(user.getId());
		return userDto;
	}
	
	private UserMapper() {
		throw new IllegalStateException();
	}

}
