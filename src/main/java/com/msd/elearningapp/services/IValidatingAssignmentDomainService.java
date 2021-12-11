package com.msd.elearningapp.services;

import com.msd.elearningapp.domain.Assignment;

import java.util.Set;

public interface IValidatingAssignmentDomainService {

    //
    Set<String> validate(Assignment assignment);
    //
    void validateWithException(Assignment assignment) throws Exception;
    //
    boolean validateAssignmentAggregate(Assignment a);

}
