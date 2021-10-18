package com.example.ecms.Models;

public class ToattendMeetingsModel {
    private String Title;
    private String Meetingtype;
    private String Agenda;
    private String StartDate;
    private String isMSTeamMeeting;

    public ToattendMeetingsModel(String title, String meetingtype, String agenda, String startDate, String isMSTeamMeeting) {
        Title = title;
        Meetingtype = meetingtype;
        Agenda = agenda;
        StartDate = startDate;
        this.isMSTeamMeeting = isMSTeamMeeting;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getMeetingtype() {
        return Meetingtype;
    }

    public void setMeetingtype(String meetingtype) {
        Meetingtype = meetingtype;
    }

    public String getAgenda() {
        return Agenda;
    }

    public void setAgenda(String agenda) {
        Agenda = agenda;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getIsMSTeamMeeting() {
        return isMSTeamMeeting;
    }

    public void setIsMSTeamMeeting(String isMSTeamMeeting) {
        this.isMSTeamMeeting = isMSTeamMeeting;
    }
}
