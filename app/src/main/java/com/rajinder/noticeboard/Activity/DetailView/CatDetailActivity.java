package com.rajinder.noticeboard.Activity.DetailView;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.model.LatLng;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rajinder.noticeboard.Activity.DialogActivity.UnlikePostDialog;
import com.rajinder.noticeboard.Activity.Profile.ViewProfileActivity;
import com.rajinder.noticeboard.Interface.DeletepostAction;
import com.rajinder.noticeboard.Interface.OnLikepostAction;
import com.rajinder.noticeboard.Interface.OnProfileViewAction;
import com.rajinder.noticeboard.Process.DeletePostProcess;
import com.rajinder.noticeboard.Process.LikeUlikePostProcess;
import com.rajinder.noticeboard.Process.ProfileViewProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.models.DeletepostModel;
import com.rajinder.noticeboard.models.PostListModel;
import com.rajinder.noticeboard.models.UserProfileViewModel;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CatDetailActivity extends MyActivity implements RoutingListener, OnProfileViewAction, DeletepostAction, OnLikepostAction {

    public static final String TAG = "CatDetailActivity";
    private NestedScrollView nestedview;
    private RatingBar ratingreview;
    private MyTextView catedis, titletxt, btnviewprofile, btndeletepost, btncomment, catedistance, catetitle, catename, catelocationname, catetime, cateprice, ratinguser;
    private MyTextView starttime, startdate, endtime, enddate;
    private Toolbar toolbar;
    Bitmap bmp;
    BitmapDrawable bitmapDrawable;
    private ImageView btnlike, btnshare, btnback;
    Bundle extras;
    private FloatingActionMenu fabmenu;
    private FloatingActionButton btnchat, btncall;
    private boolean fabExpanded = false;
    List<PostListModel> postListModels;
    private CircularImageView postuserimage;
    private LinearLayout eventview;
    private ProfileViewProcess profileViewProcess;
    private DeletePostProcess deletePostProcess;
    private LikeUlikePostProcess likeUlikePostProcess;
    private UnlikePostDialog unlikepostdialog;
    private String type;
    CarouselView carouselView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);
        extras = getIntent().getExtras();
        if (extras == null) {
            return;
        } else {
            postListModels = (List<PostListModel>) extras.getSerializable("post_item");
            type = extras.getString("type");
        }
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.transbackground);
        bitmapDrawable = new BitmapDrawable(bmp);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        /*set init*/
        inits();
        setlistener();
        setdata();

        if (type.equals("user")) {
            btndeletepost.setVisibility(View.VISIBLE);
            btnviewprofile.setVisibility(View.GONE);
            fabmenu.setVisibility(View.INVISIBLE);
        }

        nestedview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                String TAG = "nested_sync";
                if (scrollY > 500) {
                    Log.i(TAG, "TOP SCROLL");
                    toolbar.setBackgroundColor(Color.WHITE);
                    btnlike.setImageResource(R.drawable.svg_unlike_b);
                    btnshare.setImageResource(R.drawable.svg_share_b);
                    btnback.setImageResource(R.drawable.svg_back);
                    titletxt.setVisibility(View.VISIBLE);
                } else {
                    titletxt.setVisibility(View.GONE);
                    toolbar.setBackgroundDrawable(bitmapDrawable);
                    btnlike.setImageResource(R.drawable.svg_unlike_w);
                    btnshare.setImageResource(R.drawable.svg_share);
                    btnback.setImageResource(R.drawable.svg_backwhite);
                    Log.i(TAG, "DOWN SCROLL");
                }
            }
        });
    }

    private void setdata() {
        carouselView.setPageCount(postListModels.get(0).postImage.size());
        carouselView.setImageListener(imageListener);

        if (postListModels.get(0).typePost.equals(EVENT)) {
            eventview.setVisibility(View.VISIBLE);
            startdate.setText(postListModels.get(0).startDate);
            starttime.setText(postListModels.get(0).startTime);
            endtime.setText(postListModels.get(0).endTime);
            enddate.setText(postListModels.get(0).endDate);
        } else {
            eventview.setVisibility(View.GONE);
        }

        if (postListModels.get(0).typePost.equals(REVIEW)) {
            ratingreview.setVisibility(View.VISIBLE);
            ratingreview.setRating(postListModels.get(0).ratingValue);
            ratinguser.setVisibility(View.VISIBLE);
            ratinguser.setText("Reviews " + String.valueOf(postListModels.get(0).ratingValue) + " (Users " + postListModels.get(0).totalUser + ")");
        } else {
            ratingreview.setVisibility(View.GONE);
            ratinguser.setVisibility(View.GONE);
        }

        if (postListModels.get(0).priceVaule == 0)
            cateprice.setVisibility(View.GONE);
        else {
            cateprice.setVisibility(View.VISIBLE);
            cateprice.setText("\u20B9" + " " + String.valueOf(postListModels.get(0).priceVaule));
        }

        if (postListModels.get(0).likeStatus == 0)
            btnlike.setImageResource(R.drawable.svg_unlike_w);
        else {
            btnlike.setImageResource(R.drawable.svg_unlike_b);
        }

        titletxt.setText(postListModels.get(0).categoryName);
        catename.setText(postListModels.get(0).categoryName);
        catelocationname.setText(postListModels.get(0).location);
        if (postListModels.get(0).postTitle.equals(""))
            catetitle.setVisibility(View.GONE);
        else
            catetitle.setVisibility(View.VISIBLE);
        catetitle.setText(postListModels.get(0).postTitle);
        catedis.setText(postListModels.get(0).description);

        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.noticeboardlogo);
