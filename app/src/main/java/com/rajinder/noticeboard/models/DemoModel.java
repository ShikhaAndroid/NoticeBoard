package com.rajinder.noticeboard.models;

/**
 * Created by rajinder on 4/21/2018.
 */

public class DemoModel {

    String cate_id;
    int cate_image;
    String cate_name;
    String cate_distance;
    String cate_disc;
    boolean isFvt;

    public DemoModel(String cate_id, int cate_image, String cate_name, String cate_disc ,String cate_distance, boolean isFvt) {
        this.cate_id = cate_id;
        this.cate_image = cate_image;
        this.cate_name = cate_name;
        this.cate_distance = cate_distance;
        this.cate_disc=cate_disc;
        this.isFvt = isFvt;
    }

    public DemoModel() {
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public int getCate_image() {
        return cate_image;
    }

    public void setCate_image(int cate_image) {
        this.cate_image = cate_image;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getCate_disc() {
        return cate_disc;
    }

    public void setCate_disc(String cate_disc) {
        this.cate_disc = cate_disc;
    }

    public String getCate_distance() {
        return cate_distance;
    }

    public void setCate_distance(String cate_distance) {
        this.cate_distance = cate_distance;
    }


    public boolean getIsFvt() {
        return isFvt;
    }

    public void setIsFvt(boolean isFvt) {
        this.isFvt = isFvt;
    }
}
