package com.rajinder.noticeboard.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rajinder.noticeboard.Activity.HomeActivities.LocationMapActivity;
import com.rajinder.noticeboard.Activity.HomeActivities.PostActivity;
import com.rajinder.noticeboard.Interface.CreateEventFragmentListener;
import com.rajinder.noticeboard.Interface.OnPostAction;
import com.rajinder.noticeboard.MyApplication;
import com.rajinder.noticeboard.Process.PostCreateProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyEditText;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.models.PostCreateModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NeedCreateFragment extends BaseFragmentApp implements OnPostAction {

    public static final String TAG = "NeedCreateFragment";
    private static final String CAT_ID = "cate_id";
    private static final String SUB_CAT_ID = "sub_cate_id";
    private static final String SUB_CAT_NAME = "sub_cat_name";
    private static final String VIEW_TYPE = "view_type";

    private int catId;
    private int subCatId;
    private String subCatName;
    private String viewType;
    private double lat, lon;
    private String locationname;

    private Context mcontext;
    private CreateEventFragmentListener mListener;
    public MyApplication myApplication;
    private PostCreateProcess postCreateProcess;

    private MyTextView locationneed;
    MapView mMapView;
    private GoogleMap googleMap;
    private MyEditText editneed;

    public NeedCreateFragment() {
        // Required empty public constructor
    }

    public static NeedCreateFragment newInstance(int catId, int subCatId, String subCatName, String viewType) {
        NeedCreateFragment fragment = new NeedCreateFragment();
        Bundle args = new Bundle();
        args.putInt(CAT_ID, catId);
        args.putInt(SUB_CAT_ID, subCatId);
        args.putString(SUB_CAT_NAME, subCatName);
        args.putString(VIEW_TYPE, viewType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            catId = getArguments().getInt(CAT_ID);
            subCatId = getArguments().getInt(SUB_CAT_ID);
            subCatName = getArguments().getString(SUB_CAT_NAME);
            viewType = getArguments().getString(VIEW_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_need_create, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view, savedInstanceState);
        setListeners();
    }

    private void init(View view, Bundle savedInstanceState) {
        myApplication = (MyApplication) mcontext.getApplicationContext();
        /*API*/
        postCreateProcess = new PostCreateProcess(this);

        locationneed = (MyTextView) view.findViewById(R.id.location_need);
        mMapView = (MapView) view.findViewById(R.id.mapView);
        editneed = (MyEditText) view.findViewById(R.id.edit_need);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
            }
        });

    }

    private void setListeners() {
        locationneed.setOnClickListener(OnClickListener());
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationneed == v) {
                    Intent locationintent = new Intent(mcontext, LocationMapActivity.class);
                    locationintent.putExtra(CAT_ID, catId);
                    locationintent.putExtra(SUB_CAT_NAME, subCatName);
                    locationintent.putExtra(SUB_CAT_ID, subCatId);
                    //  VIEW_TYPE = NEED;
                    startActivity(locationintent);
                    getActivity().overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
                }
            }
        };
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateEventFragmentListener) {
            mListener = (CreateEventFragmentListener) context;
            this.mcontext = context;
        } else {
            throw new RuntimeException(context.toString() + " must implement CreateEventFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        setlocation();
    }

    private void setlocation() {
        try {
            String latlong = null;
            try {
                locationname = LocationMapActivity.LOCATIONNAME;
                latlong = LocationMapActivity.LATLONG;

            } catch (Exception e) {
                e.printStackTrace();
            }
            if (locationname != null) {
                Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(latlong);
                while (m.find()) {
                    String from_lat_lng = m.group(1);
                    String[] gpsVal = from_lat_lng.split(",");
                    lat = Double.parseDouble(gpsVal[0]);
                    lon = Double.parseDouble(gpsVal[1]);
                }
                addMarker();
                locationneed.setTextColor(getResources().getColor(R.color.colorblack));
                locationneed.setText(locationname);

            }
        } catch (Exception e){
            Log.d(TAG, "setlocation: "+e.getMessage());
        }
    }

    @Override
    public void onFinishPostActions(List<PostCreateModel> postCreateModels, String reponse) {
        try {
            hideLoading();
            if (postCreateModels.get(0).success) {
                showToastLong(postCreateModels.get(0).message);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            }
        }catch (Exception e){
            Log.d(TAG, "onFinishPostActions: "+e.getMessage());
        }
    }

    @Override
    public void onErrorPostAction(String error) {
        hideLoading();
    }

    public void createpost() {
        try {
            Calendar calendar = Calendar.getInstance();
            Date currentdate = calendar.getTime();
            SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy");
            SimpleDateFormat tf = new SimpleDateFormat("hh:mm aa");

            String detailtxt = editneed.getText().toString();
            String startdatetxt = df.format(currentdate);
            String starttimetxt = tf.format(currentdate);
            String locationtxt = locationneed.getText().toString();
            String lats = String.valueOf(lat);
            String longs = String.valueOf(lon);

            if (detailtxt.equals("") || detailtxt.equals(null)) {
                showToastLong("Please enter the Post Text");
            } else if (locationtxt.equals("") || locationtxt.equals("Location") || locationtxt.equals(null)) {
                showToastLong("Please enter the event location");
            } else {
                showLoading();
                List<String> imagesList = new ArrayList<>();
                postCreateProcess.startprocess(imagesList, String.valueOf(getcategory().get(catId).catid), String.valueOf(subCatId), locationtxt, Double.valueOf(lats), Double.valueOf(longs), "", detailtxt,
                        starttimetxt, "", startdatetxt, "", viewType, 0.0, 0.0, getuserinfo().get(0).userid, mcontext);
            }
        }catch (Exception e){
            Log.d(TAG, "createpost: "+e.getMessage());
        }
    }

    public void addMarker() {
        try {
            if (googleMap != null) {
                // For dropping a marker at a point on the Map
                LatLng loc = new LatLng(lat, lon);
                googleMap.addMarker(new MarkerOptions().position(loc).title(locationname).snippet("Marker Description"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(loc).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        }catch (Exception e){
            Log.d(TAG, "addMarker: "+e.getMessage());
        }
    }

}
