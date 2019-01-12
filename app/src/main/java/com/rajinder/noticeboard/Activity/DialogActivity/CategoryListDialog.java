package com.rajinder.noticeboard.Activity.DialogActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.activeandroid.query.Select;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rajinder.noticeboard.Activity.HomeActivities.SelectCategoryActivity;
import com.rajinder.noticeboard.Adapters.AllCategorysAdapter;
import com.rajinder.noticeboard.Adapters.SelectCategorysAdapter;
import com.rajinder.noticeboard.Adapters.SelectSubCategorysAdapter;
import com.rajinder.noticeboard.Interface.OnSubCategoryAction;
import com.rajinder.noticeboard.Process.SubCateListProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.Utils.recycler_decorations.VerticalItemDecoration;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.subCategory.SubCategoryModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CategoryListDialog extends Dialog implements OnSubCategoryAction {

    public Activity c;
    public Dialog d;
    private RecyclerView recyclerCat;
    private ImageView closebtn;
    public String type;
    private MyTextView titlename;
    List<Category> categories;
    int cateid;
    private SubCateListProcess subCateListProcess;
    private ProgressBar progressBar;

    public CategoryListDialog(Activity a, String type, int cate_id) {
        super(a);
        this.cateid = cate_id;
        //this.tabs=tab;
        this.c = a;
        this.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.select_cate_dialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);
        init();

        recyclerCat.setHasFixedSize(true);
        recyclerCat.addItemDecoration(new VerticalItemDecoration(1, false));
        if (type.equals(MyActivity.CATE_TYPE)) {
            titlename.setText("Categories");
            inflatecategoryList();
        } else if (type.equals(MyActivity.SUB_CATE_TYPE)) {
            titlename.setText("Sub-Categories");
            inflatesubcategoryList(cateid);
        }
        setlistener();
    }

    private void setlistener() {
        closebtn.setOnClickListener(OnclickListener());
    }

    private View.OnClickListener OnclickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (closebtn == v) {
                    dismiss();
                }
            }
        };
    }

    private void init() {
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        subCateListProcess = new SubCateListProcess(this);
        titlename = (MyTextView) findViewById(R.id.title_txt);
        recyclerCat = (RecyclerView) findViewById(R.id.recycler_cat);
        closebtn = (ImageView) findViewById(R.id.close_btn);
    }

    private void inflatecategoryList() {
        try {
            categories = new Select().from(Category.class).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SelectCategorysAdapter adapter = new SelectCategorysAdapter(categories);
        recyclerCat.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    private void inflatesubcategoryList(int cateid) {
        subcatapi(cateid);
    }

    private void subcatapi(int cateid) {
        subCateListProcess.startprocess(String.valueOf(cateid), c);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onFinishSubCateActions(List<SubCategoryModel> subCategoryModels, String reponse) {
        progressBar.setVisibility(View.GONE);
        if (subCategoryModels.get(0).subcategories.size() != 0) {
            SelectSubCategorysAdapter adapter = new SelectSubCategorysAdapter(subCategoryModels.get(0).subcategories);
            recyclerCat.setAdapter(adapter);
        }
    }

    @Override
    public void onErroSubCaterAction(String error) {}

}