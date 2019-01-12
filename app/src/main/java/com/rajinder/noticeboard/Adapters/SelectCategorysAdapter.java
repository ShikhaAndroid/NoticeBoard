package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.SelectCategoryViewHolder;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.models.Category.Category;

import java.util.List;

/**
 * Created by Rajinder on 4/21/2018.
 */

public class SelectCategorysAdapter extends RecyclerView.Adapter<SelectCategoryViewHolder>{

    private int lastPosition = -1;
    private Context context;
    private List<Category> categoryList;

    public SelectCategorysAdapter(List<Category> categories) {
        this.categoryList=categories;
    }

    @Override
    public SelectCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        return SelectCategoryViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(SelectCategoryViewHolder holder, int position) {
        setAnimation(holder.itemView,position);
        holder.setData(categoryList.get(position), position, MyActivity.CATE_TYPE);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
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
