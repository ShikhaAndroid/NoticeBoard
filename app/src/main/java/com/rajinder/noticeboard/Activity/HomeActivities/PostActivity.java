package com.rajinder.noticeboard.Activity.HomeActivities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.rajinder.noticeboard.Activity.DialogActivity.CategoryListDialog;

import com.rajinder.noticeboard.Fragments.EventCreateFragment;
import com.rajinder.noticeboard.Fragments.NeedCreateFragment;
import com.rajinder.noticeboard.Fragments.NewsFragment;
import com.rajinder.noticeboard.Fragments.NormalPostFragment;
import com.rajinder.noticeboard.Fragments.ReviewFragment;
import com.rajinder.noticeboard.Fragments.SaleFragment;
import com.rajinder.noticeboard.Interface.CreateEventFragmentListener;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectCat;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PostActivity extends MyActivity implements CreateEventFragmentListener {

    public static final String TAG = "PostActivity";
    public int sub_cate_id, cate_id, cateid;
    public int finial_sub_cate_id, finial_cate_id;
    public String sub_name;
    private String catename;

    LinearLayout create_post_container;
    NestedScrollView nested_view;
    SaleFragment salesFragment;
    EventCreateFragment eventCreateFragment;
    NeedCreateFragment needCreateFragment;
    NewsFragment newsFragment;
    ReviewFragment reviewFragment;
    NormalPostFragment normalPostFragment;
    CategoryListDialog selectTabDialog;

    /*USER INFO*/
    private CircularImageView userimage;
    private MyTextView username;
    private MyTextView txtcate, txtsubcate;
    private ImageView btnback, btnpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        hideKeyboard();
        /*get intent */
        Intent mIntent = getIntent();
        cate_id = mIntent.getIntExtra(CATE_ID, 0);
        sub_cate_id = mIntent.getIntExtra(SUB_CATE_ID, 0);
        sub_name = mIntent.getStringExtra(SUB_CAT_NAME);
        //  locationname = mIntent.getStringExtra(LOCATION_NAME);
        //   latlong = mIntent.getStringExtra(LATLONG);
        init();
        setdata();
        setListeners();
//        finial value set cat id or sub cate id
        setids(cate_id, sub_cate_id, sub_name);
    }

    private void setdata() {
        username.setText(getuserinfo().get(0).username);
        //   try {
        //       Glide.with(this).load(getuserinfo().get(0).email).into(userimage);
        //   } catch (Exception e) {
        userimage.setImageDrawable(getResources().getDrawable(R.drawable.emptyuser));
        //    }
    }

