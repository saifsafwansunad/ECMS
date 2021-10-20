package com.example.ecms.ApiResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ToAttendMeetingResponse implements Parcelable {

    @SerializedName("MeetingId")
    @Expose
    public String meetingId;
    @SerializedName("MeetingType")
    @Expose
    public String meetingType;
    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("Agenda")
    @Expose
    public String agenda;
    @SerializedName("Description")
    @Expose
    public String description;
    @SerializedName("StartDate")
    @Expose
    public String startDate;
    @SerializedName("IsMSTeamMeeting")
    @Expose
    public String isMSTeamMeeting;
    @SerializedName("MSTeamMeetingID")
    @Expose
    public String mSTeamMeetingID;
    @SerializedName("MSTeamMeetingWebLink")
    @Expose
    public String mSTeamMeetingWebLink;
    @SerializedName("MSTeamMeetingJoinUrl")
    @Expose
    public String mSTeamMeetingJoinUrl;
    @SerializedName("MeetingAttachments")
    @Expose
    public List<MeetingAttachment> meetingAttachments;

    public ToAttendMeetingResponse(String meetingId, String meetingType, String title, String agenda, String description, String startDate, String isMSTeamMeeting, String mSTeamMeetingID, String mSTeamMeetingWebLink, String mSTeamMeetingJoinUrl, List<MeetingAttachment> meetingAttachments) {
        this.meetingId = meetingId;
        this.meetingType = meetingType;
        this.title = title;
        this.agenda = agenda;
        this.description = description;
        this.startDate = startDate;
        this.isMSTeamMeeting = isMSTeamMeeting;
        this.mSTeamMeetingID = mSTeamMeetingID;
        this.mSTeamMeetingWebLink = mSTeamMeetingWebLink;
        this.mSTeamMeetingJoinUrl = mSTeamMeetingJoinUrl;
        this.meetingAttachments = meetingAttachments;
    }

    protected ToAttendMeetingResponse(Parcel in) {
        meetingId = in.readString();
        meetingType = in.readString();
        title = in.readString();
        agenda = in.readString();
        description = in.readString();
        startDate = in.readString();
        isMSTeamMeeting = in.readString();
        mSTeamMeetingID = in.readString();
        mSTeamMeetingWebLink = in.readString();
        mSTeamMeetingJoinUrl = in.readString();
    }

    public static final Creator<ToAttendMeetingResponse> CREATOR = new Creator<ToAttendMeetingResponse>() {
        @Override
        public ToAttendMeetingResponse createFromParcel(Parcel in) {
            return new ToAttendMeetingResponse(in);
        }

        @Override
        public ToAttendMeetingResponse[] newArray(int size) {
            return new ToAttendMeetingResponse[size];
        }
    };

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

    public String getmSTeamMeetingID() {
        return mSTeamMeetingID;
    }

    public void setmSTeamMeetingID(String mSTeamMeetingID) {
        this.mSTeamMeetingID = mSTeamMeetingID;
    }

    public String getmSTeamMeetingWebLink() {
        return mSTeamMeetingWebLink;
    }

    public void setmSTeamMeetingWebLink(String mSTeamMeetingWebLink) {
        this.mSTeamMeetingWebLink = mSTeamMeetingWebLink;
    }

    public String getmSTeamMeetingJoinUrl() {
        return mSTeamMeetingJoinUrl;
    }

    public void setmSTeamMeetingJoinUrl(String mSTeamMeetingJoinUrl) {
        this.mSTeamMeetingJoinUrl = mSTeamMeetingJoinUrl;
    }

    public List<MeetingAttachment> getMeetingAttachments() {
        return meetingAttachments;
    }

    public void setMeetingAttachments(List<MeetingAttachment> meetingAttachments) {
        this.meetingAttachments = meetingAttachments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(meetingId);
        dest.writeString(meetingType);
        dest.writeString(title);
        dest.writeString(agenda);
        dest.writeString(description);
        dest.writeString(startDate);
        dest.writeString(isMSTeamMeeting);
        dest.writeString(mSTeamMeetingID);
        dest.writeString(mSTeamMeetingWebLink);
        dest.writeString(mSTeamMeetingJoinUrl);
    }

    public class MeetingAttachment{
        @SerializedName("MeetingAttachmentId")
        @Expose
        public String meetingAttachmentId;
        @SerializedName("FileUrl")
        @Expose
        public String fileUrl;
        @SerializedName("Description")
        @Expose
        public String description;

        public String getMeetingAttachmentId() {
            return meetingAttachmentId;
        }

        public void setMeetingAttachmentId(String meetingAttachmentId) {
            this.meetingAttachmentId = meetingAttachmentId;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

