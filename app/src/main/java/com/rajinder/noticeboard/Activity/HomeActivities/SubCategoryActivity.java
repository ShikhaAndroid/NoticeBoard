package com.rajinder.noticeboard.Activity.HomeActivities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rajinder.noticeboard.Adapters.SubCategorysAdapter;
import com.rajinder.noticeboard.Interface.OnSubCategoryAction;
import com.rajinder.noticeboard.Process.SubCateListProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectSubCat;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.databinding.ActivitySubcategoryBinding;
import com.rajinder.noticeboard.models.CategoryModel;
import com.rajinder.noticeboard.models.subCategory.SubCategoryModel;
import com.rajinder.noticeboard.models.subCategory.Subcategory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class SubCategoryActivity extends MyActivity implements OnSubCategoryAction {

    boolean initialSizeObtained = false;
    boolean isShrink = false;
    ImageView backbtn;
    MyTextView titlename;
    LinearLayout titlecatname;
    ActivitySubcategoryBinding categoryBinding;
    int mOriginalHeight;
    public int cate_id;
    //  CategoryModel categoryModel;
    private SubCateListProcess subCateListProcess;
    private List<Subcategory> subcategoryList;
    private LinearLayout emptylist, interneterror;
    private SwipeRefreshLayout swipeRefreshLayout;

    Animation _hideAnimation = new Animation() {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) titlecatname.getLayoutParams();
            params.topMargin = -(int) (mOriginalHeight * interpolatedTime);
            titlecatname.setLayoutParams(params);
        }
    };

    Animation _showAnimation = new Animation() {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) titlecatname.getLayoutParams();
            params.topMargin = (int) (mOriginalHeight * (interpolatedTime - 1));
            titlecatname.setLayoutParams(params);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_subcategory);
        Intent mIntent = getIntent();
        cate_id = mIntent.getIntExtra(CATE_ID, 0);
        Log.e("cat", String.valueOf(cate_id) + "" + "create sub");
        init();
        settext();
        setListeners();
        subcatapi();
    }

    private void subcatapi() {
        showLoading();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.demoimage3);
        requestOptions.error(R.drawable.demoimage3);
        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(getcategory().get(cate_id).caticon).into(categoryBinding.cateImage);
        subCateListProcess.startprocess(String.valueOf(getcategory().get(cate_id).catid), this);
    }

    private void anima() {
        titlecatname.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (initialSizeObtained)
                    return;
                initialSizeObtained = true;
                mOriginalHeight = titlecatname.getMeasuredHeight();
            }
        });
        _hideAnimation.setDuration(2000);
        _showAnimation.setDuration(2000);
    }

    public void ToggleTopBar(View view) {
        isShrink = !isShrink;
        titlecatname.clearAnimation();  //Important
        titlecatname.startAnimation(isShrink ? _hideAnimation : _showAnimation);
    }

    private void setListeners() {
        // clstxt.setOnClickListener(OnClickListener());
        backbtn.setOnClickListener(OnClickListener());
        interneterror.setOnClickListener(OnClickListener());
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backbtn == v) {
                    Intent intent = new Intent(SubCategoryActivity.this, MainCategoryActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
                if(interneterror==v){
                   interneterror.setVisibility(View.GONE);
                   subcatapi();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SubCategoryActivity.this, MainCategoryActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    private void inflatecategoryList() {
        SubCategorysAdapter adapter = new SubCategorysAdapter(subcategoryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SubCategoryActivity.this);
        categoryBinding.recyclerCat.setLayoutManager(layoutManager);
        categoryBinding.recyclerCat.setHasFixedSize(true);
        categoryBinding.recyclerCat.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                1);
        categoryBinding.recyclerCat.addItemDecoration(dividerItemDecoration);
        categoryBinding.recyclerCat.setAdapter(adapter);
        categoryBinding.recyclerCat.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void settext() {
        titlename.setText("Select Sub-Category");
        // clstxt.setText("CANCEL");
        categoryBinding.cateName.setText(getcategory().get(cate_id).catname);
    }

    private void init() {
        swipeRefreshLayout = (SwipeRefreshLayout) $(R.id.swifeRefresh);
        emptylist = (LinearLayout) $(R.id.empty_list);
        interneterror = (LinearLayout) $(R.id.internet);
        subCateListProcess = new SubCateListProcess(this);
        titlecatname = (LinearLayout) $(R.id.titlecatname);
        backbtn = (ImageView) $(R.id.btn_back);
        titlename = (MyTextView) $(R.id.title_txt);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventSelectSubCat getsubcateid) {
//        showLoading();
        try {
            Intent subcateintent = new Intent(SubCategoryActivity.this, PostActivity.class);
            subcateintent.putExtra(CATE_ID, cate_id);
            subcateintent.putExtra(SUB_CAT_NAME, subcategoryList.get(getsubcateid.getPosition()).subcatname);
            subcateintent.putExtra(SUB_CATE_ID, getsubcateid.getPosition());
            startActivity(subcateintent);
            overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
            finish();

        } catch (Exception e) {
            hideLoading();
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideLoading();
    }

    @Override
    public void onFinishSubCateActions(List<SubCategoryModel> subCategoryModels, String reponse) {
        subcategoryList = subCategoryModels.get(0).getSubcategories();
        if (subcategoryList.size() != 0) {
            inflatecategoryList();
            emptylist.setVisibility(View.GONE);
            interneterror.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
        } else {
            swipeRefreshLayout.setVisibility(View.GONE);
            emptylist.setVisibility(View.VISIBLE);
            interneterror.setVisibility(View.GONE);
        }
        hideLoading();
    }

    @Override
    public void onErroSubCaterAction(String error) {
        hideLoading();
        if (error.equals("internet")) {
            swipeRefreshLayout.setVisibility(View.GONE);
            interneterror.setVisibility(View.VISIBLE);
        } else {
            swipeRefreshLayout.setVisibility(View.GONE);
            emptylist.setVisibility(View.VISIBLE);
            interneterror.setVisibility(View.GONE);
        }
    }

}
