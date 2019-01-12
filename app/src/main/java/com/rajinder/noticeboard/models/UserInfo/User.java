
package com.rajinder.noticeboard.models.UserInfo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "User")
public class User extends Model {

    @Column(name = "userid")
    @SerializedName("userid")
    @Expose
    public Integer userid;
    @Column(name = "username")
    @SerializedName("username")
    @Expose
    public String username;
    @Column(name = "email")
    @SerializedName("email")
    @Expose
    public String email;
    @Column(name = "phone_number")
    @SerializedName("phone_number")
    @Expose
    public String phoneNumber;
    @Column(name = "user_img")
    @SerializedName("user_img")
    @Expose
    public String user_img;
    @Column(name = "address")
    @SerializedName("address")
    @Expose
    public String address;
    @Column(name = "post_total")
    @SerializedName("post_total")
    @Expose
    public String post_total;

    public User(Integer userid, String username, String email, String phoneNumber, String user_img, String address, String post_total) {
        super();
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.user_img = user_img;
        this.address = address;
        this.post_total = post_total;
    }

    public User() {
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPost_total(String post_total) {
        this.post_total = post_total;
    }

    public String getPost_total() {
        return post_total;
    }
}
