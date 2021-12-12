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

import com.msd.elearningapp.domain.Meetings;
import com.msd.elearningapp.exception.ResourceNotFoundException;
import com.msd.elearningapp.repository.MeetingsRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")

public class MeetingsController {

	@Autowired
	private MeetingsRepository meetingRepository;
	
	// get all meetings
	@GetMapping("/meetings")
	public List<Meetings> getAllMeetingss(){
		return meetingRepository.findAll();
	}		
	
	// create meeting rest api
	@PostMapping("/meetings")
	public Meetings createMeetings(@RequestBody Meetings meeting) {
		return meetingRepository.save(meeting);
	}
	
	// get meeting by id rest api
	@GetMapping("/meetings/{meetingId}")
	public ResponseEntity<Meetings> getMeetingsById(@PathVariable Long meetingId) {
		Meetings meeting = meetingRepository.findById(meetingId)
				.orElseThrow(() -> new ResourceNotFoundException("Meetings does not exist with id :" + meetingId));
		return ResponseEntity.ok(meeting);
	}
	
	// update meeting rest api
	
	@PutMapping("/meetings/{meetingId}")
	public ResponseEntity<Meetings> updateMeetings(@PathVariable Long meetingId, @RequestBody Meetings meetingDetails){
		Meetings meeting = meetingRepository.findById(meetingId)
				.orElseThrow(() -> new ResourceNotFoundException("Meetings does not exist with id :" + meetingId));
		
		meeting.setMeetingStartDate(meeting.getMeetingStartDate());
		meeting.setMeetingEndDate(meeting.getMeetingEndDate());
		meeting.setMeetingBody(meeting.getMeetingBody());
		meeting.setMeetingMembers(meeting.getMeetingMembers());
		meeting.setMeetingTopic(meeting.getMeetingTopic());
		meeting.setMeetingState(meeting.getMeetingState());
		meeting.setMeetingObs(meeting.getMeetingObs());
		meeting.setMentor(meeting.getMentor());
		
		Meetings updatedMeetings = meetingRepository.save(meeting);
		return ResponseEntity.ok(updatedMeetings);
	}
	
	// delete meeting rest api
	@DeleteMapping("/meetings/{meetingId}")
	public ResponseEntity<Map<String, Boolean>> deleteMeetings(@PathVariable Long meetingId){
		Meetings meeting = meetingRepository.findById(meetingId)
				.orElseThrow(() -> new ResourceNotFoundException("Meetings does not exist with id :" + meetingId));
		
		meetingRepository.delete(meeting);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
