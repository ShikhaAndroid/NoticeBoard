package com.rajinder.noticeboard.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.rajinder.noticeboard.Activity.DialogActivity.SelectTabDialog;
import com.rajinder.noticeboard.Activity.HomeActivities.SelectTabActivity;
import com.rajinder.noticeboard.Adapters.MyFragmentPageAdapter;
import com.rajinder.noticeboard.Fragments.CategoryListFragment.AroundMeFragment;
import com.rajinder.noticeboard.Fragments.CategoryListFragment.CategoryListFragment;
import com.rajinder.noticeboard.MyApplication;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventSelectTab;
import com.rajinder.noticeboard.UI.EventBusClass.EventTab;
import com.rajinder.noticeboard.Utils.CustomTabLayout;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.constants.ConstantData;
import com.rajinder.noticeboard.models.Category.Category;
import com.rajinder.noticeboard.models.CategoryModel;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajinder on 4/23/18.
 */
public class HomeFragment extends BaseFragment {

    private View mView;
    private Context mContext;
    private CustomTabLayout tabLayout;
    private MyTextView tabTextView;
    private ImageView addtab;
    int pcolor = 0;
    private FragmentManager mFragManager;
    private LinearLayout aroundview;
    private ViewPager pager;
    private List<Category> selecttab;
    private MyTextView aroundmekm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initID();
        mContext = inflater.getContext();
        mView = inflater.inflate(R.layout.home_fragment, container, false);
        init();
        setclickListener();
        if (getArguments() != null) {}
        selecttab = getcategory(); //ConstantData.getcategorydate();
        createTabs(selecttab, 1);
        settext();
        return mView;
    }

    private void settext() {
        aroundmekm.setText("Within " + ((MyApplication)mContext.getApplicationContext()).getAroundmekm() + " Kms");
    }

    private void setclickListener() {
        addtab.setOnClickListener(OnClickListener());
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addtab == v) {
                    startActivity(new Intent(getActivity(), SelectTabActivity.class));
                    getActivity().finish();
                    /*SelectTabDialog selectTabDialog = new SelectTabDialog(getActivity(), selecttab);
                    selectTabDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation2;
                    selectTabDialog.setCanceledOnTouchOutside(false);
                    selectTabDialog.show();*/
                }
            }
        };
    }

    private void createTabs(List<Category> getcategorytab, int type) {
        pager = (ViewPager) mView.findViewById(R.id.pager);
        Category categoryModel = new Category(-1, "AROUND ME", "", true, true, "other");
        Log.d("TAG", "Create Tabs");
        getcategorytab.add(0, categoryModel);
        ColorStateList colorStateList = getColorStateList();
        List<Category> categorytabModels = new ArrayList<>();
        // categorytabModels= ConstantData.getcategorytab();
        //  tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.green2));
        tabLayout.setSelectedTabIndicatorHeight(5);
        List<Fragment> fragments = new ArrayList<>();
        fragments.clear();
        fragments = buildFragments(getcategorytab);
        ArrayList<String> categorytabModelstr = new ArrayList<>();
        for (int i = 0; i < getcategorytab.size(); i++) {
            if (getcategorytab.get(i).catselect) {
                View mView = LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, tabLayout, false);
                mView.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                LinearLayout relativeLayout = (LinearLayout) mView.findViewById(R.id.root);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    setColotForTabs(relativeLayout);
                }
                View dividerView = (View) relativeLayout.findViewById(R.id.divide_view);
                dividerView.setBackgroundColor(Color.BLACK);
                tabTextView = (MyTextView) relativeLayout.findViewById(R.id.tab_title);
                tabTextView.setText(getcategorytab.get(i).catname);
                tabTextView.setPadding(50, 0, 50, 0);
                Log.e("colortag", "create TAB");
                tabTextView.setTextColor(colorStateList);
              /*  if (i == 0) {
                    dividerView.setVisibility(View.GONE);
                } else {
                    dividerView.setVisibility(View.VISIBLE);
                }*/
                tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout));
                categorytabModels.add(getcategorytab.get(i));
                categorytabModelstr.add(getcategorytab.get(i).catname);
            }
            setTabListener(categorytabModels);
            setViewPagerListener(categorytabModels);
        }

        //   mPager = (ViewPager) v.findViewById(R.id.pager);
        MyFragmentPageAdapter mPageAdapter = new MyFragmentPageAdapter(getActivity(), getChildFragmentManager(), fragments, categorytabModelstr);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        pager.setAdapter(mPageAdapter);

        //  if (type == 1) {
//        Log.e("colortag", "open first frag TAB");
        //     openFirstFrag(categorytabModels);
