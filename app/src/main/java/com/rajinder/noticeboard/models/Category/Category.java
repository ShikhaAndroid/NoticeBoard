
package com.rajinder.noticeboard.models.Category;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "Category")
public class Category extends Model {
    @Column(name = "catid")
    @SerializedName("catid")
    @Expose
    public Integer catid;
    @Column(name = "catname")
    @SerializedName("catname")
    @Expose
    public String catname;
    @Column(name = "caticon")
    @SerializedName("caticon")
    @Expose
    public String caticon;
    @Column(name = "catselect")
    @SerializedName("catselect")
    @Expose
    public Boolean catselect;
    @Column(name = "catnotification")
    @SerializedName("catnotification")
    @Expose
    public Boolean catnotification;
    @Column(name = "type")
    @SerializedName("type")
    @Expose
    public String type;

    public Category(Integer cate_id, String cate_name, String caticon, boolean catselect, boolean catnotification, String type) {
        super();
        this.catid = cate_id;
        this.catname = cate_name;
        this.caticon = caticon;
        this.catselect = catselect;
        this.catnotification = catnotification;
        this.type = type;
    }

    public Category() {}

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCaticon() {
        return caticon;
    }

    public void setCaticon(String caticon) {
        this.caticon = caticon;
    }

    public Boolean getCatselect() {
        return catselect;
    }

    public void setCatselect(Boolean catselect) {
        this.catselect = catselect;
    }

    public Boolean getCatnotification() {
        return catnotification;
    }

    public void setCatnotification(Boolean catnotification) {
        this.catnotification = catnotification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
