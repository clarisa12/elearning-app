package com.msd.elearningapp.domain;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "workgroup")
public class Workgroups {

    @Id @GeneratedValue @NotNull
    private Integer wrkID;

    @OneToMany
    private List<Student> wkrList;

    @NotNull(message = "Workgroup Name is required!")
    @Size(min=1, message = "Workgroup Name must have an explicit name!")
    private String wkrName;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn (name="profId")
    private Professor mentor;

    public Integer getWrkID() {
        return wrkID;
    }
    public void setWrkID(Integer wrkID) {
        this.wrkID = wrkID;
    }
    public List<Student> getWkrList() {
        return wkrList;
    }
    public void setWkrList(ArrayList<Student> wkrList) {
        this.wkrList = wkrList;
    }
    public String getWkrName() {
        return wkrName;
    }
    public void setWkrName(String wkrName) {
        this.wkrName = wkrName;
    }
    public Professor getMentor() {
        return mentor;
    }
    public void setMentor(Professor mentor) {
        this.mentor = mentor;
    }

}
