package com.msd.elearningapp.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "valid"})
@JsonInclude(Include.NON_NULL)
//
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Documentation")
@Entity
@Table(name = "documentation")
public class Documentation implements Serializable, Comparable<Documentation>{

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long idDoc;

	@NotNull
	@Size(min = 1, max = 10000)
	private String docBody;

	private String docObs;

	public String getDocBody() {
		return docBody;
	}

	public void setDocBody(String docBody) {
		this.docBody = docBody;
	}

	public String getDocObs() {
		return docObs;
	}

	public void setDocObs(String docObs) {
		this.docObs = docObs;
	}

	public Long getIdDoc() {
		return idDoc;
	}

	public void setIdDoc(Long idDoc) {
		this.idDoc = idDoc;
	}

	public Documentation(Long idDoc, @Size(min = 1, max = 10000) String docBody, String docObs) {
		super();
		this.idDoc = idDoc;
		this.docBody = docBody;
		this.docObs = docObs;
	}

	public Documentation() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(docBody, docObs, idDoc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documentation other = (Documentation) obj;
		return Objects.equals(docBody, other.docBody) && Objects.equals(docObs, other.docObs)
				&& Objects.equals(idDoc, other.idDoc);
	}

	@Override
	public String toString() {
		return "Documentation [idDoc=" + idDoc + ", docBody=" + docBody + ", docObs=" + docObs + "]";
	}

	@Override
	public int compareTo(Documentation o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
