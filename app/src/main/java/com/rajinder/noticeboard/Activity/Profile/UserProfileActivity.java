package com.rajinder.noticeboard.Activity.Profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.activeandroid.query.Select;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rajinder.noticeboard.Activity.DialogActivity.ChangePasswordDialog;
import com.rajinder.noticeboard.Activity.DialogActivity.SelectTabDialog;
import com.rajinder.noticeboard.Adapters.CategoryselectAdapter;
import com.rajinder.noticeboard.Adapters.RecentActviityAdapter;
import com.rajinder.noticeboard.Interface.DeletepostAction;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Process.ProfileImageProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.Utils.recycler_decorations.VerticalItemDecoration;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.DeletepostModel;
import com.rajinder.noticeboard.models.UserInfo.User;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class UserProfileActivity extends MyActivity implements OnLoginAction {
    private RecyclerView puserrecentlist, prcategory;
    private ImageView coverimg, btnback, userimage, addcatebtn, btnedit, btnchangeimage;
    RecentActviityAdapter recentActviityAdapter;
    LinearLayoutManager layoutManager;
    public Bitmap bm = null;
    private MyTextView btnchangepassword, catcountertxt;
    public MyTextView username, userphone, userlocation, numberpost;
    private List<User> userinfo;
    private ProfileImageProcess profileImageProcess;
    private ProgressBar loaderimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        inits();
        inflateRecentList();
        setlistener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userinfo = getuserinfo();
        settext();
    }

    private void settext() {
        Log.e("try", "settext");
        username.setText(userinfo.get(0).username);
        userphone.setText(userinfo.get(0).phoneNumber);
        userlocation.setText(userinfo.get(0).address);
        numberpost.setText(userinfo.get(0).post_total);
        List<Category> categories = new Select().from(Category.class).where("catselect = ?", true).execute();
        catcountertxt.setText(String.valueOf(categories.size()) + " chosen");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.emptyuser);
        requestOptions.error(R.drawable.emptyuser);
        requestOptions.centerInside();
        Uri myUri = null;
        try {
            myUri = Uri.parse(userinfo.get(0).user_img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  icon = loadBitmap(socialProfiles.get(0).getUrl());
        if (myUri != null) {
            Glide.with(this).setDefaultRequestOptions(requestOptions)
                    .load(myUri)
                    .into(userimage);
            Glide.with(this).load(myUri).apply(bitmapTransform(new BlurTransformation(3)))
                    .into(coverimg);
        } else {
            Glide.with(this).setDefaultRequestOptions(requestOptions)
                    .load(R.drawable.emptyuser)
                    .into(userimage);
            Glide.with(this).load(R.drawable.emptyuser).apply(bitmapTransform(new BlurTransformation(3)))
                    .into(coverimg);
        }
    }

    //
    private void setlistener() {
        btnback.setOnClickListener(OnClickListener());
        userimage.setOnClickListener(OnClickListener());
        addcatebtn.setOnClickListener(OnClickListener());
        btnedit.setOnClickListener(OnClickListener());
        btnchangepassword.setOnClickListener(OnClickListener());
        btnchangeimage.setOnClickListener(OnClickListener());
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                if (btnback == v) {
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
                if (btnchangeimage == v) {
                    selectImage("user");
                }
                if (userimage == v) {
                    try {
                        showimageDialog(userinfo.get(0).user_img);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (addcatebtn == v) {
                    SelectTabDialog selectTabDialog = new SelectTabDialog(UserProfileActivity.this, getcategory());
                    selectTabDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
                    selectTabDialog.setCanceledOnTouchOutside(false);
                    selectTabDialog.show();
                }
                if (btnedit == v) {
                    startActivity(new Intent(UserProfileActivity.this, EditProfileActivity.class));
                    overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
                if (btnchangepassword == v) {
                    ChangePasswordDialog selectTabDialog = new ChangePasswordDialog(UserProfileActivity.this, userinfo.get(0).userid);
                    selectTabDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
                    selectTabDialog.setCanceledOnTouchOutside(false);
                    selectTabDialog.show();
                }
            }
        };
    }


    private void setupdata() {
        Bitmap icon;

        if (socialProfiles.size() != 0) {
            username.setText(socialProfiles.get(0).getname());
            userphone.setText(socialProfiles.get(0).getEmail());

            Uri myUri = null;
            try {
                myUri = Uri.parse(socialProfiles.get(0).getUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                icon = BitmapFactory.decodeResource(getResources(),
                        R.drawable.emptyuser);
                Bitmap covverbitmap = blur(UserProfileActivity.this, icon);
                coverimg.setImageBitmap(covverbitmap);
            }

        } else {
            Glide.with(this)
                    .load(R.drawable.emptyuser)
                    .into(userimage);
            icon = BitmapFactory.decodeResource(getResources(),
                    R.drawable.emptyuser);
            Bitmap covverbitmap = blur(UserProfileActivity.this, icon);
            coverimg.setImageBitmap(covverbitmap);
        }
    }


    private void inflateRecentList() {
        recentActviityAdapter = new RecentActviityAdapter(ConstantData.getrecentDemoModels());
        layoutManager = new LinearLayoutManager(this);
        puserrecentlist.setLayoutManager(layoutManager);
        puserrecentlist.setHasFixedSize(true);
        puserrecentlist.addItemDecoration(new VerticalItemDecoration((int) getResources().getDimension(R.dimen.margin_5), false));
        puserrecentlist.setAdapter(recentActviityAdapter);
    }

    private void inits() {
        loaderimage=(ProgressBar)$(R.id.loader_image);
        profileImageProcess = new ProfileImageProcess(this);
        numberpost = (MyTextView) $(R.id.numberpost);
        catcountertxt = (MyTextView) $(R.id.cat_counter_txt);
        userimage = (ImageView) $(R.id.user_image);
        puserrecentlist = (RecyclerView) $(R.id.p_user_recent);
        prcategory = (RecyclerView) $(R.id.p_r_categories);
        coverimg = (ImageView) $(R.id.cover_img);
        btnback = (ImageView) $(R.id.btn_back);
        addcatebtn = (ImageView) $(R.id.btn_add_cate);
        btnedit = (ImageView) $(R.id.btn_edit);
        btnchangepassword = (MyTextView) $(R.id.btn_change_password);
        addcatebtn.setColorFilter(getResources().getColor(R.color.colorAccent));
        username = (MyTextView) $(R.id.user_name);
        userphone = (MyTextView) $(R.id.user_phone);
        userlocation = (MyTextView) $(R.id.user_address);
        btnchangeimage = (ImageView) $(R.id.change_image);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userimage.setImageBitmap(bm);
        imageupload();
        blurs();
    }

    private void onCaptureImageResult(Intent data) {
        userimage.setImageURI(imageUri);
        try {
            bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageupload();
        blurs();
    }

    public void imageupload() {
        loaderimage.setVisibility(View.VISIBLE);
        String image = null;
        try {
            image = BitMapToString(bm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        profileImageProcess.startprocess(userinfo.get(0).userid, image, userinfo.get(0).username.concat("1.jpg"), UserProfileActivity.this);
    }

    public void blurs() {
        Bitmap covverbitmap = blur(UserProfileActivity.this, bm);
        coverimg.setImageBitmap(covverbitmap);
    }

    private void inflatecategoryList() {
        List<Category> categorytabModels = new ArrayList<>();
        //  categoryModelArrayList.add()
        categorytabModels.addAll(getcategory());
        CategoryselectAdapter adapter = new CategoryselectAdapter(categorytabModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        prcategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        prcategory.setHasFixedSize(true);
        prcategory.addItemDecoration(new VerticalItemDecoration((int) getResources().getDimension(R.dimen.margin_5), false));
        prcategory.setAdapter(adapter);
    }


    @Override
    public void onFinishLoginActions(List<UserModel> userModels, String reponse) {
        loaderimage.setVisibility(View.GONE);
        userinfo = getuserinfo();
        settext();

    }

    @Override
    public void onErrorLoginAction(String error) {
        loaderimage.setVisibility(View.GONE);
        showToastLong("Please Try Again");
    }
}
