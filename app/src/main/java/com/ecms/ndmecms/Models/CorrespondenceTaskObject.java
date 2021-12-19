package com.ecms.ndmecms.Models;

public class CorrespondenceTaskObject {

    private String TitleText;
    private String CorrespondenceText;
    private  String TargetDateText;
    private String ExtendedDaysText;
    private String AssignedToText;
    private String CompletedPercentageText;


    public CorrespondenceTaskObject(String titleText, String correspondenceText, String targetDateText, String extendedDaysText, String assignedToText, String completedPercentageText) {
        TitleText = titleText;
        CorrespondenceText = correspondenceText;
        TargetDateText = targetDateText;
        ExtendedDaysText = extendedDaysText;
        AssignedToText = assignedToText;
        CompletedPercentageText = completedPercentageText;
    }

    public String getTitleText() {
        return TitleText;
    }

    public void setTitleText(String titleText) {
        TitleText = titleText;
    }

    public String getCorrespondenceText() {
        return CorrespondenceText;
    }

    public void setCorrespondenceText(String correspondenceText) {
        CorrespondenceText = correspondenceText;
    }

    public String getTargetDateText() {
        return TargetDateText;
    }

    public void setTargetDateText(String targetDateText) {
        TargetDateText = targetDateText;
    }

    public String getExtendedDaysText() {
        return ExtendedDaysText;
    }

    public void setExtendedDaysText(String extendedDaysText) {
        ExtendedDaysText = extendedDaysText;
    }

    public String getAssignedToText() {
        return AssignedToText;
    }

    public void setAssignedToText(String assignedToText) {
        AssignedToText = assignedToText;
    }

    public String getCompletedPercentageText() {
        return CompletedPercentageText;
    }

    public void setCompletedPercentageText(String completedPercentageText) {
        CompletedPercentageText = completedPercentageText;
    }
}
