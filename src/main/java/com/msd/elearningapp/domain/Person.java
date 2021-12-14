package com.msd.elearningapp.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class Person {
	@NotNull(message = "CNP is required!")
	private Long persIdentityNum;

	@NotNull(message = "First name is required!")
	@Size(min = 1, message = "First name must be an explicit name!")
	private String persFirstName;

	@NotNull(message = "Last name is required!")
	@Size(min = 1, message = "Last name must be an explicit name!")
	private String persLastName;

	@Temporal(TemporalType.DATE)
	@Past(message = "Date of birth must be from past!")
	private Date persDoB;

	@Email(message = "Email must be in correct format! example@mail.com")
	@NotNull
	@Size(min = 5, message = "Email must be in correct format!")
	private String persEmail;

	@NotNull
	private String persPhone;

	@Size(min = 1, message = "Adress must be explicit!")
	private String persAdress;

	public Long getPersIdentityNum() {
		return persIdentityNum;
	}

	public void setPersIdentityNum(Long persIdentityNum) {
		this.persIdentityNum = persIdentityNum;
	}

	public String getPersFirstName() {
		return persFirstName;
	}

	public void setPersFirstName(String persFirstName) {
		this.persFirstName = persFirstName;
	}

	public String getPersLastName() {
		return persLastName;
	}

	public void setPersLastName(String persLastName) {
		this.persLastName = persLastName;
	}

	public Date getPersDoB() {
		return persDoB;
	}

	public void setPersDoB(Date persDoB) {
		this.persDoB = persDoB;
	}

	public String getPersEmail() {
		return persEmail;
	}

	public void setPersEmail(String persEmail) {
		this.persEmail = persEmail;
	}

	public String getPersPhone() {
		return persPhone;
	}

	public void setPersPhone(String persPhone) {
		this.persPhone = persPhone;
	}

	public String getPersAdress() {
		return persAdress;
	}

	public void setPersAdress(String persAdress) {
		this.persAdress = persAdress;
	}

	public Person(@NotNull(message = "CNP is required!") Long persIdentityNum,
			@NotNull(message = "First name is required!") @Size(min = 1, message = "First name must be an explicit name!") String persFirstName,
			@NotNull(message = "Last name is required!") @Size(min = 1, message = "Last name must be an explicit name!") String persLastName,
			@Past(message = "Date of birth must be from past!") Date persDoB,
			@Email(message = "Email must be in correct format! example@mail.com") @NotNull @Size(min = 5, message = "Email must be in correct format!") String persEmail,
			@NotNull String persPhone, @Size(min = 1, message = "Adress must be explicit!") String persAdress) {
		super();
		this.persIdentityNum = persIdentityNum;
		this.persFirstName = persFirstName;
		this.persLastName = persLastName;
		this.persDoB = persDoB;
		this.persEmail = persEmail;
		this.persPhone = persPhone;
		this.persAdress = persAdress;
	}

	public Person() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(persAdress, persDoB, persEmail, persFirstName, persIdentityNum, persLastName, persPhone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(persAdress, other.persAdress) && Objects.equals(persDoB, other.persDoB)
				&& Objects.equals(persEmail, other.persEmail) && Objects.equals(persFirstName, other.persFirstName)
				&& Objects.equals(persIdentityNum, other.persIdentityNum)
				&& Objects.equals(persLastName, other.persLastName) && Objects.equals(persPhone, other.persPhone);
	}

	@Override
	public String toString() {
		return "Person [persIdentityNum=" + persIdentityNum + ", persFirstName=" + persFirstName + ", persLastName="
				+ persLastName + ", persDoB=" + persDoB + ", persEmail=" + persEmail + ", persPhone=" + persPhone
				+ ", persAdress=" + persAdress + "]";
	}

}