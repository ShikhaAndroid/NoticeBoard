package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.CategoryTabViewHolder;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajinder on 5/15/2018.
 */

public class CategoryTabAdapter extends RecyclerView.Adapter<CategoryTabViewHolder> {

    private int lastPosition=-1;
    private Context context;
    private String tabtype;
    private List<Category> categorytabModels;

    public CategoryTabAdapter(List<Category> tabs, String tabType) {
        this.categorytabModels=tabs;
        this.tabtype=tabType;
    }

    @Override
    public CategoryTabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        return CategoryTabViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(CategoryTabViewHolder holder, int position) {
        setAnimation(holder.itemView,position);
        holder.setData(categorytabModels.get(position),position,tabtype);
    }

    @Override
    public int getItemCount() {
        return categorytabModels.size();
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
