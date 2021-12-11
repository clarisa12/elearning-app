package com.msd.elearningapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Administrator extends Person {

    @NotEmpty
    private String adminDepartment;

    @Id @NotNull  @GeneratedValue
    private Integer adminId;

    private String adminTitle;

    public String getAdminDepartment() {
        return adminDepartment;
    }
    public void setAdminDepartment(String adminDepartment) {
        this.adminDepartment = adminDepartment;
    }
    public Integer getAdminId() {
        return adminId;
    }
    public void setAdminId(Integer adminId) {
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
}
