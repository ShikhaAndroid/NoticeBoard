package com.rajinder.noticeboard.UI.ViewHolder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectCat;
import com.rajinder.noticeboard.databinding.AllcategoryRowItemBinding;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.CategoryModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Rajinder on 4/23/2018.
 */

public class SelectCategoryViewHolder extends RecyclerView.ViewHolder {

    private AllcategoryRowItemBinding mBinding;
    private static Context mContext;

    public SelectCategoryViewHolder(AllcategoryRowItemBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static SelectCategoryViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        AllcategoryRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.allcategory_row_item, parent, false);
        return new SelectCategoryViewHolder(binding);
    }

    public void setData(Category categoryModeldate, int position, String type) {
        bind(categoryModeldate,position,type);
    }

    public void bind(final Category obj, final int position, final String type) {
        //   mBinding.cateImage.setImageResource(obj.getCate_image());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.demoimage3);
        requestOptions.error(R.drawable.demoimage3);
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(obj.caticon).into(mBinding.cateImage);
        mBinding.cateName.setText(obj.catname);
        mBinding.catCheck.setVisibility(View.GONE);
        //   mBinding.setVariable(BR.category, obj);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movecategoryMenuPage(null, position,type);
            }
        });
        mBinding.executePendingBindings();
    }

    private void movecategoryMenuPage(Bundle bundle, int cate_id,String type) {
        EventBus.getDefault().post(new EventSelectCat(cate_id,type));
    }

}
