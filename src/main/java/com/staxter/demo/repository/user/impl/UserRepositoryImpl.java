package com.staxter.demo.repository.user.impl;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.stereotype.Repository;

import com.staxter.demo.data.user.User;
import com.staxter.demo.db.InMemoryDB;
import com.staxter.demo.exception.UserAlreadyExistsException;
import com.staxter.demo.exception.UserDoesNotExistException;
import com.staxter.demo.exception.WrongPasswordException;
import com.staxter.demo.repository.user.UserRepository;
import com.staxter.demo.util.SecurityUtil;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Override
	public User createUser(User user) throws UserAlreadyExistsException {
		if (InMemoryDB.USER_TABLE.containsKey(user.getUserName())) {
			throw new UserAlreadyExistsException("USER_ALREADY_EXISTS",
					"A user with the given username already exists");
		}
		user.setId(Timestamp.from(Instant.now()));
		InMemoryDB.USER_TABLE.put(user.getUserName(), user);
		return user;
	}

	@Override
	public User getUser(String username, String password) throws WrongPasswordException, UserDoesNotExistException {
		if (InMemoryDB.USER_TABLE.containsKey(username)) {
			if (!SecurityUtil.md5(password).equals(InMemoryDB.USER_TABLE.get(username).getHashedPassword())) {
				throw new WrongPasswordException("WRONG_PASSWORD", "Given password does not match");
			}
		} else {
			throw new UserDoesNotExistException("USER_DOES_NOT_EXIST", "Given username does not exist");
		}
		return InMemoryDB.USER_TABLE.get(username);
	}

}
