package com.staxter.demo.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String code;
	private final String description;

	public UserAlreadyExistsException(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	@Override
	public String toString() {
		return "{\"code\": \"" + this.code + "\"," + "\"description\": \"" + this.description + "\"}";
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
