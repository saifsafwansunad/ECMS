package com.ecms.ndmecms;

public class Commitee {
    private String Broader;
    private String show;

    public Commitee(String broader, String show) {
        Broader = broader;
        this.show = show;
    }

    public String getBroader() {
        return Broader;
    }

    public void setBroader(String broader) {
        Broader = broader;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
}
