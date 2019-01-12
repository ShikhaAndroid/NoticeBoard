package com.rajinder.noticeboard.UI.EventBusClass;

/**
 * Created by rajinder 4/30/2018
 */

public class EventSelectCat {
    int  position;
    String type;

    public EventSelectCat() {}

    public EventSelectCat(int position) {
        this.position = position;
    }

    public EventSelectCat(int position,String type) {
        this.position = position;
        this.type=type;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }

}
