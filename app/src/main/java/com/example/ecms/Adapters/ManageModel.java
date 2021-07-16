package com.example.ecms.Adapters;

public class ManageModel {

    private String Title;
    private String Meetingtype;
    private String Agenda;
    private String Date;
    private String createdBy;

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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ManageModel(String title, String meetingtype, String agenda, String date, String createdBy) {
        Title = title;
        Meetingtype = meetingtype;
        Agenda = agenda;
        Date = date;
        this.createdBy = createdBy;
    }
}


