package com.rajinder.noticeboard.Activity.Authentication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
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
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

import com.rajinder.noticeboard.Activity.DialogActivity.OTPConfirmDialog;
import com.rajinder.noticeboard.Activity.HomeActivities.HomeActivity;
import com.rajinder.noticeboard.Activity.HomeActivities.TabCategoryActivity;
import com.rajinder.noticeboard.Interface.DrawableClickListener;
import com.rajinder.noticeboard.Process.LoginProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Utils.CryptLib;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.databinding.ActivityLoginBinding;
import com.rajinder.noticeboard.models.SocialProfile;
import com.rajinder.noticeboard.models.UserInfo.UserModel;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;

import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import retrofit2.Call;

public class LoginActivity extends MyActivity implements OnLoginAction, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private ActivityLoginBinding activityLoginBinding;
    private CryptLib _crypt;
    private ImageView backbtn;
    private MyTextView btnsignup;
    private LoginProcess loginProcess;
    private boolean showhide = false;
    public CallbackManager callbackManager;
    private static final String EMAIL = "email";
    private GoogleApiClient mGoogleApiClient;
    private ArrayList<SocialProfile> socialProfiles = new ArrayList<>();
    private  OTPConfirmDialog otpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(Plus.API)
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        final float scale = this.getResources().getDisplayMetrics().density;
        activityLoginBinding.remembercheck.setPadding(activityLoginBinding.remembercheck.getPaddingLeft() + (int) (12.0f * scale + 1f),
                activityLoginBinding.remembercheck.getPaddingTop(),
                activityLoginBinding.remembercheck.getPaddingRight(),
                activityLoginBinding.remembercheck.getPaddingBottom());
        deletesocialprofile();
        init();
        setListeners();
        settext();
    }

    private void init() {
        try {
            _crypt = new CryptLib();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        loginProcess = new LoginProcess(this);
        backbtn = (ImageView) $(R.id.btn_back);
        btnsignup = (MyTextView) $(R.id.clear_btn);
    }

    /*setlisteners*/
    private void setListeners() {
        activityLoginBinding.forgotBtn.setOnClickListener(onClickListener());
        activityLoginBinding.loginBtn.setOnClickListener(onClickListener());
        activityLoginBinding.remembercheck.setOnClickListener(onClickListener());
        backbtn.setVisibility(View.GONE);
        btnsignup.setVisibility(View.VISIBLE);
        btnsignup.setOnClickListener(onClickListener());
        activityLoginBinding.btnFacebook.setOnClickListener(onClickListener());
        activityLoginBinding.btnGoogle.setOnClickListener(onClickListener());
        activityLoginBinding.btnTwitter.setOnClickListener(onClickListener());
        activityLoginBinding.passwordEdit.setDrawableClickListener(new DrawableClickListener() {
            @Override
            public void onClick(DrawablePosition target) {
                if (showhide) {
                    activityLoginBinding.passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    showhide = false;
                    activityLoginBinding.passwordEdit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0);
                } else {
                    activityLoginBinding.passwordEdit.setInputType(InputType.TYPE_CLASS_TEXT);
                    showhide = true;
                    activityLoginBinding.passwordEdit.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility, 0);
                }   //50100205001420
            }
        });

        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "publish_actions",
                "manage_pages", "publish_pages", "user_birthday", "public_profile", "AccessToken", "user_birthday");
        activityLoginBinding.loginButton.setReadPermissions(Arrays.asList(new String[]{"email", "public_profile"}));

        activityLoginBinding.loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        AccessToken accessToken = loginResult.getAccessToken();
                        //publish_actions permission, or manage_pages and publish_pages as an admin with sufficient administrative permission}}
                        GraphRequest request = GraphRequest.newMeRequest(
                                accessToken, new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        activityLoginBinding.loginButton.clearPermissions();
                                        activityLoginBinding.loginButton.setPublishPermissions(Arrays.asList("publish_actions", "manage_pages", "publish_pages"));
                                        try {
                                            String id = object.getString("id");
                                            try {
                                                URL profile_pic = new URL("http://graph.facebook.com/" + id + "/picture?type=large&redirect=true&width=300&height=300");
                                                Log.i("profile_pic", profile_pic + "");

                                                /*SocialProfile socialProfile = new SocialProfile();
                                                socialProfile.setIds(id);
                                                socialProfile.setname(object.getString("name"));
                                                socialProfile.setState(true);
                                                Log.d(TAG, "onCompleted:fb login "+id);
                                                try {
                                                    socialProfile.setEmail(object.getString("email"));
                                                } catch (JSONException e) {
                                                    socialProfile.setEmail("");
                                                }
                                                socialProfile.setType("Facebook");
                                                socialProfile.setUrl(String.valueOf(profile_pic));
                                                socialProfiles.add(socialProfile);
                                                socialProfile.save();
                                                createSession();*/

                                                //emailid,fb_id,gmailid,name,device_os,profile_pic
                                                JSONObject data = new JSONObject();
                                                try {
                                                    data.put("emailid", object.getString("email"));
                                                } catch (JSONException e) {
                                                    data.put("emailid", "");
                                                }
                                                data.put("fb_id", object.getString("id"));
                                                data.put("gmailid", "");
                                                data.put("username", object.getString("name"));
                                                data.put("device_os", "Android");
                                                data.put("picture_url", profile_pic);
                                                String strdata = data.toString();
                                                String data_encrypt;
                                                String iv = CryptLib.generateRandomIV(16); //16 bytes = 128 bit
                                                String text = new String(strdata.getBytes(), "UTF-8");
                                                data_encrypt = _crypt.encrypt(text, "2d996bc5ef04605bb0e78d57f6515112", iv);
                                                byte[] demos = Base64.decode(data_encrypt, Base64.DEFAULT);
                                                String hexstr = bytesToHexs(demos);
                                                Log.d(TAG, "onClick: facebook login "+hexstr+" , iv: "+iv);
                                                loginProcess.startprocess(LoginActivity.this, hexstr, iv, "social");

                                            } catch (JSONException e) {
                                                Log.e("detail", e.toString());
                                            } catch (MalformedURLException e) {
                                                Log.e("detail", e.toString());
                                            } catch (Exception e) {
                                                Log.e(TAG, "onCompleted: "+e.getMessage() );
                                            }
                                        } catch (JSONException e) {
                                            Log.e("detail", e.toString());
                                        }
                                    }
                                });

