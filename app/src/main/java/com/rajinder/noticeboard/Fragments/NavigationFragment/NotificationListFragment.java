package com.rajinder.noticeboard.Fragments.NavigationFragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.rajinder.noticeboard.Adapters.ItemAdapter;
import com.rajinder.noticeboard.Adapters.NotificationAdapter;
import com.rajinder.noticeboard.Fragments.BaseFragment;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.recycler_decorations.VerticalItemDecoration;
import com.rajinder.noticeboard.constants.ConstantData;

import java.util.HashMap;


public class NotificationListFragment extends BaseFragment {
    private View mView;
    public String ListType;
    Context mContext;
    private LinearLayout l1;

    private RecyclerView recycnewnoti,recycealiernoti;
    NotificationAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // initID();
        mContext = inflater.getContext();
        mView = inflater.inflate(R.layout.activity_notification, container, false);
        init();
        inflatecategoryList();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        if (getArguments() != null) {
            ListType = getArguments().getString(getString(R.string.catName));
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("categoryId", getArguments().getString(getString(R.string.catId)));
            hashMap.put("deviceType", "android");
            }
    }


    private void init() {
       recycnewnoti=(RecyclerView)mView.findViewById(R.id.recycler_new_noti);
        recycealiernoti=(RecyclerView)mView.findViewById(R.id.recycler_earlier_noti);
        l1=(LinearLayout)mView.findViewById(R.id.l1);
        l1.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }


    private void inflatecategoryList() {
        adapter = new NotificationAdapter(ConstantData.getnotificationearlier());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
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
        });}





}