package com.msd.elearningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.elearningapp.domain.Workgroup;

@Repository
public interface WorkgroupRepository extends JpaRepository<Workgroup, Long>{

}