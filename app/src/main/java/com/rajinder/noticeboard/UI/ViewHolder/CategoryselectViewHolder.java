package com.rajinder.noticeboard.UI.ViewHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.rajinder.noticeboard.Adapters.CategoryselectAdapter;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectTab;
import com.rajinder.noticeboard.databinding.CategoryRowItemBinding;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.CategoryModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Rajinder on 3/23/2018.
 */
@SuppressLint("ResourceAsColor")
public class CategoryselectViewHolder extends RecyclerView.ViewHolder {

    public static final String TAG = "CategoryselectView";
    private CategoryRowItemBinding mBinding;
    private static Context mContext;

    public CategoryselectViewHolder(CategoryRowItemBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static CategoryselectViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        CategoryRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.category_row_item, parent, false);
        return new CategoryselectViewHolder(binding);
    }

    public void setData(Category categoryModeldate, int position) {
        bind(categoryModeldate, position);
    }

    public void bind(final Category obj, final int position) {
        // mBinding.cateImage.setImageResource(obj.getCate_image());
        if (obj.catid == -1) {
            mBinding.btnSelect.setVisibility(View.GONE);
        }
        mBinding.cateName.setText(obj.catname);
        mBinding.btnSelect.setChecked(obj.catselect);
        mBinding.cateImage.setBackgroundColor(Color.TRANSPARENT);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.demoimage3);
        requestOptions.error(R.drawable.demoimage3);
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(obj.caticon).into(mBinding.cateImage);
    /*    if (obj.getIsFvt()) {
         //   mBinding.cateName.setTextColor(Color.BLACK);
       //     mBinding.cateImage.setBackgroundColor(R.color.colorAccent);

        } else {
        //    mBinding.cateName.setTextColor(R.color.hintcolor);
        }*/
        //   mBinding.setVariable(BR.category, obj);
        mBinding.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!obj.catselect) {
                    try {
                        new Update(Category.class)
                                .set("catselect = 1")
                                .where("catid = ?", obj.catid)
                                .execute();
                        obj.catselect = true;
                        Log.d(TAG, "onClick: "+true);
                    } catch (Exception e) {
                        Log.d(TAG, "onClick: "+e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Update(Category.class)
                                .set("catselect = 0")
                                .where("catid = ?", obj.catid)
                                .execute();
                        obj.catselect = false;
                        Log.d(TAG, "onClick: "+false);
                    } catch (Exception e) {
                        Log.d(TAG, "onClick: "+e.getMessage());
                        e.printStackTrace();
                    }
                }
//                adapter.refreshAdapter();
//                List<Category> categories = new Select().from(Category.class).where("catid = ?", obj.catid).execute();
//                Log.d(TAG, "onClick: "+obj.catid+">>>>"+categories.size()+",.."+categories.get(0).catselect);
                movecategoryMenuPage(null, obj.catname, obj, position);
            }
        });

        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {}
        });
        mBinding.executePendingBindings();
    }

    private void movecategoryMenuPage(Bundle bundle, String cate_name, Category obj, int position) {

      /*  if (obj.catselect) {
            mBinding.cateImage.setBackgroundColor(Color.TRANSPARENT);
            mBinding.cateName.setTextColor(R.color.hintcolor);
            obj.setCatselect(false);
        } else {
            mBinding.cateName.setTextColor(Color.BLACK);
            mBinding.cateImage.setBackgroundColor(R.color.colorAccent);
            obj.setCatselect(true);
        }*/
        EventBus.getDefault().post(new EventSelectTab(position));
    }

}
