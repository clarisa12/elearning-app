package com.msd.elearningapp.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
public class Grades {

    @NotNull(message = "Grade Date is required!")
    @Temporal(TemporalType.DATE)
    @Past(message = "Grade date must be earlier than present!")
    private Date gradeDate;

    @NotNull 
    @Min(1)
    @Max(10)
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
	public Grades(
			@NotNull(message = "Grade Date is required!") @Past(message = "Grade date must be earlier than present!") Date gradeDate,
			@NotNull @Min(1) @Max(10) Double gradeValue, Integer gradeId) {
		super();
		this.gradeDate = gradeDate;
		this.gradeValue = gradeValue;
		this.gradeId = gradeId;
	}

}
