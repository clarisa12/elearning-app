package com.msd.elearningapp.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Administrator extends Person {


	@NotEmpty
    private String adminDepartment;

    @Id @NotNull  @GeneratedValue
    private Long adminId;

    private String adminTitle;

    public String getAdminDepartment() {
        return adminDepartment;
    }
    public void setAdminDepartment(String adminDepartment) {
        this.adminDepartment = adminDepartment;
    }
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

    @Override
    public String toString() {
        return "Administrator{" +
                "adminDepartment='" + adminDepartment + '\'' +
                ", adminId=" + adminId +
                ", adminTitle='" + adminTitle + '\'' +
                '}';
    }
	public Administrator(@NotNull Long persIdentityNum, @NotNull String persFirstName, @NotNull String persLastName,
			@Past Date persDoB, @Email @NotNull String persEmail, @NotNull String persNum, String persAdress,
			String persPhone, @NotEmpty String adminDepartment, @NotNull @NotNull Long adminId, String adminTitle) {
		super(persIdentityNum, persFirstName, persLastName, persDoB, persEmail, persNum, persAdress, persPhone);
		this.adminDepartment = adminDepartment;
		this.adminId = adminId;
		this.adminTitle = adminTitle;
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
    
}
