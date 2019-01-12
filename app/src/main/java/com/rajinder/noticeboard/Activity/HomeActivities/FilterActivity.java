package com.rajinder.noticeboard.Activity.HomeActivities;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rajinder.noticeboard.Adapters.CategorysAdapter;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.databinding.ActivityFilterBinding;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.subCategory.Subcategory;
import com.warkiz.widget.IndicatorSeekBar;

public class FilterActivity extends MyActivity {

    public static final String TAG = "FilterActivity";
    ActivityFilterBinding filterBinding;
    private ImageView backbtn;
    private MyTextView titlename, cleartxt;
    private TextView filterData;
    public String arr[] = {"Auto", "5", "15", "50", "100", "300", "Other"};
    private int kms, currentProgress;

    public static Category filterCat;
    private static Subcategory filterSubCat;
    public static void setFilterCat(Category filterCat) {
        FilterActivity.filterCat = filterCat;
    }
    public static void setFilterSubCat(Subcategory filterSubCat) {
        FilterActivity.filterSubCat = filterSubCat;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filterBinding = DataBindingUtil.setContentView(this, R.layout.activity_filter);
        init();
        setListeners();
        settext();
        inflatecategoryList();
    }

    //(int spanCount, int spacing, boolean includeEdge)
    //(int value, boolean verticalOrientation)
    private void inflatecategoryList() {
        CategorysAdapter adapter = new CategorysAdapter(getcategory());
        // GridLayoutManager layoutManager = new GridLayoutManager(FilterActivity.this,calculateNoOfColumns(this));
        // filterBinding.recyclerCat.setLayoutManager(layoutManager);
        filterBinding.recyclerCat.setHasFixedSize(true);
        filterBinding.recyclerCat.setLayoutManager(new GridLayoutManager(this,4));
        //   filterBinding.recyclerCat.addItemDecoration(new GridSpacingItemDecoration((int) getResources().getDimension(R.dimen.top_margin_small), (int) getResources().getDimension(R.dimen.top_margin_small), false));
        filterBinding.recyclerCat.setAdapter(adapter);
    }

    private void setListeners() {
        backbtn.setOnClickListener(OnClickListener());
        cleartxt.setOnClickListener(OnClickListener());
        filterBinding.applybtn.setOnClickListener(OnClickListener());
        filterBinding.seekKm.setOnSeekChangeListener(OnSeekChangeListener());
    }

    private IndicatorSeekBar.OnSeekBarChangeListener OnSeekChangeListener() {
        return new IndicatorSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
                //    aroundmekm= Integer.parseInt(arr[seekBar.getThumbPosOnTick()].toString());

                currentProgress = progress;
                if (progress == 0 /*|| seekBar.getThumbPosOnTick() == 0*/) {
                    filterBinding.kmTxt.setText("Discover ads near you (Kms)");
                    kms = 3;
                } else if (progress == 100 /*|| seekBar.getThumbPosOnTick() == 6*/) {
                    filterBinding.kmTxt.setText("Discover ads within 300+ Kms");
                    kms = 300;
                } else if ((progress > 0 && progress < 100) /*|| (seekBar.getThumbPosOnTick() > 0 && seekBar.getThumbPosOnTick() < 6)*/) {
//                    kms = Integer.valueOf(arr[seekBar.getThumbPosOnTick()].toString());
                    switch (progress){
                        case 17:
                            kms = 5;
                            break;
                        case 33:
                            kms = 15;
                            break;
                        case 50:
                            kms = 50;
                            break;
                        case 67:
                            kms = 100;
                            break;
                        case 83:
                            kms = 300;
                            break;
                    }
                    filterBinding.kmTxt.setText("Discover ads within " +/* arr[seekBar.getThumbPosOnTick()].toString()*/ kms + " Kms");
                }
                cleartxt.setTextColor(Color.BLACK);
            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String tickBelowText, boolean fromUserTouch) {
                // aroundmekm= Integer.parseInt(arr[seekBar.getThumbPosOnTick()].toString());
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {}

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {}

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
                if (filterBinding.applybtn == v) {
                    myApplication.setProgress(currentProgress);
                    myApplication.setFilterCat(filterCat);
                    myApplication.setFiterSubCat(filterSubCat);
                    if (kms != 0) {
                        myApplication.setAroundmekm(kms);
                    } else {
                        myApplication.setAroundmekm(3);
                    }
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
                if (cleartxt == v) {
                    filterBinding.seekKm.setProgress(0);
                    myApplication.clearFilterData();
                    refreshFilterData();
                }
            }
        };
    }

    private void settext() {
        filterBinding.seekKm.setProgress(myApplication.getProgress());  // myApplication.getAroundmekm()
        titlename.setText(getString(R.string.filter));
    }

    private void init() {
        backbtn = (ImageView) $(R.id.btn_back);
        titlename = (MyTextView) $(R.id.title_txt);
        cleartxt = (MyTextView) $(R.id.clear_btn);
        filterData = (TextView) $(R.id.filter_data);
        cleartxt.setVisibility(View.VISIBLE);
        if (myApplication.getFilterCat() != null)
            filterCat = myApplication.getFilterCat();
        if (myApplication.getFiterSubCat() != null)
            filterSubCat = myApplication.getFiterSubCat();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshFilterData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        filterCat = null;
        filterSubCat = null;
    }

    public void refreshFilterData() {
        if (myApplication.getFilterCat() != null) {
            if (myApplication.getFiterSubCat() != null) {
                filterBinding.filterData.setText(myApplication.getFilterCat().catname+ " > " +myApplication.getFiterSubCat().subcatname);
            } else {
                filterBinding.filterData.setText(myApplication.getFilterCat().catname);
            }
            filterBinding.filterData.setVisibility(View.VISIBLE);
            filterBinding.recyclerCat.setVisibility(View.GONE);
        } else if (filterCat != null) {
            if (filterSubCat != null) {
                filterBinding.filterData.setText(filterCat.catname+ " > " +filterSubCat.subcatname);
            } else {
                filterBinding.filterData.setText(filterCat.catname);
            }
            filterBinding.filterData.setVisibility(View.VISIBLE);
            filterBinding.recyclerCat.setVisibility(View.GONE);
        } else {
            filterBinding.filterData.setText("");
            filterBinding.filterData.setVisibility(View.GONE);
            filterBinding.recyclerCat.setVisibility(View.VISIBLE);
        }
    }

}
