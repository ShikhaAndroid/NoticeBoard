package com.rajinder.noticeboard.Activity.HomeActivities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.rajinder.noticeboard.Adapters.CategoryselectAdapter;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.recycler_decorations.VerticalItemDecoration;
import com.rajinder.noticeboard.models.Category.Category;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SelectTabActivity extends MyActivity {

    public Activity c;
    public Dialog d;
    private RecyclerView recyclerCat;
    private List<Category> tabs;
    private ImageView closebtn;

  /*  public SelectTabActivity(Activity a, List<Category> tab) {
        super(a);
        this.tabs = tab;
        this.c = a;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.select_tab_dialog);
       /* WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);*/
        init();
        inflatecategoryList();
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
                    startActivity(new Intent(SelectTabActivity.this, HomeActivity.class));
                    finish();
                }
            }
        };
    }

    private void init() {
        recyclerCat = (RecyclerView) findViewById(R.id.recycler_cat);
        closebtn = (ImageView) findViewById(R.id.close_btn);
    }

    private void inflatecategoryList() {
        CategoryselectAdapter adapter = new CategoryselectAdapter(getcategory());
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerCat.setLayoutManager(layoutManager);
        recyclerCat.setHasFixedSize(true);
        //    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(c,
        //            1);
        recyclerCat.addItemDecoration(new VerticalItemDecoration((int) getResources().getDimension(R.dimen.margin_5), false));

        // recyclerCat.addItemDecoration(dividerItemDecoration);//new GridSpacingItemDecoration((int) getContext().getResources().getDimension(R.dimen.top_margin_small), (int) getContext().getResources().getDimension(R.dimen.top_margin_small), false));
        recyclerCat.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        Log.d("TAG", "On stop");
        super.onStop();
        EventBus.getDefault().post("stop");
    }

}