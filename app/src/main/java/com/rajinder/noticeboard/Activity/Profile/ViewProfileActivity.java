package com.rajinder.noticeboard.Activity.Profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rajinder.noticeboard.Adapters.RecentActviityAdapter;
import com.rajinder.noticeboard.Adapters.ViewPostItemAdapter;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Interface.OnProfileViewAction;
import com.rajinder.noticeboard.Process.ProfileViewProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.Utils.recycler_decorations.VerticalItemDecoration;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.models.PostListModel;
import com.rajinder.noticeboard.models.UserInfo.UserModel;
import com.rajinder.noticeboard.models.UserProfileViewModel;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class ViewProfileActivity extends MyActivity implements OnProfileViewAction {

    private ImageView coverimg, btnback;
    Bundle extras;
    private int userid;
    private ProfileViewProcess profileViewProcess;
    private CircularImageView userimage;
    private MyTextView pusernametxt, paddresstxt, pjointxt, pcall, pchat, pphonetxt, pemail;
    String phonenumber;
    Uri myUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        extras = getIntent().getExtras();
        if (extras == null) {
            return;
        } else {
            userid = extras.getInt("user_id");
        }
        showLoading();
        inits();
        setupdata();
        setlistener();
    }
    private void setlistener() {
        pcall.setOnClickListener(OnClickListener());
        pchat.setOnClickListener(OnClickListener());
        btnback.setOnClickListener(OnClickListener());
    }
    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnback == v) {
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
                if (userimage == v) {
                    try {
                        showimageDialog(String.valueOf(myUri));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (pchat == v) {
                    showMessageAlert("UNDER PROGRESS");
                }
                if (pcall == v) {
                    if (!phonenumber.equals("")) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + phonenumber));
                        if (ActivityCompat.checkSelfPermission(ViewProfileActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(intent);
                    }
                }
            }
        };
    }


    private void setupdata() {
        profileViewProcess.startprocess(String.valueOf(userid), this);
    }


    private void inits() {
        pemail = (MyTextView) $(R.id.p_email);
        pphonetxt = (MyTextView) $(R.id.p_phone_txt);
        pcall = (MyTextView) $(R.id.p_call);
        pchat = (MyTextView) $(R.id.p_chat);
        pjointxt = (MyTextView) $(R.id.p_join_txt);
        paddresstxt = (MyTextView) $(R.id.p_address_txt);
        pusernametxt = (MyTextView) $(R.id.p_username_txt);
        userimage = (CircularImageView) $(R.id.cate_image);
        profileViewProcess = new ProfileViewProcess(this);
        coverimg = (ImageView) $(R.id.cover_img);
        btnback = (ImageView) $(R.id.btn_back);
    }

    @Override
    public void onFinishProfileActions(List<UserProfileViewModel> userModels, String reponse) {
        if (userModels.size() != 0) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.emptyuser);
            requestOptions.error(R.drawable.emptyuser);
            requestOptions.centerInside();
            Glide.with(this).setDefaultRequestOptions(requestOptions).load(userModels.get(0).getPicUrl()).into(userimage);
            pusernametxt.setText(userModels.get(0).getUsername());
            pphonetxt.setText(userModels.get(0).getPhoneNo());
            pemail.setText(userModels.get(0).getEmailId());
            paddresstxt.setText(userModels.get(0).getAddress());
            if (userModels.get(0).getPicUrl().equals("")) {
                Bitmap icon = BitmapFactory.decodeResource(getResources(),
                        R.drawable.emptyuser);
                Bitmap covverbitmap = blur(ViewProfileActivity.this, icon);
                coverimg.setImageBitmap(covverbitmap);
            } else {
            }
            phonenumber = userModels.get(0).phoneNo;
            //p.setText(userModels.get(0).getUsername());
            // pusernametxt.setText(userModels.get(0).ge());
            try {
                myUri = Uri.parse(userModels.get(0).picUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //  icon = loadBitmap(socialProfiles.get(0).getUrl());
            if (myUri != null) {
                Glide.with(this)
                        .load(myUri)
                        .into(userimage);
                Glide.with(this).load(myUri).apply(bitmapTransform(new BlurTransformation(3)))
                        .into(coverimg);
            } else {
                Glide.with(this)
                        .load(R.drawable.emptyuser)
                        .into(userimage);
                Glide.with(this).load(R.drawable.emptyuser).apply(bitmapTransform(new BlurTransformation(3)))
                        .into(coverimg);
            }
        }
        hideLoading();
    }

    @Override
    public void onErrorProfileAction(String error) {
        hideLoading();
    }
}
