package com.rajinder.noticeboard.UI.ViewHolder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectSubCat;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectTab;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.databinding.SubcategoryRowItemBinding;
import com.rajinder.noticeboard.models.CategoryModel;
import com.rajinder.noticeboard.models.subCategory.Subcategory;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Rajinder on 4/23/2018.
 */
public class SubCategoryViewHolder extends RecyclerView.ViewHolder {

    private SubcategoryRowItemBinding mBinding;
    private static Context mContext;
    private int SELECT_SUB_CAT = 0;
    private int FILTER_SUB_CAT = 1;

    public SubCategoryViewHolder(SubcategoryRowItemBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static SubCategoryViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        SubcategoryRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.subcategory_row_item, parent, false);
        return new SubCategoryViewHolder(binding);
    }

    public void setData(Subcategory subcategory, int position) {
        bind(subcategory, position);
    }

    public void bind(final Subcategory obj, final int position) {
        mBinding.cateName.setText(obj.subcatname);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.demoimage3);
        requestOptions.error(R.drawable.demoimage3);
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(obj.subcaticon).into(mBinding.cateImage);
      //  mBinding.setVariable(BR.category, obj);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movecategoryMenuPage(null, obj, position);
            }
        });
        mBinding.executePendingBindings();
    }

    private void movecategoryMenuPage(Bundle bundle, Subcategory subcategory, int position) {
        Log.e("position", String.valueOf(position));
        EventBus.getDefault().post(new EventSelectSubCat(subcategory, position));
    }

}
