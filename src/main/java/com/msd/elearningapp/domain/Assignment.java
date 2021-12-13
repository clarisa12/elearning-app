package com.msd.elearningapp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Assignment {

    @Id @GeneratedValue @NotNull @Min(10)
    private Long assigId;

    @NotNull(message = "Start Date is required!")
    @Future(message = "Start Date must be a future date!")
    @Temporal(TemporalType.DATE)
    private Date assigDatestart;

    @NotNull(message = "End Date is required!")
    @Future(message = "End Date must be a future date!")
    @Temporal(TemporalType.DATE)
    private Date assigDateEnd;

    @NotNull(message = "Assignment Name is required!")
    @Size(min=1, message = "Assignment must have an explicit name!")
    private String assigName;

    @OneToOne
    @JoinColumn(name = "assig_starter_stud_id")
    //@NotNull
    private Student assigStarter;

    private AssignmentState assigState;

    @ManyToMany
    private List<Student> assigMem;

    public Student getAssigStarter() {
        return assigStarter;
    }

    public @NotNull @Min(10) Long getassigId() {
        return assigId;
    }
    public void setassigId(@NotNull @Min(10) Long assigId) {
        this.assigId = assigId;
    }
    public Date getassigDatestart() {
        return assigDatestart;
    }
    public void setassigDatestart(Date assigDatestart) {
        this.assigDatestart = assigDatestart;
    }
    public Date getassigDateEnd() {
        return assigDateEnd;
    }
    public void setassigDateEnd(Date assigDateEnd) {
        this.assigDateEnd = assigDateEnd;
    }
    public String getassigName() {
        return assigName;
    }
    public void setassigName(String assigName) {
        this.assigName = assigName;
    }
    public Student getassigStarter() {
        return assigStarter;
    }
    public void setassigStarter (Student assigStarter) {
        this.assigStarter = assigStarter;
    }
    public AssignmentState getassigState() {
        return assigState;
    }
    public void setassigState(AssignmentState assigState) {
        this.assigState = assigState;
    }
    public List<Student> getassigMem() {
        return assigMem;
    }
    public void setassigMem(List<Student> list) {
        this.assigMem = list;
    }
    @Override
    public String toString() {
        return "Assignment [assigId=" + assigId + ", assigDatestart=" + assigDatestart + ", assigDateEnd=" + assigDateEnd
                + ", assigName=" + assigName + ", assigStarter=" + assigStarter + ", assigState=" + assigState + ", assigMem="
                + assigMem + "]";
    }

    public Assignment(@NotNull @Min(10) Long assigId, Date assigDatestart, Date assigDateEnd, String assigName, Student assigStarter,
                      AssignmentState assigState, ArrayList<Student> assigMem) {
        super();
        this.assigId = assigId;
        this.assigDatestart = assigDatestart;
        this.assigDateEnd = assigDateEnd;
        this.assigName = assigName;
        this.assigStarter = (Student) assigStarter;
        this.assigState = assigState;
        this.assigMem = assigMem;
    }

    public Assignment() {

    }
    @Override
    public int hashCode() {
        return Objects.hash(assigId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Assignment other = (Assignment) obj;
        return Objects.equals(assigId, other.assigId);
    }

	


}
