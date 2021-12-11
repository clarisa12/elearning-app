package com.msd.elearningapp.services;


import com.msd.elearningapp.domain.Notification;

public interface ISendNotification {

    public Notification sendAssigGrade(Double grade);
    public Notification sendTskAssigned();
    public Notification sendWrkCreated();
    public Notification sendWrkDeleted();
    public Notification sendWrkGrade(Double grade);

}
