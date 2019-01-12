package com.rajinder.noticeboard.Utils;

import android.Manifest.permission;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.internal.gmsg.HttpClient;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.location.places.GeoDataClient;

import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.messaging.FirebaseMessaging;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rajinder.noticeboard.Activity.Authentication.LoginActivity;
import com.rajinder.noticeboard.Activity.DialogActivity.InternetErrorDialog;
import com.rajinder.noticeboard.MyApplication;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.LoadingDialog;
import com.rajinder.noticeboard.Utils.LocationUtil.GPSTracker;
import com.rajinder.noticeboard.Utils.LocationUtil.PermissionUtils;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.SocialProfile;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;

import static android.Manifest.permission.*;

/**
 * Created by Rajinder on 4/19/2018.
 */
public class MyActivity extends AppCompatActivity implements OnRequestPermissionsResultCallback {

    private boolean loadingVisible = false;
    private LoadingDialog loadingDialog;
    public static final String FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION = "FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION_NOTICE_B";
    public int PLACE_AUTOCOMPLETE_REQUEST_CODE = 11;

    public static final String TAB_TYPE_LOGIN = "TAB_TYPE_LOGIN";
    public static final String TAB_TYPE_NOTIFICATION = "TAB_TYPE_NOTIFICATION";

    public static final String CATE_ID = "cate_id";
    public static final String SUB_CATE_ID = "sub_cate_id";
    public static final String SUB_CAT_NAME = "sub_cat_name";
    public static String VIEW_TYPE = "VIEW_TYPE";
    public static final String LOCATION_NAME = "location_name";
    public static final String LATLONG = "lat_long";
    public static final String PLACEID = "Place_id";
    public static final String CATE_TYPE = "cate_type";
    public static final String SUB_CATE_TYPE = "sub_cate_type";
    public static final String SET_IMAGE = "set_image";

    private BaseActivityReceiver baseActivityReceiver = new BaseActivityReceiver();
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final Integer LOCATION = 0x1;
    public static final Integer CALL = 0x2;
    public static final Integer WRITE_EXST = 0x3;
    public static final Integer READ_EXST = 0x4;
    public static final Integer CAMERA = 0x5;
    public static final Integer ACCOUNTS = 0x6;
    public static final Integer GPS_SETTINGS = 0x7;
    public static final int GOOGLE_SIGN_IN = 0x8;
    public GoogleApiClient client;
    LocationRequest mLocationRequest;
    //PendingResult<LocationSettingsResult> result;
    // list of permissions

    ArrayList<String> permissions = new ArrayList<>();
    PermissionUtils permissionUtils;

    boolean isPermissionGranted;
    public int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;

    public String filename;
    public Uri imageUri;
    public GeoDataClient mGeoDataClient;
    public GoogleSignInOptions gso;
    public GoogleSignInClient mGoogleSignInClient;

    public MyApplication myApplication;
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 7.5f;
    private static final int REQUEST = 112;

    private NotificationManager notifManager;
    public Dialog dialog;
    public String HOMEFRAGMENTTAG = "HomeFragmant";
    public String MYPOSTLISTFRAGMENTTAG = "mypostlistfragment";
    public List<SocialProfile> socialProfiles;
    public int userid;
    public static double lat = 0;
    public static double lng = 0;

    public static final String EVENT = "event";
    public static final String OTHER = "other";
    public static final String NEED = "need";
    public static final String SALE = "sale";
    public static final String NEWS = "news";
    public static final String REVIEW = "review";
    public InternetErrorDialog interneterror;

    /*newpost check and refresh fragment*/
    public static boolean newpostcreate = false;

    /*around me km*/
    public static int aroundmekm = 3;

