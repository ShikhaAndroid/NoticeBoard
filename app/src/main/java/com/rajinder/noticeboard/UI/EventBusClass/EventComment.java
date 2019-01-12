package com.rajinder.noticeboard.UI.EventBusClass;

/**
 * Created by rajinder 4/30/2018
 */

public class EventComment {
    int  position;
    String type;

    public EventComment() {
    }


    public EventComment(int position, String type) {

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
