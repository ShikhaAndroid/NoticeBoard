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

import com.rajinder.noticeboard.Activity.DetailView.CatDetailActivity;
import com.rajinder.noticeboard.Activity.HomeActivities.TabCategoryActivity;
import com.rajinder.noticeboard.Interface.OnLikepostAction;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Process.LikeUlikePostProcess;
import com.rajinder.noticeboard.Process.OtpConfirmProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyEditText;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.models.DeletepostModel;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.util.List;


public class UnlikePostDialog extends Dialog implements OnLikepostAction {
    public Activity c;
    public Dialog d;
    private String userid;
    private int postId;
    public static final String TAB_TYPE_LOGIN = "TAB_TYPE_LOGIN";
    private MyTextView btnno, btnyes;
    private LikeUlikePostProcess likeUlikePostProcess;

    public UnlikePostDialog(Activity a, String userId, Integer postId) {
        super(a);
        this.c = a;
        this.userid = userId;
        this.postId = postId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  signOutInterface=new SignOutInterface(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.unlike_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.TOP;
        getWindow().setAttributes(lp);
        init();
        setlistener();
    }


    private void setlistener() {
        btnno.setOnClickListener(OnclickListener());
        btnyes.setOnClickListener(OnclickListener());
    }

    private View.OnClickListener OnclickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnno == v) {
                    dismiss();
                }
                if (btnyes == v) {
                    likeUlikePostProcess.startprocess(Integer.parseInt(userid), postId, "unlike", c);
                }

            }
        };
    }

    private void init() {
        likeUlikePostProcess = new LikeUlikePostProcess(this);
        btnno = (MyTextView) findViewById(R.id.btn_no);
        btnyes = (MyTextView) findViewById(R.id.btn_yes);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onFinishLUActions(List<DeletepostModel> userModels, String reponse) {
        dismiss();
    }

    @Override
    public void onErrorLUAction(String error) {
        dismiss();
    }
}