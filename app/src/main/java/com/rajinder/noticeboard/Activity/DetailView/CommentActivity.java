package com.rajinder.noticeboard.Activity.DetailView;

import android.app.Dialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rajinder.noticeboard.Adapters.CommentAdapter;
import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.UI.EventBusClass.EventComment;
import com.rajinder.noticeboard.Utils.MyActivity;
import com.rajinder.noticeboard.Utils.MyEditText;
import com.rajinder.noticeboard.Utils.MyTextView;
import com.rajinder.noticeboard.Utils.recycler_decorations.VerticalItemDecoration;
import com.rajinder.noticeboard.models.CommetModel;
import com.rajinder.noticeboard.models.PostListModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CommentActivity extends MyActivity {

    private ImageView backbtn;
    private MyTextView titlename, btnpost, btnprivate, btnpublic, btnok, btncancel, catedis;
    private MyEditText edtcomment;
    private RecyclerView commentrecycler;
    private CommentAdapter commentAdapter;
    ArrayList<CommetModel> commetModels;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Dialog dialog;
    Bundle extras;
    List<PostListModel> postListModels;
    private CircularImageView puserimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        extras = getIntent().getExtras();
        if (extras == null) {
            return;
        } else {
            postListModels = (List<PostListModel>) extras.getSerializable("postitem");
        }
        commetModels = new ArrayList<>();
        init();
        setListeners();
        settext();
        inflatecategoryList();
    }


    private void inflatecategoryList() {
        Log.d("CATTAG", "On create view8");
        commentAdapter = new CommentAdapter(commetModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // layoutManager.setReverseLayout(true);
        //layoutManager.setStackFromEnd(true);
        commentrecycler.setLayoutManager(layoutManager);
        commentrecycler.setHasFixedSize(true);
        commentrecycler.addItemDecoration(new VerticalItemDecoration((int) getResources().getDimension(R.dimen.margin_5), false));
        commentrecycler.setAdapter(commentAdapter);
        Log.d("CATTAG", "On create view9");
    }

    private void settext() {
        titlename.setText(getString(R.string.comments));
        GradientDrawable bgShape = (GradientDrawable) btnpublic.getBackground();
        bgShape.setColor(getResources().getColor(R.color.colorblack));
        GradientDrawable bgShapes = (GradientDrawable) btnprivate.getBackground();
        bgShapes.setColor(getResources().getColor(R.color.hintcolor));

        if (postListModels.get(0).postTitle.equals(""))
            catedis.setText(postListModels.get(0).description);
        else
            catedis.setText(postListModels.get(0).postTitle);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.emptyuser);
        requestOptions.error(R.drawable.emptyuser);
        requestOptions.centerInside();
        Glide.with(this).setDefaultRequestOptions(requestOptions).load(postListModels.get(0).profilePic).into(puserimage);
    }

    private void setListeners() {
        backbtn.setOnClickListener(OnClickListener());
        btnpost.setOnClickListener(OnClickListener());
        edtcomment.addTextChangedListener(OnTextChanged());
        mSwipeRefreshLayout.setOnRefreshListener(OnRefreshListener());
        btnpublic.setOnClickListener(OnClickListener());
        btnprivate.setOnClickListener(OnClickListener());
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        mSwipeRefreshLayout.setRefreshing(false);
                        commentAdapter.setnotifydata();

                        try {
                            commentrecycler.smoothScrollToPosition(commetModels.size() - 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 3000);
            }
        };
    }

    private TextWatcher OnTextChanged() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (edtcomment.getText().toString().trim().length() == 0) {
                    btnpost.setTextColor(getResources().getColor(R.color.hintcolor));
                    btnpost.setClickable(false);
                } else {
                    btnpost.setTextColor(getResources().getColor(R.color.colorwhite));
                    btnpost.setClickable(true);
                }
            }
        };
    }

    private void init() {
        puserimage = (CircularImageView) $(R.id.p_user_image);
        catedis = (MyTextView) $(R.id.cate_dis);
        btnpost = (MyTextView) $(R.id.btn_post);
        edtcomment = (MyEditText) $(R.id.edt_comment);
        edtcomment.setFocusable(true);
        edtcomment.requestFocus();
        backbtn = (ImageView) $(R.id.btn_back);
        titlename = (MyTextView) $(R.id.title_txt);
        commentrecycler = (RecyclerView) $(R.id.comment_recycler);
        mSwipeRefreshLayout = (SwipeRefreshLayout) $(R.id.swifeRefresh);
        btnprivate = (MyTextView) $(R.id.btn_private);
        btnpublic = (MyTextView) $(R.id.btn_public);
    }


    private View.OnClickListener OnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backbtn == v) {
                    finish();
                    overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                }
                if (btnpost == v) {
                    if (edtcomment.getText().toString().trim().length() != 0) {
                        Date currentTime = Calendar.getInstance().getTime();
                        commetModels.add(new CommetModel("1", R.drawable.demoimage3, "in Rent", edtcomment.getText().toString(), currentTime, false, false));
                        edtcomment.setText("");
                        commentAdapter.setnotifydata();
                        commentrecycler.smoothScrollToPosition(commetModels.size() - 1);
                    }
                }

                if (btnprivate == v) {
                    GradientDrawable bgShape = (GradientDrawable) btnprivate.getBackground();
                    bgShape.setColor(getResources().getColor(R.color.colorblack));
                    GradientDrawable bgShapes = (GradientDrawable) btnpublic.getBackground();
                    bgShapes.setColor(getResources().getColor(R.color.hintcolor));

                }
                if (btnpublic == v) {
                    GradientDrawable bgShape = (GradientDrawable) btnpublic.getBackground();
                    bgShape.setColor(getResources().getColor(R.color.colorblack));
                    GradientDrawable bgShapes = (GradientDrawable) btnprivate.getBackground();
                    bgShapes.setColor(getResources().getColor(R.color.hintcolor));
                }

            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventComment getevent) {
        try {

            if (getevent.getType().equals("edit")) {
                // Date currentTime = Calendar.getInstance().getTime();
                //   CommetModel cc = new CommetModel("1", R.drawable.demoimage, "in Rent", "EDIT TEXT", currentTime, false);
                //    commetModels.set(getevent.getPosition(), cc);
                //    commentAdapter.setnotifydata();
                editcommentdialog(getevent.getPosition());
            } else {
                commetModels.remove(getevent.getPosition());
                commentAdapter.setnotifydata();
                //    showDialogs(getevent.getPosition());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editcommentdialog(final int position) {
        dialog = new Dialog(CommentActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.TOP;
        dialog.setContentView(R.layout.edit_comment_layout);
        btnok = (MyTextView) dialog.findViewById(R.id.btn_post);
        final MyEditText edtcomment = (MyEditText) dialog.findViewById(R.id.edt_comment);
        edtcomment.setText(commetModels.get(position).getComment());
        btncancel = (MyTextView) dialog.findViewById(R.id.btn_cancel);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Date currentTime = Calendar.getInstance().getTime();
                CommetModel cc = new CommetModel(commetModels.get(position).getComment_id(), commetModels.get(position).getUser_image(), commetModels.get(position).getUser_name(), edtcomment.getText().toString(), commetModels.get(position).getComment_time(), true, commetModels.get(position).getIsFvt());
                commetModels.set(position, cc);
                commentAdapter.setnotifydata();
                dialog.dismiss();
                commentrecycler.smoothScrollToPosition(commetModels.size() - 1);
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

}
