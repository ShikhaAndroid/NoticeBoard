package com.rajinder.noticeboard.Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rajinder.noticeboard.Activity.HomeActivities.LocationMapActivity;
import com.rajinder.noticeboard.Activity.HomeActivities.PostImagesListActivity;
import com.rajinder.noticeboard.Adapters.CreatePostImagesAdapter;
import com.rajinder.noticeboard.Interface.CreateEventFragmentListener;
import com.rajinder.noticeboard.Interface.OnPostAction;
import com.rajinder.noticeboard.Interface.SpannedGridInterface;
import com.rajinder.noticeboard.MyApplication;
import com.rajinder.noticeboard.Process.ImageFilePath;
import com.rajinder.noticeboard.Process.PostCreateProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyEditText;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.models.PostCreateModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewFragment extends BaseFragmentApp implements OnPostAction {

    public static final String TAG = "ReviewFragment";
    private static final String CAT_ID = "cate_id";
    private static final String SUB_CAT_ID = "sub_cate_id";
    private static final String SUB_CAT_NAME = "sub_cat_name";
    private static final String VIEW_TYPE = "view_type";

    private int catId;
    private int subCatId;
    private String subCatName;
    private String viewType;
    private double lat, lon;

    private Context mcontext;
    private CreateEventFragmentListener mListener;
    public MyApplication myApplication;
    private PostCreateProcess postCreateProcess;

    private MyEditText edtpost;
    private RecyclerView post_images_rv;
    private CreatePostImagesAdapter imagesAdapter;
    private ArrayList<String> imagesList;
    private RatingBar ratingreview;
    private MyTextView addimage, btnlocation;

    /*pick image from gallery or camera*/
    public Bitmap bm = null;
    public String realPath = null;
    public boolean setbitmap = false;

    public ReviewFragment() {
        // Required empty public constructor
    }

    public static ReviewFragment newInstance(int catId, int subCatId, String subCatName, String viewType) {
        ReviewFragment fragment = new ReviewFragment();
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
        return inflater.inflate(R.layout.fragment_review, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init( view);
        setListeners();
    }

    private void init(View view) {
        myApplication = (MyApplication) mcontext.getApplicationContext();
        /*API*/
        postCreateProcess = new PostCreateProcess(this);

        edtpost =  view.findViewById(R.id.edt_post);
        ratingreview = (RatingBar) view.findViewById(R.id.rating_review);
        addimage = (MyTextView) view.findViewById(R.id.add_image);
        btnlocation = (MyTextView) view.findViewById(R.id.btn_location);
        post_images_rv = (RecyclerView) view.findViewById(R.id.post_images_rv);
        /*imagesAdapter*/
        post_images_rv.setLayoutManager(new GridLayoutManager(mcontext, 1));
        imagesList = new ArrayList<>();
        imagesAdapter = new CreatePostImagesAdapter(mcontext, imagesList, itemClickListener());
        post_images_rv.setAdapter(imagesAdapter);
    }

    private void setListeners() {
        btnlocation.setOnClickListener(OnClickListener());
        addimage.setOnClickListener(OnClickListener());
    }

    private CreatePostImagesAdapter.ItemClickListener itemClickListener() {
        return new CreatePostImagesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent in = new Intent(mcontext, PostImagesListActivity.class);
                in.putStringArrayListExtra("images",imagesList);
                in.putExtra("position",position);
                startActivityForResult(in,IMAGES);
            }
        };
    }

    private SpannedGridInterface onCellSizeCalculate() {
        return new SpannedGridInterface() {
            @Override
            public void onCalculateWindowSize(int cellHeight, int cellWidth) {
                if (imagesList.size() <= 2){
                    RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) post_images_rv.getLayoutParams();
                    param.height = cellHeight;
                    post_images_rv.setLayoutParams(param);}
                else if (imagesList.size() >= 3){
                    RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) post_images_rv.getLayoutParams();
                    param.height = cellHeight*2;
                    post_images_rv.setLayoutParams(param);
                }
            }
        };
    }

    private void setCells(){
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) post_images_rv.getLayoutParams();
        param.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        post_images_rv.setLayoutParams(param);
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnlocation == v) {
                    Intent locationintent = new Intent(getActivity(), LocationMapActivity.class);
                    locationintent.putExtra(CAT_ID, catId);
                    locationintent.putExtra(SUB_CAT_NAME, subCatId);
                    locationintent.putExtra(SUB_CAT_NAME, subCatName);
                    startActivity(locationintent);
                    getActivity().overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);

                } else if (addimage == v) {
                    selectImage( mcontext,viewType);
                }
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
            else if(requestCode == IMAGES){
                ArrayList<String> list1 = data.getStringArrayListExtra("images");
                imagesList.clear();
                imagesList.addAll(list1);
                refreshImgAdapter();
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        try {
            // get multiple selected images here...
            if (data != null) {
                // Get the Image from data
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                if (data.getData() != null) {
                    Uri mImageUri = data.getData();
                    realPath = ImageFilePath.getPath(mcontext, mImageUri);

                    // if size is greater
                    if(!validateImageSize(realPath)) {
//                      Bitmap bmp = BitmapFactory.decodeFile(realPath);
//                      File f = compressAndSaveImage(bmp, 50);
                        File f = compressImage(realPath);
                        Log.d(TAG, "onCaptureImageResult:" + f.getAbsolutePath() + ", size: " + f.length()/1024);
                        imagesList.add(f.getAbsolutePath());
                    } else {
                        imagesList.add(realPath);
                    }

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            String path = ImageFilePath.getPath(mcontext, uri);

                            if(!validateImageSize(path)) {
//                                Bitmap bmp = BitmapFactory.decodeFile(path);
//                                File f = compressAndSaveImage(bmp, 50);
                                File f = compressImage(path);
                                Log.d(TAG, "onCaptureImageResult:" + f.getAbsolutePath() + ", size: " + f.length() / 1024);
                                imagesList.add(f.getAbsolutePath());
                            } else {
                                imagesList.add(path);
                            }
                        }
                    }
                }
             } else {
                    Toast.makeText(mcontext, "You haven't picked Image", Toast.LENGTH_LONG).show();
             }
                refreshImgAdapter();

        } catch (Exception e){
            Log.d(TAG, "onSelectFromGalleryResult: "+e.getMessage());
        }
    }

    private void onCaptureImageResult(Intent data) {
        try {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bytes);
            // write to file..
            File f = null;
            try {
                f = createImageFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
                long length = f.length() / 1024; // Size in KB
                fo.close();
                Log.d(TAG, "onCaptureImageResult:"+f.getAbsolutePath()+ ", size: " +length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imagesList.add(f.getAbsolutePath());
            refreshImgAdapter();

        } catch (Exception e){
            Log.d(TAG, "onCaptureImageResult: "+e.getMessage());
        }
    }

    private void refreshImgAdapter(){
        try {
            addimage.setVisibility(View.VISIBLE);
            if (imagesList.size() <= 1) {
                post_images_rv.setLayoutManager(new GridLayoutManager(mcontext, 1));
                setCells();
            } else if (imagesList.size() == 2) {
                post_images_rv.setLayoutManager(new GridLayoutManager(mcontext, 2));
                setCells();
            } else if (imagesList.size() >= 3) {
                post_images_rv.setLayoutManager(new SpannedGridLayoutManager(
                        new SpannedGridLayoutManager.GridSpanLookup() {
                            @Override
                            public SpannedGridLayoutManager.SpanInfo getSpanInfo(int position) {
                                if (position == 0) {
                                    return new SpannedGridLayoutManager.SpanInfo(2, 2);
                                } else {
                                    return new SpannedGridLayoutManager.SpanInfo(1, 1);
                                }
                            }
                        },
                        3 /* Three columns */,
                        1f /* We want our items to be 1:1 ratio */,onCellSizeCalculate()));
                if (imagesList.size() >= 10) {
                    if (imagesList.size() > 10)
                        Toast.makeText(mcontext,"You can not select more than 10 images.",Toast.LENGTH_SHORT).show();
                    imagesList.subList(10, imagesList.size()).clear();
                    addimage.setVisibility(View.GONE);
                }
            } else if (imagesList.size() > 3) {
                post_images_rv.setLayoutManager(new SpannedGridLayoutManager(
                        new SpannedGridLayoutManager.GridSpanLookup() {
                            @Override
                            public SpannedGridLayoutManager.SpanInfo getSpanInfo(int position) {
                                if (position == 0) {
                                    return new SpannedGridLayoutManager.SpanInfo(2, 3);
                                } else {
                                    return new SpannedGridLayoutManager.SpanInfo(1, 1);
                                }
                            }
                        },
                        3 /* Three columns */,
                        1f /* We want our items to be 1:1 ratio */));
                if (imagesList.size() >= 10) {
                    addimage.setVisibility(View.GONE);
                }
            }
            imagesAdapter.notifyDataSetChanged();

        }catch (Exception e){
            Log.d(TAG, "refreshImgAdapter: "+e.getMessage());
        }
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
    public void onResume() {
        super.onResume();
        setlocation();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setlocation() {
        try {
            String locationname = null, latlong = null;
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
                btnlocation.setTextColor(getResources().getColor(R.color.colorblack));
                btnlocation.setText(locationname);
            }
        } catch (Exception e){
            Log.d(TAG, "setlocation: "+e.getMessage());
        }
    }

    public void createpost() {
        try {
            Calendar calendar = Calendar.getInstance();
            Date currentdate = calendar.getTime();
            SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy");
            SimpleDateFormat tf = new SimpleDateFormat("hh:mm aa");

            String detailtxt = edtpost.getText().toString();
            String startdatetxt = df.format(currentdate);
            String locationtxt = btnlocation.getText().toString();            String starttimetxt = tf.format(currentdate);

            String lats = String.valueOf(lat);
            String longs = String.valueOf(lon);
            String ratingtxt = String.valueOf(ratingreview.getRating());

            if (detailtxt.equals("") || detailtxt.equals(null)) {
                showToastLong("Please enter the Post Text");
            } else if (locationtxt.equals("") || locationtxt.equals(null)) {
                showToastLong("Please enter the event location");
            } else if (ratingtxt.equals("") || ratingtxt.equals(null) || ratingtxt.equals("0.0")) {
                showToastLong("Please give rating before proceed");
            } else if (imagesList.size() <=0 ) {
                showToastLong("Please add pictures before proceed");
            } else {
                showLoading();
                postCreateProcess.startprocess(imagesList, String.valueOf(getcategory().get(catId).catid), String.valueOf(subCatId), locationtxt, Double.valueOf(lats), Double.valueOf(longs), "", detailtxt,
                        starttimetxt, "", startdatetxt, "", viewType, 0.0, Double.valueOf(ratingtxt), getuserinfo().get(0).userid, mcontext);
            }
        } catch (Exception e){
            Log.d(TAG, "createpost: "+e.getMessage());
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
        } catch (Exception e){
            Log.d(TAG, "onFinishPostActions: "+e.getMessage());
        }
    }

    @Override
    public void onErrorPostAction(String error) {
        hideLoading();
    }

}
