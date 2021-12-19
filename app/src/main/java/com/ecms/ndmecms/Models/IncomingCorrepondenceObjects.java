package com.ecms.ndmecms.Models;

public class IncomingCorrepondenceObjects  {
    public String getAutoIndexNumberText() {
        return AutoIndexNumberText;
    }

    public void setAutoIndexNumberText(String autoIndexNumberText) {
        AutoIndexNumberText = autoIndexNumberText;
    }

    public String getIndextNumberText() {
        return IndextNumberText;
    }

    public void setIndextNumberText(String indextNumberText) {
        IndextNumberText = indextNumberText;
    }

    public String getTitleText() {
        return TitleText;
    }

    public void setTitleText(String titleText) {
        TitleText = titleText;
    }

    public String getSourceText() {
        return SourceText;
    }

    public void setSourceText(String sourceText) {
        SourceText = sourceText;
    }

    public String getPriorityText() {
        return PriorityText;
    }

    public void setPriorityText(String priorityText) {
        PriorityText = priorityText;
    }

    public String getCreatedbyText() {
        return CreatedbyText;
    }

    public void setCreatedbyText(String createdbyText) {
        CreatedbyText = createdbyText;
    }

    public IncomingCorrepondenceObjects(String autoIndexNumberText, String indextNumberText, String titleText, String sourceText, String priorityText, String createdbyText) {
        AutoIndexNumberText = autoIndexNumberText;
        IndextNumberText = indextNumberText;
        TitleText = titleText;
        SourceText = sourceText;
        PriorityText = priorityText;
        CreatedbyText = createdbyText;
    }

    private String AutoIndexNumberText;
    private String IndextNumberText;
    private String TitleText;
    private String SourceText;
    private  String PriorityText;
    private String CreatedbyText;
}
