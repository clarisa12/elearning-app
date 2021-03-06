package com.msd.elearningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.elearningapp.domain.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long>{

}
