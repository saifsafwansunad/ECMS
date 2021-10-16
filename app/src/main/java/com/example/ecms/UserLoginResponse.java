package com.example.ecms;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class UserLoginResponse {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("PhotoURL")
    @Expose
    private String photoURL;
    @SerializedName("IsCouncillor")
    @Expose
    private String isCouncillor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getIsCouncillor() {
        return isCouncillor;
    }

    public void setIsCouncillor(String isCouncillor) {
        this.isCouncillor = isCouncillor;
    }

}
