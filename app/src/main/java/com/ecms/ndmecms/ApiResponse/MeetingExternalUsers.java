package com.ecms.ndmecms.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeetingExternalUsers {

    @SerializedName("MeetingExternalUserId")
    @Expose
    public String MeetingExternalUserId;
    @SerializedName("PersonEmail")
    @Expose
    public String PersonEmail;
    @SerializedName("PersonName")
    @Expose
    public String PersonName;
    @SerializedName("PersonMobile")
    @Expose
    public String PersonMobile;

    public MeetingExternalUsers(String meetingExternalUserId, String personEmail, String personName, String personMobile) {
        MeetingExternalUserId = meetingExternalUserId;
        PersonEmail = personEmail;
        PersonName = personName;
        PersonMobile = personMobile;
    }

    public String getMeetingExternalUserId() {
        return MeetingExternalUserId;
    }

    public void setMeetingExternalUserId(String meetingExternalUserId) {
        MeetingExternalUserId = meetingExternalUserId;
    }

    public String getPersonEmail() {
        return PersonEmail;
    }

    public void setPersonEmail(String personEmail) {
        PersonEmail = personEmail;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getPersonMobile() {
        return PersonMobile;
    }

    public void setPersonMobile(String personMobile) {
        PersonMobile = personMobile;
    }
}
