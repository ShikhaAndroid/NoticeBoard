
package com.rajinder.noticeboard.models.subCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subcategory {
    @SerializedName("catid")
    @Expose
    public Integer catid;
    @SerializedName("subcatid")
    @Expose
    public Integer subcatid;
    @SerializedName("subcatname")
    @Expose
    public String subcatname;
    @SerializedName("subcaticon")
    @Expose
    public String subcaticon;

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public Integer getSubcatid() {
        return subcatid;
    }

    public void setSubcatid(Integer subcatid) {
        this.subcatid = subcatid;
    }

    public String getSubcatname() {
        return subcatname;
    }

    public void setSubcatname(String subcatname) {
        this.subcatname = subcatname;
    }

    public String getSubcaticon() {
        return subcaticon;
    }

    public void setSubcaticon(String subcaticon) {
        this.subcaticon = subcaticon;
    }

}
