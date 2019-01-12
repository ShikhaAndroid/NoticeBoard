package com.rajinder.noticeboard.Activity.Authentication;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import com.rajinder.noticeboard.Activity.HomeActivities.HomeActivity;
import com.rajinder.noticeboard.Activity.HomeActivities.TabCategoryActivity;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.models.CategoryModel;

import java.util.ArrayList;

public class SplashActivity extends MyActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));
        }
        MultiDex.install(this);
        setContentView(R.layout.activity_splash);
        //  animateLogo();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpActivity();
            }
        }, 1000);
    }

 /*   private void animateLogo() {
        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bounce);
        animation.setAnimationListener(this);
        //   mLogo.startAnimation(animation);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        jumpActivity();
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }*/

    /* jump activity */
    private void jumpActivity() {
        if(getuserinfo().size() != 0 || getsocialinfo().size() != 0) {
//            Intent subcateintent = new Intent(SplashActivity.this, TabCategoryActivity.class);
//            subcateintent.putExtra("TYPE", TAB_TYPE_LOGIN);
//            startActivity(subcateintent);
            if (getcategory().size() != 0) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
            } else {
                Intent subcateintent = new Intent(SplashActivity.this, TabCategoryActivity.class);
                subcateintent.putExtra("TYPE", TAB_TYPE_LOGIN);
                startActivity(subcateintent);
            }
            finish();
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }

}
