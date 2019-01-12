package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.RecentActivityViewHolder;
import com.rajinder.noticeboard.models.DemoModel;

import java.util.ArrayList;

/**
 * Created by Rajinder on 5/10/2018.
 */

public class RecentActviityAdapter extends RecyclerView.Adapter<RecentActivityViewHolder> {

    private int lastPosition = -1;
    private Context context;
    private ArrayList<DemoModel> demoModels;

    public RecentActviityAdapter(ArrayList<DemoModel> demoModels) {
        this.demoModels = demoModels;
    }


    @Override
    public RecentActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return RecentActivityViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(RecentActivityViewHolder holder, int position) {
        setAnimation(holder.itemView, position);
        holder.setData(demoModels.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (demoModels.size() > 3)
            return 3;
        else
            return demoModels.size();
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
}
