package com.rajinder.noticeboard.UI.EventBusClass;

import com.rajinder.noticeboard.models.subCategory.Subcategory;

/**
 * Created by rajinder 4/30/2018
 */

public class EventSelectSubCat {

    int position;
    Subcategory subcategory;

    public EventSelectSubCat() {}

    public EventSelectSubCat(Subcategory  subcategory, int position) {
        this.position = position;
        this.subcategory = subcategory;
    }

    public int getPosition() {
        return position;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

}
