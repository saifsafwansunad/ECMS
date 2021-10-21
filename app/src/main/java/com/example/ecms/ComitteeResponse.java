package com.example.ecms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComitteeResponse {


    @SerializedName("CommitteeId")
    @Expose
    private String committeeId;
    @SerializedName("CommitteeName")
    @Expose
    private String committeeName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("FolderPath")
    @Expose
    private String folderPath;

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }


}
