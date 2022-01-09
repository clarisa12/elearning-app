package com.msd.elearningapp.domain;

import java.util.Date;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Administrator")
@Entity
@Table(name = "administrator")
public class Administrator extends Person {

	@Id
	@NotNull
	@GeneratedValue
	private Long adminId;

	private String adminTitle;

	@NotEmpty
	private String adminDepartment;

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminTitle() {
		return adminTitle;
	}

	public void setAdminTitle(String adminTitle) {
		this.adminTitle = adminTitle;
	}

	public String getAdminDepartment() {
		return adminDepartment;
	}

	public void setAdminDepartment(String adminDepartment) {
		this.adminDepartment = adminDepartment;
	}

	public Administrator(@NotNull(message = "CNP is required!") Long persIdentityNum,
			@NotNull(message = "First name is required!") @Size(min = 1, message = "First name must be an explicit name!") String persFirstName,
			@NotNull(message = "Last name is required!") @Size(min = 1, message = "Last name must be an explicit name!") String persLastName,
			@Past(message = "Date of birth must be from past!") Date persDoB,
			@Email(message = "Email must be in correct format! example@mail.com") @NotNull @Size(min = 5, message = "Email must be in correct format!") String persEmail,
			@NotNull String persPassword, @NotNull String persPhone,
			@Size(min = 1, message = "Adress must be explicit!") String persAdress, @NotNull Long adminId,
			String adminTitle, @NotEmpty String adminDepartment) {
		super(persIdentityNum, persFirstName, persLastName, persDoB, persEmail, persPassword, persPhone, persAdress);
		this.adminId = adminId;
		this.adminTitle = adminTitle;
		this.adminDepartment = adminDepartment;
	}

	public Administrator() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(adminDepartment, adminId, adminTitle);
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
		Administrator other = (Administrator) obj;
		return Objects.equals(adminDepartment, other.adminDepartment) && Objects.equals(adminId, other.adminId)
				&& Objects.equals(adminTitle, other.adminTitle);
	}

	@Override
	public String toString() {
		return "Administrator [adminId=" + adminId + ", adminTitle=" + adminTitle + ", adminDepartment="
				+ adminDepartment + "]";
	}
}
