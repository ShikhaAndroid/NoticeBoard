package com.rajinder.noticeboard.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rajinder.noticeboard.R;

import java.io.File;
import java.util.List;

public class PostImagesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG= "PostImagesListAdapter";
    List<String> mValues;
    Context mContext;
    protected CreatePostImagesAdapter.ItemClickListener mListener;

    public PostImagesListAdapter(Context context, List<String> values, CreatePostImagesAdapter.ItemClickListener itemListener) {
        mValues = values;
        mContext = context;
        mListener=itemListener;
    }

    @Override
    public int getItemViewType(int position) {
       return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.template_post_images_list, parent, false);
        return new PostImagesListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ViewHolder vh = (ViewHolder)holder;
//        File imgFile = new File(mValues.get(position));
//        if (imgFile.exists()) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.demoimage3);
            requestOptions.error(R.drawable.demoimage3);
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(mValues.get(position)).into(vh.imageView);
//            }

        vh.close_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ImageView  close_image;
        public ViewHolder(View v) {
            super(v);
            imageView =  v.findViewById(R.id.post_img);
            close_image = v.findViewById(R.id.post_img_close);
        }
        public void setData() {}

    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
