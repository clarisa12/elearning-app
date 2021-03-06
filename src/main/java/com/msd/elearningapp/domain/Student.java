package com.msd.elearningapp.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Student")
@Entity
@Table(name = "student")
public class Student extends Person {

	@Id
	@GeneratedValue
	@NotNull
	private Long studId;

	@NotEmpty(message = "Student must be part of a faculty!")
	private String studFaculty;

	@NotEmpty(message = "Student must be part of a specialization!")
	private String studSpecialization;

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

	public Student(@NotNull(message = "CNP is required!") Long persIdentityNum,
			@NotNull(message = "First name is required!") @Size(min = 1, message = "First name must be an explicit name!") String persFirstName,
			@NotNull(message = "Last name is required!") @Size(min = 1, message = "Last name must be an explicit name!") String persLastName,
			@Past(message = "Date of birth must be from past!") Date persDoB,
			@Email(message = "Email must be in correct format! example@mail.com") @NotNull @Size(min = 5, message = "Email must be in correct format!") String persEmail,
			@NotNull String persPhone, @Size(min = 1, message = "Adress must be explicit!") String persAdress,
			@NotNull Long studId, @NotEmpty(message = "Student must be part of a faculty!") String studFaculty,
			@NotEmpty(message = "Student must be part of a specialization!") String studSpecialization) {
		super(persIdentityNum, persFirstName, persLastName, persDoB, persEmail, persPhone, persAdress);
		this.studId = studId;
		this.studFaculty = studFaculty;
		this.studSpecialization = studSpecialization;
	}

	public Student() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(studFaculty, studId, studSpecialization);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(studFaculty, other.studFaculty) && Objects.equals(studId, other.studId)
				&& Objects.equals(studSpecialization, other.studSpecialization);
	}

	@Override
	public String toString() {
		return "Student [studId=" + studId + ", studFaculty=" + studFaculty + ", studSpecialization="
				+ studSpecialization + "]";
	}

}
