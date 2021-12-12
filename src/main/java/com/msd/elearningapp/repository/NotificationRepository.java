package com.msd.elearningapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msd.elearningapp.domain.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
