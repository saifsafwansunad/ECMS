package com.ecms.ndmecms.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportResponse {

    @SerializedName("Message")
    @Expose
    public String message;

    public ReportResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
