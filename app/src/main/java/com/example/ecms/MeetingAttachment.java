package com.example.ecms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeetingAttachment {

    @SerializedName("MeetingAttachmentId")
    @Expose
    private String meetingAttachmentId;
    @SerializedName("FileUrl")
    @Expose
    private String fileUrl;
    @SerializedName("Description")
    @Expose
    private String description;

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
