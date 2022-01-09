package com.msd.elearningapp.domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;

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
	private String wrkName;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Student> wrkList;

	// @OneToOne(fetch = FetchType.LAZY)
	// @NotNull(message = "Workgroup must have a mentor!")
	// @JoinColumn(name = "profId")
	// private Professor mentor;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
	private Set<Assignment> wrkAssig;

	public Long getWrkId() {
		return wrkId;
	}

	public void setWrkId(Long wrkId) {
		this.wrkId = wrkId;
	}

	public String getWrkName() {
		return wrkName;
	}

	public void setWrkName(String wrkName) {
		this.wrkName = wrkName;
	}

	public List<Student> getWrkList() {
		return wrkList;
	}

	public void setWrkList(List<Student> wrkList) {
		this.wrkList = wrkList;
	}

	public Set<Assignment> getWrkAssig() {
		return wrkAssig;
	}

	public void setWrkAssig(Set<Assignment> wrkAssig) {
		this.wrkAssig = wrkAssig;
	}

	public Workgroup(@NotNull Long wrkId,
			@NotNull(message = "Workgroup Name is required!") @Size(min = 1, message = "Workgroup Name must have an explicit name!") String wrkName,
			List<Student> wrkList, Set<Assignment> wrkAssig) {
		super();
		this.wrkId = wrkId;
		this.wrkName = wrkName;
		this.wrkList = wrkList;
		this.wrkAssig = wrkAssig;
	}

	public Workgroup() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(wrkAssig, wrkId, wrkList, wrkName);
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
		return Objects.equals(wrkAssig, other.wrkAssig) && Objects.equals(wrkId, other.wrkId)
				&& Objects.equals(wrkList, other.wrkList) && Objects.equals(wrkName, other.wrkName);
	}

	

}
