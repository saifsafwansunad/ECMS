package com.example.ecms.ApiResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MeetingAttachment {

    @SerializedName("MeetingAttachmentId")
    @Expose
    public String MeetingAttachmentId;
    @SerializedName("FileUrl")
    @Expose
    public String FileUrl;
    @SerializedName("Description")
    @Expose
    public String Description;

    public String getMeetingAttachmentId() {
        return MeetingAttachmentId;
    }

    public void setMeetingAttachmentId(String meetingAttachmentId) {
        MeetingAttachmentId = meetingAttachmentId;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        FileUrl = fileUrl;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
