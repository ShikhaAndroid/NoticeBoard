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
import com.rajinder.noticeboard.databinding.NotificationRowItemBinding;
import com.rajinder.noticeboard.models.NotificationModel;

/**
 * Created by Rajinder on 5/8/2018.
 */

public class NotificationViewHolder extends RecyclerView.ViewHolder {

    NotificationRowItemBinding mBinding;
    private static Context mContext;

    public NotificationViewHolder(NotificationRowItemBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static NotificationViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        NotificationRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.notification_row_item, parent, false);
        return new NotificationViewHolder(binding);
    }

    public void setData(NotificationModel notificationModel) {
        bind(notificationModel);
    }

    public void bind(final NotificationModel obj) {
        if (obj.getNoti_time().equals("5 Km away")) {
            mBinding.mainLayout.setBackgroundColor(Color.parseColor("#ecf1ef"));
            mBinding.notiImg.setBackgroundColor(Color.parseColor("#ecf1ef"));
        }

        if (obj.getNoti_time().equals("new")) {
            mBinding.mainLayout.setVisibility(View.GONE);
            mBinding.typeLayout.setVisibility(View.VISIBLE);
            mBinding.typeTxt.setText("NEW");

        } else if (obj.getNoti_time().equals("Earlier")) {
            mBinding.typeTxt.setText("EARLIER");
            mBinding.mainLayout.setVisibility(View.GONE);
            mBinding.typeLayout.setVisibility(View.VISIBLE);
        } else {
            mBinding.mainLayout.setVisibility(View.VISIBLE);
            mBinding.typeLayout.setVisibility(View.GONE);
        }

        mBinding.notiImg.setImageResource(obj.getNoti_image());
        mBinding.notiName.setText(obj.getNoti_name());
        mBinding.notiDis.setText(obj.getNoti_dis());
        mBinding.notiTime.setText(obj.getNoti_time());
        //   mBinding.setVariable(BR.category, obj);
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movecategoryMenuPage(null, obj.getNoti_name());
            }
        });
        mBinding.executePendingBindings();
    }

    private void movecategoryMenuPage(Bundle bundle, String cate_name) {
        Toast.makeText(mContext, cate_name, Toast.LENGTH_LONG).show();
    }


}