//        requestOptions.error(R.drawable.noticeboardlogo);
//        requestOptions.centerInside();
//        Glide.with(this).setDefaultRequestOptions(requestOptions).load(postListModels.get(0).postImage).into(postimg);
        String ff = postListModels.get(0).profilePic;
        requestOptions.placeholder(R.drawable.emptyuser);
        requestOptions.error(R.drawable.emptyuser);
        requestOptions.centerInside();
        Uri myUri = null;
        try {
            myUri = Uri.parse(postListModels.get(0).profilePic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (postListModels.get(0).profilePic != null) {
            if (postListModels.get(0).profilePic.equals(""))
                postuserimage.setImageDrawable(getResources().getDrawable(R.drawable.emptyuser));
            else
                Glide.with(this).setDefaultRequestOptions(requestOptions).load(myUri).into(postuserimage);
        } else
            postuserimage.setImageDrawable(getResources().getDrawable(R.drawable.emptyuser));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm aa");
        try {
            String repls = (postListModels.get(0).addedOn).replace("/Date(", "");
            String repl = repls.replace(")/", "");
            long abc = Long.valueOf(repl);
            Calendar calendar = Calendar.getInstance();
            String currentdate = dateFormat.format(calendar.getTime());
            calendar.setTimeInMillis(abc);
            //  Date publishdate = dateFormat.parse(repl);

            String newsdate = formatter.format(calendar.getTime());
            Date publishdate = dateFormat.parse(newsdate);
            String postdate = dateFormat.format(publishdate);

            if (postdate.equals(currentdate)) {
                String timepost = timeformat.format(calendar.getTime());
                catetime.setText("Ad posted at " + timepost);
            } else
                catetime.setText("Ad posted at " + postdate);
        } catch (Exception e) {
            e.toString();
        }
    }

    private void setlistener() {
        btndeletepost.setOnClickListener(OnClickListener());
        btnback.setOnClickListener(OnClickListener());
        btnshare.setOnClickListener(OnClickListener());
        btnlike.setOnClickListener(OnClickListener());
        btncall.setOnClickListener(OnClickListener());
        btnchat.setOnClickListener(OnClickListener());
        fabmenu.setOnClickListener(OnClickListener());
        btnviewprofile.setOnClickListener(OnClickListener());
        btncomment.setOnClickListener(OnClickListener());

        fabmenu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (fabExpanded == true) {
                    Log.e("fab", "fabclose");
                    closeSubMenusFab();
                } else {
                    Log.e("fab", "faboprn");
                    openSubMenusFab();
                }
            }
        });
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnback == v) {
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
                if (btnshare == v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, postListModels.get(0).postTitle + "\n" + postListModels.get(0).description);
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
                if (btnlike == v) {
                    showLoading();
                    if (postListModels.get(0).likeStatus == 0)
                        likeUlikePostProcess.startprocess(getuserinfo().get(0).userid, postListModels.get(0).postId, "like", CatDetailActivity.this);
                    else
                        likeUlikePostProcess.startprocess(getuserinfo().get(0).userid, postListModels.get(0).postId, "unlike", CatDetailActivity.this);
                }
                if (btncall == v) {
                    if (ContextCompat.checkSelfPermission(CatDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CatDetailActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    } else {
                        showLoading();
                        String userid = String.valueOf(postListModels.get(0).addedBy);
                        profileViewProcess.startprocess(userid, CatDetailActivity.this);
                    /*    Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:0377778888"));
                        startActivity(callIntent);*/
                    }
                }
                if (btnchat == v) {
                    showMessageAlert("Under Working");
                }
                if (fabmenu == v) {
                    ObjectAnimator.ofFloat(fabmenu, "rotation", 0f, 360f).setDuration(800).start();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (fabExpanded == true) {
                                Log.e("fab", "fabclose");
                                closeSubMenusFab();
                            } else {
                                Log.e("fab", "fabopen");
                                openSubMenusFab();
                            }
                        }
                    }, 400);
                }
                if (btnviewprofile == v) {
                    Intent viewprofileintent = new Intent(CatDetailActivity.this, ViewProfileActivity.class);
                    viewprofileintent.putExtra("user_id", postListModels.get(0).addedBy);
                    startActivity(viewprofileintent);
                    overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
                if (btncomment == v) {
                    Intent catadetail = new Intent(CatDetailActivity.this, CommentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("postitem", (Serializable) postListModels);
                    catadetail.putExtras(bundle);
                    startActivity(catadetail);
                    overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
                if (btndeletepost == v) {
                    showLoading();
                    deletePostProcess.startprocess(getuserinfo().get(0).userid, postListModels.get(0).postId, CatDetailActivity.this);
                }
            }
        };
    }

    private void openSubMenusFab() {
        //   fabmenu.setImageResource(R.drawable.svg_add);
        fabExpanded = true;
        Log.e("fab", "faboprn1");
        fabmenu.getMenuIconView().setImageResource(R.drawable.svg_close);
    }

    private void closeSubMenusFab() {
        fabExpanded = false;
        Log.e("fab", "fabclose1");
        fabmenu.getMenuIconView().setImageResource(R.drawable.svg_dot_three);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    private void inits() {
        likeUlikePostProcess = new LikeUlikePostProcess(this);
        deletePostProcess = new DeletePostProcess(this);
        profileViewProcess = new ProfileViewProcess(this);
        carouselView = (CarouselView) findViewById(R.id.post_img);
        starttime = (MyTextView) $(R.id.start_time);
        startdate = (MyTextView) $(R.id.start_date);
        endtime = (MyTextView) $(R.id.end_time);
        enddate = (MyTextView) $(R.id.end_date);
        eventview = (LinearLayout) $(R.id.event_view);
        ratingreview = (RatingBar) $(R.id.rating_review);
        ratinguser = (MyTextView) $(R.id.rating_user);
        cateprice = (MyTextView) $(R.id.cate_price);
        catetime = (MyTextView) $(R.id.cate_time);
        catename = (MyTextView) $(R.id.cate_name);
        titletxt = (MyTextView) $(R.id.title_txt);
        nestedview = (NestedScrollView) $(R.id.nested_view);
        catedis = (MyTextView) $(R.id.cate_dis);
        catetitle = (MyTextView) $(R.id.cate_title);
        toolbar = (Toolbar) $(R.id.toolbar);
        btnlike = (ImageView) $(R.id.btn_like);
        btnshare = (ImageView) $(R.id.btn_share);
        btnback = (ImageView) $(R.id.btn_back);
        fabmenu = (FloatingActionMenu) $(R.id.fab_menu);
        btnchat = (FloatingActionButton) $(R.id.btn_chat);
        btncall = (FloatingActionButton) $(R.id.btn_call);
        btnviewprofile = (MyTextView) $(R.id.btn_view_profile);
        btndeletepost = (MyTextView) $(R.id.delete_post);
        btncomment = (MyTextView) $(R.id.btn_comment);
        catedistance = (MyTextView) $(R.id.cate_distance);
        catelocationname = (MyTextView) $(R.id.cate_location_name);
        postuserimage = (CircularImageView) $(R.id.post_user_image);
        meterDistanceBetweenPoints(Float.valueOf(String.valueOf(postListModels.get(0).latitude)), Float.valueOf(String.valueOf(MyActivity.lat)), Float.valueOf(String.valueOf(postListModels.get(0).latitude)), Float.valueOf(String.valueOf(MyActivity.lat)));
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.noticeboardlogo);
            requestOptions.error(R.drawable.noticeboardlogo);
            requestOptions.centerInside();
            Glide.with(getApplicationContext()).setDefaultRequestOptions(requestOptions).load(postListModels.get(0).postImage.get(position)).into(imageView);
        }
    };

    private void meterDistanceBetweenPoints(float lat1, float lat2, float lon1, float lon2) {
        LatLng start = new LatLng(lat1, lon1);
        LatLng end = new LatLng(lat2, lon2);
        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(this)
                .alternativeRoutes(true)
                .waypoints(start, end)
                .build();
        routing.execute();
    }

    @Override
    public void onRoutingFailure(RouteException e) {}

    @Override
    public void onRoutingStart() {}

    @Override
    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {
        catedistance.setText(arrayList.get(0).getDistanceText() + " away");
    }

    @Override
    public void onRoutingCancelled() {}

    @Override
    public void onFinishProfileActions(List<UserProfileViewModel> userModels, String reponse) {
        hideLoading();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + userModels.get(0).phoneNo));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //   int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

    @Override
    public void onErrorProfileAction(String error) {
        hideLoading();
    }

    @Override
    public void onFinishCateActions(List<DeletepostModel> deletepostModels, String reponse) {
        hideLoading();
        if (deletepostModels.get(0).getSuccess()) {
            showToastShort(deletepostModels.get(0).getMessage());
            finish();
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        } else {
            showToastShort(deletepostModels.get(0).getMessage());
        }
    }

    @Override
    public void onErroCaterAction(String error) {
        hideLoading();
        if (error.equals("internet"))
            showinterneterror();
        else
            showToastShort("Please Try Again");
    }

    @Override
    public void onFinishLUActions(List<DeletepostModel> likeunlike, String reponse) {
        hideLoading();
        showToastShort(likeunlike.get(0).getMessage());
        Log.d(TAG, "onFinishLUActions: "+likeunlike.get(0).getMessage());
        if (likeunlike.get(0).getMessage().equals("Already Liked")) {
            unlikepostdialog = new UnlikePostDialog(CatDetailActivity.this, String.valueOf(userid),postListModels.get(0).postId);
            unlikepostdialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
            unlikepostdialog.setCanceledOnTouchOutside(false);
            unlikepostdialog.show();
        } else if (likeunlike.get(0).getMessage().equals("Liked")) {
            btnlike.setImageResource(R.drawable.svg_unlike_b);
            postListModels.get(0).likeStatus = 1;
        } else if (likeunlike.get(0).getMessage().equals("Unlike")) {
            btnlike.setImageResource(R.drawable.svg_unlike_w);
            postListModels.get(0).likeStatus = 0;
        }
    }

    @Override
    public void onErrorLUAction(String error) {
        hideLoading();
        if (error.equals("internet"))
            showinterneterror();
        else
            showToastShort("Please Try Again");
    }

}
