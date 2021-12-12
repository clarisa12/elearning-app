package com.msd.elearningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.elearningapp.domain.Workgroups;

@Repository
public interface WorkgroupsRepository extends JpaRepository<Workgroups, Long>{

}