package com.msd.elearningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.elearningapp.domain.Grades;


@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {

}
