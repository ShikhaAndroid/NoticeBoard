package com.rajinder.noticeboard.UI.ViewHolder;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.RequestResult;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Info;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.directions.route.AbstractRouting;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.BasicNameValuePair;
import com.koushikdutta.async.http.NameValuePair;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.koushikdutta.ion.builder.Builders;
import com.rajinder.noticeboard.Adapters.ItemAdapter;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectCat;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.Utils;
import com.rajinder.noticeboard.databinding.ItemRowItemBinding;
import com.rajinder.noticeboard.models.DemoModel;
import com.rajinder.noticeboard.models.PostListModel;

import org.greenrobot.eventbus.EventBus;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rajinder on 4/23/2018.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder implements RoutingListener {

    public static final String TAG = "ItemViewHolder";
    private ItemRowItemBinding mBinding;
    private static Context mContext;

    public ItemViewHolder(ItemRowItemBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static ItemViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        ItemRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_row_item, parent, false);
        return new ItemViewHolder(binding);
    }

    public void setData(PostListModel postListModel, int position, ItemAdapter.OnItemClickListener onItemClickListener, String type) {
        bind(postListModel, position, onItemClickListener,type);
    }

    public void bind(final PostListModel obj, final int position, final ItemAdapter.OnItemClickListener onItemClickListener, String type) {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        if (obj.postTitle.equals(""))
            mBinding.cateDis.setText(obj.description);
        else
            mBinding.cateDis.setText(obj.postTitle);

        mBinding.catName.setText(obj.categoryName);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_image_black);
        requestOptions.error(R.drawable.ic_image_black);
        requestOptions.centerInside();
        if(obj.postImage.size()>0)
        Glide.with(mContext).setDefaultRequestOptions(requestOptions).load(obj.postImage.get(0)).into(mBinding.cateImage);
        meterDistanceBetweenPoints(Float.valueOf(String.valueOf(obj.latitude)), Float.valueOf(String.valueOf(MyActivity.lat)), Float.valueOf(String.valueOf(obj.latitude)), Float.valueOf(String.valueOf(MyActivity.lat)));
        mBinding.txtPrice.setText(String.valueOf(obj.priceVaule));
        mBinding.catKm.setText(obj.location);
        mBinding.viewLinear.setBackgroundResource(R.drawable.viewbackground);  //drawable id
        GradientDrawable gd = (GradientDrawable) mBinding.viewLinear.getBackground().getCurrent();
        gd.setColor(color); //set color
        gd.setStroke(1, color, 1, 1);
        if (obj.typePost.equals("review")) {
            mBinding.catKm.setVisibility(View.GONE);
            mBinding.txtPrice.setVisibility(View.GONE);
            mBinding.ratinglayout.setVisibility(View.VISIBLE);
            mBinding.txtFollowing.setVisibility(View.GONE);
            mBinding.txtFollow.setVisibility(View.VISIBLE);
            LayerDrawable stars = (LayerDrawable) mBinding.rating.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.parseColor("#EBA628"), PorterDuff.Mode.SRC_ATOP);
            mBinding.viewLinear.setBackgroundResource(R.drawable.viewbackground);  //drawable id
            GradientDrawable gd1 = (GradientDrawable) mBinding.viewLinear.getBackground().getCurrent();
            gd1.setColor(Color.parseColor("#EBA628")); //set color
            gd1.setStroke(0, Color.parseColor("#EBA628"), 1, 1);
            mBinding.rating.setRating(obj.ratingValue);
            mBinding.totalUser.setText("(" + String.valueOf(obj.totalUser) + ")");
        } else {
            mBinding.txtFollowing.setVisibility(View.GONE);
            mBinding.txtFollow.setVisibility(View.VISIBLE);
            mBinding.ratinglayout.setVisibility(View.GONE);
            mBinding.catKm.setVisibility(View.VISIBLE);
            mBinding.txtPrice.setVisibility(View.VISIBLE);
        }
        if (obj.typePost.equals("Image")) {
            mBinding.mainLayout.setVisibility(View.GONE);
            mBinding.image.setVisibility(View.VISIBLE);
            mBinding.photoTxt.setVisibility(View.VISIBLE);
            mBinding.viewLinear.setVisibility(View.GONE);
        } else {
            mBinding.viewLinear.setVisibility(View.VISIBLE);
            mBinding.photoTxt.setVisibility(View.GONE);
            mBinding.mainLayout.setVisibility(View.VISIBLE);
            mBinding.image.setVisibility(View.GONE);
        }
        //   mBinding.setVariable(BR.category, obj);
        mBinding.txtPrice.setTextColor(color);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(obj, "open");
                // movecategoryMenuPage(null, obj.categoryName, position);
            }
        });
        if (position == 0) {
            mBinding.topLinear.setVisibility(View.VISIBLE);
        } else {
            mBinding.topLinear.setVisibility(View.GONE);
        }
        if (obj.priceVaule == 0)
            mBinding.txtPrice.setVisibility(View.GONE);

        mBinding.executePendingBindings();
    }

    private void movecategoryMenuPage(Bundle bundle, String cate_name, int position) {
        Toast.makeText(mContext, cate_name, Toast.LENGTH_LONG).show();
        EventBus.getDefault().post(new EventSelectCat(position, "item"));
    }

    public void imageset(ImageView image) {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(image, "scaleX", 0.7f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(image, "scaleY", 0.7f);
        scaleDownX.setDuration(2500);
        scaleDownY.setDuration(2500);
        ObjectAnimator moveUpY = ObjectAnimator.ofFloat(image, "translationY", -100);
        moveUpY.setDuration(2500);
        AnimatorSet scaleDown = new AnimatorSet();
        AnimatorSet moveUp = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);
        moveUp.play(moveUpY);
        scaleDown.start();
        moveUp.start();
    }

    private void meterDistanceBetweenPoints(float lat1, float lat2, float lon1, float lon2) {
        LatLng start = new LatLng(lat1, lon1);
        LatLng end = new LatLng(lat2, lon2);
        String apiKey = "AIzaSyDmAsUxodoT14Chjk8R0wvIDIbjFTNL_9c";  //TODO get this from official account..
        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(this)
                .alternativeRoutes(true)
                .waypoints(start, end)
                .key(apiKey)
                .build();
        routing.execute();
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        Log.d(TAG, "onRoutingFailure: "+e.getMessage());
    }

    @Override
    public void onRoutingStart() {}

    @Override
    public void onRoutingSuccess(ArrayList<com.directions.route.Route> arrayList, int i) {
//        mBinding.catKm.setText(arrayList.get(0).getDistanceText());
    }

    @Override
    public void onRoutingCancelled() {
        Log.d(TAG, "onRoutingCancelled: ");
    }

}

