package com.rajinder.noticeboard.Activity.Authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Process.ForgotProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyEditText;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.databinding.ActivityForgotBinding;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.util.List;

public class ForgotActivity extends MyActivity implements OnLoginAction {

    ActivityForgotBinding forgotBinding;
    private ImageView backbtn;
    private MyTextView titlename;
    private ForgotProcess forgotProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        forgotBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot);
        init();
        setListeners();
        settext();
    }

    /*on click listener*/
    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (backbtn == v) {
                    finish();
                }
                if (forgotBinding.forgotBtn == v) {
                    if (forgotBinding.userEdit.getText().toString().isEmpty() && forgotBinding.phoneEdit.getText().toString().isEmpty()) {
                        showToastLong("Please enter the field");
                    } else {
                        if (!forgotBinding.userEdit.getText().toString().isEmpty()) {
                            showLoading();
                            forgotProcess.startprocess("EMAIL", forgotBinding.userEdit.getText().toString(), ForgotActivity.this);
                        } else if (!forgotBinding.phoneEdit.getText().toString().isEmpty() && isValidNumber(forgotBinding.phoneEdit.getText().toString())) {
                            showLoading();
                            forgotProcess.startprocess("Phone", forgotBinding.userEdit.getText().toString(), ForgotActivity.this);
                        } else {
                            showToastLong("Please enter the valid field");
                        }
                    }

                }
            }
        };
    }

    /*init */
    private void init() {
        forgotProcess = new ForgotProcess(this);
        backbtn = (ImageView) $(R.id.btn_back);
        titlename = (MyTextView) $(R.id.title_txt);
    }

    /*set on click*/
    private void setListeners() {
        forgotBinding.forgotBtn.setOnClickListener(OnClickListener());
        backbtn.setOnClickListener(OnClickListener());
    }

    /*set text*/
    private void settext() {
        titlename.setText(getString(R.string.forgotstr));
    }

    @Override
    public void onFinishLoginActions(List<UserModel> userModels, String reponse) {
        hideLoading();
        if (userModels.get(0).success) {
            showToastLong(userModels.get(0).message);
            finish();
        } else {
            showToastLong(userModels.get(0).message);
        }
    }

    @Override
    public void onErrorLoginAction(String error) {
        hideLoading();
        if(error.equals("internet"))
            showinterneterror();
        else
        showToastLong("TRY AGAIN");
    }

}
