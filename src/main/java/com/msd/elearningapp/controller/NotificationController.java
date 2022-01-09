package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.msd.elearningapp.domain.Notification;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.NotificationRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
class NotificationController {

  private final NotificationRepository repository;

  NotificationController(NotificationRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/notifications")
  List<Notification> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/notifications")
  Notification newNotification(@RequestBody Notification newNotification) {
    return repository.save(newNotification);
  }

  // Single item
  
  @GetMapping("/notifications/{id}")
  Notification one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @PutMapping("/notifications/{id}")
  Notification replaceNotification(@RequestBody Notification newNotification, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(notification -> {
    	notification.setDateNotif(notification.getDateNotif());
    	notification.setTxtNotif(notification.getTxtNotif());
        return repository.save(notification);
      })
      .orElseGet(() -> {
        newNotification.setIdNotif(id);
        return repository.save(newNotification);
      });
  }

  @DeleteMapping("/notifications/{id}")
  void deleteNotification(@PathVariable Long id) {
    repository.deleteById(id);
  }
}