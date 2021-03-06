package com.msd.elearningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.elearningapp.domain.Grade;


@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

}
