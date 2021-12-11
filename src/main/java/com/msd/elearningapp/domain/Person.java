package com.msd.elearningapp.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.Objects;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Person {
    @NotNull
    private String persIdentityNum;

    @NotNull
    private String persFirstName;

    @NotNull
    private String persLastName;

    @Temporal(TemporalType.DATE)
    @Past
    private Date persDoB;

    @Email
    @NotNull
    private String persEmail;

    @NotNull
    private String persNum;
    private String persAdress;
    private String persPhone;
    public String getPersIdentityNum() {
        return persIdentityNum;
    }
    public void setPersIdentityNum(String persIdentityNum) {
        this.persIdentityNum = persIdentityNum;
    }
    public String getPersFirstName() {
        return persFirstName;
    }
    public void setPersFirstName(String persFirstName) {
        this.persFirstName = persFirstName;
    }
    public String getPersLastName() {
        return persLastName;
    }
    public void setPersLastName(String persLastName) {
        this.persLastName = persLastName;
    }
    public Date getPersDoB() {
        return persDoB;
    }
    public void setPersDoB(Date persDoB) {
        this.persDoB = persDoB;
    }
    public String getPersEmail() {
        return persEmail;
    }
    public void setPersEmail(String persEmail) {
        this.persEmail = persEmail;
    }
    public String getPersNum() {
        return persNum;
    }
    public void setPersNum(String persNum) {
        this.persNum = persNum;
    }
    public String getPersAdress() {
        return persAdress;
    }
    public void setPersAdress(String persAdress) {
        this.persAdress = persAdress;
    }
    public String getPersPhone() {
        return persPhone;
    }
    public void setPersPhone(String persPhone) {
        this.persPhone = persPhone;
    }
    @Override
    public int hashCode() {
        return Objects.hash(persIdentityNum);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        return Objects.equals(persIdentityNum, other.persIdentityNum);
    }

    public int compareTo(Person other) {
        if (this.equals(other))
            return 0;
        return this.getPersIdentityNum().compareTo(other.getPersIdentityNum());
    }
    @Override
    public String toString() {
        return "Person [persIdentityNum=" + persIdentityNum + ", persFirstName=" + persFirstName + ", persLastName="
                + persLastName + ", persDoB=" + persDoB + ", persEmail=" + persEmail + ", persNum=" + persNum
                + ", persAdress=" + persAdress + ", persPhone=" + persPhone + "]";
    }


}