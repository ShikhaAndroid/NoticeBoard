package com.rajinder.noticeboard.models;

import java.util.Date;

/**
 * Created by rajinder on 5/12/2018.
 */

public class CommetModel {

    String comment_id;
    int user_image;
    String user_name;
    String comment;
    Date comment_time;
    boolean comment_edit;
    boolean isFvt;

    public CommetModel(String comment_id, int user_image, String user_name, String comment, Date comment_time, boolean comment_edit, boolean isFvt) {
        this.comment_id = comment_id;
        this.user_image = user_image;
        this.user_name = user_name;
        this.comment = comment;
        this.comment_time = comment_time;
        this.comment_edit = comment_edit;
        this.isFvt = isFvt;
    }

    public CommetModel() {
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_image() {
        return user_image;
    }

    public void setUser_image(int user_image) {
        this.user_image = user_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }


    public boolean isComment_edit() {
        return comment_edit;
    }

    public void setComment_edit(boolean comment_edit) {
        this.comment_edit = comment_edit;
    }

    public boolean getIsFvt() {
        return isFvt;
    }

    public void setIsFvt(boolean isFvt) {
        this.isFvt = isFvt;
    }
}
