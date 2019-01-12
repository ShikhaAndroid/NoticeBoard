package com.rajinder.noticeboard;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.multidex.MultiDex;


import com.activeandroid.ActiveAndroid;
import com.rajinder.noticeboard.Activity.HomeActivities.FilterActivity;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.subCategory.Subcategory;
import com.twitter.sdk.android.core.Twitter;

import java.net.URL;

public class MyApplication extends Application {

    Bitmap setimage;
    Boolean setboolean;
    Uri imageurl;
    int type;

    // filter
    int progress = 0;
    int aroundmekm = 3;
    Category filterCat;
    Subcategory fiterSubCat;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    // Gloabl declaration of variable to use in whole app
    public static boolean activityVisible; // Variable that will check the
    // current activity state

    public static boolean isActivityVisible() {
        return activityVisible; // return true or false
    }

    public static void activityResumed() {
        activityVisible = true;// this will set true when activity resumed
    }

    public static void activityPaused() {
        activityVisible = false;// this will set false when activity paused
    }

    /*set image*/
    public void setSetimage(Bitmap setimage, Boolean setboolean) {
        this.setimage = setimage;
        this.setboolean = setboolean;
        type = 0;
    }

    /*set image*/
    public void setSetimageurl(Uri imageurl, Boolean setboolean) {
        this.imageurl = imageurl;
        this.setboolean = setboolean;
        type = 1;
    }

    public Bitmap getSetimage() {
        return setimage;
    }

    public Uri getImageurl() {
        return imageurl;
    }

    public Boolean getSetboolean() {
        return setboolean;
    }

    public int getType() {
        return type;
    }

    // filters...
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getAroundmekm() {
        return aroundmekm;
    }

    public void setAroundmekm(int aroundmekm) {
        this.aroundmekm = aroundmekm;
    }

    public Category getFilterCat() {
        return filterCat;
    }

    public void setFilterCat(Category filterCat) {
        this.filterCat = filterCat;
    }

    public Subcategory getFiterSubCat() {
        return fiterSubCat;
    }

    public void setFiterSubCat(Subcategory fiterSubCat) {
        this.fiterSubCat = fiterSubCat;
    }

    public void clearFilterData() {
        setProgress(0);
        setAroundmekm(3);
        setFilterCat(null);
        setFiterSubCat(null);
        FilterActivity.setFilterCat(null);
        FilterActivity.setFilterSubCat(null);
    }

}