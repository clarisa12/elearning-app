package com.msd.elearningapp.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Notification {

    @Temporal(TemporalType.DATE)
    private Date dateNotif;

    @NotNull  @Id @GeneratedValue
    private Integer idNotif;

    @NotEmpty
    private String txtNotif;

    public Date getDateNotif() {
        return dateNotif;
    }
    public void setDateNotif(Date dateNotif) {
        this.dateNotif = dateNotif;
    }
    public Integer getIdNotif() {
        return idNotif;
    }
    public void setIdNotif(Integer idNotif) {
        this.idNotif = idNotif;
    }
    public String getTxtNotif() {
        return txtNotif;
    }
    public void setTxtNotif(String txtNotif) {
        this.txtNotif = txtNotif;
    }


}
