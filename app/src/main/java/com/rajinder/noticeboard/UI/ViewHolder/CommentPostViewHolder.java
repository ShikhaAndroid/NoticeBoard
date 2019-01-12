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
import com.rajinder.noticeboard.databinding.CommentRowItemNoBinding;
import com.rajinder.noticeboard.databinding.NotificationRowItemBinding;
import com.rajinder.noticeboard.models.NotificationModel;

/**
 * Created by Rajinder on 5/8/2018.
 */

public class CommentPostViewHolder extends RecyclerView.ViewHolder {

    CommentRowItemNoBinding mBinding;
    private static Context mContext;

    public CommentPostViewHolder(CommentRowItemNoBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static CommentPostViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        CommentRowItemNoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_row_item_no, parent, false);
        return new CommentPostViewHolder(binding);
    }

    public void setData(NotificationModel notificationModel) {
        bind(notificationModel);
    }

    public void bind(final NotificationModel obj) {
             mBinding.mainLayout.setVisibility(View.VISIBLE);
            mBinding.typeLayout.setVisibility(View.GONE);


        mBinding.postImg.setImageResource(obj.getNoti_image());
        mBinding.postName.setText(obj.getNoti_name());
        mBinding.postDis.setText(obj.getNoti_dis());
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
