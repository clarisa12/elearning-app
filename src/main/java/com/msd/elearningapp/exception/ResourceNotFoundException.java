package com.msd.elearningapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Long id) {
		super("Could not find resource " + id);
	}

	public ResourceNotFoundException(String id) {
		super("Could not find resource " + id);
	}
}
