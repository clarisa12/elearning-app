package com.msd.elearningapp.domain;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "valid" })
@XmlRootElement(name = "Assignment")
@Table(name = "assignment")
public class Assignment {

	@Id
	@GeneratedValue
	@NotNull
	//@Min(10)
	private Long assigId;

	@NotNull(message = "Assignment Name is required!")
	@Size(min = 1, message = "Assignment must have an explicit name!")
	private String assigName;

	@NotNull(message = "Start Date is required!")
	@Future(message = "Start Date must be a future date!")
	//@Temporal(TemporalType.DATE)
	private LocalDate assigDatestart;

	
	@NotNull(message = "End Date is required!")
	@Future(message = "End Date must be a future date!")
	//@Temporal(TemporalType.DATE)
	private LocalDate assigDateEnd;
	private AssignmentState assigState;

	@OneToOne
	//@JoinColumn(name = "assig_starter_stud_id")
	@JoinColumn(name = "student")
	// @NotNull
	private Student assigStarter;

	@ManyToMany
	private List<Student> assigMem;

	public Long getAssigId() {
		return assigId;
	}

	public void setAssigId(Long assigId) {
		this.assigId = assigId;
	}

	public String getAssigName() {
		return assigName;
	}

	public void setAssigName(String assigName) {
		this.assigName = assigName;
	}

	public LocalDate getAssigDatestart() {
		return assigDatestart;
	}

	public void setAssigDatestart(LocalDate assigDatestart) {
		this.assigDatestart = assigDatestart;
	}

	public LocalDate getAssigDateEnd() {
		return assigDateEnd;
	}

	public void setAssigDateEnd(LocalDate assigDateEnd) {
		this.assigDateEnd = assigDateEnd;
	}

	public AssignmentState getAssigState() {
		return assigState;
	}

	public void setAssigState(AssignmentState assigState) {
		this.assigState = assigState;
	}

	public Student getAssigStarter() {
		return assigStarter;
	}

	public void setAssigStarter(Student assigStarter) {
		this.assigStarter = assigStarter;
	}

	public List<Student> getAssigMem() {
		return assigMem;
	}

	public void setAssigMem(List<Student> assigMem) {
		this.assigMem = assigMem;
	}

	public Assignment(@NotNull Long assigId,
			@NotNull(message = "Assignment Name is required!") @Size(min = 1, message = "Assignment must have an explicit name!") String assigName,
			@NotNull(message = "Start Date is required!") @Future(message = "Start Date must be a future date!") LocalDate assigDatestart,
			@NotNull(message = "End Date is required!") @Future(message = "End Date must be a future date!") LocalDate assigDateEnd,
			AssignmentState assigState, Student assigStarter, List<Student> assigMem) {
		super();
		this.assigId = assigId;
		this.assigName = assigName;
		this.assigDatestart = assigDatestart;
		this.assigDateEnd = assigDateEnd;
		this.assigState = assigState;
		this.assigStarter = assigStarter;
		this.assigMem = assigMem;
	}

	public Assignment() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(assigDateEnd, assigDatestart, assigId, assigMem, assigName, assigStarter, assigState);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assignment other = (Assignment) obj;
		return Objects.equals(assigDateEnd, other.assigDateEnd) && Objects.equals(assigDatestart, other.assigDatestart)
				&& Objects.equals(assigId, other.assigId) && Objects.equals(assigMem, other.assigMem)
				&& Objects.equals(assigName, other.assigName) && Objects.equals(assigStarter, other.assigStarter)
				&& assigState == other.assigState;
	}

	@Override
	public String toString() {
		return "Assignment [assigId=" + assigId + ", assigName=" + assigName + ", assigDatestart=" + assigDatestart
				+ ", assigDateEnd=" + assigDateEnd + ", assigState=" + assigState + ", assigStarter=" + assigStarter
				+ ", assigMem=" + assigMem + "]";
	}

}
