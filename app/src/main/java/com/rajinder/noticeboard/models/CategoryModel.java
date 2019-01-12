package com.rajinder.noticeboard.models;


import java.util.ArrayList;

/**
 * Created by rajinder on 4/21/2018.
 */
public class CategoryModel  {

    String cate_id;
    int cate_image;
    String cate_name;
    String cate_status;
    boolean isFvt;

    ArrayList<CategoryModel> categoryModels;


    public CategoryModel(String cate_id, int cate_image, String cate_name, String cate_status, boolean isFvt) {
        super();
        this.cate_id = cate_id;
        this.cate_image = cate_image;
        this.cate_name = cate_name;
        this.cate_status = cate_status;
        this.isFvt = isFvt;
    }

    public CategoryModel() {
    }

    public void setCategoryModels(ArrayList<CategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
    }

    public ArrayList<CategoryModel> getCategoryModels() {
        return categoryModels;
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

    public String getCate_status() {
        return cate_status;
    }

    public void setCate_status(String cate_distance) {
        this.cate_status = cate_distance;
    }


    public boolean getIsFvt() {
        return isFvt;
    }

    public void setIsFvt(boolean isFvt) {
        this.isFvt = isFvt;
    }
}
