package com.msd.elearningapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msd.elearningapp.domain.Meetings;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.MeetingsRepository;

@RestController
class MeetingsController {

  private final MeetingsRepository repository;

  MeetingsController(MeetingsRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/meetings")
  List<Meetings> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/meetings")
  Meetings newMeetings(@RequestBody Meetings newMeetings) {
    return repository.save(newMeetings);
  }

  // Single item
  
  @GetMapping("/meetings/{id}")
  Meetings one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(id));
  }

  @PutMapping("/meetings/{id}")
  Meetings replaceMeetings(@RequestBody Meetings newMeetings, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(meeting -> {
    	meeting.setMeetingStartDate(meeting.getMeetingStartDate());
  		meeting.setMeetingEndDate(meeting.getMeetingEndDate());
  		meeting.setMeetingBody(meeting.getMeetingBody());
  		meeting.setMeetingMembers(meeting.getMeetingMembers());
  		meeting.setMeetingTopic(meeting.getMeetingTopic());
  		meeting.setMeetingState(meeting.getMeetingState());
  		meeting.setMeetingObs(meeting.getMeetingObs());
  		meeting.setMentor(meeting.getMentor());
        return repository.save(meeting);
      })
      .orElseGet(() -> {
        newMeetings.setMeetingId(id);
        return repository.save(newMeetings);
      });
  }

  @DeleteMapping("/meetings/{id}")
  void deleteMeetings(@PathVariable Long id) {
    repository.deleteById(id);
  }
}