    @Subscribe
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getlatlong();
        StrictMode.VmPolicy.Builder builders = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builders.build());

        TwitterAuthConfig authConfig = new TwitterAuthConfig("KC3WDCixYopNo8IpS6ihW2L4N", "ofrzPQ3SxfQhokSA97DPzBwca5i6sh9hscwM0NYRqR4pJVBEeG");
        TwitterConfig.Builder builder = new TwitterConfig.Builder(this);
        builder.twitterAuthConfig(authConfig);
        Twitter.initialize(builder.build());
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        myApplication = (MyApplication) getApplicationContext();

        /*gmail account*/
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        /*google map*/
        client = new GoogleApiClient.Builder(this)
                .addApi(AppIndex.API)
                .addApi(LocationServices.API)
                .build();
        mGeoDataClient = Places.getGeoDataClient(this, null);

        /*Event bus register*/

        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadingDialog = new LoadingDialog(this);
        Utils.log("Enter en activity: " + getLocalClassName()); //todo quitar
        permissionIO();
        try {
            userid = getuserinfo().get(0).userid;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public View $(int id) {
        return findViewById(id);
    }

    public void socialProfiles() {
        try {
            socialProfiles = new Select().from(SocialProfile.class).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletesocialprofile() {
        try {
            new Delete().from(SocialProfile.class).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Resume */
    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.activityResumed();
       /* if (SharedPrefUtils.getInstancia().isLogueado()) {
            ManagerSipdroid.openConnection(this);
        }*/
    }

    /*pause*/
    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPaused();
    }

    /*Start*/
    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(baseActivityReceiver, createIntentFilter());
        client.connect();
    }

    /*Stop*/
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(baseActivityReceiver);
        client.disconnect();
    }

    public void pedirPermiso(String permiso, int requestCode) {
        if (tengoPermiso(permiso)) {
            try {
                onPermisoGranted(requestCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ActivityCompat.requestPermissions(this, new String[]{permiso}, requestCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    protected void onPermisoGranted(int requestCode) {
        isPermissionGranted = true;
    }

    protected void onPermisoDenied(int requestCode) {}

    private boolean tengoPermiso(String permiso) {
        return ContextCompat.checkSelfPermission(this, permiso)
                == PackageManager.PERMISSION_GRANTED;
    }

    /*Show loader */
    public void showLoading() {
        if (!loadingVisible) {
            loadingVisible = true;
            if (loadingDialog != null) {
                if (!loadingDialog.isShowing())
                    loadingDialog.show();
            }
        }
    }

    /*hide loader */
    public void hideLoading() {
        if (loadingVisible) {
            loadingVisible = false;
            if (loadingDialog != null) {
                if (loadingDialog.isShowing())
                    loadingDialog.dismiss();
            }
        }
    }

    public void requestEdittextKeyboard(final View editText) {
        if (editText.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    /*Hide key board */
    public void hideKeyboard() {
        //  view = view;//getCurrentFocus();
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /*Show toast long */
    public void showToastLong(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    public void showsnackbar(String str, View v) {
        Snackbar.make(v, str, Snackbar.LENGTH_LONG);
    }

    /*/*Show toast Short */
    public void showToastShort(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void showMessageAlert(String message, MaterialDialog.SingleButtonCallback callback) {
        new MaterialDialog.Builder(this)
                .content(message)
                .positiveColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .title(R.string.message_title)
                .positiveText(R.string.accept)
                .onPositive(callback)
                .show();
    }

    public void showinterneterror() {
        interneterror = new InternetErrorDialog(this);
        interneterror.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        interneterror.setCanceledOnTouchOutside(false);
        interneterror.show();
    }

    public void dismissinterneterror(){
            interneterror.dismiss();
    }

    public void showMessageAlert(String message) {
        try {
            hideLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
        showMessageAlert(message, null);
    }

    public void showMessageAlert(int string_id, MaterialDialog.SingleButtonCallback callback) {
        showMessageAlert(getString(string_id), null);
    }

    public void showMessageAlert(int string_id) {
        showMessageAlert(string_id, null);
    }


    public void showMessageElection(String message, String positiveBtn, String negativeBtn,
                                    MaterialDialog.SingleButtonCallback positive,
                                    MaterialDialog.SingleButtonCallback negative) {
        new MaterialDialog.Builder(this)
                .content(message)
                .onPositive(positive)
                .onNegative(negative)
                .positiveText(positiveBtn)
                .negativeText(negativeBtn)
                .positiveColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .negativeColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .show();
    }


    public void showMessageElections(String message, String positiveBtn, String negativeBtn,
                                     MaterialDialog.SingleButtonCallback positive,
                                     MaterialDialog.SingleButtonCallback negative) {
        new MaterialDialog.Builder(this)
                .content(message)
                .onPositive(positive)
                .onNegative(negative)
                .positiveText(positiveBtn)
                .negativeText(negativeBtn)
                .positiveColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .negativeColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .show();
    }

    public void showMessageElection(int message, int positiveBtn, int negativeBtn,
                                    MaterialDialog.SingleButtonCallback positive,
                                    MaterialDialog.SingleButtonCallback negative) {
        showMessageElection(getString(message), getString(positiveBtn), getString(negativeBtn),
                positive, negative);
    }


    public class BaseActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION)) {
                finish();
            }
        }
    }

    private static IntentFilter createIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION);
        return filter;
    }


    public static void finishAllActivities(Context context) {
        Intent i = new Intent(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION);
        context.sendBroadcast(i);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

/*
    public Date getdate()
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();

            try {
                date = dateFormat.parse(dateFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
    return date;
        }
*/

    public void checkNetwork() {
        if (isNetworkAvailable())
            return;
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle(R.string.network_connection);
        myDialog.setMessage(R.string.you_are_not_connected_to_any_network_press_ok_to_change_settings);
        myDialog.setPositiveButton(R.string.ok_button,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Intent settings = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        settings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(settings);
                        dialog.dismiss();

                    }
                });
        myDialog.setNegativeButton(R.string.exit,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        exitFromApp();
                        dialog.dismiss();
                    }
                });
        myDialog.setCancelable(false);
        AlertDialog alertd = myDialog.create();
        alertd.show();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isNetworkAvailable = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        return isNetworkAvailable;
    }


    /*Exit App*/
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void exitFromApp() {
        finish();
        finishAffinity();
    }


    /*valid email*/
    public boolean isValidMail(String email) {
        return email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+");
    }

    /*valid number*/
    public boolean isValidNumber(String number) {
        try {
            Double.parseDouble(number);

        } catch (Exception e) {
            return false;
        }
        int d = number.length();
        if (number.length() >= 10)
            return true;
        else
            return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /*calculate no of columns*/
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        int noOfColumns = (int) (dpWidth / 100);
        return noOfColumns;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
        EventBus.getDefault().unregister(this);
    }

    public String findCurrentLocation() {
        final String[] locationstr = new String[1];
        // mGoogleClientApi.connect();
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MyActivity.this);
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MyActivity.this, "Please turn on GPS service", Toast.LENGTH_SHORT).show();
            // return;
        } else {
            Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
            locationTask.addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isComplete()) {
                        task.addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                Log.e(">>>", "Location object >>> " + location);
                                locationstr[0] = String.valueOf(location.getAltitude());
                            }
                        });

                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(">>>", "Location object >>> Error " + e);
                            }
                        });
                    }
                }
            });
        }
        return locationstr[0];
    }

    public void getlatlong(){
        GPSTracker gpsTracker = new GPSTracker(this);
        lat = gpsTracker.getLatitude();
        lng = gpsTracker.getLongitude();
    }

    public void selectImage(final String type) {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = checkPermission(MyActivity.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result) {
                        try {
                            cameraIntent(type);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent(type);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public void cameraIntent(String type) throws IOException {
        //  filename = Environment.getExternalStorageDirectory().getPath() + "/testfile.jpg";
        imageUri = Uri.fromFile(createImageFile());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("type", type);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    public void galleryIntent(String type) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        intent.putExtra("type", type);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

/*    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }*/

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        filename = "file:" + image.getAbsolutePath();
        // generate URI, I defined authority as the application ID in the Manifest, the last param is file I want to open

        return image;
    }

    /*GPS PREmissio*/

    public void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MyActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MyActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MyActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MyActivity.this, new String[]{permission}, requestCode);
            }
        } else {}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            switch (requestCode) {
                //Location
                case 1:
                    //   askForGPS();
                    break;
                //Call
                case 2:
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + "{This is a telephone number}"));
                    if (ActivityCompat.checkSelfPermission(this, permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        startActivity(callIntent);
                    }
                    break;
                //Write external Storage
                case 3:
                    break;
                //Read External Storage
                case 4:
                    Intent imageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(imageIntent, 11);
                    break;
                //Camera
                case 5:
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, 12);
                    }
                    break;
                //Accounts
                case 6:
                    AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
                    Account[] list = manager.getAccounts();
                    Toast.makeText(this, "" + list[0].name, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < list.length; i++) {
                        Log.e("Account " + i, "" + list[i].name);
                    }
            }

            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

