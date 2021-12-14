package com.msd.elearningapp.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Notification")
@Entity
@Table(name = "notification")
public class Notification {

	@NotNull
	@Id
	@GeneratedValue
	private Long idNotif;

	@Temporal(TemporalType.DATE)
	private Date dateNotif;

	@NotEmpty
	private String txtNotif;

	public Date getDateNotif() {
		return dateNotif;
	}

	public void setDateNotif(Date dateNotif) {
		this.dateNotif = dateNotif;
	}

	public @NotNull Long getIdNotif() {
		return idNotif;
	}

	public void setIdNotif(@NotNull Long idNotif) {
		this.idNotif = idNotif;
	}

	public String getTxtNotif() {
		return txtNotif;
	}

	public void setTxtNotif(String txtNotif) {
		this.txtNotif = txtNotif;
	}

	public Notification(@NotNull Long idNotif, Date dateNotif, @NotEmpty String txtNotif) {
		super();
		this.idNotif = idNotif;
		this.dateNotif = dateNotif;
		this.txtNotif = txtNotif;
	}

	public Notification() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateNotif, idNotif, txtNotif);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		return Objects.equals(dateNotif, other.dateNotif) && Objects.equals(idNotif, other.idNotif)
				&& Objects.equals(txtNotif, other.txtNotif);
	}

	@Override
	public String toString() {
		return "Notification [idNotif=" + idNotif + ", dateNotif=" + dateNotif + ", txtNotif=" + txtNotif + "]";
	}

}
