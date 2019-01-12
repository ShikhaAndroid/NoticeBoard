package com.rajinder.noticeboard.UI.ViewHolder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.databinding.AllcategoryRowItemBinding;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.CategoryModel;

/**
 * Created by Rajinder on 4/23/2018.
 */

public class AllCategoryViewHolder extends RecyclerView.ViewHolder {

    private AllcategoryRowItemBinding mBinding;
    private static Context mContext;

    public AllCategoryViewHolder(AllcategoryRowItemBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static AllCategoryViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        AllcategoryRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.allcategory_row_item, parent, false);
        return new AllCategoryViewHolder(binding);
    }

    public void setData(Category categoryModeldate) {
        bind(categoryModeldate);

    }

    public void bind(final Category obj) {
        mBinding.cateName.setText(obj.catname);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movecategoryMenuPage(null, obj.catname);
            }
        });
        mBinding.executePendingBindings();
    }

    private void movecategoryMenuPage(Bundle bundle, String cate_name) {
        Toast.makeText(mContext, cate_name, Toast.LENGTH_LONG).show();
    }
}
