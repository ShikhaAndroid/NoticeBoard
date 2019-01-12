
package com.rajinder.noticeboard.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileViewModel {

    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("email_id")
    @Expose
    public String emailId;
    @SerializedName("phone_no")
    @Expose
    public String phoneNo;
    @SerializedName("pic_url")
    @Expose
    public String picUrl;
    @SerializedName("fb_id")
    @Expose
    public String fbId;
    @SerializedName("gmail_id")
    @Expose
    public String gmailId;
    @SerializedName("selected_cat")
    @Expose
    public String selectedCat;
    @SerializedName("notification_cat")
    @Expose
    private String notificationCat;
    @SerializedName("address")
    @Expose
    private String address;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getGmailId() {
        return gmailId;
    }

    public void setGmailId(String gmailId) {
        this.gmailId = gmailId;
    }

    public String getSelectedCat() {
        return selectedCat;
    }

    public void setSelectedCat(String selectedCat) {
        this.selectedCat = selectedCat;
    }

    public String getNotificationCat() {
        return notificationCat;
    }

    public void setNotificationCat(String notificationCat) {
        this.notificationCat = notificationCat;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

}
