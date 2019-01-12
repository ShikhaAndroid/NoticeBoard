package com.rajinder.noticeboard.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "SocialProfile")
public class SocialProfile extends Model {

    @Column(name = "ids")
    String ids;
    @Column(name = "name")
    String name;
    @Column(name = "state")
    Boolean state;
    @Column(name = "url")
    String url;
    @Column(name = "type")
    String type;
    @Column(name = "email")
    String email;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
