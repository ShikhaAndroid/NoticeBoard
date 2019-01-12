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

public class ChangePasswordDialog extends Dialog implements OnLoginAction {

    private ImageView btnback;
    private MyTextView titlename, btnchangepassword;
    private MyEditText oldpassword, newpassword;
    public Activity c;
    public Dialog d;
    int userid;
    private ChangePesswordProcess changePesswordProcess;

    public ChangePasswordDialog(Activity a, Integer userid) {
        super(a);
        this.userid = userid;
        //this.tabs=tab;
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.change_password_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.TOP;
        getWindow().setAttributes(lp);
        init();
        setlistener();
        setdata();
    }

    private void setdata() {
        titlename.setText("Change Password");
        btnchangepassword.setText("Submit");
    }

    private void setlistener() {
        btnback.setOnClickListener(OnclickListener());
        btnchangepassword.setOnClickListener(OnclickListener());
    }

    private View.OnClickListener OnclickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnback == v) {
                    dismiss();
                }
                if (btnchangepassword == v) {
                    if (oldpassword.getText().toString().isEmpty()) {
                        Toast.makeText(c, "Please enter old Password", Toast.LENGTH_LONG).show();
                    } else if (oldpassword.getText().toString().isEmpty()) {
                        Toast.makeText(c, "Please enter new Password", Toast.LENGTH_LONG).show();
                    } else {
                        changePesswordProcess.startprocess(String.valueOf(userid), oldpassword.getText().toString(), newpassword.getText().toString(), c);
                    }
                    //  dismiss();
                }
            }
        };
    }

    private void init() {
        changePesswordProcess = new ChangePesswordProcess(this);
        oldpassword = (MyEditText) findViewById(R.id.old_password);
        newpassword = (MyEditText) findViewById(R.id.new_password);
        btnback = (ImageView) findViewById(R.id.btn_back);
        titlename = (MyTextView) findViewById(R.id.title_txt);
        btnchangepassword = (MyTextView) findViewById(R.id.forgot_btn);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onFinishLoginActions(List<UserModel> userModels, String reponse) {
        Toast.makeText(c, "Your password change successfully", Toast.LENGTH_LONG).show();
        dismiss();
    }

    @Override
    public void onErrorLoginAction(String error) {
        Toast.makeText(c, "Please Try Again, Check Old password", Toast.LENGTH_LONG).show();
        oldpassword.setText("");
        newpassword.setText("");
    }
}