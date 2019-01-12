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
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.databinding.CategoryTabRowItemBinding;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.CategoryModel;

import java.util.List;

/**
 * Created by Rajinder on 3/23/2018.
 */
@SuppressLint("ResourceAsColor")
public class CategoryTabViewHolder extends RecyclerView.ViewHolder {

    public static final String TAG = "CategoryTabViewHolder";
    private CategoryTabRowItemBinding mBinding;
    private static Context mContext;

    public CategoryTabViewHolder(CategoryTabRowItemBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static CategoryTabViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        CategoryTabRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.category_tab_row_item, parent, false);
        return new CategoryTabViewHolder(binding);
    }

    public void setData(Category categoryModeldate, int position, String tabtype) {
        bind(categoryModeldate, position, tabtype);
    }

    public void bind(final Category obj, final int position, String tabtype) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.demoimage3);
        requestOptions.error(R.drawable.demoimage3);
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(obj.caticon).into(mBinding.cateImage);
        mBinding.btnSelect.setChecked(obj.catselect);
        if (obj.catselect) {
            mBinding.btnNotification.setChecked(obj.catnotification);
            mBinding.btnNotification.setEnabled(true);
        } else {
            mBinding.btnNotification.setChecked(false);
            mBinding.btnNotification.setEnabled(false);
        }
        if (tabtype.equals(MyActivity.TAB_TYPE_LOGIN)) {
            mBinding.btnNotification.setVisibility(View.GONE);
            mBinding.btnSelect.setVisibility(View.VISIBLE);
        } else {
            mBinding.btnSelect.setVisibility(View.GONE);
            mBinding.btnNotification.setVisibility(View.VISIBLE);
        }

        mBinding.cateName.setText(obj.catname);
        mBinding.cateImage.setBackgroundColor(Color.TRANSPARENT);
       /* if (obj.catselect) {
            mBinding.btnNotification.setChecked(true);
            mBinding.btnSelect.setChecked(true);

        } else {
            mBinding.btnNotification.setChecked(false);
            mBinding.btnSelect.setChecked(false);
        }*/
        mBinding.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!obj.catselect){
                    try {
                        new Update(Category.class)
                                .set("catselect = 1")
                                .where("catid = ?", obj.catid)
                                .execute();
                        obj.catselect = true;
                        new Update(Category.class)
                                .set("catnotification = 1")
                                .where("catid = ?", obj.catid)
                                .execute();
                        obj.catnotification = true;
                        Log.d(TAG, "onClick: "+true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Update(Category.class)
                                .set("catselect = 0")
                                .where("catid = ?", obj.catid)
                                .execute();
                        obj.catselect = false;
                        new Update(Category.class)
                                .set("catnotification = 0")
                                .where("catid = ?", obj.catid)
                                .execute();
                        obj.catnotification = false;
                        Log.d(TAG, "onClick: "+false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//                List<Category> categories = new Select().from(Category.class).where("catid = ?", obj.catid).execute();
//                Log.d(TAG, "onClick: "+obj.catid+">>>>"+categories.size()+",.."+categories.get(0).catselect);
            }
        });

        mBinding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!obj.catnotification){
                    try {
                        if(obj.catselect) {
                            new Update(Category.class)
                                    .set("catnotification = 1")
                                    .where("catid = ?", obj.catid)
                                    .execute();
                            obj.catnotification = true;
                            Log.d(TAG, "onClick: " + true);
                        } else {
                            mBinding.btnNotification.setChecked(false);
                            Toast.makeText(mContext,"Please select this category to Turn On Notifications.",Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new Update(Category.class)
                                .set("catnotification = 0")
                                .where("catid = ?", obj.catid)
                                .execute();
                        obj.catnotification = false;
                        Log.d(TAG, "onClick: "+false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        mBinding.setVariable(BR.category, obj);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movecategoryMenuPage(null, obj.catname, obj, position);
            }
        });
        mBinding.executePendingBindings();

    }

    private void movecategoryMenuPage(Bundle bundle, String cate_name, Category obj, int position) {}

}
