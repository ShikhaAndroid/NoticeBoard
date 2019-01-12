package com.rajinder.noticeboard.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rajinder.noticeboard.Activity.HomeActivities.PostImagesListActivity;
import com.rajinder.noticeboard.Fragments.SpannedGridLayoutManager;
import com.rajinder.noticeboard.Interface.OnLoginAction;
import com.rajinder.noticeboard.Interface.SpannedGridInterface;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.NetUtils.Json;
import com.rajinder.noticeboard.Utils.UrlSigner;
import com.rajinder.noticeboard.models.PostListModel;
import com.rajinder.noticeboard.models.UserInfo.UserModel;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AroundMeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "AroundMeAdapter";
    public static final int EVENT = 0;
    public static final int OTHER = 1;
    public static final int NEED = 2;
    public static final int SALE = 3;
    public static final int NEWS = 4;
    public static final int REVIEW = 5;
    public static final int ONLY_TEXT = 6;
    public static final int MAP = 7;

    private int lastPosition = -1;
    private static final int IMAGES = 2;

    private Context context;
    private Activity mActivity;
    private List<PostListModel> postListModels;
    private ItemAdapter.OnItemClickListener onItemClickListener;
    private Typeface roboto_Regular, roboto_Bold, roboto_Medium, roboto_Thin, roboto_Light, roboto_Black, roboto_Condensed_Regular, roboto_Condensed_Light;

    public AroundMeAdapter(Context mContext, List<PostListModel> postListModels, String type, ItemAdapter.OnItemClickListener onItemClickListener) {
        this.context = mContext;
        this.mActivity = (Activity) mContext;
        this.postListModels = postListModels;
        this.onItemClickListener = onItemClickListener;
//        this.type=type;
        roboto_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Regular.ttf");
        roboto_Bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Bold.ttf");
        roboto_Thin = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Thin.ttf");
        roboto_Medium = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Medium.ttf");
        roboto_Black = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Black.ttf");
        roboto_Light = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto_Light.ttf");
        roboto_Condensed_Regular = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed_Regular.ttf");
        roboto_Condensed_Light = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed_Light.ttf");
    }

    @Override
    public int getItemCount() {
        return postListModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 2 || position == 6 || position == 10 || position == 15 || position == 20 )
            return MAP;
        switch (postListModels.get(position).typePost) {
            case "event":
                if (postListModels.get(position).postImage.size() == 0)
                    return ONLY_TEXT;
                else
                    return EVENT;
            case "other":
                if (postListModels.get(position).postImage.size() == 0)
                    return ONLY_TEXT;
                else
                    return OTHER;
            case "need":
                return MAP;
            case "sale":
                if (postListModels.get(position).postImage.size() == 0)
                    return ONLY_TEXT;
                else
                    return SALE;
            case "news":
                if (postListModels.get(position).postImage.size() == 0)
                    return ONLY_TEXT;
                else
                    return NEWS;
            case "review":
                if (postListModels.get(position).postImage.size() == 0)
                    return ONLY_TEXT;
                else
                    return REVIEW;
            default:
                return ONLY_TEXT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case SALE: case EVENT: case NEWS: case REVIEW: case OTHER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_images_around, parent, false);
                return new AroundMeImagesHolder(view);
//            case EVENT:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_images_around, parent, false);
//                return new AroundMeImagesHolder(view);
//            case NEWS:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_images_around, parent, false);
//                return new AroundMeImagesHolder(view);
//            case REVIEW:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_images_around, parent, false);
//                return new AroundMeImagesHolder(view);
//            case OTHER:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_images_around, parent, false);
//                return new AroundMeImagesHolder(view);
            case ONLY_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_text_around, parent, false);
                return new AroundMeTextHolder(view);
            case MAP:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_map_around, parent, false);
                return new AroundMeMapHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
