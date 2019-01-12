package com.rajinder.noticeboard.UI.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rajinder.noticeboard.Activity.HomeActivities.FilterActivity;
import com.rajinder.noticeboard.Activity.HomeActivities.FilterSubActivity;
import com.rajinder.noticeboard.Activity.HomeActivities.MainCategoryActivity;
import com.rajinder.noticeboard.Activity.HomeActivities.SubCategoryActivity;
import com.rajinder.noticeboard.MyApplication;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.databinding.TemplateFilterCategoryBinding;
import com.rajinder.noticeboard.models.Category.Category;

/**
 * Created by Rajinder on 3/23/2018.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {

//  private CategoryRowItemBinding mBinding;
    private TemplateFilterCategoryBinding mBinding;
    private static Context mContext;
    public static final String CATE_ID = "cate_id";

    public CategoryViewHolder(TemplateFilterCategoryBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static CategoryViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        TemplateFilterCategoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.template_filter_category, parent, false); //R.layout.category_row_item
        return new CategoryViewHolder(binding);
    }

    public void setData(Category categoryModeldate) {
        bind(categoryModeldate);
    }

    public void bind(final Category obj) {
//          mBinding.cateImage.setImageResource(obj.getCate_image());
//          mBinding.cateName.setText(obj.catname);
//          mBinding.setVariable(BR.category, obj);
        mBinding.catName.setText(obj.catname);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movecategoryMenuPage(null, obj);
            }
        });
        mBinding.executePendingBindings();
    }

    private void movecategoryMenuPage(Bundle bundle, Category category) {
        Toast.makeText(mContext, category.catname, Toast.LENGTH_LONG).show();
//        ((MyApplication) mContext.getApplicationContext()).setFilterCat(category);
        FilterActivity.setFilterCat(category);
        Intent subcateintent = new Intent(mContext, FilterSubActivity.class);
        subcateintent.putExtra(CATE_ID, category.catid);
        mContext.startActivity(subcateintent);
//        overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
//        finish();
    }

}
