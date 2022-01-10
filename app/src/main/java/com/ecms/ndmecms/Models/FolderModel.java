package com.ecms.ndmecms.Models;

public class FolderModel {
    private String Foldername;
    private String Folderpath;

    public FolderModel(String foldername, String folderpath) {
        Foldername = foldername;
        Folderpath = folderpath;
    }

    public String getFoldername() {
        return Foldername;
    }

    public void setFoldername(String foldername) {
        Foldername = foldername;
    }

    public String getFolderpath() {
        return Folderpath;
    }

    public void setFolderpath(String folderpath) {
        Folderpath = folderpath;
    }
}
