package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.gson.Gson;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.CategoryViewHolder;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.CategoryModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajinder on 4/21/2018.
 */

public class CategorysAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private static final String TAG = "CategorysAdapter";
    private int lastPosition = -1;
    private Context context;
    List<Category> categoryModelArrayList;

    public CategorysAdapter(List<Category> getcategorydate) {
        this.categoryModelArrayList = getcategorydate;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return CategoryViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        setAnimation(holder.itemView, position);
        holder.setData(categoryModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryModelArrayList.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
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