//        Log.e("colortag", "open first frag TAB1");
        //  }
        //     mPageAdapter.notifyDataSetChanged();

    }

    private void setViewPagerListener(final List<Category> categorytabModels) {
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                EventBus.getDefault().post(new EventTab((position)));
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {}

        });
    }

    private List<Fragment> buildFragments(List<Category> getcategorytab) {
     /*   if (getActivity().getSupportFragmentManager().getFragments() != null && getActivity().getSupportFragmentManager().getFragments().size() > 0) {
            for (int i = 0; i < getActivity().getSupportFragmentManager().getFragments().size(); i++) {
                Fragment mFragment = getActivity().getSupportFragmentManager().getFragments().get(i);
                if (mFragment != null) {
                    getActivity().getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
                }
            }
        }*/
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.clear();
        for (int i = 0; i < getcategorytab.size(); i++) {
            if (getcategorytab.get(i).catselect) {
                Bundle b = new Bundle();
                Log.e("cat_id", String.valueOf(getcategorytab.get(i).catid));
                try {
                    b.putString("cat_id", String.valueOf(getcategorytab.get(i).catid));
                } catch (Exception e) {
                    b.putString("cat_id", String.valueOf(getcategorytab.get(i).catid));
                }
                if (getcategorytab.get(i).catid == -1)
                    fragments.add(Fragment.instantiate(getActivity(), AroundMeFragment.class.getName(), b));
                else
                    fragments.add(Fragment.instantiate(getActivity(), CategoryListFragment.class.getName(), b));
            }
        }
        return fragments;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void init() {
        aroundview = (LinearLayout) mView.findViewById(R.id.around_view);
        tabLayout = (CustomTabLayout) mView.findViewById(R.id.tab_layout);
        pager = (ViewPager) mView.findViewById(R.id.pager);
        addtab = (ImageView) mView.findViewById(R.id.add_tab);
        aroundmekm = (MyTextView) mView.findViewById(R.id.around_me_km);
    }

    private void setTabListener(final List<Category> categorytabModels) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (categorytabModels.size() > 0) {
                    View view = tab.getCustomView();
                    MyTextView tabTextView = (MyTextView) view.findViewById(R.id.tab_title);
                    try {
                        view.setBackgroundColor(pcolor);
                        Log.e("colortag", "settablistener TAB");
                        tabTextView.setTextColor(Color.BLACK);
                    } catch (Exception e) {
                        e.printStackTrace();
                        //  tab.getCustomView().setBackgroundColor(ActivityCompat.getColor(getActivity(), R.color.hintcolor));
                        Log.e("colortag", "set tab error TAB");
                        tabTextView.setTextColor(Color.BLACK);
                    }
                    if (tab != null) {
                        pager.setCurrentItem(tab.getPosition());
                    }
                }
                if (tab.getPosition() == 0) {
                    aroundmekm.setVisibility(View.VISIBLE);
                } else {
                    aroundmekm.setVisibility(View.GONE);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                MyTextView tabTextView = (MyTextView) view.findViewById(R.id.tab_title);
                view.setBackgroundColor(Color.WHITE);
                Log.e("colortag", "on tab unselected TAB");
                tabTextView.setTextColor(Color.LTGRAY);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                if (categorytabModels.size() > 0) {
                    View view = tab.getCustomView();
                    MyTextView tabTextView = (MyTextView) view.findViewById(R.id.tab_title);
                    try {
                        view.setBackgroundColor(pcolor);
                        Log.e("colortag", "on tab reselected TAB");

                        tabTextView.setTextColor(Color.BLACK);
                    } catch (Exception e) {
                        e.printStackTrace();
                        tab.getCustomView().setBackgroundColor(ActivityCompat.getColor(getActivity(), R.color.hintcolor));
                        Log.e("colortag", "error on tab reselected TAB");
                        tabTextView.setTextColor(Color.BLACK);
                    }

                    if (tab != null) {
                        int catId = categorytabModels.get(tab.getPosition()).catid;
                        String catName = categorytabModels.get(tab.getPosition()).catname;
                        //tvHeader.setText(catName);
                         /*   Bundle bundle = new Bundle();
                            bundle.putString(getString(R.string.catId), catId);
                            bundle.putString(getString(R.string.catName), catName);
                            if (catName.startsWith("Bookmar")) {
                                fragment = new BookmarkFragment();
                            } else {
                                fragment = new MainNewsFragment();
                            }
                            fragment.setArguments(bundle);
                            switchFragment(fragment, "", false, false);*/
                    }
                }
            }
        });
    }

    private ColorStateList getColorStateList() {
        int[][] states = new int[][]{new int[]{android.R.attr.state_selected}, // enabled
                new int[]{-android.R.attr.state_selected} // pressed
        };
        int[] colors = new int[0];
        colors = new int[]{Color.BLACK, Color.LTGRAY,};
        return new ColorStateList(states, colors);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setColotForTabs(LinearLayout relativeLayout) {
        try {
            pcolor = Color.parseColor("#ffffff");
        } catch (Exception e) {
            e.printStackTrace();
        }
        relativeLayout.setBackground(makeSelector(pcolor));
    }

    public void openFirstFrag(ArrayList<CategoryModel> categorytabModels) {
        if (categorytabModels.size() > 0) {
            Log.e("colortag", "open first frag TAB2");
            //  Fragment fragment = null;
            String catId = categorytabModels.get(0).getCate_id();
            String catName = categorytabModels.get(0).getCate_name();
            Bundle bundle = new Bundle();
            bundle.putString("position", catName);
            bundle.putString(getString(R.string.catId), catId);
            bundle.putString(getString(R.string.catName), catName);
            CategoryListFragment fragment = new CategoryListFragment();
            fragment.setArguments(bundle);
            switchFragment(fragment, "", false, false);
            Log.e("colortag", "open first frag TAB3");
        }
    }

    public void switchFragment(Fragment fragment, String tag, boolean addToStack, boolean clearStack) {
        if (clearStack) {
            mFragManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        FragmentTransaction transaction = mFragManager.beginTransaction();
        transaction.replace(R.id.container_tab, fragment);
        if (addToStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMassageEvent(EventSelectTab Tab) {
        selecttab = new ArrayList<>();
        selecttab = getcategory();
        tabLayout.removeAllTabs();
        createTabs(selecttab, 0);
        // createTabs(ConstantData.getcategorytab());
        Log.d("TAG", "message event");
    }

    @Override
    public void onResume() {
        Log.d("TAG", "RESUME");
        super.onResume();
        settext();
    }

}