/*
    private void askForGPS(){
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        result = LocationServices.SettingsApi.checkLocationSettings(client, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(MyActivity.this, GPS_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }
*/


    public Address getAddress(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(MyActivity.this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            return addresses.get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void callPlaceSearchIntent(double lat, double logs) {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setBoundsBias(new LatLngBounds(new LatLng(lat, logs), new LatLng(lat, logs)))
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
            Log.e("add", "execute");
        }

    }

    public void currentlocation() {
        // create class object
        GPSTracker gps = new GPSTracker(MyActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

        } else {
            gps.showSettingsAlert();
        }
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHexs(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = 0;
            try {
                v = bytes[j] & 0xFF;
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                hexChars[j * 2] = hexArray[v >>> 4];
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String aaa = new String(hexChars);
        return aaa;
    }


    public Bitmap blur(Context context, Bitmap image) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);


        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
            Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
            theIntrinsic.setRadius(BLUR_RADIUS);
            theIntrinsic.setInput(tmpIn);
            theIntrinsic.forEach(tmpOut);
            tmpOut.copyTo(outputBitmap);
        }

        return outputBitmap;
    }

    public void permissionIO() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {permission.READ_EXTERNAL_STORAGE, permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(this, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, REQUEST);
            } else {
                //do here
            }
        } else {
            //do here
        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public void createNotification(String aMessage) {
        final int NOTIFY_ID = 1002;

        // There are hardcoding only for show it's just strings
        String name = "my_package_channel";
        String id = "my_package_channel_1"; // The user-visible name of the channel.
        String description = "my_package_first_channel"; // The user-visible description of the channel.

        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;

        if (notifManager == null) {
            notifManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, name, importance);
                mChannel.setDescription(description);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(this, id);

            intent = new Intent(this, MyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder.setContentTitle(aMessage)  // required
                    .setSmallIcon(R.drawable.noticeboardlogo) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else {

            builder = new NotificationCompat.Builder(this);

            intent = new Intent(this, MyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            builder.setContentTitle(aMessage)                           // required
                    .setSmallIcon(R.drawable.noticeboardlogo) // required
                    .setContentText(this.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        } // else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }

    public void clearnotification() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                try {
                    notifManager.cancelAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 10000);
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void showimageDialog(String myuri) {
        dialog = new Dialog(MyActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        dialog.setContentView(R.layout.imageview_layout);
        ImageView btnclose = (ImageView) dialog.findViewById(R.id.btn_close);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        CircularImageView user_image = (CircularImageView) dialog.findViewById(R.id.user_image);
        if (!myuri.equals("")) {
            Glide.with(this)
                    .load(myuri)
                    .into(user_image);
        }
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    /*get user info*/
    public static List<com.rajinder.noticeboard.models.UserInfo.User> getuserinfo() {
        // This is how you execute a query
        return new Select().from(com.rajinder.noticeboard.models.UserInfo.User.class).execute();
    }

    /*get category info*/
    public static List<Category> getcategory() {
        // This is how you execute a query
        return new Select().from(Category.class).execute();
    }

    /*get social info*/
    public static List<SocialProfile> getsocialinfo() {
        // This is how you execute a query
        return new Select().from(SocialProfile.class).execute();
    }

    public File compressImage(Uri imageUri) {
        String filePath = getRealPathFromURI(String.valueOf(imageUri));
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();
//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;
            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image
        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
//        String filename = getFilename();
        File f = null;
        try {
            f = createImageFile();
            out = new FileOutputStream(f);
//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }
        return inSampleSize;
    }


}