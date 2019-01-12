package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.ItemViewHolder;
import com.rajinder.noticeboard.models.PostListModel;

import java.util.List;

/**
 * Created by Rajinder on 4/23/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private int lastPosition = -1;
    private Context context;
    private List<PostListModel> postListModels;
    private  OnItemClickListener onItemClickListener;
    private String type;

    public ItemAdapter(List<PostListModel> postListModels, String type, OnItemClickListener onItemClickListener) {
        this.postListModels=postListModels;
        this.onItemClickListener=onItemClickListener;
        this.type=type;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return ItemViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        setAnimation(holder.itemView, position);
        holder.setData(postListModels.get(position), position,onItemClickListener,type);
    }

    @Override
    public int getItemCount() {
        return postListModels.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.recycler);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        } else{
            lastPosition = position;
        }
        if (position == 0)
            lastPosition = -1;

    }

    public interface OnItemClickListener {
        void onItemClick(PostListModel item, String type_id);
    }

}
// GET http://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&sensor=false
// You have exceeded your daily request quota for this API. If you did not set a custom daily request quota, verify your project has an active billing account: http://g.co/dev/maps-no-account

