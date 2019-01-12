package com.rajinder.noticeboard.UI.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectCat;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.CategoryModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Rajinder on 4/21/2018.
 */

public class MainCategoryViewHolder extends RecyclerView.ViewHolder {

    private static Context mContext;
    private CircularImageView cat_img;
    private MyTextView cat_name;
    private RelativeLayout relcate;
    String row_index = "", temprow_index = "";

    public MainCategoryViewHolder(View view) {
        super(view);
        cat_img = (CircularImageView) view.findViewById(R.id.cate_image);
        cat_name = (MyTextView) view.findViewById(R.id.cate_name);
        relcate = (RelativeLayout) view.findViewById(R.id.rel_cat);
    }

    public static MainCategoryViewHolder getHolderInstance(ViewGroup parent, int viewtype) {
        mContext = parent.getContext();
        View rowview;
        if (viewtype < 4) {
            rowview = LayoutInflater.from(parent.getContext()).inflate(R.layout.main2_row_item, parent, false);
            return new MainCategoryViewHolder(rowview);
        } else if (viewtype == 4) {
            rowview = LayoutInflater.from(parent.getContext()).inflate(R.layout.main1_row_item, parent, false);
            return new MainCategoryViewHolder(rowview);
        }/* else if (viewtype == 5) {
            rowview = LayoutInflater.from(parent.getContext()).inflate(R.layout.main3_row_item, parent, false);
            return new MainCategoryViewHolder(rowview);
        } else if (viewtype == 6) {
            rowview = LayoutInflater.from(parent.getContext()).inflate(R.layout.main3_row_item, parent, false);
            return new MainCategoryViewHolder(rowview);
        }*/ else {
            rowview = LayoutInflater.from(parent.getContext()).inflate(R.layout.main3_row_item, parent, false);
            return new MainCategoryViewHolder(rowview);
        }

        //   CategoryRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewtype == 0 ? R.layout.main1_row_item : R.layout.main2_row_item, parent, false);
    }

    public void setData(Category categoryModeldate, int position) {
        bind(categoryModeldate, position);
    }

    public void bind(final Category obj, final int position) {
        cat_name.setText(obj.catname);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_demosub);
        requestOptions.error(R.drawable.ic_demosub);
        Glide.with(mContext).setDefaultRequestOptions(requestOptions).load(obj.caticon).into(cat_img);
        relcate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = obj.catname;
                movecategoryMenuPage(null, position);
            }
        });
        //   mBinding.executePendingBindings();
        if (row_index == obj.catname) {
            relcate.setBackgroundColor(Color.parseColor("#FFF4A11B"));
            cat_name.setTextColor(Color.parseColor("#FFFFFF"));
            row_index = "";
            // holder.tv1.setTextColor(Color.parseColor("#ffffff"));
        } else {
            relcate.setBackgroundColor(Color.parseColor("#ffffff"));
            cat_name.setTextColor(Color.parseColor("#000000"));
        }
    }

    private void movecategoryMenuPage(Bundle bundle, int cate_id) {
        relcate.setBackgroundColor(Color.parseColor("#FFF4A11B"));
        cat_name.setTextColor(Color.parseColor("#FFFFFF"));
        EventBus.getDefault().post(new EventSelectCat((cate_id)));
        relcate.setBackgroundColor(Color.parseColor("#ffffff"));
        cat_name.setTextColor(Color.parseColor("#000000"));
    }

}
