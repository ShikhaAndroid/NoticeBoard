package com.rajinder.noticeboard.Fragments.NavigationFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.rajinder.noticeboard.Activity.DialogActivity.ChangePasswordDialog;
import com.rajinder.noticeboard.Activity.HomeActivities.TabCategoryActivity;
import com.rajinder.noticeboard.Activity.Profile.EditProfileActivity;
import com.rajinder.noticeboard.Activity.Profile.UserProfileActivity;
import com.rajinder.noticeboard.Fragments.BaseFragment;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.models.UserInfo.User;

import java.util.List;


public class SettingFragment extends BaseFragment {
    private View mView;
    Context mContext;
    private MyTextView btnlogout, btnok, btncancel, btnedit, btncustomnoti, btnchangepassword, username, useremail;
    private Dialog dialog;
    List<User> userinfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // initID();
        mContext = inflater.getContext();
        mView = inflater.inflate(R.layout.fragment_setting, container, false);
        userinfo = getuserinfo();
        init();
        setlistener();
        settext();
        return mView;
    }

    private void settext() {
        username.setText(getuserinfo().get(0).username);
        useremail.setText(getuserinfo().get(0).email);
    }

    private void setlistener() {
        btnlogout.setOnClickListener(OnClickListener());
        btncustomnoti.setOnClickListener(OnClickListener());
        btnedit.setOnClickListener(OnClickListener());
        btnchangepassword.setOnClickListener(OnClickListener());
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnlogout == v) {
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog();
                }
                if (btnok == v) {
                    try {
                        new Delete().from(User.class).execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dialog.dismiss();
                    getActivity().finish();
                }
                if (btncancel == v) {
                    dialog.dismiss();
                }
                if (btncustomnoti == v) {
                    Intent subcateintent = new Intent(getActivity(), TabCategoryActivity.class);
                    subcateintent.putExtra("TYPE", MyActivity.TAB_TYPE_NOTIFICATION);
                    startActivity(subcateintent);
                    getActivity().overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
                if (btnchangepassword == v) {
                    changespassword();
                }
                if (btnedit == v) {
                    startActivity(new Intent(getActivity(), EditProfileActivity.class));
                    getActivity().overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        if (getArguments() != null) {

        }
    }


    private void init() {
        btnlogout = (MyTextView) mView.findViewById(R.id.btn_logout);
        username = (MyTextView) mView.findViewById(R.id.user_name);
        useremail = (MyTextView) mView.findViewById(R.id.user_email);
        btnedit = (MyTextView) mView.findViewById(R.id.btn_edit);
        btncustomnoti = (MyTextView) mView.findViewById(R.id.btn_custom_noti);
        btnchangepassword = (MyTextView) mView.findViewById(R.id.btn_change_password);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public class ViewDialog {

        public void showDialog() {
            dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;
            dialog.setContentView(R.layout.logout_dialog);
            TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
            btnok = (MyTextView) dialog.findViewById(R.id.btn_ok);
            btncancel = (MyTextView) dialog.findViewById(R.id.btn_cancel);
            btnok.setOnClickListener(OnClickListener());
            btncancel.setOnClickListener(OnClickListener());
            dialog.show();
            dialog.getWindow().setAttributes(lp);
        }
    }

    public void changespassword() {
        ChangePasswordDialog selectTabDialog = new ChangePasswordDialog(getActivity(), userinfo.get(0).userid);
        selectTabDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        selectTabDialog.setCanceledOnTouchOutside(false);
        selectTabDialog.show();
    }

}