package com.msd.elearningapp.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Professor")
@Entity(name = "Professor")
@Table(name = "professor")
public class Professor extends Person {

	@Id
	@GeneratedValue
	@NotNull
	private Long profId;

	@NotEmpty(message = "Professor must have a title!")
	private String profTitle;
	@NotEmpty(message = "Professor must be part of a department!")
	private String profDepartment;
	@NotEmpty(message = "Professor must be part of a faculty!")
	private String profFaculty;

	/*
	 * @OneToOne(mappedBy = "mentor", cascade = CascadeType.ALL, fetch =
	 * FetchType.LAZY, optional = false) private Workgroups workgroups;
	 */

	public String getProfDepartment() {
		return profDepartment;
	}

	public void setProfDepartment(String profDepartment) {
		this.profDepartment = profDepartment;
	}

	public String getProfFaculty() {
		return profFaculty;
	}

	public void setProfFaculty(String profFaculty) {
		this.profFaculty = profFaculty;
	}

	public String getProfTitle() {
		return profTitle;
	}

	public void setProfTitle(String profTitle) {
		this.profTitle = profTitle;
	}

	public Long getProfId() {
		return profId;
	}

	public void setProfId(Long profId) {
		this.profId = profId;
	}

	public Professor(@NotNull(message = "CNP is required!") Long persIdentityNum,
			@NotNull(message = "First name is required!") @Size(min = 1, message = "First name must be an explicit name!") String persFirstName,
			@NotNull(message = "Last name is required!") @Size(min = 1, message = "Last name must be an explicit name!") String persLastName,
			@Past(message = "Date of birth must be from past!") Date persDoB,
			@Email(message = "Email must be in correct format! example@mail.com") @NotNull @Size(min = 5, message = "Email must be in correct format!") String persEmail,
			@NotNull String persPassword, @NotNull String persPhone,
			@Size(min = 1, message = "Adress must be explicit!") String persAdress, @NotNull Long profId,
			@NotEmpty(message = "Professor must have a title!") String profTitle,
			@NotEmpty(message = "Professor must be part of a department!") String profDepartment,
			@NotEmpty(message = "Professor must be part of a faculty!") String profFaculty) {
		super(persIdentityNum, persFirstName, persLastName, persDoB, persEmail, persPassword, persPhone, persAdress);
		this.profId = profId;
		this.profTitle = profTitle;
		this.profDepartment = profDepartment;
		this.profFaculty = profFaculty;
	}

	public Professor() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(profDepartment, profFaculty, profId, profTitle);
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
		Professor other = (Professor) obj;
		return Objects.equals(profDepartment, other.profDepartment) && Objects.equals(profFaculty, other.profFaculty)
				&& Objects.equals(profId, other.profId) && Objects.equals(profTitle, other.profTitle);
	}

	@Override
	public String toString() {
		return "Professor [profId=" + profId + ", profTitle=" + profTitle + ", profDepartment=" + profDepartment
				+ ", profFaculty=" + profFaculty + "]";
	}

}
