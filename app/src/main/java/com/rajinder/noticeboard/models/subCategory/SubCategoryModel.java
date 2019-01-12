
package com.rajinder.noticeboard.models.subCategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryModel {

    @SerializedName("subcategories")
    @Expose
    public List<Subcategory> subcategories = null;

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

}
