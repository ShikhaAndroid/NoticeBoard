package com.rajinder.noticeboard.Activity.HomeActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rajinder.noticeboard.Adapters.CreatePostImagesAdapter;
import com.rajinder.noticeboard.Adapters.PostImagesListAdapter;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;

import java.util.ArrayList;

public class PostImagesListActivity extends MyActivity {

    private LinearLayout empty_list;
    private ImageView btn_back;
    private RecyclerView post_images_list;
    private PostImagesListAdapter imagesAdapter;
    private ArrayList<String> imagesList;
    private int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_images_list);
        init();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResultBack();
            }
        });
    }

    public void init(){
        imagesList = getIntent().getStringArrayListExtra("images");
        position = getIntent().getIntExtra("position",0);
        empty_list = findViewById(R.id.empty_list);
        post_images_list = findViewById(R.id.post_images_list);
        btn_back = findViewById(R.id.btn_back);
        if(imagesList == null)
            imagesList = new ArrayList<>();
        post_images_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        imagesAdapter = new PostImagesListAdapter(getApplicationContext(), imagesList, itemClickListener());
        post_images_list.setAdapter(imagesAdapter);
        post_images_list.scrollToPosition(position);
        refreshView();
    }

    private CreatePostImagesAdapter.ItemClickListener itemClickListener() {
        return new CreatePostImagesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(view.getId() == R.id.post_img_close ){
                    if(imagesList.size()>= position+1){
                        imagesList.remove(position);
                        imagesAdapter.notifyDataSetChanged();
                    }
                    refreshView();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        setResultBack();
    }

    public void setResultBack(){
        Intent intent = new Intent();
        intent.putStringArrayListExtra("images",imagesList);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void refreshView(){
        if(imagesList.size() <= 0) {
            empty_list.setVisibility(View.VISIBLE);
        } else {
            empty_list.setVisibility(View.GONE);
        }
    }

}
