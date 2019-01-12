package com.rajinder.noticeboard.Activity.DialogActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Process.ChangePesswordProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyEditText;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.util.List;

public class InternetErrorDialog extends Dialog {


    public Activity c;
    public Dialog d;
    public MyTextView tryagain;

    public InternetErrorDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.internet_error_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);
        init();
        setlistener();
        setdata();
    }

    private void setdata() {

    }

    private void setlistener() {
        tryagain.setOnClickListener(OnclickListener());
    }

    private View.OnClickListener OnclickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tryagain == v) {
                    dismiss();
                }
            }
        };
    }


    private void init() {
        tryagain = (MyTextView) findViewById(R.id.try_again);
    }


    @Override
    protected void onStop() {
        super.onStop();

    }


}