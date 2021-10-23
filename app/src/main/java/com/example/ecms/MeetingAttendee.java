package com.example.ecms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeetingAttendee {

    @SerializedName("MeetingAttendeeId")
    @Expose
    private String meetingAttendeeId;
    @SerializedName("Attendee")
    @Expose
    private String attendee;
    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("IsAttended")
    @Expose
    private String isAttended;

    public String getMeetingAttendeeId() {
        return meetingAttendeeId;
    }

    public void setMeetingAttendeeId(String meetingAttendeeId) {
        this.meetingAttendeeId = meetingAttendeeId;
    }

    public String getAttendee() {
        return attendee;
    }

    public void setAttendee(String attendee) {
        this.attendee = attendee;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getIsAttended() {
        return isAttended;
    }

    public void setIsAttended(String isAttended) {
        this.isAttended = isAttended;
    }

}
