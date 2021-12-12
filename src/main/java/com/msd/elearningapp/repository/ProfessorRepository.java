package com.msd.elearningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.elearningapp.domain.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
