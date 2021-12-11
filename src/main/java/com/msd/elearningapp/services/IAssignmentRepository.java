package com.msd.elearningapp.services;


import com.msd.elearningapp.domain.Assignment;

import java.util.Collection;

public interface IAssignmentRepository {
    public Integer getNextID() ;

    //
    public Assignment getById(Integer id);
    public Assignment get(Assignment sample);
    public Collection<Assignment> toCollection(); // getAll

    //
    public Assignment add(Assignment entity);
    public Collection<Assignment> addAll(Collection<Assignment> entities);
    public boolean remove(Assignment entity);
    public boolean removeAll(Collection<Assignment> entities);

    //
    public int size();
}
