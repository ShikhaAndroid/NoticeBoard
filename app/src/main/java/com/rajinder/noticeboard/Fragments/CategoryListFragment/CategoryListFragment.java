package com.rajinder.noticeboard.Fragments.CategoryListFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.rajinder.noticeboard.Activity.DetailView.CatDetailActivity;
import com.rajinder.noticeboard.Adapters.ItemAdapter;
import com.rajinder.noticeboard.Fragments.BaseFragment;
import com.rajinder.noticeboard.Interface.OnPostListAction;
import com.rajinder.noticeboard.Process.PostListProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventTab;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.recycler_decorations.VerticalItemDecoration;
import com.rajinder.noticeboard.models.PostListModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryListFragment extends BaseFragment implements OnPostListAction {

    private View mView;
    public String ListType;
    Context mContext;
    private RecyclerView catrecycler;
    FloatingActionButton fab;
    CoordinatorLayout mainContent;
    boolean mIsHiding = false;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ItemAdapter adapter;
    private Bundle bundle;
    private String cat_id;
    private PostListProcess postListProcess;
    private List<PostListModel> postListModels;
    private LinearLayout interneterror, loader, emptylist;
    public int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bundle = this.getArguments();
        if (bundle != null) {
            cat_id = bundle.getString("cat_id");
        }
        initID();
        mContext = inflater.getContext();
        mView = inflater.inflate(R.layout.fragment_tab, container, false);
        init();
        onScrollListener();
        onclicklistener();
        if(!cat_id.equals("-1"))
            Apicall(cat_id);
        return mView;
    }

    private void Apicall(String cat_id) {
        loader.setVisibility(View.VISIBLE);
        interneterror.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.GONE);
        emptylist.setVisibility(View.GONE);
        if (getuserinfo() != null && getuserinfo().size() != 0)
            postListProcess.startprocess(cat_id, String.valueOf(getuserinfo().get(0).userid), getActivity(),"catid", MyActivity.lng, MyActivity.lat);
    }

    private void onclicklistener() {
        mSwipeRefreshLayout.setOnRefreshListener(OnRefreshListener());
        interneterror.setOnClickListener(OnClickListener());
        emptylist.setOnClickListener(OnClickListener());
    }

    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emptylist == v) {
                    Apicall(cat_id);
                }
                if (interneterror == v) {
                    Apicall(cat_id);
                }
            }
        };
    }

    private void onScrollListener() {
        catrecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    //   hideFabWithViewAnimation();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //   showFabWithViewAnimation();
                }
            }
        });
    }

    private void datainit() {
        inflatecategoryList();
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
            // datainit();
            // Toast.makeText(getActivity(),String.valueOf(getArguments().getString("position")),Toast.LENGTH_LONG).show();
        }
    }

    private void init() {
        postListModels = new ArrayList<>();
        postListProcess = new PostListProcess(this);
        catrecycler = (RecyclerView) mView.findViewById(R.id.recycler_cat);
        fab = (FloatingActionButton) mView.findViewById(R.id.fab);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swifeRefresh);
        loader = (LinearLayout) mView.findViewById(R.id.loader);
        interneterror = (LinearLayout) mView.findViewById(R.id.internet_error);
        emptylist = (LinearLayout) mView.findViewById(R.id.empty_list);
        datainit();
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
        adapter = new ItemAdapter(postListModels, "user", new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PostListModel item, String type_id) {
                List<PostListModel> itemlist = new ArrayList<>();
                itemlist.add(item);
                Intent catadetail = new Intent(getActivity(), CatDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("post_item", (Serializable) itemlist);
                bundle.putString("type", "cate");
                catadetail.putExtras(bundle);
                startActivity(catadetail);
                getActivity().overridePendingTransition(R.anim.slide_from_rightl, R.anim.slide_to_left);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        catrecycler.setLayoutManager(layoutManager);
        catrecycler.setHasFixedSize(true);
        catrecycler.addItemDecoration(new VerticalItemDecoration((int) getResources().getDimension(R.dimen.margin_5), false));
        catrecycler.setAdapter(adapter);
    }

    public void hideFabWithViewAnimation() {
        if (mIsHiding || fab.getVisibility() != View.VISIBLE) {
            return;
        }
        mIsHiding = true;
        Animation anim = AnimationUtils.loadAnimation(
                fab.getContext(), R.anim.design_fab_out);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(200);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mIsHiding = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsHiding = false;
                fab.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fab.startAnimation(anim);
    }

    public void showFabWithViewAnimation() {
        if (fab.getVisibility() != View.VISIBLE || mIsHiding) {
            fab.clearAnimation();
            fab.setVisibility(View.VISIBLE);

            Animation anim = AnimationUtils.loadAnimation(fab.getContext(), R.anim.design_fab_in);
            anim.setDuration(400);
            anim.setInterpolator(new OvershootInterpolator());
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    fab.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            fab.startAnimation(anim);
        }
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Apicall(cat_id);
             /*   final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000);*/
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventTab getcateid) {
        try {
            //    Toast.makeText(getActivity(), String.valueOf(getcateid.getPosition()), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {}
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if(cat_id.equals("-1"))
        Apicall(cat_id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onFinishPostActions(final List<PostListModel> postlModels, String reponse) {
        mSwipeRefreshLayout.setRefreshing(false);
//        postListModels = postlModels;
        postListModels.clear();
        postListModels.addAll(postlModels);
        adapter.notifyDataSetChanged();
//        datainit();
        loader.setVisibility(View.GONE);
        interneterror.setVisibility(View.GONE);
        emptylist.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorPostAction(String error) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (error.equals("internet")) {
            loader.setVisibility(View.GONE);
            interneterror.setVisibility(View.VISIBLE);
            mSwipeRefreshLayout.setVisibility(View.GONE);
            emptylist.setVisibility(View.GONE);
        } else {
            emptylist.setVisibility(View.VISIBLE);
            loader.setVisibility(View.GONE);
            interneterror.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
    }

}