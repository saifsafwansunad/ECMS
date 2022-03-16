package com.ecms.ndmecms;

import com.ecms.ndmecms.ApiResponse.ToAttendMeetingResponse;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommiteeMeetingModel implements Comparable<CommiteeMeetingModel> {

        @SerializedName("MeetingId")
        @Expose
        private String meetingId;
        @SerializedName("MeetingType")
        @Expose
        private String meetingType;
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Agenda")
        @Expose
        private String agenda;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("StartDate")
        @Expose
        private String startDate;
        @SerializedName("IsMSTeamMeeting")
        @Expose
        private String isMSTeamMeeting;
        @SerializedName("MSTeamMeetingID")
        @Expose
        private String mSTeamMeetingID;
        @SerializedName("MSTeamMeetingWebLink")
        @Expose
        private String mSTeamMeetingWebLink;
        @SerializedName("MSTeamMeetingJoinUrl")
        @Expose
        private String mSTeamMeetingJoinUrl;
        @SerializedName("MeetingAttachments")
        @Expose
        private JsonElement meetingAttachments = null;
        @SerializedName("MeetingAttendees")
        @Expose
        private JsonElement meetingAttendees = null;
        @SerializedName("MeetingExternalUsers")
        @Expose
        private JsonElement meetingExternalUsers;

        public String getMeetingId() {
            return meetingId;
        }

        public void setMeetingId(String meetingId) {
            this.meetingId = meetingId;
        }

        public String getMeetingType() {
            return meetingType;
        }

        public void setMeetingType(String meetingType) {
            this.meetingType = meetingType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAgenda() {
            return agenda;
        }

        public void setAgenda(String agenda) {
            this.agenda = agenda;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getIsMSTeamMeeting() {
            return isMSTeamMeeting;
        }

        public void setIsMSTeamMeeting(String isMSTeamMeeting) {
            this.isMSTeamMeeting = isMSTeamMeeting;
        }

        public String getMSTeamMeetingID() {
            return mSTeamMeetingID;
        }

        public void setMSTeamMeetingID(String mSTeamMeetingID) {
            this.mSTeamMeetingID = mSTeamMeetingID;
        }

        public String getMSTeamMeetingWebLink() {
            return mSTeamMeetingWebLink;
        }

        public void setMSTeamMeetingWebLink(String mSTeamMeetingWebLink) {
            this.mSTeamMeetingWebLink = mSTeamMeetingWebLink;
        }

        public String getMSTeamMeetingJoinUrl() {
            return mSTeamMeetingJoinUrl;
        }

        public void setMSTeamMeetingJoinUrl(String mSTeamMeetingJoinUrl) {
            this.mSTeamMeetingJoinUrl = mSTeamMeetingJoinUrl;
        }

        public JsonElement getMeetingAttachments() {
            return meetingAttachments;
        }

        public void setMeetingAttachments(JsonElement meetingAttachments) {
            this.meetingAttachments = meetingAttachments;
        }

        public JsonElement getMeetingAttendees() {
            return meetingAttendees;
        }

        public void setMeetingAttendees(JsonElement meetingAttendees) {
            this.meetingAttendees = meetingAttendees;
        }

        public JsonElement getMeetingExternalUsers() {
            return meetingExternalUsers;
        }

        public void setMeetingExternalUsers(JsonElement meetingExternalUsers) {
            this.meetingExternalUsers = meetingExternalUsers;
        }

    public Date getDateTime(){
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("MM/dd/yyyy HH:mm aa").parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    @Override
    public int compareTo(CommiteeMeetingModel o) {
        if (getDateTime() == null || o.getDateTime() == null)
            return 0;
        return getDateTime().compareTo(o.getDateTime());
    }
}


