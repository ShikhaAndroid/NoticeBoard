package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.SubCategoryViewHolder;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.models.CategoryModel;
import com.rajinder.noticeboard.models.subCategory.Subcategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajinder on 4/21/2018.
 */

public class SubCategorysAdapter extends RecyclerView.Adapter<SubCategoryViewHolder> {

    private int lastPosition = -1;
    private Context context;
    private  List<Subcategory> subcate;

    public SubCategorysAdapter(List<Subcategory> getsubcate) {
        this.subcate=getsubcate;
    }

    @Override
    public SubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return SubCategoryViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(SubCategoryViewHolder holder, int position) {
        setAnimation(holder.itemView, position);
        holder.setData(subcate.get(position),position);
    }

    @Override
    public int getItemCount() {
        return subcate.size();
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
    }

}
