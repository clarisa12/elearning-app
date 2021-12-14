package com.msd.elearningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.elearningapp.domain.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long>{

}
