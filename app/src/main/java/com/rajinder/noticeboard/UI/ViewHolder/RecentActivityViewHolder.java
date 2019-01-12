package com.rajinder.noticeboard.UI.ViewHolder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectCat;
import com.rajinder.noticeboard.databinding.RecentItemRowBinding;
import com.rajinder.noticeboard.models.DemoModel;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

/**
 * Created by Rajinder on 5/10/2018.
 */

public class RecentActivityViewHolder extends RecyclerView.ViewHolder {

    private RecentItemRowBinding mBinding;
    private static Context mContext;

    public RecentActivityViewHolder(RecentItemRowBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static RecentActivityViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        RecentItemRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recent_item_row, parent, false);
        return new RecentActivityViewHolder(binding);
    }

    public void setData(DemoModel categoryModeldate, int position) {
        bind(categoryModeldate, position);

    }

    public void bind(final DemoModel obj, final int position) {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

     //   mBinding.userImage.setImageResource(obj.getCate_image());
        //  imageset(mBinding.cateImage);
        //  mBinding.cateName.setText(obj.getCate_name());
       // mBinding.userDis.setText(obj.getCate_disc());
      //  mBinding.userName.setText(obj.getCate_name());

        //   mBinding.setVariable(BR.category, obj);

        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movecategoryMenuPage(null, obj.getCate_name(), position);
            }
        });


        mBinding.executePendingBindings();
    }

    private void movecategoryMenuPage(Bundle bundle, String cate_name, int position) {
        Toast.makeText(mContext, cate_name, Toast.LENGTH_LONG).show();
        EventBus.getDefault().post(new EventSelectCat((position)));
    }


}
