package com.msd.elearningapp.domain;

import java.util.List;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "workgroup")
public class Workgroups {

    @Id @GeneratedValue @NotNull
    private Integer wrkId;

    @OneToMany
    private List<Student> wkrList;

    @NotNull(message = "Workgroup Name is required!")
    @Size(min=1, message = "Workgroup Name must have an explicit name!")
    private String wkrName;

    @OneToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Workgroup must have a mentor!")
    @JoinColumn (name="profId")
    private Professor mentor;

    public Integer getWrkID() {
        return wrkId;
    }
    public void setWrkID(Integer wrkId) {
        this.wrkId = wrkId;
    }
    public List<Student> getWkrList() {
        return wkrList;
    }
    public void setWkrList(List<Student> list) {
        this.wkrList = list;
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
	public Workgroups(@NotNull Integer wrkId, List<Student> wkrList,
			@NotNull(message = "Workgroup Name is required!") @Size(min = 1, message = "Workgroup Name must have an explicit name!") String wkrName,
			@NotNull(message = "Workgroup must have a mentor!") Professor mentor) {
		super();
		this.wrkId = wrkId;
		this.wkrList = wkrList;
		this.wkrName = wkrName;
		this.mentor = mentor;
	}

}
