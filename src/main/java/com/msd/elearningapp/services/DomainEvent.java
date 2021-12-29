package com.msd.elearningapp.services;

import com.msd.elearningapp.domain.*;
import org.springframework.context.ApplicationEvent;

public class DomainEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	//
	private Assignment message;
	
	public DomainEvent(Object source) {
		super(source);
	}

	public DomainEvent(Object source, Assignment message) {
		super(source);
		this.message = message;
	}
	
	public Assignment getMessage() {
		return message;
	}
}

// https://www.baeldung.com/spring-events