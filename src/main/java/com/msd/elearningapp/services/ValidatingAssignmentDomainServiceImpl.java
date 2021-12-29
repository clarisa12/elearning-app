package com.msd.elearningapp.services;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.msd.elearningapp.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class ValidatingAssignmentDomainServiceImpl 
	implements ApplicationListener<DomainEvent>, 
		IValidatingAssignmentDomainService{
	private static Logger logger = Logger.getLogger(ValidatingAssignmentDomainServiceImpl.class.getName());
	
	@Autowired
	private Validator validator;
	
	//
	@Override
	public Set<String> validate(Assignment assignment){
		logger.info(">>> Service: ValidatingAssignmentDomainServiceImpl :: validate.");
		
		//
		Set<ConstraintViolation<Assignment>> violations = validator
				.validate(assignment);
		logger.info("Violations count: " + violations.size());
		
		
		return violations.stream()
				.map(violation -> violation.getMessage() + " (" 
							+ violation.getInvalidValue() + ") is an invalid value!" )
				.collect(Collectors.toSet()); 
	}
	
	@Override
	public void validateWithException(Assignment assignment){
		Set<String> violations = validate(assignment);
		logger.info("Violations count (to generate exception): " + violations.size());
		
		if (violations.size() > 0) {
			String violationExceptionMessage = violations.stream()
					.map(violation -> ">>> JEE.Spring bean validator exception: " + violation)
					.collect(Collectors.joining(", "));
			try {
				validateAssignmentAggregate(assignment);
			}catch(Exception ex) {
				violationExceptionMessage += " Local validation: "
						+ ex.getMessage();
			}
			throw new RuntimeException(violationExceptionMessage);
		}
	}
	
	// Event-based Business Service Integration
	// SpringEvents:: Listening
	@Override
	public void onApplicationEvent(DomainEvent domainEvent) {
		Assignment buildAssignment = domainEvent.getMessage();
		logger.info(">>>***Validating_ListeningEvents***>>> for: " + domainEvent.getMessage());
		this.validateWithException(buildAssignment);
	}
	
	// Local business validation method
	@Override
	public boolean validateAssignmentAggregate(Assignment assignment) {
		if (assignment == null)
			throw new RuntimeException("Null assignment!");
		return true;
	}
}
