package com.rajinder.noticeboard.Activity.HomeActivities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.activeandroid.query.Update;
import com.rajinder.noticeboard.Adapters.CategoryTabAdapter;
import com.rajinder.noticeboard.Interface.OnCategoryAction;
import com.rajinder.noticeboard.Process.CateListProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.databinding.ActivityTabcategoryBinding;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.Category.CategoryModel;
import java.util.List;

public class TabCategoryActivity extends MyActivity implements OnCategoryAction {

    public static final String TAG = "TabCategoryActivity";
    ImageView backbtn;
    MyTextView titlename, btndone;
    CheckBox selectAll;
    LinearLayout titlecatname;
    ActivityTabcategoryBinding tabcategoryBinding;
    public String type, aftertype = "noti";
    private CateListProcess cateListProcess;
    private List<Category> categoryList;
    CategoryTabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabcategoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_tabcategory);
        Intent mIntent = getIntent();
        type = mIntent.getStringExtra("TYPE");

        init();
        settext();
        setListeners();
        //inflatecategoryList(type);
        if (getcategory().size() != 0) {
            categoryList = getcategory();
            inflatecategoryList(type);
        } else {
            callapi();
        }
    }

    private void callapi() {
        showLoading();
        if (userid > 0)
            cateListProcess.startprocess(String.valueOf(userid), this);
        else if (getsocialinfo() != null && getsocialinfo().size() > 0)
            cateListProcess.startprocess(getsocialinfo().get(0).getIds(), this);
    }

    private void setListeners() {
        backbtn.setOnClickListener(OnClickListener());
        btndone.setOnClickListener(OnClickListener());
        selectAll.setOnClickListener(OnClickListener());
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backbtn == v) {
                    if (aftertype.equals("login")) {
                        btndone.setVisibility(View.VISIBLE);
                        backbtn.setVisibility(View.GONE);
                        titlename.setText("Select Categories");
                        btndone.setText("NEXT");
                        selectAll.setVisibility(View.VISIBLE);
                        inflatecategoryList(TAB_TYPE_LOGIN);
                        type = TAB_TYPE_LOGIN;
                    } else {
                        finish();
                        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                    }
                }
                if (btndone == v) {
                    showLoading();
                    if (type.equals(TAB_TYPE_LOGIN)) {
                        type = TAB_TYPE_NOTIFICATION;
                        inflatecategoryList(TAB_TYPE_NOTIFICATION);
                        hideLoading();
                        btndone.setText("DONE");
                        titlename.setText("Custom Notification");
                        backbtn.setVisibility(View.VISIBLE);
                        selectAll.setVisibility(View.GONE);
                    } else {
                        startActivity(new Intent(TabCategoryActivity.this, HomeActivity.class));
                        finish();
                    }
                }
                if (selectAll == v) {
                    if (selectAll.isChecked()) {
                        new Update(Category.class)
                                .set("catselect = 1")
                                .execute();
                        new Update(Category.class)
                                .set("catnotification = 1")
                                .execute();
                        for (Category cat : categoryList) {
                            cat.catselect = true;
                            cat.catnotification = true;
                        }
                    } else {
                        new Update(Category.class)
                                .set("catselect = 0")
                                .execute();
                        new Update(Category.class)
                                .set("catnotification = 0")
                                .execute();
                        for (Category cat : categoryList) {
                            cat.catselect = false;
                            cat.catnotification = false;
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void inflatecategoryList(String type) {
        adapter = new CategoryTabAdapter(categoryList, type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(TabCategoryActivity.this);
        tabcategoryBinding.recyclerCat.setLayoutManager(layoutManager);
        tabcategoryBinding.recyclerCat.setHasFixedSize(true);
        tabcategoryBinding.recyclerCat.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        tabcategoryBinding.recyclerCat.addItemDecoration(dividerItemDecoration);
        tabcategoryBinding.recyclerCat.setAdapter(adapter);
        tabcategoryBinding.recyclerCat.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void settext() {
        if (type.equals(TAB_TYPE_LOGIN)) {
            btndone.setVisibility(View.VISIBLE);
            backbtn.setVisibility(View.GONE);
            selectAll.setVisibility(View.VISIBLE);
            titlename.setText("Select Categories");
            btndone.setText("NEXT");
            aftertype = "login";
        } else {
            titlename.setText("Custom Notification");
            backbtn.setVisibility(View.VISIBLE);
            selectAll.setVisibility(View.GONE);
        }
    }

    private void init() {
        cateListProcess = new CateListProcess(this);
        titlecatname = (LinearLayout) $(R.id.titlecatname);
        backbtn = (ImageView) $(R.id.btn_back);
        titlename = (MyTextView) $(R.id.title_txt);
        btndone = (MyTextView) $(R.id.next_btn);
        selectAll = (CheckBox) $(R.id.selectAll);
    }

    @Override
    public void onFinishCateActions(List<CategoryModel> categoryModels, String reponse) {
        hideLoading();
        categoryList = categoryModels.get(0).getCategories();
        inflatecategoryList(type);
    }

    @Override
    public void onErroCaterAction(String error) {}

}
