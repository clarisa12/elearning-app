package com.msd.elearningapp.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Documentation {
	
    @Size(min= 1, max= 10000)
    private String docBody;

    private String docObs;

    @Id @GeneratedValue
    private Long idDoc;
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
	public Documentation(@Size(min = 1, max = 10000) String docBody, String docObs, Long idDoc) {
		super();
		this.docBody = docBody;
		this.docObs = docObs;
		this.idDoc = idDoc;
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
	
	
}