//        setAnimation(holder.itemView, position);
        if (holder instanceof AroundMeImagesHolder) {
            AroundMeImagesHolder viewHolder = (AroundMeImagesHolder) holder;
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

//            viewHolder.title.setText(postListModels.get(position).postTitle);
//            viewHolder.description.setText(postListModels.get(position).description);
//            viewHolder.size.setText(postListModels.get(position).getPostImage().size()+"");
//            viewHolder.cat_subCat.setText(postListModels.get(position).getCategoryName());
            viewHolder.setData(postListModels.get(position).getPostImage());
            GradientDrawable gd = (GradientDrawable) viewHolder.view_linear.getBackground().getCurrent();
            gd.setColor(color); //set color
            gd.setStroke(1, color, 1, 1);

            viewHolder.block1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(postListModels.get(position), "open");
                }
            });

        } else if (holder instanceof AroundMeTextHolder) {
            AroundMeTextHolder viewHolder = (AroundMeTextHolder) holder;
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

            viewHolder.title.setText(postListModels.get(position).postTitle);
            viewHolder.description.setText(postListModels.get(position).description);
            viewHolder.size.setText(postListModels.get(position).postId.toString());
            viewHolder.cat_subCat.setText(postListModels.get(position).getCategoryName());

            GradientDrawable gd = (GradientDrawable) viewHolder.view_linear.getBackground().getCurrent();
            gd.setColor(color); //set color
            gd.setStroke(1, color, 1, 1);
            viewHolder.block1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(postListModels.get(position), "open");
                }
            });

        } else if (holder instanceof AroundMeMapHolder) {
            final AroundMeMapHolder viewHolder = (AroundMeMapHolder) holder;
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            GradientDrawable gd = (GradientDrawable) viewHolder.view_linear.getBackground().getCurrent();
            gd.setColor(color); //set color
            gd.setStroke(1, color, 1, 1);

            viewHolder.addMarker(postListModels.get(position).latitude, postListModels.get(position).longitude);
            viewHolder.block1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(postListModels.get(position), "open");
                }
            });
        }
    }

    public class AroundMeTextHolder extends RecyclerView.ViewHolder {

        public TextView title, cat_subCat, description, size, cat_km, txt_follow;
        public LinearLayout view_linear, block1;

        public AroundMeTextHolder(View itemView) {
            super(itemView);
            view_linear = itemView.findViewById(R.id.view_linear);
            block1 = itemView.findViewById(R.id.block1);
            title = itemView.findViewById(R.id.title);
            cat_subCat = itemView.findViewById(R.id.cat_subCat);
            description = itemView.findViewById(R.id.description);
            size = itemView.findViewById(R.id.size);
            cat_km = itemView.findViewById(R.id.cat_km);
            txt_follow = itemView.findViewById(R.id.txt_follow);

            title.setTypeface(roboto_Bold);
            cat_subCat.setTypeface(roboto_Regular);
            description.setTypeface(roboto_Regular);
            cat_km.setTypeface(roboto_Regular);
            txt_follow.setTypeface(roboto_Regular);
        }

    }

    public class AroundMeMapHolder extends RecyclerView.ViewHolder implements OnLoginAction {

        public TextView title, cat_subCat, description, location_txt, cat_km, txt_follow;
        public LinearLayout view_linear, block1;
        public ImageView map_img;
        public MapView mMapView;
        public GoogleMap googleMap;
        public String url = "https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318&markers=color:red%7Clabel:C%7C40.718217,-73.998284&key=AIzaSyAtjDMmxkV_0Brj1JtpU5x84xwyeG55YuY&signature=mUlVSKYErPK-S1fVpmUCC9Crofc=";

        public AroundMeMapHolder(View itemView) {
            super(itemView);
            view_linear = itemView.findViewById(R.id.view_linear);
            block1 = itemView.findViewById(R.id.block1);
            title = itemView.findViewById(R.id.title);
            cat_subCat = itemView.findViewById(R.id.cat_subCat);
            description = itemView.findViewById(R.id.description);
            location_txt = itemView.findViewById(R.id.location_txt);
            map_img = itemView.findViewById(R.id.map_img);
            cat_km = itemView.findViewById(R.id.cat_km);
            txt_follow = itemView.findViewById(R.id.txt_follow);

            mMapView = (MapView) itemView.findViewById(R.id.mapView);
            mMapView.onCreate(null);
            mMapView.onResume();

            try {
                MapsInitializer.initialize(context);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    googleMap = mMap;
                    googleMap.getUiSettings().setScrollGesturesEnabled(false);
                    notifyDataSetChanged();
//                    addMarker(40.702147,-74.015794);
                }
            });

            title.setTypeface(roboto_Bold);
            cat_subCat.setTypeface(roboto_Regular);
            description.setTypeface(roboto_Regular);
            location_txt.setTypeface(roboto_Regular);
            cat_km.setTypeface(roboto_Regular);
            txt_follow.setTypeface(roboto_Regular);

