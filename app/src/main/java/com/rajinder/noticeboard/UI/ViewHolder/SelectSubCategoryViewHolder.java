package com.rajinder.noticeboard.UI.ViewHolder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectCat;
import com.rajinder.noticeboard.databinding.AllcategoryRowItemBinding;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.subCategory.Subcategory;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Rajinder on 4/23/2018.
 */

public class SelectSubCategoryViewHolder extends RecyclerView.ViewHolder {

    private AllcategoryRowItemBinding mBinding;
    private static Context mContext;

    public SelectSubCategoryViewHolder(AllcategoryRowItemBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static SelectSubCategoryViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        AllcategoryRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.allcategory_row_item, parent, false);
        return new SelectSubCategoryViewHolder(binding);
    }

    public void setData(Subcategory subcategory, int position, String type) {
        bind(subcategory,position,type);

    }

    public void bind(final Subcategory obj, final int position, final String type) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.demoimage3);
        requestOptions.error(R.drawable.demoimage3);
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(obj.subcaticon).into(mBinding.cateImage);
        mBinding.cateName.setText(obj.subcatname);
        mBinding.catCheck.setVisibility(View.GONE);
        //   mBinding.setVariable(BR.category, obj);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movecategoryMenuPage(null, position,obj.subcatname);
            }
        });
        mBinding.executePendingBindings();
    }

    private void movecategoryMenuPage(Bundle bundle, int cate_id,String type) {
        EventBus.getDefault().post(new EventSelectCat(cate_id,type));


    }
}
