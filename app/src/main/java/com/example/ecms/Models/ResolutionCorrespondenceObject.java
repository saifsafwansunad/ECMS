package com.example.ecms.Models;

public class ResolutionCorrespondenceObject {

    private String AutoIndexNumberText;
    private String IndexTNumberText;
    private String TitleText;
    private String ResolutionNumber;
    private String MeetingType;
    private  String PriorityText;
    private String CreatedByText;

    public ResolutionCorrespondenceObject(String autoIndexNumberText, String indexTNumberText, String titleText, String resolutionNumber, String meetingType, String priorityText, String createdByText) {
        AutoIndexNumberText = autoIndexNumberText;
        IndexTNumberText = indexTNumberText;
        TitleText = titleText;
        ResolutionNumber = resolutionNumber;
        MeetingType = meetingType;
        PriorityText = priorityText;
        CreatedByText = createdByText;
    }

    public String getAutoIndexNumberText() {
        return AutoIndexNumberText;
    }

    public void setAutoIndexNumberText(String autoIndexNumberText) {
        AutoIndexNumberText = autoIndexNumberText;
    }

    public String getIndexTNumberText() {
        return IndexTNumberText;
    }

    public void setIndexTNumberText(String indexTNumberText) {
        IndexTNumberText = indexTNumberText;
    }

    public String getTitleText() {
        return TitleText;
    }

    public void setTitleText(String titleText) {
        TitleText = titleText;
    }

    public String getResolutionNumber() {
        return ResolutionNumber;
    }

    public void setResolutionNumber(String resolutionNumber) {
        ResolutionNumber = resolutionNumber;
    }

    public String getMeetingType() {
        return MeetingType;
    }

    public void setMeetingType(String meetingType) {
        MeetingType = meetingType;
    }

    public String getPriorityText() {
        return PriorityText;
    }

    public void setPriorityText(String priorityText) {
        PriorityText = priorityText;
    }

    public String getCreatedByText() {
        return CreatedByText;
    }

    public void setCreatedByText(String createdByText) {
        CreatedByText = createdByText;
    }
}
