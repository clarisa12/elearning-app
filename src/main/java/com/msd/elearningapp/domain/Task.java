package com.msd.elearningapp.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Task {
    @Id @GeneratedValue @NotNull
    private String tskID;

    @NotNull(message = "Start Date is required!")
    @Future(message = "Start Date must be a future date!")
    @Temporal(TemporalType.DATE)
    private Date tskStartDate;

    @NotNull(message = "End Date is required!")
    @Future(message = "End Date must be a future date!")
    @Temporal(TemporalType.DATE)
    private Date tskEndDate;

    @NotNull @Size(min =1, max=1000)
    private String tskDescription;

    @OneToOne
    private Student tskStudResponsible;//HERE

    @ManyToOne
    private Assignment tskAssig; //HERE
    private TaskState tskState;
    public String getTskID() {
        return tskID;
    }
    public void setTskID(String tskID) {
        this.tskID = tskID;
    }
    public Date getTskStartDate() {
        return tskStartDate;
    }
    public void setTskStartDate(Date tskStartDate) {
        this.tskStartDate = tskStartDate;
    }
    public Date getTskEndDate() {
        return tskEndDate;
    }
    public void setTskEndDate(Date tskEndDate) {
        this.tskEndDate = tskEndDate;
    }
    public String getTskDescription() {
        return tskDescription;
    }
    public void setTskDescription(String tskDescription) {
        this.tskDescription = tskDescription;
    }
    public Student getTskStudResponsible() {
        return tskStudResponsible;
    }
    public void setTskStudResponsible(Student tskStudResponsible) {
        this.tskStudResponsible = tskStudResponsible;
    }
    public Assignment getTskAssig() {
        return tskAssig;
    }
    public void setTskAssig(Assignment tskAssig) {
        this.tskAssig = tskAssig;
    }
    public TaskState getTskState() {
        return tskState;
    }
    public void setTskState(TaskState tskState) {
        this.tskState = tskState;
    }
    public Task(String tskID, Date tskStartDate, Date tskEndDate, String tskDescription, Student tskStudResponsible,
                Assignment tskAssig, TaskState tskState) {
        super();
        this.tskID = tskID;
        this.tskStartDate = tskStartDate;
        this.tskEndDate = tskEndDate;
        this.tskDescription = tskDescription;
        this.tskStudResponsible = tskStudResponsible;
        this.tskAssig = tskAssig;
        this.tskState = tskState;
    }
    public Task() {
        super();
    }
    @Override
    public int hashCode() {
        return Objects.hash(tskID);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        return Objects.equals(tskID, other.tskID);
    }


}
