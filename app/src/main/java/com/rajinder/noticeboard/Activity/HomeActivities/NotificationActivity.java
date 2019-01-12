package com.rajinder.noticeboard.Activity.HomeActivities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rajinder.noticeboard.Adapters.NotificationAdapter;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.Utils.recycler_decorations.VerticalItemDecoration;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.databinding.ActivityNotificationBinding;

public class NotificationActivity extends MyActivity   {

    ActivityNotificationBinding notificationBinding;
    private ImageView backbtn;
    private MyTextView titlename;
    private RecyclerView recycnewnoti,recycealiernoti;
    NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification);

        init();
        setListeners();
        settext();
     //   inflatenewnotificationList();
         inflateearliernotificationList();
    }

    private void setListeners() {
        backbtn.setOnClickListener(OnClickListener());
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

    private void settext() {
        titlename.setText(getString(R.string.notification));
    }

    private void init() {
        backbtn = (ImageView) $(R.id.btn_back);
        titlename = (MyTextView) $(R.id.title_txt);
        recycnewnoti=(RecyclerView)$(R.id.recycler_new_noti);
        recycealiernoti=(RecyclerView)$(R.id.recycler_earlier_noti);
    }

    private void inflatenewnotificationList() {
       adapter = new NotificationAdapter(ConstantData.getnotificationnew());
        LinearLayoutManager layoutManager = new LinearLayoutManager(NotificationActivity.this);
        recycnewnoti.setLayoutManager(layoutManager);
        recycnewnoti.setHasFixedSize(true);
        recycnewnoti.setItemAnimator(new DefaultItemAnimator());
      //  DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
      //          0);
        recycnewnoti.addItemDecoration(new VerticalItemDecoration(1, false));

    //    recycnewnoti.addItemDecoration(dividerItemDecoration);
        recycnewnoti.setAdapter(adapter);
        recycnewnoti.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void inflateearliernotificationList() {
        adapter = new NotificationAdapter(ConstantData.getnotificationearlier());
        LinearLayoutManager layoutManager = new LinearLayoutManager(NotificationActivity.this);
        recycealiernoti.setLayoutManager(layoutManager);
        recycealiernoti.setHasFixedSize(true);
        recycealiernoti.setItemAnimator(new DefaultItemAnimator());
        //  DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
        //          0);
        recycealiernoti.addItemDecoration(new VerticalItemDecoration(1, false));

        //    recycnewnoti.addItemDecoration(dividerItemDecoration);
        recycealiernoti.setAdapter(adapter);
        recycealiernoti.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

}
