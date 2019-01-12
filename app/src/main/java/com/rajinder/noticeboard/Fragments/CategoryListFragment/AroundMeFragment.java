package com.rajinder.noticeboard.Fragments.CategoryListFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rajinder.noticeboard.Activity.DetailView.CatDetailActivity;
import com.rajinder.noticeboard.Adapters.AroundMeAdapter;
import com.rajinder.noticeboard.Adapters.ItemAdapter;
import com.rajinder.noticeboard.Fragments.BaseFragment;
import com.rajinder.noticeboard.Interface.OnPostListAction;
import com.rajinder.noticeboard.Process.PostListProcess;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.recycler_decorations.VerticalItemDecoration;
import com.rajinder.noticeboard.models.PostListModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AroundMeFragment extends BaseFragment implements OnPostListAction {

    public static final String TAG = "AroundMeFragment";
    private OnFragmentInteractionListener mListener;
    private View mView;
    private Context mContext;
    private Bundle bundle;
    private String cat_id;
    private PostListProcess postListProcess;
    private List<PostListModel> postListModels;
    private RecyclerView aroundMeList;
    private AroundMeAdapter adapter;
    private FloatingActionButton fab;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout interneterror, loader, emptylist;
    private int position;

    public AroundMeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_around_me, container, false);
        mContext = inflater.getContext();
        bundle = this.getArguments();
        if (bundle != null) {
            cat_id = bundle.getString("cat_id");
        }
        init();
        onclicklistener();
        return mView;
    }

    private void init() {
        postListModels = new ArrayList<>();
        postListProcess = new PostListProcess(this);
        aroundMeList = (RecyclerView) mView.findViewById(R.id.around_me_list);
        fab = (FloatingActionButton) mView.findViewById(R.id.fab);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swifeRefresh);
        loader = (LinearLayout) mView.findViewById(R.id.loader);
        interneterror = (LinearLayout) mView.findViewById(R.id.internet_error);
        emptylist = (LinearLayout) mView.findViewById(R.id.empty_list);
        inflatecategoryList();
    }

    private void Apicall(String cat_id) {
        loader.setVisibility(View.VISIBLE);
        interneterror.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.GONE);
        emptylist.setVisibility(View.GONE);
        if (getuserinfo() != null && getuserinfo().size() != 0)
            postListProcess.startprocess(cat_id, String.valueOf(getuserinfo().get(0).userid), getActivity(),"catid", MyActivity.lng, MyActivity.lat);
    }

    private void inflatecategoryList() {
        adapter = new AroundMeAdapter(getContext(), postListModels, "user", new ItemAdapter.OnItemClickListener() {
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
        aroundMeList.setLayoutManager(layoutManager);
        aroundMeList.setHasFixedSize(true);
        aroundMeList.addItemDecoration(new VerticalItemDecoration((int) getResources().getDimension(R.dimen.margin_5), false));
        aroundMeList.setAdapter(adapter);
    }

    private void refreshAdapter() {
        adapter.notifyDataSetChanged();
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

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Apicall(cat_id);
            }
        };
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
//        else {
//            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Apicall(cat_id);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFinishPostActions(List<PostListModel> postlModels, String reponse) {
        Log.d(TAG, "onFinishPostActions: "+reponse);
        mSwipeRefreshLayout.setRefreshing(false);
//        postListModels = postlModels;
        postListModels.clear();
        postListModels.addAll(postlModels);
//        inflatecategoryList();
        refreshAdapter();
        loader.setVisibility(View.GONE);
        interneterror.setVisibility(View.GONE);
        emptylist.setVisibility(View.GONE);
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorPostAction(String error) {
        Log.d(TAG, "onErrorPostAction: "+error);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
