package com.msd.elearningapp.domain;

import java.util.Date;
import java.util.Objects;

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
    private Long gradeId;
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
    public Long getGradeId() {
        return gradeId;
    }
    public void setGradeId(Long id) {
        this.gradeId = id;
    }
	public Grades(
			@NotNull(message = "Grade Date is required!") @Past(message = "Grade date must be earlier than present!") Date gradeDate,
			@NotNull @Min(1) @Max(10) Double gradeValue, Long gradeId) {
		super();
		this.gradeDate = gradeDate;
		this.gradeValue = gradeValue;
		this.gradeId = gradeId;
	}
	public Grades() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(gradeDate, gradeId, gradeValue);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grades other = (Grades) obj;
		return Objects.equals(gradeDate, other.gradeDate) && Objects.equals(gradeId, other.gradeId)
				&& Objects.equals(gradeValue, other.gradeValue);
	}
}
