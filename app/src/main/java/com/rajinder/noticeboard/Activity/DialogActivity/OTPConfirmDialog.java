package com.rajinder.noticeboard.Activity.DialogActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rajinder.noticeboard.Activity.Authentication.LoginActivity;
import com.rajinder.noticeboard.Activity.HomeActivities.TabCategoryActivity;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Process.OtpConfirmProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyEditText;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.util.List;


public class OTPConfirmDialog extends Dialog implements OnLoginAction {

    public static final String TAB_TYPE_LOGIN = "TAB_TYPE_LOGIN";
    public Activity c;
    public Dialog d;
    private MyEditText otpedit;
    private MyTextView otpbtn;
    private OtpConfirmProcess otpConfirmProcess;
    private ProgressBar loader;
    private String userid;

    public OTPConfirmDialog(Activity a, String userId) {
        super(a);
        this.c = a;
        this.userid = userId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  signOutInterface=new SignOutInterface(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.otp_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);
        init();
        setlistener();
    }

    private void setlistener() {
        otpbtn.setOnClickListener(OnclickListener());
    }

    private View.OnClickListener OnclickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otpbtn == v) {
                    if (otpedit.getText().toString().isEmpty()) {
                        otpedit.setError("Please Enter OTP");
                    } else {
                        loader.setVisibility(View.VISIBLE);
                        otpConfirmProcess.startprocess(userid, otpedit.getText().toString(), c);
                    }
                }
            }
        };
    }

    private void init() {
        loader = (ProgressBar) findViewById(R.id.loader);
        otpConfirmProcess = new OtpConfirmProcess(this);
        otpbtn = (MyTextView) findViewById(R.id.otp_btn);
        otpedit = (MyEditText) findViewById(R.id.otp_edit);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onFinishLoginActions(List<UserModel> userModels, String reponse) {
        loader.setVisibility(View.GONE);
        if (userModels.get(0).success) {
            Intent subcateintent = new Intent(c, TabCategoryActivity.class);
            subcateintent.putExtra("TYPE", TAB_TYPE_LOGIN);
            c.startActivity(subcateintent);
            c.overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
            c.finish();
            dismiss();
        } else {
            Toast.makeText(c, "Please Enter the currect OTP", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onErrorLoginAction(String error) {
        Toast.makeText(c, "Please Enter the currect OTP", Toast.LENGTH_LONG).show();
    }

}