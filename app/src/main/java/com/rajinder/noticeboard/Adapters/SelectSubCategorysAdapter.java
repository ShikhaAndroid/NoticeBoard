package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.SelectCategoryViewHolder;
import com.rajinder.noticeboard.UI.ViewHolder.SelectSubCategoryViewHolder;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.models.subCategory.SubCategoryModel;
import com.rajinder.noticeboard.models.subCategory.Subcategory;

import java.util.List;

/**
 * Created by Rajinder on 4/21/2018.
 */

public class SelectSubCategorysAdapter extends RecyclerView.Adapter<SelectSubCategoryViewHolder>{

    private int lastPosition=-1;
    private Context context;
private List<Subcategory> subCategoryModels;
    public SelectSubCategorysAdapter(List<Subcategory> subcategories) {
        this.subCategoryModels=subcategories;
    }


    @Override
    public SelectSubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        return SelectSubCategoryViewHolder.getHolderInstance(parent);
    }

    @Override
    public void onBindViewHolder(SelectSubCategoryViewHolder holder, int position) {
        setAnimation(holder.itemView,position);
        holder.setData(subCategoryModels.get(position),position, MyActivity.SUB_CATE_TYPE);
    }
    @Override
    public int getItemCount() {
        return subCategoryModels.size();
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
