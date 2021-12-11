package com.msd.elearningapp.services;

import com.msd.elearningapp.domain.Meetings;

public interface IManageMeetings {

    public Meetings createMeet(String meetingTopic);
    public void deleteMeet();
    public Meetings modifyMeet(String meetingTopic);
}
