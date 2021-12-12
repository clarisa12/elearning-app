package com.msd.elearningapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msd.elearningapp.domain.Notification;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.NotificationRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")

public class NotificationController {

	@Autowired
	private NotificationRepository notificationRepository;
	
	// get all notifications
	@GetMapping("/notifications")
	public List<Notification> getAllNotifications(){
		return notificationRepository.findAll();
	}		
	
	// create notification rest api
	@PostMapping("/notifications")
	public Notification createNotification(@RequestBody Notification notification) {
		return notificationRepository.save(notification);
	}
	
	// get notification by id rest api
	@GetMapping("/notifications/{idNotif}")
	public ResponseEntity<Notification> getNotificationById(@PathVariable Long idNotif) {
		Notification notification = notificationRepository.findById(idNotif)
				.orElseThrow(() -> new ResourceNotFoundException("Notification does not exist with id :" + idNotif));
		return ResponseEntity.ok(notification);
	}
	
	// update notification rest api
	
	@PutMapping("/notifications/{idNotif}")
	public ResponseEntity<Notification> updateNotification(@PathVariable Long idNotif, @RequestBody Notification notificationDetails){
		Notification notification = notificationRepository.findById(idNotif)
				.orElseThrow(() -> new ResourceNotFoundException("Notification does not exist with id :" + idNotif));
		
		notification.setTxtNotif(notification.getTxtNotif());
		notification.setDateNotif(notification.getDateNotif());
		
		Notification updatedNotification = notificationRepository.save(notification);
		return ResponseEntity.ok(updatedNotification);
	}
	
	// delete notification rest api
	@DeleteMapping("/notifications/{idNotif}")
	public ResponseEntity<Map<String, Boolean>> deleteNotification(@PathVariable Long idNotif){
		Notification notification = notificationRepository.findById(idNotif)
				.orElseThrow(() -> new ResourceNotFoundException("Notification does not exist with id :" + idNotif));
		
		notificationRepository.delete(notification);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
