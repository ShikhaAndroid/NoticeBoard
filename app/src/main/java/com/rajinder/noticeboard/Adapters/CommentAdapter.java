package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.CommentViewHolder;
import com.rajinder.noticeboard.models.CommetModel;

import java.util.ArrayList;

/**
 * Created by Rajinder on 5/12/2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder>{

    private int lastPosition=-1;
    private Context context;
    private ArrayList<CommetModel> commetModels;
    public CommentAdapter(ArrayList<CommetModel> commetModels) {
        this.commetModels=commetModels;
    }


    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        return CommentViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        setAnimation(holder.itemView,position);
        holder.setData(commetModels.get(position),position);
    }
    @Override
    public int getItemCount() {
        return commetModels.size();
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

    public void setnotifydata() {
        notifyItemInserted(commetModels.size()-1);
        notifyDataSetChanged();


    }
}
