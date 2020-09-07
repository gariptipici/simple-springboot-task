package com.staxter.demo.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staxter.demo.data.user.User;
import com.staxter.demo.dto.user.UserDto;
import com.staxter.demo.mapper.user.UserMapper;
import com.staxter.demo.repository.user.UserRepository;
import com.staxter.demo.service.user.UserService;

import static com.staxter.demo.util.SecurityUtil.md5;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = UserMapper.userDtoToUser(userDto);
		user.setHashedPassword(md5(user.getPlainTextPassword()));
		user.setPlainTextPassword(null);
		User newUser = userRepository.createUser(user);
		UserDto newUserDto = UserMapper.userDtoToUser(newUser);
		return newUserDto;
	}

	@Override
	public UserDto getUser(String userName, String password) {
		User user = userRepository.getUser(userName, password);
		UserDto userDto = UserMapper.userDtoToUser(user);
		return userDto;
	}

}
