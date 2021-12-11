package com.msd.elearningapp.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Grades {

    @NotNull(message = "Grade Date is required!")
    @Temporal(TemporalType.DATE)
    private Date gradeDate;

    @NotNull @Min(1) @Max(10)
    private Double gradeValue;

    @Id @GeneratedValue
    private Integer gradeId;
    public Date getGradeDate() {
        return gradeDate;
    }
    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }
    public Double getGradeValue() {
        return gradeValue;
    }
    public void setGradeValue(Double gradeValue) {
        this.gradeValue = gradeValue;
    }
    public Integer getGradeId() {
        return gradeId;
    }
    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }


}