//                        GraphRequest request1 = GraphRequest.newGraphPathRequest(
//                                accessToken,
//                                "/me/feed",
//                                new GraphRequest.Callback() {
//                                    @Override
//                                    public void onCompleted(GraphResponse response) {
//                                        // Insert your code here
//                                    }
//                                });
//
//                        request1.executeAsync();

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, name, email, gender, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "onCancel: ");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d(TAG, "onError: "+exception.getMessage());
                    }
        });


        /*twitter login */
        activityLoginBinding.loginTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                Log.e("result", "result " + result);
                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
                AccountService accountService = twitterApiClient.getAccountService();
                Call<User> call = accountService.verifyCredentials(true, true, true);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        //here we go User details
                        Log.e("result", "result user " + result);
                        String imageUrl = result.data.profileImageUrl;
                        String email = result.data.email;
                        String userName = result.data.name;
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        exception.printStackTrace();
                        Log.e("result", "failure " + exception.toString());
                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                exception.printStackTrace();
                Log.e("result", "failure1" + exception.toString());
            }
        });
    }

    public void createSession() {
        Intent subcateintent = new Intent(LoginActivity.this, TabCategoryActivity.class);
        subcateintent.putExtra("TYPE", TAB_TYPE_LOGIN);
        startActivity(subcateintent);
        overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
        finish();
    }

    private void settext() {
        activityLoginBinding.titleTxt.setText("User Login");
        btnsignup.setText("Sign Up");
        btnsignup.setTextColor(Color.parseColor("#000000"));
    }

    /*on click methods*/
    private View.OnClickListener onClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activityLoginBinding.forgotBtn == view) {
                    startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
                }
                if (activityLoginBinding.loginBtn == view) {
                    if ((activityLoginBinding.userEdit.getText().toString()).equals("")) {
                        showToastLong(getString(R.string.emptyuser));
                    } else if (activityLoginBinding.passwordEdit.getText().toString().equals("")) {
                        showToastLong(getString(R.string.emptypassword));
                    } else {
                        {
                            try {
                                showLoading();
                                String loing_ = activityLoginBinding.userEdit.getText().toString();
                                String passwo = activityLoginBinding.passwordEdit.getText().toString();

                                JSONObject data = new JSONObject();
                                data.put("emailid", loing_);
                                data.put("password", passwo);
                                data.put("fb_id", "");
                                data.put("gmailid", "");
                                data.put("device_os", "Android");
                                String strdata = data.toString();
                                String data_encrypt;
                                // String key = CryptLib.SHA256("2d996bc5ef04605bb0e78d57f6515112", 32); //32 bytes = 256 bit
                                String iv = CryptLib.generateRandomIV(16); // 16 bytes = 128 bit
                                String text = new String(strdata.getBytes(), "UTF-8");
                                data_encrypt = _crypt.encrypt(text, "2d996bc5ef04605bb0e78d57f6515112", iv);
                                byte[] demos = Base64.decode(data_encrypt, Base64.DEFAULT);
                                String hexstr = bytesToHexs(demos);

//                                loginProcess.startprocess(loing_, passwo, LoginActivity.this);
                                Log.d(TAG, "onClick: custom login "+hexstr+" , iv: "+iv);
                                loginProcess.startprocess(LoginActivity.this, hexstr, iv, "login");

                            } catch (Exception e) {
                                Log.e(TAG, "onClick: "+e.getMessage() );
                            }
                        }
                    }
                }
                if (activityLoginBinding.remembercheck == view) {
                    showToastLong("remember");
                }
                if (btnsignup == view) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    finish();
                    overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
                if (activityLoginBinding.btnFacebook == view) {
                    activityLoginBinding.loginButton.performClick();
                }
                if (activityLoginBinding.btnTwitter == view) {
                    activityLoginBinding.loginTwitter.performClick();
                }
                if (activityLoginBinding.btnGoogle == view) {
//                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//                    startActivityForResult(signInIntent, GOOGLE_SIGN_IN);

                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
                }
            }
        };
    }

    public void logoutFromFb() {
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                LoginManager.getInstance().logOut();
            }
        }).executeAsync();
    }

    /*Login process*/
    @Override
    public void onFinishLoginActions(List<UserModel> userModels, String reponse) {
        hideLoading();
        if (userModels.get(0).success) {
            Intent subcateintent = new Intent(LoginActivity.this, TabCategoryActivity.class);
            subcateintent.putExtra("TYPE", TAB_TYPE_LOGIN);
            startActivity(subcateintent);
            overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
            finish();
            showToastShort(reponse);
        } else if (!userModels.get(0).verify) {
            int userid = userModels.get(0).user.getUserid();
            otpDialog = new OTPConfirmDialog(LoginActivity.this,String.valueOf(userid) );
            otpDialog .getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
            otpDialog .setCanceledOnTouchOutside(false);
            otpDialog .show();
        } else {
//            showToastShort("Error! User not valid Please check email/password");
            showToastShort(userModels.get(0).message);
        }
    }

    @Override
    public void onErrorLoginAction(String error) {
       hideLoading();
       if(error.equals("internet"))
           showinterneterror();
       else
           showToastShort(error);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        activityLoginBinding.loginTwitter.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
            Log.e("facebook", String.valueOf(isLoggedIn));
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            showLoading();
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String email = "";
            String personPhotoUrl = null;
            String personName = acct.getDisplayName();
            String userid = acct.getId();
            try {
                email = acct.getEmail();
                personPhotoUrl = acct.getPhotoUrl().toString();
            } catch (Exception e) {}

            /*SocialProfile socialProfile = new SocialProfile();
            socialProfile.setIds(acct.getId());
            socialProfile.setname(acct.getDisplayName());
            socialProfile.setState(true);
            try {
                socialProfile.setEmail(acct.getEmail());
            } catch (Exception e) {
                e.printStackTrace();
            }
            socialProfile.setType("GMAIL");
            socialProfile.setUrl(personPhotoUrl);
            socialProfiles.add(socialProfile);
            socialProfile.save();
            createSession();*/

            // logout
            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            }

            try {
                JSONObject data = new JSONObject();
                data.put("emailid", email);
                data.put("fb_id", "");
                data.put("gmailid", userid);
                data.put("username", personName);
                data.put("device_os", "Android");
                data.put("picture_url", "");
                String strdata = data.toString();
                String data_encrypt;
                String iv = CryptLib.generateRandomIV(16); //16 bytes = 128 bit
                String text = new String(strdata.getBytes(), "UTF-8");
                data_encrypt = _crypt.encrypt(text, "2d996bc5ef04605bb0e78d57f6515112", iv);
                byte[] demos = Base64.decode(data_encrypt, Base64.DEFAULT);
                String hexstr = bytesToHexs(demos);
                showLoading();
                Log.d(TAG, "handleSignInResult: "+hexstr+" ,iv "+iv);
                loginProcess.startprocess(LoginActivity.this, hexstr, iv, "social");

            } catch (Exception e) {
                Log.e(TAG, "handleSignInResult: "+e.getMessage() );
            }

        } else {}
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*   @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d("gmail", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }*/

}








