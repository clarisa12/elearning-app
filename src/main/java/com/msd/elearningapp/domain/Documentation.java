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
    private String idDoc;
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
    public String getIdDoc() {
        return idDoc;
    }
    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }


}
