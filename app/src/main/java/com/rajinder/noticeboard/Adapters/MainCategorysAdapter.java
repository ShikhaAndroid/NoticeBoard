package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.ViewHolder.MainCategoryViewHolder;
import com.rajinder.noticeboard.models.Category.Category;

import java.util.List;

/**
 * Created by Rajinder on 4/21/2018.
 */

public class MainCategorysAdapter extends RecyclerView.Adapter<MainCategoryViewHolder>  {

    private int lastPosition = -1;
    private Context context;
    private List<Category> categoryList;

    public MainCategorysAdapter(List<Category> categoryList) {
        this.categoryList=categoryList;
    }

    @Override
    public MainCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return MainCategoryViewHolder.getHolderInstance(parent, viewType);
    }

    @Override
    public void onBindViewHolder(MainCategoryViewHolder holder, int position) {
        setAnimation(holder.itemView, position);
        holder.setData(categoryList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 4)
            return 3;
        else if (position == 4)
            return 4;
        else if (position == 5)
            return 5;
        else if (position == 6)
            return 6;
        else
            return 7;
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.recycler);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
        else {
            lastPosition=position;
        }
    }

    public void setdata() {
        notifyDataSetChanged();
    }

}
