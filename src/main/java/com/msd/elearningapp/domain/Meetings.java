package com.msd.elearningapp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Meetings {
    @Id @GeneratedValue @NotNull
    private long meetingId;

    @Size(min = 1, max = 1000)
    private String meetingBody;

    @NotNull 
    @Future(message = "Meeting start date must be later than present date!")
    @Temporal(TemporalType.DATE)
    private Date meetingStartDate;

    @NotNull 
    @Future(message = "Meeting end date must be later than present date!")
    @Temporal(TemporalType.DATE)
    private Date meetingEndDate;

    @ManyToMany
    private List<Student> meetingMembers;

    @OneToOne
    private Professor mentor;

    private String meetingObs;
    private String meetingTopic;
    private MeetingState meetingState;
	public long getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(long meetingId) {
		this.meetingId = meetingId;
	}
	public String getMeetingBody() {
		return meetingBody;
	}
	public void setMeetingBody(String meetingBody) {
		this.meetingBody = meetingBody;
	}
	public Date getMeetingStartDate() {
		return meetingStartDate;
	}
	public void setMeetingStartDate(Date meetingStartDate) {
		this.meetingStartDate = meetingStartDate;
	}
	public Date getMeetingEndDate() {
		return meetingEndDate;
	}
	public void setMeetingEndDate(Date meetingEndDate) {
		this.meetingEndDate = meetingEndDate;
	}
	public List<Student> getMeetingMembers() {
		return meetingMembers;
	}
	public void setMeetingMembers(List<Student> meetingMembers) {
		this.meetingMembers = meetingMembers;
	}
	public Professor getMentor() {
		return mentor;
	}
	public void setMentor(Professor mentor) {
		this.mentor = mentor;
	}
	public String getMeetingObs() {
		return meetingObs;
	}
	public void setMeetingObs(String meetingObs) {
		this.meetingObs = meetingObs;
	}
	public String getMeetingTopic() {
		return meetingTopic;
	}
	public void setMeetingTopic(String meetingTopic) {
		this.meetingTopic = meetingTopic;
	}
	public MeetingState getMeetingState() {
		return meetingState;
	}
	public void setMeetingState(MeetingState meetingState) {
		this.meetingState = meetingState;
	}
	public Meetings(@NotNull long meetingId, @Size(min = 1, max = 1000) String meetingBody,
			@NotNull @Future(message = "Meeting start date must be later than present date!") Date meetingStartDate,
			@NotNull @Future(message = "Meeting end date must be later than present date!") Date meetingEndDate,
			List<Student> meetingMembers, Professor mentor, String meetingObs, String meetingTopic,
			MeetingState meetingState) {
		super();
		this.meetingId = meetingId;
		this.meetingBody = meetingBody;
		this.meetingStartDate = meetingStartDate;
		this.meetingEndDate = meetingEndDate;
		this.meetingMembers = meetingMembers;
		this.mentor = mentor;
		this.meetingObs = meetingObs;
		this.meetingTopic = meetingTopic;
		this.meetingState = meetingState;
	}
	public Meetings() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(meetingBody, meetingEndDate, meetingId, meetingMembers, meetingObs, meetingStartDate,
				meetingState, meetingTopic, mentor);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meetings other = (Meetings) obj;
		return Objects.equals(meetingBody, other.meetingBody) && Objects.equals(meetingEndDate, other.meetingEndDate)
				&& meetingId == other.meetingId && Objects.equals(meetingMembers, other.meetingMembers)
				&& Objects.equals(meetingObs, other.meetingObs)
				&& Objects.equals(meetingStartDate, other.meetingStartDate) && meetingState == other.meetingState
				&& Objects.equals(meetingTopic, other.meetingTopic) && Objects.equals(mentor, other.mentor);
	}


    
}