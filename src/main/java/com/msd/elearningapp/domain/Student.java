package com.msd.elearningapp.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Student extends Person {
    @NotEmpty(message = "Student must be part of a faculty!")
    private String studFaculty;

    @NotEmpty(message = "Student must be part of a specialization!")
    private String studSpecialization;

    @Id @GeneratedValue @NotNull
    private Long studId;

	public String getStudFaculty() {
		return studFaculty;
	}

	public void setStudFaculty(String studFaculty) {
		this.studFaculty = studFaculty;
	}

	public String getStudSpecialization() {
		return studSpecialization;
	}

	public void setStudSpecialization(String studSpecialization) {
		this.studSpecialization = studSpecialization;
	}

	public Long getStudId() {
		return studId;
	}

	public void setStudId(Long studId) {
		this.studId = studId;
	}

	public Student(@NotNull Long persIdentityNum, @NotNull String persFirstName, @NotNull String persLastName,
			@Past Date persDoB, @Email @NotNull String persEmail, @NotNull String persNum, String persAdress,
			String persPhone, @NotEmpty String studFaculty, @NotEmpty String studSpecialization, @NotNull Long studId) {
		super(persIdentityNum, persFirstName, persLastName, persDoB, persEmail, persNum, persAdress, persPhone);
		this.studFaculty = studFaculty;
		this.studSpecialization = studSpecialization;
		this.studId = studId;
	}

}
