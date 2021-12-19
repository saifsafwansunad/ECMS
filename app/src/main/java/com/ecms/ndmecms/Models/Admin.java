package com.ecms.ndmecms.Models;

public class Admin {

    private String ID;

    public String getID() {
        return ID;
    }

    public Admin(String ID, String title, String priority, String messageto) {
        this.ID = ID;
        Title = title;
        this.priority = priority;
        this.messageto = messageto;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMessageto() {
        return messageto;
    }

    public void setMessageto(String messageto) {
        this.messageto = messageto;
    }

    private String Title;
    private String priority;
    private String messageto;



}
