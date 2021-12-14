package com.msd.elearningapp.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Workgroup")
@Entity
@Table(name = "workgroup")
public class Workgroup {

	@Id
	@GeneratedValue
	@NotNull
	private Long wrkId;

	@NotNull(message = "Workgroup Name is required!")
	@Size(min = 1, message = "Workgroup Name must have an explicit name!")
	private String wkrName;

	@OneToMany
	private List<Student> wkrList;

	@OneToOne(fetch = FetchType.LAZY)
	@NotNull(message = "Workgroup must have a mentor!")
	@JoinColumn(name = "profId")
	private Professor mentor;

	public @NotNull Long getWrkID() {
		return wrkId;
	}

	public void setWrkId(@NotNull Long wrkId) {
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

	public Workgroup(@NotNull Long wrkId,
			@NotNull(message = "Workgroup Name is required!") @Size(min = 1, message = "Workgroup Name must have an explicit name!") String wkrName,
			List<Student> wkrList, @NotNull(message = "Workgroup must have a mentor!") Professor mentor) {
		super();
		this.wrkId = wrkId;
		this.wkrName = wkrName;
		this.wkrList = wkrList;
		this.mentor = mentor;
	}

	public Workgroup() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(mentor, wkrList, wkrName, wrkId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Workgroup other = (Workgroup) obj;
		return Objects.equals(mentor, other.mentor) && Objects.equals(wkrList, other.wkrList)
				&& Objects.equals(wkrName, other.wkrName) && Objects.equals(wrkId, other.wrkId);
	}

	@Override
	public String toString() {
		return "Workgroups [wrkId=" + wrkId + ", wkrName=" + wkrName + ", wkrList=" + wkrList + ", mentor=" + mentor
				+ "]";
	}

}
