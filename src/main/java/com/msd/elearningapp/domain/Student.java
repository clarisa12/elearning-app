package com.msd.elearningapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Student extends Person {
    @NotEmpty
    private String studFaculty;

    @NotEmpty
    private String studSpecialization;

    @Id @GeneratedValue @NotNull
    private String studId;

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
    public String getStudId() {
        return studId;
    }
    public void setStudId(String studId) {
        this.studId = studId;
    }


}
