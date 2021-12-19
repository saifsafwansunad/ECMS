package com.ecms.ndmecms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PasswordResp {

    @SerializedName("Saved")
    @Expose
    private String saved;

    public String getSaved() {
        return saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
    }

}