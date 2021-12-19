package com.ecms.ndmecms.Models;

public class PublicModels {
    public PublicModels(String publicHeader, String publicSubHeader) {
        this.publicHeader = publicHeader;
        this.publicSubHeader = publicSubHeader;
    }

    public String getPublicHeader() {
        return publicHeader;
    }

    public void setPublicHeader(String publicHeader) {
        this.publicHeader = publicHeader;
    }

    public String getPublicSubHeader() {
        return publicSubHeader;
    }

    public void setPublicSubHeader(String publicSubHeader) {
        this.publicSubHeader = publicSubHeader;
    }

    private String publicHeader;
    private String publicSubHeader;
}
