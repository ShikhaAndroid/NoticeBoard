package com.rajinder.noticeboard.Activity.HomeActivities;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rajinder.noticeboard.Activity.DialogActivity.Search.SearchDialog;
import com.rajinder.noticeboard.Activity.DetailView.CatDetailActivity;
import com.rajinder.noticeboard.Activity.Profile.UserProfileActivity;
import com.rajinder.noticeboard.Fragments.HomeFragment;
import com.rajinder.noticeboard.Fragments.NavigationFragment.CommentPostListFragment;
import com.rajinder.noticeboard.Fragments.NavigationFragment.MyFavListFragment;
import com.rajinder.noticeboard.Fragments.NavigationFragment.MyPostListFragment;
import com.rajinder.noticeboard.Fragments.NavigationFragment.NotificationListFragment;
import com.rajinder.noticeboard.Fragments.NavigationFragment.SettingFragment;
import com.rajinder.noticeboard.IntentService.BadgeIntentService;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectCat;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.models.SocialProfile;
import com.rajinder.noticeboard.models.UserInfo.User;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import me.leolin.shortcutbadger.ShortcutBadgeException;
import me.leolin.shortcutbadger.ShortcutBadger;
import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class HomeActivity extends MyActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView filterbtn, searchbtn, usercoverimg;
    private RelativeLayout notibtn;
    private LinearLayout catebtn, btnuserview;
    private CircularImageView userimage;

    private MyTextView username, useremail;
    DrawerLayout drawer;
    NavigationView navigationView;
    private FloatingActionButton fabedit;
    View headerView;
    MyTextView messcounter;

    FragmentTransaction transaction;
    Fragment Fragment;
    List<User> userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) $(R.id.toolbar);
        setSupportActionBar(toolbar);
        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION);
        try {
            askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, GPS_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        navigationView = (NavigationView) $(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);

        //  socialProfiles();
        //  if (socialProfiles.size() != 0)
        //        createNotification("Hello " + socialProfiles.get(0).getname() + "\n Welcome Notice Broad App");
        init();
        setListeners();
        currentlocation();
        /*drawer layout set */

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.svg_menu);
        navigationView.setNavigationItemSelectedListener(this);
        /*Fragement set*/
        homefragmentopen();
        //setdata();
    }

    private void settext() {
        username.setText(userinfo.get(0).username);
        useremail.setText(userinfo.get(0).email);
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
                    .into(usercoverimg);
        } else {
            Glide.with(this).setDefaultRequestOptions(requestOptions)
                    .load(R.drawable.emptyuser)
                    .into(userimage);
            Glide.with(this).load(R.drawable.emptyuser).apply(bitmapTransform(new BlurTransformation(3)))
                    .into(usercoverimg);
        }
    }

    public void homefragmentopen() {
        Fragment = new HomeFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, Fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void notificationfragmentopen() {
        Fragment = new NotificationListFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, Fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void commentfragmentopen() {
        Fragment = new CommentPostListFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, Fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void settingfragmentopen() {
        Fragment = new SettingFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, Fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void mypostfragment() {
        Fragment = new MyPostListFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, Fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void myfavfragment() {
        Fragment = new MyFavListFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, Fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setdata() {
                    //color_background.xml
                    Bitmap icon = null;
                    if (socialProfiles != null && socialProfiles.size() != 0) {
                        username.setText(socialProfiles.get(0).getname());
                        useremail.setText(socialProfiles.get(0).getEmail());
                        Uri myUri = null;
                        try {
                            myUri = Uri.parse(socialProfiles.get(0).getUrl());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //  icon = loadBitmap(socialProfiles.get(0).getUrl());
                        if (myUri != null) {
                            Glide.with(this)
                                    .load(myUri)
                                    .into(userimage);
                            Glide.with(this).load(myUri).apply(bitmapTransform(new BlurTransformation(3)))
                                    .into(usercoverimg);
                        } else {
                            Glide.with(this)
                                    .load(R.drawable.emptyuser)
                                    .into(userimage);
                            Glide.with(this).load(R.drawable.emptyuser).apply(bitmapTransform(new BlurTransformation(3)))
                                    .into(usercoverimg);
                        }
                    } else {
                            Glide.with(this)
                                    .load(R.drawable.emptyuser)
                                    .into(userimage);
                            Glide.with(this).load(R.drawable.emptyuser).apply(bitmapTransform(new BlurTransformation(3)))
                                    .into(usercoverimg);
                    }
        ShortcutBadger.applyCount(HomeActivity.this, 3);
        startService(new Intent(HomeActivity.this, BadgeIntentService.class).putExtra("badgeCount", 3));
    }

    /*listener*/
    private void setListeners() {
        filterbtn.setOnClickListener(OnClickListener());
        catebtn.setOnClickListener(OnClickListener());
        fabedit.setOnClickListener(OnClickListener());
        searchbtn.setOnClickListener(OnClickListener());
        notibtn.setOnClickListener(OnClickListener());
        btnuserview.setOnClickListener(OnClickListener());
        clearnotification();
    }

    /*click*/
    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                if (filterbtn == v) {
                    startActivity(new Intent(HomeActivity.this, FilterActivity.class));
                    overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
                /*select category and location */
                if (catebtn == v) {
                    startActivity(new Intent(HomeActivity.this, SelectCategoryActivity.class));
                    overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
                if (fabedit == v) {
                    createpost();
                }
                if (searchbtn == v) {
                    // uploadFacebook();
                    //   hideLoading();
                    SearchDialog selectTabDialog = new SearchDialog(HomeActivity.this);
                    selectTabDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
                    selectTabDialog.setCanceledOnTouchOutside(false);
                    selectTabDialog.show();
                    hideLoading();
                }
                if (notibtn == v) {
                    startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
                    overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
                if (btnuserview == v) {
                    startActivity(new Intent(HomeActivity.this, UserProfileActivity.class));
                    overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
            }
        };
    }

    public void uploadFacebook() {
        try {
            LoginManager.getInstance().logInWithPublishPermissions(
                    this,
                    Arrays.asList("publish_actions"));
            LoginManager.getInstance().logInWithReadPermissions(
                    this,
                    Arrays.asList("publish_actions"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("postfacebook", "s");
        //  for (File f : files) {
        File f = null;
        Bitmap bitmap = null;
        ByteArrayOutputStream stream = null;
        try {
            bitmap = BitmapFactory.decodeFile(f.getPath());
            stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //   byte[] byteArray = stream.toByteArray();
        final Bundle params = new Bundle();
        params.putString("fields", "id,name,picture");
        //  params.putString("message", "Hello");
        //  params.putByteArray("source", byteArray);
        params.putBoolean("published", true);
        Log.d("postfacebook", "0");

        new GraphRequest(com.facebook.AccessToken.getCurrentAccessToken(),
                "/me",
                params,
                HttpMethod.POST,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            Log.d("postfacebook", "1");

                            try {
                                Log.d("postfacebook", response.toString());
                                //     ids.add(response.getJSONObject().getString("id"));
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.d("postfacebook", e.toString());
                            }

                            Log.d("postfacebook", "2");
                            //      if (ids.size() == files.size()) {
                            Log.d("postfacebook", "3");
                            JSONObject jsonObject = new JSONObject();
                            //   for (int i = 0; i < ids.size(); i++) {
                            Log.d("postfacebook", "4");
                            jsonObject.put("message", "Hello");
                            // jsonObject.put("attached_media[" + i + "]", new JSONObject().put("media_fbid", ids.get(i)));
                            //   }
                            Log.d("postfacebook", String.valueOf(com.facebook.AccessToken.getCurrentAccessToken().getPermissions()));

                            GraphRequest request = GraphRequest.newPostRequest(
                                    com.facebook.AccessToken.getCurrentAccessToken(),
                                    "/me/feed",
                                    jsonObject,
                                    new GraphRequest.Callback() {
                                        @Override
                                        public void onCompleted(GraphResponse response) {
                                            Log.v("postfacebook", String.valueOf(response));
                                        }
                                    });
                            Log.d("postfacebook", "6");
                            request.executeAsync();
                            //  }
                        } catch (JSONException e) {
                            Log.d("postfacebook", e.toString());
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
        //   }
        Log.d("postfacebook", "e");
    }

    @Override
    protected void onResume() {
        super.onResume();
        userinfo = getuserinfo();
        socialProfiles();
        if (userinfo != null && userinfo.size() != 0) {
            settext();
        } else {
            setdata();
        }
        hideLoading();
        if (newpostcreate) {
            homefragmentopen();
            newpostcreate = false;
        }
    }

    private void createpost() {
        startActivity(new Intent(HomeActivity.this, MainCategoryActivity.class));
        overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
        // finish();
    }

    /*init*/
    private void init() {
        drawer = (DrawerLayout) $(R.id.drawer_layout);
        navigationView = (NavigationView) $(R.id.nav_view);
        filterbtn = (ImageView) $(R.id.filter_btn);
        catebtn = (LinearLayout) $(R.id.catebtn);
        fabedit = (FloatingActionButton) $(R.id.fab_edit);
        searchbtn = (ImageView) $(R.id.search_btn);
        notibtn = (RelativeLayout) $(R.id.noti_btn);
        btnuserview = (LinearLayout) headerView.findViewById(R.id.btn_user_view);
        usercoverimg = (ImageView) headerView.findViewById(R.id.user_cover_img);
        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.nav_message);
        View actionView = MenuItemCompat.getActionView(menuItem);
        messcounter = (MyTextView) actionView.findViewById(R.id.mess_counter);
        //messcounter.setText("10");
        username = (MyTextView) headerView.findViewById(R.id.user_name);
        useremail = (MyTextView) headerView.findViewById(R.id.user_email);
        userimage = (CircularImageView) headerView.findViewById(R.id.user_image);
    }

    /*back press*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) $(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        // Handle navigation view item clicks here.
        showLoading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();
                fabedit.setVisibility(View.GONE);
                if (id == R.id.nav_home) {
                    fabedit.setVisibility(View.VISIBLE);
                    homefragmentopen();
                } else if (id == R.id.nav_create_post) {
                    createpost();
                } else if (id == R.id.nav_message) {
                    commentfragmentopen();
                } else if (id == R.id.nav_notification) {
                    notificationfragmentopen();
                } else if (id == R.id.nav_posts) {
                    fabedit.setVisibility(View.VISIBLE);
                    mypostfragment();
                } else if (id == R.id.nav_myfav) {
                    fabedit.setVisibility(View.VISIBLE);
                    myfavfragment();
                } else if (id == R.id.nav_manage) {
                    settingfragmentopen();
                } else if (id == R.id.nav_share) {
                    try {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, "Notice Board");
                        String sAux = "\nLet me recommend you this application\n\n";
                        sAux = sAux + "https://play.google.com/store/apps/details?id=com.punjabkesari\n\n";
                        i.putExtra(Intent.EXTRA_TEXT, sAux);
                        startActivity(Intent.createChooser(i, "choose one"));
                    } catch (Exception e) {
                        //e.toString();
                    }
                } else if (id == R.id.nav_logout) {
                    try {
                        new Delete().from(User.class).execute();
                        new Delete().from(SocialProfile.class).execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }
        }, 500);

        DrawerLayout drawer = (DrawerLayout) $(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        hideLoading();
        return true;
    }

    /*select category event bus*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventSelectCat position) {
        //try {
        /*    Log.e("event", position.getType());
            if (position.getType().equals("item")) {
                startActivity(new Intent(HomeActivity.this, CatDetailActivity.class));
                overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

}
