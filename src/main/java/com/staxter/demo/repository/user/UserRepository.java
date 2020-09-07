package com.staxter.demo.repository.user;

import com.staxter.demo.data.user.User;
import com.staxter.demo.exception.UserAlreadyExistsException;
import com.staxter.demo.exception.UserDoesNotExistException;
import com.staxter.demo.exception.WrongPasswordException;

public interface UserRepository {
	User createUser(User user) throws UserAlreadyExistsException;
	User getUser(String username, String password) throws WrongPasswordException, UserDoesNotExistException;
}
