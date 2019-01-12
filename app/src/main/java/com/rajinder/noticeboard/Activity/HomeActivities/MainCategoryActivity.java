package com.rajinder.noticeboard.Activity.HomeActivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.rajinder.noticeboard.Adapters.MainCategorysAdapter;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectCat;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.Utils.recycler_decorations.VerticalItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.models.Category.Category;

import java.util.List;

public class MainCategoryActivity extends MyActivity {

    // ActivityMaincategoryBinding categoryBinding;
    RecyclerView recyclerCat;
    ImageView backbtn;
    private MyTextView titlename;
    MainCategorysAdapter adapter;
    private Boolean nextboolean = false;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincategory);
        //  categoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_maincategory);
        init();
        settext();
        setListeners();
        inflatecategoryList();
    }

    private void setListeners() {
        //  nextbtn.setOnClickListener(OnClickListener());
        backbtn.setOnClickListener(OnClickListener());
        mSwipeRefreshLayout.setOnRefreshListener(OnRefreshListener());
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        };
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backbtn == v) {
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    private void inflatecategoryList() {
        categoryList = getcategory();
        adapter = new MainCategorysAdapter(categoryList);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                int aa1 = 0;// = 11;
                int aa = categoryList.get(position).catname.length();
                if (categoryList.size() > position + 1)
                    aa1 = categoryList.get(position + 1).catname.length();
                if (position < 4)
                    return 1;
                else if (position == 5)
                    return 1;
                else if (position == 6)
                    return 1;
                else if (aa < 10) {
                    if (aa1 < 10) {
                        return 1;
                    } else //{
                        return 2;
                    //            }
                } else
                return 2;
            }
        });
        recyclerCat.setLayoutManager(layoutManager);
        recyclerCat.setHasFixedSize(true);
        recyclerCat.addItemDecoration(new VerticalItemDecoration((int) getResources().getDimension(R.dimen.margin_5), false));
        recyclerCat.setAdapter(adapter);
        initdata();
    }

    private void initdata() {
        adapter.setdata();
    }

    private void settext() {
        titlename.setText("Category");
        //   nextbtn.setText("NEXT");
    }

    private void init() {
        recyclerCat = (RecyclerView) $(R.id.recycler_cat);
        backbtn = (ImageView) $(R.id.btn_back);
        titlename = (MyTextView) $(R.id.title_txt);
        //  nextbtn = (MyTextView) findViewById(R.id.clear_btn);
        //  nextbtn.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout = (SwipeRefreshLayout) $(R.id.swifeRefresh);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventSelectCat getcateid) {
//        showLoading();
        try {
            Intent subcateintent = new Intent(MainCategoryActivity.this, SubCategoryActivity.class);
            subcateintent.putExtra(CATE_ID, getcateid.getPosition());
            startActivity(subcateintent);
            overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            hideLoading();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideLoading();
    }

}
