package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.CommentPostViewHolder;
import com.rajinder.noticeboard.UI.ViewHolder.NotificationViewHolder;
import com.rajinder.noticeboard.models.NotificationModel;

import java.util.ArrayList;

/**
 * Created by Rajinder on 4/21/2018.
 */

public class CommentPostAdapter extends RecyclerView.Adapter<CommentPostViewHolder>{

    private int lastPosition=-1;
    private Context context;
    ArrayList<NotificationModel> modelArrayList;

    public CommentPostAdapter(ArrayList<NotificationModel> getnotification) {
        this.modelArrayList=getnotification;
    }


    @Override
    public CommentPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        return CommentPostViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(CommentPostViewHolder holder, int position) {
        setAnimation(holder.itemView,position);
        holder.setData(modelArrayList.get(position));
    }
    @Override
    public int getItemCount() {
        return 4;
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.recycler);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
