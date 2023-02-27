package com.ios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundExceptoin extends Exception {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundExceptoin(String message) {
		super(message);
	}
}
