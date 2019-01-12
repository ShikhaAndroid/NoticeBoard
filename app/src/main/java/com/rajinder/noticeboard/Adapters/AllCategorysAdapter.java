package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.AllCategoryViewHolder;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajinder on 4/21/2018.
 */

public class AllCategorysAdapter extends RecyclerView.Adapter<AllCategoryViewHolder> {

    private int lastPosition = -1;
    private Context context;
    private List<Category> categoryModels;

    public AllCategorysAdapter(List<Category> getcategorydate) {
        this.categoryModels = getcategorydate;
    }

    @Override
    public AllCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return AllCategoryViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(AllCategoryViewHolder holder, int position) {
        setAnimation(holder.itemView, position);
        holder.setData(categoryModels.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.recycler);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
