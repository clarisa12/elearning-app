package com.msd.elearningapp.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Person {
    @NotNull(message = "CNP is required!")
    private Long persIdentityNum;

    @NotNull(message = "First name is required!")
    @Size(min=1, message = "First name must be an explicit name!")
    private String persFirstName;

    @NotNull(message = "Last name is required!")
    @Size(min=1, message = "Last name must be an explicit name!")
    private String persLastName;

    @Temporal(TemporalType.DATE)
    @Past(message ="Date of birth must be from past!")
    private Date persDoB;

    @Email(message = "Email must be in correct format! example@mail.com")
    @NotNull
    @Size(min=5, message = "Email must be in correct format!")
    private String persEmail;

    private String persNum;
    
    @Size(min=1, message = "Adress must be explicit!")
    private String persAdress;
    
    private String persPhone;
    
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
    public String getPersNum() {
        return persNum;
    }
    public void setPersNum(String persNum) {
        this.persNum = persNum;
    }
    public String getPersAdress() {
        return persAdress;
    }
    public void setPersAdress(String persAdress) {
        this.persAdress = persAdress;
    }
    public String getPersPhone() {
        return persPhone;
    }
    public void setPersPhone(String persPhone) {
        this.persPhone = persPhone;
    }
    @Override
    public int hashCode() {
        return Objects.hash(persIdentityNum);
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
        return Objects.equals(persIdentityNum, other.persIdentityNum);
    }

    public int compareTo(Person other) {
        if (this.equals(other))
            return 0;
        return this.getPersIdentityNum().compareTo(other.getPersIdentityNum());
    }
    @Override
    public String toString() {
        return "Person [persIdentityNum=" + persIdentityNum + ", persFirstName=" + persFirstName + ", persLastName="
                + persLastName + ", persDoB=" + persDoB + ", persEmail=" + persEmail + ", persNum=" + persNum
                + ", persAdress=" + persAdress + ", persPhone=" + persPhone + "]";
    }
	public Person(@NotNull Long persIdentityNum, @NotNull String persFirstName, @NotNull String persLastName,
			@Past Date persDoB, @Email @NotNull String persEmail, @NotNull String persNum, String persAdress,
			String persPhone) {
		super();
		this.persIdentityNum = persIdentityNum;
		this.persFirstName = persFirstName;
		this.persLastName = persLastName;
		this.persDoB = persDoB;
		this.persEmail = persEmail;
		this.persNum = persNum;
		this.persAdress = persAdress;
		this.persPhone = persPhone;
	}
	public Person() {
		super();
	}
	
}