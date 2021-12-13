package com.msd.elearningapp.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity(name = "Professor")
@Table(name = "professor")
public class Professor extends Person {
    @NotEmpty(message = "Professor must be part of a department!")
    private String profDepartment;
    @NotEmpty(message = "Professor must be part of a faculty!")
    private String profFaculty;
    @NotEmpty(message = "Professor must have a title!")
    private String profTitle;

    @Id @GeneratedValue @NotNull
    private Long profId;

    /*@OneToOne(mappedBy = "mentor", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Workgroups workgroups;*/

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
	public Professor(@NotNull Long persIdentityNum, @NotNull String persFirstName, @NotNull String persLastName,
			@Past Date persDoB, @Email @NotNull String persEmail, @NotNull String persNum, String persAdress,
			String persPhone, @NotEmpty String profDepartment, @NotEmpty String profFaculty, String profTitle,
			@NotNull Long profId) {
		super(persIdentityNum, persFirstName, persLastName, persDoB, persEmail, persNum, persAdress, persPhone);
		this.profDepartment = profDepartment;
		this.profFaculty = profFaculty;
		this.profTitle = profTitle;
		this.profId = profId;
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
    
}

