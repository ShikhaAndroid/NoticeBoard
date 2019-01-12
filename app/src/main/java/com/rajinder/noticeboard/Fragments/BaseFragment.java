package com.rajinder.noticeboard.Fragments;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.app.Fragment;
import android.view.View;
import com.activeandroid.query.Select;
import com.rajinder.noticeboard.UI.EventBusClass.EventTab;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.SocialProfile;

import org.greenrobot.eventbus.EventBus;
import java.util.List;


public class BaseFragment extends Fragment   {

    /*
      This method is used for intilazing views from resources
   */
    public void initID() {
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StateListDrawable makeSelector(int pcolor) {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int[]{android.R.attr.state_selected}, new ColorDrawable(pcolor));
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(pcolor));
        res.addState(new int[]{-android.R.attr.state_pressed}, new ColorDrawable(Color.WHITE));
        res.addState(new int[]{-android.R.attr.state_selected}, new ColorDrawable(Color.WHITE));
        return res;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /*get user info*/
    public static List<com.rajinder.noticeboard.models.UserInfo.User> getuserinfo() {
        // This is how you execute a query
        return new Select().from(com.rajinder.noticeboard.models.UserInfo.User.class).execute();
    }

    /*get social info*/
    public static List<SocialProfile> getsocialinfo() {
        // This is how you execute a query
        return new Select().from(SocialProfile.class).execute();
    }

    /*get category info*/
    public static List<Category> getcategory() {
        // This is how you execute a query
        return new Select().from(Category.class).execute();
    }

}
