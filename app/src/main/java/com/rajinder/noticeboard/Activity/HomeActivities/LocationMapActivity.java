package com.rajinder.noticeboard.Activity.HomeActivities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoMetadataBuffer;
import com.google.android.gms.location.places.PlacePhotoMetadataResponse;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.LocationUtil.GPSTracker;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;

public class LocationMapActivity extends MyActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {

    GPSTracker gps;
    public double latitude, longitude;
    public Address address;
    public MyTextView txtlocation, btndone;
    GoogleMap googleMap;
    public ImageView btnback, btncurrent;
    LatLng clicklatlong;
    public static String placeid;
    public Bitmap bm = null;
    public static String LOCATIONNAME,LATLONG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map);
        /*get intent */

        init();
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setonclicklister();
    }

    private void setonclicklister() {
        txtlocation.setOnClickListener(OnClickListener());
        btnback.setOnClickListener(OnClickListener());
        btncurrent.setOnClickListener(OnClickListener());
        btndone.setOnClickListener(OnClickListener());
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtlocation == v)
                    callPlaceSearchIntent(latitude, longitude);
                if (btnback == v) {
                    //    Intent postintent=new Intent(LocationMapActivity.this,PostActivity.class);
                    //    postintent.putExtra(CATE_ID,cate_id);
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
                if (btncurrent == v) {
                    if(googleMap != null) {
                        googleMap.clear();
                        currentlocation(googleMap);
                        if (address != null) {
                            txtlocation.setText(address.getAddressLine(0) + "," + address.getAdminArea());
                            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                            clicklatlong = latLng;
                        }
                    }
                }
                if (btndone == v) {
                    if (!txtlocation.getText().toString().equals("")) {
                       /* Intent postintent = new Intent(LocationMapActivity.this, PostActivity.class);
                        Intent mIntent = getIntent();
                        postintent.putExtra(CATE_ID, mIntent.getIntExtra(CATE_ID, 0));
                        postintent.putExtra(SUB_CAT_NAME, mIntent.getStringExtra(SUB_CAT_NAME));
                        postintent.putExtra(SUB_CATE_ID, mIntent.getIntExtra(SUB_CATE_ID, 0));
                        postintent.putExtra(LOCATION_NAME, txtlocation.getText().toString());
                        postintent.putExtra(LATLONG, String.valueOf(clicklatlong));
                        postintent.putExtra(PLACEID, placeid);
                        startActivity(postintent);*/

                        LOCATIONNAME=txtlocation.getText().toString();
                        LATLONG=String.valueOf(clicklatlong);
                        placeid=placeid;
                        finish();
                        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                    } else {
                        showToastLong("PLEASE ENTER THE LOCATION NAME");
                    }
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    private void init() {
        txtlocation = (MyTextView) $(R.id.txt_location);
        btnback = (ImageView) $(R.id.btn_back);
        btncurrent = (ImageView) $(R.id.btn_current);
        btndone = (MyTextView) $(R.id.btn_done);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Some buildings have indoor maps. Center the camera over
        // the building, and a floor picker will automatically appear.
        googleMap = map;
        currentlocation(map);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setOnMapClickListener(this);
        map.setOnMarkerClickListener(this);
    }

    public void currentlocation(GoogleMap map) {
        // create class object
        gps = new GPSTracker(LocationMapActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            try {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 18));
                LatLng current = new LatLng(latitude, longitude);
                map.addMarker(new MarkerOptions().position(current));

                try {
                    address = getAddress(latitude, longitude);
                    //     txtlocation.setText(address.getAddressLine(0)+","+address.getAdminArea());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            gps.showSettingsAlert();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //autocompleteFragment.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                try {
                    Log.e("place", place.getId());
                    googleMap.clear();
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                            new LatLng(place.getLatLng().latitude, place.getLatLng().longitude), 18));
                    LatLng current = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                    googleMap.addMarker(new MarkerOptions().position(current));
                    try {
                        placeid = place.getId();
                        txtlocation.setText(place.getAddress());
                        clicklatlong=current;



                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
            } else if (requestCode == RESULT_CANCELED) {

            }
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
      /*  address = getAddress(latLng.latitude, latLng.longitude);
        googleMap.clear();
        LatLng current = new LatLng(latLng.latitude, latLng.longitude);
        Log.e("url",address.getUrl()+" " +address.toString());
        clicklatlong = current;
        googleMap.addMarker(new MarkerOptions().position(current));
        txtlocation.setText(address.getAddressLine(0) + "," + address.getAdminArea());
*/
        /*postintent.putExtra(PLACEID,placeid);*/
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        showToastLong(marker.getId().toString());
        return false;
    }

}