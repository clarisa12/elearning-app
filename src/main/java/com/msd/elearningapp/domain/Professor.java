package com.msd.elearningapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "Professor")
@Table(name = "professor")
public class Professor extends Person {
    @NotEmpty
    private String profDepartment;
    @NotEmpty
    private String profFaculty;

    private String profTitle;

    @Id @GeneratedValue @NotNull
    private String profId;

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
    public String getProfId() {
        return profId;
    }
    public void setProfId(String profId) {
        this.profId = profId;
    }

}

