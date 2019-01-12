package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rajinder.noticeboard.Fragments.SpannedGridLayoutManager;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.ScaleImageView;

import java.io.File;
import java.util.List;

public class CreatePostImagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG= "CreatePostImagesAdapter";
    public static final int FirstPosition = 0;
    public static final int SecondPosition = 1;
    public static final int OtherPosition = 2;
    public static final int GradientPosition = 3;

    List<String> mValues;
    Context mContext;
    protected ItemClickListener mListener;

    public CreatePostImagesAdapter(Context context, List<String> values, ItemClickListener itemListener) {
        mValues = values;
        mContext = context;
        mListener=itemListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 )  //&&  mValues.size() <= 2
            return FirstPosition;
        /*else if(position == 1 && mValues.size() == 2)
            return OtherPosition;*/
        else if(position == 3 && mValues.size() > 4)   //else if(position == 2 && mValues.size() > 3)
            return GradientPosition;
        else
            return OtherPosition;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FirstPosition) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.template_singleimage, parent, false);
            return new ViewHolder(view);
        } else if (viewType == GradientPosition) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.template_post_gradientimg, parent, false);
            return new GradientViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.template_createpost_image, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ViewHolder) {
            ViewHolder vh = (ViewHolder)holder;
//            File imgFile = new File(mValues.get(position));
//            if (imgFile.exists()) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.ic_demosub);
                requestOptions.error(R.drawable.ic_demosub);   //demoimage3
                Glide.with(mContext)
                        .setDefaultRequestOptions(requestOptions)
                        .load(mValues.get(position)).into(vh.imageView);
//            }
            vh.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(v, position);
                    }
                }
            });
        } else if(holder instanceof GradientViewHolder) {
            GradientViewHolder gh = (GradientViewHolder)holder;
//            File imgFile = new File(mValues.get(position));
//            if (imgFile.exists()) {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.ic_demosub);
                requestOptions.error(R.drawable.ic_demosub);
                Glide.with(mContext)
                        .setDefaultRequestOptions(requestOptions)
                        .load(mValues.get(position)).into(gh.imageView);
//            }

            gh.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(v, position);
                    }
                }
            });
            int count = mValues.size() - 4; // 3
            gh.imgCount.setText("+"+count);
        }
    }

    @Override
    public int getItemCount() {
        if(mValues.size() <= 4)  // 3
            return mValues.size();
        else
            return 4;  // 3
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            imageView =  v.findViewById(R.id.post_img);
        }
    }

    public class GradientViewHolder extends RecyclerView.ViewHolder  {
        public ImageView imageView;
        public ImageView  gradient_img;
        public TextView imgCount;
        public GradientViewHolder(View v) {
            super(v);
            imageView =  v.findViewById(R.id.post_img);
            gradient_img = v.findViewById(R.id.gradient_img);
            imgCount = v.findViewById(R.id.img_count);
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}