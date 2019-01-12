package com.rajinder.noticeboard.Activity.Authentication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.tasks.Task;
import com.rajinder.noticeboard.Activity.DialogActivity.OTPConfirmDialog;
import com.rajinder.noticeboard.Activity.HomeActivities.HomeActivity;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Process.SignUpProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.CryptLib;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.Validation;
import com.rajinder.noticeboard.databinding.ActivityRegisterBinding;
import com.rajinder.noticeboard.models.UserInfo.UserModel;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import retrofit2.Call;

public class RegisterActivity extends MyActivity implements OnLoginAction {

    public static final String TAG = "RegisterActivity";
    ActivityRegisterBinding registerBinding;
    private CryptLib _crypt;
    private ImageView backbtn;
    private MyTextView titlename;
    private SignUpProcess signUpProcess;
    private OTPConfirmDialog otpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppEventsLogger.activateApp(this);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        final float scale = this.getResources().getDisplayMetrics().density;
        registerBinding.termCheck.setPadding(registerBinding.termCheck.getPaddingLeft() + (int) (10.0f * scale + 0.5f),
                registerBinding.termCheck.getPaddingTop(),
                registerBinding.termCheck.getPaddingRight(),
                registerBinding.termCheck.getPaddingBottom());
        init();
        settext();
        setListeners();
    }

    private void init() {
        try {
            _crypt = new CryptLib();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        signUpProcess = new SignUpProcess(this);
        backbtn = (ImageView) findViewById(R.id.btn_back);
        titlename = (MyTextView) findViewById(R.id.title_txt);
    }

    /*set click listeners*/
    private void setListeners() {
        registerBinding.registerBtn.setOnClickListener(onClickListener());
        backbtn.setOnClickListener(onClickListener());
    }

    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (backbtn == view) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
                if (registerBinding.registerBtn == view) {
                    /*if (registerBinding.fnameEdit.getText().toString().isEmpty()) {
                        showToastLong("Please Enter the User Name");
                    } else if (registerBinding.phoneEdit.getText().toString().isEmpty()) {
                        showToastLong("Please Enter the Phone Number");
                    } else if (!isValidNumber(registerBinding.phoneEdit.getText().toString())) {
                        showToastLong("Please Enter the valid Phone Number");
                        registerBinding.phoneEdit.setError("Please Enter the Valid Phone Number");
                    } else if ((registerBinding.userEdit.getText().toString()).equals("")) {
                        showToastLong("Please Enter the EmailID");
                   *//* else if (!isValidMail(registerBinding.userEdit.getText().toString())) {
                        registerBinding.userEdit.setError("Please Enter the Valid EmailID");
                        showToastLong("Please Enter the Valid EmailID");*//*
                    } else if (registerBinding.passwordEdit.getText().toString().equals("")) {
                        showToastLong(getString(R.string.emptypassword));
                    } else if (!registerBinding.termCheck.isChecked()) {
                        showToastShort("Please check TERMS & CONDITIONS");
                    } else {
                        {
                            showLoading();
                            signUpProcess.startprocess(registerBinding.fnameEdit.getText().toString(), registerBinding.phoneEdit.getText().toString(), registerBinding.userEdit.getText().toString(), registerBinding.passwordEdit.getText().toString(), RegisterActivity.this);
                        }
                    }*/
                    if (!Validation.hasText(registerBinding.fnameEdit))
                        return;
                    else if (!Validation.isPhoneNumber(registerBinding.phoneEdit, true))
                        return;
                    else if (!Validation.isEmailAddress(registerBinding.userEdit, true))
                        return;
                    else if (!Validation.isValidPassword(registerBinding.passwordEdit,true))
                        return;
                    else if (!registerBinding.termCheck.isChecked())
                        showToastShort("Please check TERMS & CONDITIONS");
                    else {
                        try {
                            showLoading();
                            Json data = Json.object();
                            data.set("username", registerBinding.fnameEdit.getText().toString());
                            data.set("phone_no", registerBinding.phoneEdit.getText().toString());
                            data.set("emailid", registerBinding.userEdit.getText().toString());
                            data.set("password", registerBinding.passwordEdit.getText().toString());
                            data.set("phone_token", "");
                            //phonetoken
                            String strdata = data.toString();
                            String data_encrypt;
                            //     String key = CryptLib.SHA256("2d996bc5ef04605bb0e78d57f6515112", 32); //32 bytes = 256 bit
                            String iv = CryptLib.generateRandomIV(16); //16 bytes = 128 bit
                            String text = new String(strdata.getBytes(), "UTF-8");
                            data_encrypt = _crypt.encrypt(text, "2d996bc5ef04605bb0e78d57f6515112", iv);
                            byte[] demos = Base64.decode(data_encrypt, Base64.DEFAULT);
                            String hexstr = bytesToHexs(demos);
                            Log.d(TAG, "onClick: "+hexstr+" , iv: "+iv);
                            signUpProcess.startprocess(RegisterActivity.this, hexstr, iv);
                        } catch (Exception e){
                            Log.e(TAG, "onClick: "+e.getMessage() );
                        }
                    }
                }
            }
        };
    }

    private void settext() {
        titlename.setText(getString(R.string.register));
        String htmlText = null;
        String termstr = (getString(R.string.termstr));
        String termstr1 = (getString(R.string.termstr1));
        String termstr2 = null;
        try {
            termstr2 = termstr.replace(termstr1, "<font color='" + "#06744e" + "' >" + termstr1 + "</font>");
        } catch (Exception e) {
            termstr2 = termstr.replace(termstr1, "<font color='#06744e' >" + termstr1 + " </font>");
        }
        registerBinding.termCheck.setText(Html.fromHtml(termstr2));
    }

    @Override
    public void onFinishLoginActions(List<UserModel> userModels, String reponse) {
        hideLoading();
        if (userModels.get(0).success) {
            int userid = userModels.get(0).user.getUserid();
            otpDialog = new OTPConfirmDialog(RegisterActivity.this, String.valueOf(userid));
            otpDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
            otpDialog.setCanceledOnTouchOutside(false);
            otpDialog.show();
        } else if (userModels.get(0).message.equals("Already Exist")) {
            showToastShort("Already Exist");
            registerBinding.phoneEdit.setText("");
            registerBinding.userEdit.setText("");
        } else if (!userModels.get(0).verify) {
            showToastShort(userModels.get(0).message);
            otpDialog = new OTPConfirmDialog(RegisterActivity.this, String.valueOf(userid));
            otpDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
            otpDialog.setCanceledOnTouchOutside(false);
            otpDialog.show();
        } else {
            showToastShort(userModels.get(0).message);
            registerBinding.phoneEdit.setText("");
            registerBinding.userEdit.setText("");
        }
    }

    @Override
    public void onErrorLoginAction(String error) {
        hideLoading();
        if (error.equals("internet"))
            showinterneterror();
        else
            showToastLong(error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
