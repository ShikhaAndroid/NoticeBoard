package com.rajinder.noticeboard.Activity.Profile;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Process.EditProfileProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.databinding.ActivityEditProfileBinding;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.util.List;

public class EditProfileActivity extends MyActivity implements OnLoginAction {

    ImageView btnback;
    MyTextView titlename;
    ActivityEditProfileBinding activityEditProfileBinding;
    private EditProfileProcess editProfileProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        activityEditProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile);
        init();
        setlistener();
        setdata();
    }

    private void setdata() {
        titlename.setText("Edit Profile");
        activityEditProfileBinding.fnameEdit.setText(getuserinfo().get(0).username);
        activityEditProfileBinding.phoneEdit.setText(getuserinfo().get(0).phoneNumber);
        activityEditProfileBinding.userEdit.setText(getuserinfo().get(0).email);
        activityEditProfileBinding.userAddress.setText(getuserinfo().get(0).address);
    }

    private void setlistener() {
        btnback.setOnClickListener(OnClickListener());
        activityEditProfileBinding.editBtn.setOnClickListener(OnClickListener());
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnback == v) {
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
                if (activityEditProfileBinding.editBtn == v) {
                    if (activityEditProfileBinding.fnameEdit.getText().toString().isEmpty() || activityEditProfileBinding.phoneEdit.getText().toString().isEmpty()) {
                        showToastShort("Please enter the feild value");
                    }
                    else if(!isValidNumber(activityEditProfileBinding.phoneEdit.getText().toString())){
                        showToastShort("Please enter the valid phone number");
                    }
                    else {
                        showLoading();
                        editProfileProcess.startprocess(activityEditProfileBinding.fnameEdit.getText().toString(), getuserinfo().get(0).userid, activityEditProfileBinding.phoneEdit.getText().toString(), activityEditProfileBinding.userAddress.getText().toString(), EditProfileActivity.this);
                    }
                }
            }
        };
    }

    private void init() {
        editProfileProcess = new EditProfileProcess(this);
        btnback = (ImageView) $(R.id.btn_back);
        titlename = (MyTextView) $(R.id.title_txt);
    }

    @Override
    public void onFinishLoginActions(List<UserModel> userModels, String reponse) {
        hideLoading();
        showToastShort("Edit Profile Successfully");
        finish();
    }

    @Override
    public void onErrorLoginAction(String error) {
        hideLoading();
        showToastShort("Please Try Again");

    }
}
