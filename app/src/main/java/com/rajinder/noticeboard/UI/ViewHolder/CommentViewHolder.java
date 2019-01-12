package com.rajinder.noticeboard.UI.ViewHolder;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventComment;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectCat;
import com.rajinder.noticeboard.databinding.CommentRowItemBinding;
import com.rajinder.noticeboard.models.CommetModel;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rajinder on 5/12/2018.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private CommentRowItemBinding mBinding;
    private static Context mContext;

    public CommentViewHolder(CommentRowItemBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public static CommentViewHolder getHolderInstance(ViewGroup parent) {
        mContext = parent.getContext();
        CommentRowItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_row_item, parent, false);
        return new CommentViewHolder(binding);
    }

    public void setData(CommetModel commetModel, int position) {
        bind(commetModel,position);

    }

    public void bind(final CommetModel obj, final int position) {
        //   mBinding.cateImage.setImageResource(obj.getCate_image());
        //    mBinding.userName.setText(obj.getUser_name());

        mBinding.commentTxt.setText(obj.getComment());
        mBinding.commentTime.setText(printDifference(Calendar.getInstance().getTime(), obj.getComment_time()));
        mBinding.commentReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Reply" + obj.getComment(), Toast.LENGTH_LONG).show();

            }
        });
        mBinding.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "like" + obj.getComment(), Toast.LENGTH_LONG).show();

                mBinding.btnLike.setImageResource(R.drawable.svg_like);
            }
        });

        if(obj.isComment_edit()){
            mBinding.commentEdit.setVisibility(View.VISIBLE);
        }
        else
            mBinding.commentEdit.setVisibility(View.GONE);
        //   mBinding.setVariable(BR.category, obj);
        mBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                opendialog(obj.getComment(),position);
                return false;
            }
        });
            mBinding.executePendingBindings();
    }

    private void movecategoryMenuPage(Bundle bundle, String cate_name) {
        Toast.makeText(mContext, cate_name, Toast.LENGTH_LONG).show();

    }


    public String printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : " + endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;
        String time;
        if (elapsedDays != 0)
            time = String.valueOf(elapsedDays) + "day";
        else if (elapsedHours != 0)
            time = String.valueOf(elapsedHours) + "hr";
        else if (elapsedMinutes != 0)
            time = String.valueOf(elapsedMinutes) + "min";
        else if (elapsedSeconds != 0)
            time = String.valueOf(elapsedSeconds) + "sec";
        else
            time = "0sec";
        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
        try {
            time = time.replace("-", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;//String.valueOf(elapsedDays)+" "+String.valueOf(elapsedHours)+" "+String.valueOf(elapsedMinutes)+" "+String.valueOf(elapsedSeconds);
    }

    public void opendialog(String comment, final int position) {
        final CharSequence[] items = {"Edit","Delete","Cancel"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(comment);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(item==0){
                    Toast.makeText(mContext,"EDIT",Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new EventComment(position,"edit"));
                    builder.show().dismiss();
                }
                else if(item==1)
                {
                    Toast.makeText(mContext,"Delete",Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post(new EventComment(position,"delete"));
                }
                else
                    builder.show().dismiss();

            }
        });
        builder.show();

    }
}
