package com.rajinder.noticeboard.models;

/**
 * Created by rajinder on 5/8/2018.
 */

public class NotificationModel {

    String noti_id;
    int noti_image;
    String noti_name;
    String noti_dis;
    String noti_time;
    boolean isFvt;

    public NotificationModel(String noti_id, int noti_image, String noti_name, String noti_dis, String noti_time, boolean isFvt) {
        this.noti_id = noti_id;
        this.noti_image = noti_image;
        this.noti_name = noti_name;
        this.noti_dis = noti_dis;
        this.noti_time = noti_time;
        this.isFvt = isFvt;
    }

    public NotificationModel() {
    }

    public String getNoti_id() {
        return noti_id;
    }

    public void setNoti_id(String noti_id) {
        this.noti_id = noti_id;
    }

    public int getNoti_image() {
        return noti_image;
    }

    public void setNoti_image(int noti_image) {
        this.noti_image = noti_image;
    }

    public String getNoti_name() {
        return noti_name;
    }

    public void setNoti_name(String noti_name) {
        this.noti_name = noti_name;
    }

    public String getNoti_dis() {
        return noti_dis;
    }

    public void setNoti_dis(String noti_dis) {
        this.noti_dis = noti_dis;
    }

    public String getNoti_time() {
        return noti_time;
    }

    public void setNoti_time(String noti_time) {
        this.noti_time = noti_time;
    }


    public boolean getIsFvt() {
        return isFvt;
    }

    public void setIsFvt(boolean isFvt) {
        this.isFvt = isFvt;
    }
}
