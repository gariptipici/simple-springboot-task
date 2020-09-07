package com.staxter.demo.db;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.staxter.demo.data.user.User;

public class InMemoryDB {
	public static Map<String, User> USER_TABLE = new ConcurrentHashMap<>();

}
