package com.msd.elearningapp.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Grade")
@Entity
@Table(name = "grade")
public class Grade {

	@Id
	@GeneratedValue
	private Long gradeId;

	@NotNull
	@Min(1)
	@Max(10)
	private Double gradeValue;

	@NotNull(message = "Grade Date is required!")
	@Temporal(TemporalType.DATE)
	@Past(message = "Grade date must be earlier than present!")
	private Date gradeDate;

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

	public Grade(Long gradeId, @NotNull @Min(1) @Max(10) Double gradeValue,
			@NotNull(message = "Grade Date is required!") @Past(message = "Grade date must be earlier than present!") Date gradeDate) {
		super();
		this.gradeId = gradeId;
		this.gradeValue = gradeValue;
		this.gradeDate = gradeDate;
	}

	public Grade() {
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
		Grade other = (Grade) obj;
		return Objects.equals(gradeDate, other.gradeDate) && Objects.equals(gradeId, other.gradeId)
				&& Objects.equals(gradeValue, other.gradeValue);
	}

	@Override
	public String toString() {
		return "Grades [gradeId=" + gradeId + ", gradeValue=" + gradeValue + ", gradeDate=" + gradeDate + "]";
	}

}