//            StaticMapApiProcess process = new StaticMapApiProcess(this);
//            process.startprocess(context);
//            new DownloadFilesTask().execute();
        }

        public void addMarker(double lat, double lon) {
            try {
                if (googleMap != null) {
                    Log.d(TAG, "addMarker: latlong");
                    // For dropping a marker at a point on the Map
                    LatLng loc = new LatLng(lat, lon);
                    googleMap.addMarker(new MarkerOptions().position(loc).title("Location Name").snippet("Marker Description"));

                    // For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(loc).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    notifyDataSetChanged();
                }
            } catch (Exception e) {
                Log.d(TAG, "addMarker: "+e.getMessage());
            }
        }

        @Override
        public void onFinishLoginActions(List<UserModel> userModels, String reponse) {}

        @Override
        public void onErrorLoginAction(String error) {}

        private class DownloadFilesTask extends AsyncTask<Void, Integer, Bitmap> {

            private static final String URL_STATIC_MAP = "https://maps.googleapis.com/maps/api/staticmap";

            protected Bitmap doInBackground(Void... params) {
                Json data = Json.object();
                /*data.set("center", "Brooklyn+Bridge,New+York,NY");
                data.set("zoom", "13");
                data.set("size", "600x300");
//                data.set("format", "jpg");
                data.set("maptype", "roadmap");
                data.set("markers", "color:blue%7Clabel:S%7C40.702147,-74.015794");
                data.set("markers", "color:green%7Clabel:G%7C40.711614,-74.012318");
                data.set("markers", "color:red%7Clabel:C%7C40.718217,-73.998284");
                data.set("key", "AIzaSyAtjDMmxkV_0Brj1JtpU5x84xwyeG55YuY");*/

                try {
                    data.set("center", URLEncoder.encode("Brooklyn Bridge,New York,NY","UTF-8"));
                    data.set("zoom", URLEncoder.encode("13","UTF-8"));
                    data.set("size", URLEncoder.encode("600x300","UTF-8"));
//                data.set("format", "jpg");
                    data.set("maptype", URLEncoder.encode("roadmap","UTF-8"));
                    data.set("markers", URLEncoder.encode("color:blue|label:S|40.702147,-74.015794","UTF-8"));
                    data.set("markers", URLEncoder.encode("color:green|label:G|40.711614,-74.012318","UTF-8"));
                    data.set("markers", URLEncoder.encode("color:red|label:C|40.718217,-73.998284","UTF-8"));
                    data.set("key", "AIzaSyAtjDMmxkV_0Brj1JtpU5x84xwyeG55YuY");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String signedUrl = "";
                try {
                    UrlSigner signer = new UrlSigner("bKb9bEKv0-T17tmFjsL53szP9_w=");
                    String abc = getUrlWithData(URL_STATIC_MAP, data);
                    URL url = new URL(abc);
                    try {
                         signedUrl = signer.signRequest(url.getPath(),url.getQuery());
                         signedUrl = signedUrl.replaceFirst("/maps/api/staticmap",URL_STATIC_MAP);

                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Bitmap bmp = null;
                HttpClient httpclient = new DefaultHttpClient();
                HttpGet request = new HttpGet(signedUrl);

                InputStream in = null;
                try {
                    HttpResponse response = httpclient.execute(request);
                    in = response.getEntity().getContent();
                    bmp = BitmapFactory.decodeStream(in);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bmp;
            }

            protected void onProgressUpdate(Integer...  progress) {}

            protected void onPostExecute(Bitmap bmp) {
                map_img.setImageBitmap(bmp);
                notifyDataSetChanged();
            }

            private String getUrlWithData(String url, Json data) {
                if (data != null && data.isObject()) {
                    boolean first = true;
                    for (Map.Entry<String, Json> uno : data.asJsonMap().entrySet()) {
                        if (first) {
                            url += "?";
                            first = false;
                        } else {
                            url += "&";
                        }
                        if (uno.getValue().isArray()) {
                            String keyArr = uno.getKey() + "[]";
                            for (Json dos : uno.getValue().asJsonList()) {
                                url += (Uri.encode(keyArr) + "=" + Uri.encode(dos.asString()));
                            }
                        } else {
                            url += (Uri.encode(uno.getKey()) + "=" + Uri.encode(uno.getValue().asString()));
                        }
                    }
                }
                return url;
            }
        }
    }

    public class AroundMeImagesHolder extends RecyclerView.ViewHolder {

        public TextView title, cat_subCat, description, size, cat_km, txt_follow;
        public LinearLayout view_linear, block1;
        public RecyclerView images_grid;
        public ArrayList<String> imagesList;
        public CreatePostImagesAdapter imagesAdapter;

        public AroundMeImagesHolder(View itemView) {
            super(itemView);
            images_grid = itemView.findViewById(R.id.images_grid);
            block1 = itemView.findViewById(R.id.block1);
            view_linear = itemView.findViewById(R.id.view_linear);
            title = itemView.findViewById(R.id.title);
            cat_subCat = itemView.findViewById(R.id.cat_subCat);
            description = itemView.findViewById(R.id.description);
            size = itemView.findViewById(R.id.size);
            cat_km = itemView.findViewById(R.id.cat_km);
            txt_follow = itemView.findViewById(R.id.txt_follow);

            title.setTypeface(roboto_Bold);
            cat_subCat.setTypeface(roboto_Regular);
            description.setTypeface(roboto_Regular);
            cat_km.setTypeface(roboto_Regular);
            txt_follow.setTypeface(roboto_Regular);

            init();
        }

        public void init () {
            /*imagesAdapter*/
//            images_grid.setLayoutManager(new GridLayoutManager(context, 1));
            imagesList = new ArrayList<>();
            imagesAdapter = new CreatePostImagesAdapter(context, imagesList, itemClickListener());
            images_grid.setAdapter(imagesAdapter);
            refreshImgAdapter();
        }

        public void setData(List<String> imgsList) {
            imagesList.clear();
            imagesList.addAll(imgsList);
            refreshImgAdapter();
        }

        private void refreshImgAdapter() {
            try {
                if(imagesList.size() <= 1) {
                    images_grid.setLayoutManager(new GridLayoutManager(context, 1));
                    setCells();
                } else if(imagesList.size() == 2) {
//                    images_grid.setLayoutManager(new GridLayoutManager(context, 2));
//                    setCells();
                    images_grid.setLayoutManager(new SpannedGridLayoutManager(
                            new SpannedGridLayoutManager.GridSpanLookup() {
                                @Override
                                public SpannedGridLayoutManager.SpanInfo getSpanInfo(int position) {
                                    return new SpannedGridLayoutManager.SpanInfo(2, 2);
                                }
                            },
                            4  /*Three columns*/ ,
                            1f /* We want our items to be 1:1 ratio*/ , onCellSizeCalculate()));
                } else if(imagesList.size() == 3) {  // >= 3
                    images_grid.setLayoutManager(new SpannedGridLayoutManager(
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
                            3  /*Three columns*/ ,
                            1f  /*We want our items to be 1:1 ratio*/ ,onCellSizeCalculate()));

                } else if(imagesList.size() > 3) {
                    images_grid.setLayoutManager(new SpannedGridLayoutManager (
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
                            1f /* We want our items to be 1:1 ratio*/ ,onCellSizeCalculate()));
                }
                imagesAdapter.notifyDataSetChanged();
            } catch (Exception e){
                Log.e(TAG, "refreshImgAdapter: "+e.getMessage());
            }
        }

        private SpannedGridInterface onCellSizeCalculate() {
            return new SpannedGridInterface() {
                @Override
                public void onCalculateWindowSize(int cellHeight, int cellWidth) {
                    if (imagesList.size() <= 3) {
                        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) images_grid.getLayoutParams();
                        param.height = cellHeight * 2;
                        images_grid.setLayoutParams(param);
                    } else {
                        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) images_grid.getLayoutParams();
                        param.height = cellHeight * 3;
                        images_grid.setLayoutParams(param);
                    }
                }
            };
        }

        private void setCells() {
            RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) images_grid.getLayoutParams();
            param.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            images_grid.setLayoutParams(param);
        }

        private CreatePostImagesAdapter.ItemClickListener itemClickListener() {
            return new CreatePostImagesAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent in = new Intent(context, PostImagesListActivity.class);
                    in.putStringArrayListExtra("images",imagesList);
                    in.putExtra("position",position);
                    mActivity.startActivityForResult(in,IMAGES);
                }
            };
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.recycler);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        } else {
            lastPosition = position;
        }
        if (position == 0)
            lastPosition = -1;
    }

    public interface OnItemClickListener {
        void onItemClick(PostListModel item, String type_id);
    }

}
