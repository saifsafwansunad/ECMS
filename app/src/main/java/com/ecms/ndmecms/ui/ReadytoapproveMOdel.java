package com.ecms.ndmecms.ui;

public class ReadytoapproveMOdel {
    private String Title;
    private String Meetingtype;
    private String Agenda;
    private String Date;

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

    public ReadytoapproveMOdel(String title, String meetingtype, String agenda, String date) {
        Title = title;
        Meetingtype = meetingtype;
        Agenda = agenda;
        Date = date;
    }
}
