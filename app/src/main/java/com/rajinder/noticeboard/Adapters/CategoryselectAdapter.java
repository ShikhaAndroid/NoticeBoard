package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.CategoryselectViewHolder;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajinder on 4/21/2018.
 */

public class CategoryselectAdapter extends RecyclerView.Adapter<CategoryselectViewHolder>{

    private int lastPosition = -1;
    private Context context;
    private List<Category> categorytabModels;

    public CategoryselectAdapter(List<Category> tabs) {
        this.categorytabModels = tabs;
    }

    @Override
    public CategoryselectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        return CategoryselectViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(CategoryselectViewHolder holder, int position) {
        setAnimation(holder.itemView,position);
        holder.setData(categorytabModels.get(position),position);
    }

    @Override
    public int getItemCount() {
        return categorytabModels.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.recycler);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public void refreshAdapter(){
        notifyDataSetChanged();
    }

}
