package com.msd.elearningapp.domain;

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
    private Integer idDoc;
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
    public Integer getIdDoc() {
        return idDoc;
    }
    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }
	public Documentation(@Size(min = 1, max = 10000) String docBody, String docObs, Integer idDoc) {
		super();
		this.docBody = docBody;
		this.docObs = docObs;
		this.idDoc = idDoc;
	}
}