//    private void setlocation() {
//        try {
//            locationname = LocationMapActivity.LOCATIONNAME;
//            latlong = LocationMapActivity.LATLONG;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (locationname != null) {
//            Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(latlong);
//            while (m.find()) {
//                String from_lat_lng = m.group(1);
//                String[] gpsVal = from_lat_lng.split(",");
//                lat = Double.parseDouble(gpsVal[0]);
//                lon = Double.parseDouble(gpsVal[1]);
//            }
//            if (VIEW_TYPE.equals(OTHER)) {
//                try {
//                    getPhotos(LocationMapActivity.placeid);//mIntent.getStringExtra(PLACEID));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                /*image set */
//                try {
//                    if (myApplication.getSetboolean()) {
//                        setimgrel.setVisibility(View.VISIBLE);
//                        if (myApplication.getType() == 0) {
//                            setimage.setImageBitmap(myApplication.getSetimage());
//                        } else {
//                            setimage.setImageURI(myApplication.getImageurl());
//                        }
//                    }
//                } catch (Exception e) {
//                }
//                onsetmap();
//            }
//        }
//    }

    private void setids(int cate_id, int sub_cate_id, String sub_name) {
        finial_cate_id = cate_id;
        finial_sub_cate_id = sub_cate_id;
        settext(finial_cate_id, finial_sub_cate_id, sub_name);
    }

    private void onsetmap() {
//        setmap.setVisibility(View.VISIBLE);
    }

    /*Set listeners*/
    private void setListeners() {
        btnback.setOnClickListener(OnClickListener());
        btnpost.setOnClickListener(OnClickListener());
        txtcate.setOnClickListener(OnClickListener());
        txtsubcate.setOnClickListener(OnClickListener());
    }

    /*On click listener */
    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnback == v) {
                    Intent intent = new Intent(PostActivity.this, SubCategoryActivity.class);
                    intent.putExtra(CATE_ID, cate_id);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
                if (btnpost == v) {
                    if (VIEW_TYPE.equals(EVENT)) {
                        eventCreateFragment.createpost();
                    } else if(VIEW_TYPE.equals(SALE)){
                        salesFragment.createpost();
                    } else if(VIEW_TYPE.equals(NEED)){
                        needCreateFragment.createpost();
                    } else if(VIEW_TYPE.equals(REVIEW)){
                        reviewFragment.createpost();
                    } else if(VIEW_TYPE.equals(NEWS)){
                        newsFragment.createpost();
                    } else if(VIEW_TYPE.equals(OTHER)){
                        normalPostFragment.createpost();
                    }
                   /* Intent homeintent = new Intent(PostActivity.this, HomeActivity.class);
                    startActivity(homeintent);
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);*/
                }
                if (txtcate == v) {
                    opendialog(CATE_TYPE, cate_id);
                }
                if (txtsubcate == v) {
                    opendialog(SUB_CATE_TYPE, cate_id);
                }
            }
        };
    }

    public void clearLocation() {
        LocationMapActivity.placeid = null;
        LocationMapActivity.LATLONG = null;
        LocationMapActivity.LOCATIONNAME = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PostActivity.this, SubCategoryActivity.class);
        intent.putExtra(CATE_ID, cate_id);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    /*SET text */
    private void settext(int finial_cate_id, int finial_sub_cate_id, String sub_name) {
        //  categoryModel = ConstantData.getcategorydate().get(finial_cate_id);
        txtcate.setText(getcategory().get(finial_cate_id).catname);
        //  categoryModel = ConstantData.getsubcate().get(finial_sub_cate_id);
        txtsubcate.setText(sub_name);
        VIEW_TYPE = getcategory().get(finial_cate_id).type;
        Log.d(TAG, "settext: VIEWTYPE: "+ VIEW_TYPE);

        if(VIEW_TYPE.equals(NEED)) {
            needCreateFragment = NeedCreateFragment.newInstance(finial_cate_id,finial_sub_cate_id,sub_name,VIEW_TYPE);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.create_post_container, needCreateFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (VIEW_TYPE.equals(EVENT)) {
            eventCreateFragment = EventCreateFragment.newInstance(finial_cate_id,finial_sub_cate_id,sub_name,VIEW_TYPE);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.create_post_container, eventCreateFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (VIEW_TYPE.equals(REVIEW)) {
            reviewFragment = ReviewFragment.newInstance(finial_cate_id,finial_sub_cate_id,sub_name,VIEW_TYPE);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.create_post_container, reviewFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (VIEW_TYPE.equals(OTHER)) {
            normalPostFragment = NormalPostFragment.newInstance(finial_cate_id,finial_sub_cate_id,sub_name,VIEW_TYPE);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.create_post_container, normalPostFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (VIEW_TYPE.equals(SALE)) {
            salesFragment = SaleFragment.newInstance(finial_cate_id,finial_sub_cate_id,sub_name,VIEW_TYPE);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.create_post_container, salesFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (VIEW_TYPE.equals(NEWS)) {
            newsFragment = NewsFragment.newInstance(finial_cate_id,finial_sub_cate_id,sub_name,VIEW_TYPE);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.create_post_container, newsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    /*init */
    private void init() {
        nested_view = (NestedScrollView) $(R.id.nested_view);
        create_post_container = (LinearLayout) $(R.id.create_post_container);
        /*user info*/
        userimage = (CircularImageView) $(R.id.user_image);
        username = (MyTextView) $(R.id.user_name);
        txtcate = (MyTextView) $(R.id.txt_cate);
        txtsubcate = (MyTextView) $(R.id.txt_subcate);
        btnback = (ImageView) $(R.id.btn_back);
        btnpost = (ImageView) $(R.id.btn_post);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearLocation();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*GET PLACE IMAGE AND RATING*/
//    public void getPhotos(final String placeId) {
//        Log.e("aa", "1");
//        final Bitmap[] bitmap = new Bitmap[1];
//        Log.e("aa", "2");
//        final Task<PlacePhotoMetadataResponse> photoMetadataResponse = mGeoDataClient.getPlacePhotos(placeId);// mGeoDataClient.getPlacePhotos(placeId);
//        Log.e("aa", "3");
//        /*RAING PLACE */
//        mGeoDataClient.getPlaceById(placeId).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
//            @Override
//            public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
//                if (task.isSuccessful()) {
//                    PlaceBufferResponse places = task.getResult();
//
//                    Place myPlace = places.get(0);
//                    txtlocationname.setText(myPlace.getName() + "\n" + myPlace.getAddress());
//                    //     setlocation.setVisibility(View.VISIBLE);
//                    //  setlocation.setText(myPlace.getName());
//                    int aa = (int) myPlace.getRating();
//                    if (aa != -1)
//                        locationrating.setRating(myPlace.getRating());
//                    else
//                        locationrating.setVisibility(View.GONE);
//                    int myPlaceType = 1;
//                    List<Integer> types = myPlace.getPlaceTypes();
//                    Field[] fields = Place.class.getDeclaredFields();
//
//                    for (Field field : fields) {
//                        Class<?> type = field.getType();
//
//                        if (type == int.class) {
//                            for (int a = 0; a < types.size(); a++) {
//                                try {
//                                    if (types.get(a) == field.getInt(null)) {
//                                        Log.i("Testing", "onCreate: " + field.getName());
//                                        //     break;
//                                    }
//                                } catch (IllegalAccessException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                    places.release();
//                } else {
//                    Log.e("dd", "Place not found.");
//                }
//            }
//        });
//
//
//        /*PHOTO PLACE */
//        photoMetadataResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoMetadataResponse>() {
//            @Override
//            public void onComplete(@NonNull Task<PlacePhotoMetadataResponse> task) {
//                // Get the list of photos.
//                Log.e("aa", "4");
//                PlacePhotoMetadataResponse photos = task.getResult();
//                Log.e("aa", "5");
//                // Get the PlacePhotoMetadataBuffer (metadata for all of the photos).
//                PlacePhotoMetadataBuffer photoMetadataBuffer = photos.getPhotoMetadata();
//                Log.e("aa", "5");
//                // Get the first photo in the list.
//                if (photoMetadataBuffer.getCount() != 0) {
//                    PlacePhotoMetadata photoMetadata = photoMetadataBuffer.get(0);
//                    // Get the attribution text.
//                    CharSequence attribution = photoMetadata.getAttributions();
//                    // Get a full-size bitmap for the photo.
//                    Task<PlacePhotoResponse> photoResponse = mGeoDataClient.getPhoto(photoMetadata);
//                    photoResponse.addOnCompleteListener(new OnCompleteListener<PlacePhotoResponse>() {
//                        @Override
//                        public void onComplete(@NonNull Task<PlacePhotoResponse> task) {
//                            PlacePhotoResponse photo = task.getResult();
//                            bitmap[0] = photo.getBitmap();
//                            Bitmap bitmap1 = bitmap[0];
//                            locationimage.setImageBitmap(bitmap1);
//                        }
//                    });
//                } else {
//                    locationimage.setVisibility(View.GONE);
//                }
//            }
//        });
//    }


    /*Open dialog category or subcategory*/
    public void opendialog(String type, int cate_id) {
        selectTabDialog = new CategoryListDialog(PostActivity.this, type, getcategory().get(cate_id).catid);
        selectTabDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
        selectTabDialog.setCanceledOnTouchOutside(false);
        selectTabDialog.show();
    }

    /*select category event bus*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventSelectCat getcateid) {
        try {
            selectTabDialog.dismiss();
            if (getcateid.getType().equals(MyActivity.CATE_TYPE)) {
                cateid = getcateid.getPosition();
                opendialog(SUB_CATE_TYPE, cateid);
            } else {
                sub_cate_id = getcateid.getPosition();
                selectTabDialog.dismiss();
                clearLocation();
                setids(cateid, sub_cate_id, getcateid.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}


}
