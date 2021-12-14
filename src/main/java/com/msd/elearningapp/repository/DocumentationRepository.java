package com.msd.elearningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.elearningapp.domain.Documentation;

@Repository
public interface DocumentationRepository extends JpaRepository<Documentation, Long> {

}
