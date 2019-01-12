package com.rajinder.noticeboard.Activity.HomeActivities;

import android.Manifest;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.LocationUtil.GPSTracker;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;

import android.support.v7.widget.LinearLayoutManager;

import com.rajinder.noticeboard.Adapters.AllCategorysAdapter;
import com.rajinder.noticeboard.constants.ConstantData;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SelectCategoryActivity extends MyActivity {

    RecyclerView recyclerCat;
    private LinearLayout changelocation, progresslayout;
    ImageView backbtn, btncurrent;
    private MyTextView titlename, nextbtn, currentlocation;
    AllCategorysAdapter adapter;
    private Boolean nextboolean = false;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    String location;
    GPSTracker gps;
    public double latitude, longitude;
    public Address address;
    public ProgressBar progressBar;
    public boolean currentloc = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcategory);
        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION);
        try {
            askForPermission(Manifest.permission.ACCESS_FINE_LOCATION, GPS_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        currentlocationa();
        init();
        settext();
        setListeners();
        inflatecategoryList();
    }

    public void currentlocationa() {
        if (currentloc) {
        } else {
            gps = new GPSTracker(SelectCategoryActivity.this);
            // create class object
            // check if GPS enabled
            if (gps.canGetLocation()) {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
                try {
                    address = getAddress(latitude, longitude);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                gps.showSettingsAlert();
            }
        }
    }

    public void getlocation() {
        if (!currentloc) {
            gps = new GPSTracker(SelectCategoryActivity.this);
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            try {
                address = getAddress(latitude, longitude);
            } catch (Exception e) {
                e.printStackTrace();
            }
            setaddress();
        }
    }

    private void setListeners() {
        nextbtn.setOnClickListener(OnClickListener());
        backbtn.setOnClickListener(OnClickListener());
        mSwipeRefreshLayout.setOnRefreshListener(OnRefreshListener());
        changelocation.setOnClickListener(OnClickListener());
        btncurrent.setOnClickListener(OnClickListener());
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        };
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nextbtn == v) {
                    if (nextboolean) {
                        //   startActivity(new Intent(MainCategoryActivity.this, SubCategoryActivity.class));
                        Intent subcateintent = new Intent(SelectCategoryActivity.this, SubCategoryActivity.class);
                        startActivity(subcateintent);
                        overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                        //finish();
                    } else {
                        showsnackbar("Please Select a Category", v);
                        //    showToastLong("Please select a category");
                    }
                }
                if (backbtn == v)
                    finish();
                if (changelocation == v) {
                    if (latitude != 0)
                        callPlaceSearchIntent(latitude, longitude);
                }
                if (btncurrent == v) {
                    currentlocationa();
                    settext();
                }
            }
        };
    }

    private void inflatecategoryList() {
        adapter = new AllCategorysAdapter(getcategory());
        LinearLayoutManager layoutManager = new LinearLayoutManager(SelectCategoryActivity.this);
        recyclerCat.setLayoutManager(layoutManager);
        recyclerCat.setHasFixedSize(true);
        recyclerCat.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,0);
        recyclerCat.addItemDecoration(dividerItemDecoration);
        //   categoryBinding.recyclerCat.addItemDecoration(new VerticalItemDecoration((int) getResources().getDimension(R.dimen.margin_5), false));
        recyclerCat.setAdapter(adapter);
        recyclerCat.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }


    private void settext() {
        titlename.setText("Location/Category");
        nextbtn.setText("NEXT");
        setaddress();
    }

    public void setaddress() {

        if (address != null) {
            try {
                location = address.getAddressLine(0);
                currentlocation.setText(location);
                currentlocation.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                progresslayout.setVisibility(View.GONE);
                if (!currentlocation.getText().toString().equals(""))
                    currentloc = true;
                else {
                    Log.e("Adress", "getlocation");
                    getlocation();
                    currentloc = false;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getlocation();
                    currentloc = false;
                }
            }, 2000);
        }

    }

    private void init() {
        currentlocation = (MyTextView) $(R.id.current_location);
        recyclerCat = (RecyclerView) $(R.id.recycler_cat);
        backbtn = (ImageView) $(R.id.btn_back);
        btncurrent = (ImageView) $(R.id.btn_current);
        titlename = (MyTextView) $(R.id.title_txt);
        nextbtn = (MyTextView) $(R.id.clear_btn);
        changelocation = (LinearLayout) $(R.id.change_location);
        mSwipeRefreshLayout = (SwipeRefreshLayout) $(R.id.swifeRefresh);
        progressBar=(ProgressBar)$(R.id.progress);
        progresslayout=(LinearLayout)$(R.id.progress_layout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //autocompleteFragment.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                currentlocation.setText(place.getAddress());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
            } else if (requestCode == RESULT_CANCELED) {

            }
        }
    }


}
