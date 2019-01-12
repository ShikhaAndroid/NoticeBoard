package com.rajinder.noticeboard.Activity.DialogActivity.Search;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.rajinder.noticeboard.R;
import com.rajinder.noticeboard.Utils.MyEditText;

public class SearchDialog extends Dialog {


    private MyEditText edtsearch;
    private Activity activity;
    private ImageView btnclear, btnback;

    public SearchDialog(Activity a) {
        super(a);
        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);

        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);


        init();
        setlister();


    }

    private void setlister() {
        btnback.setOnClickListener(OnclickListener());
        btnclear.setOnClickListener(OnclickListener());
        edtsearch.addTextChangedListener(OnaddTextlistener());
    }


    /*ADD TEXT LISTENER*/
    private TextWatcher OnaddTextlistener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()!=0) {
                    btnclear.setVisibility(View.VISIBLE);
                } else {
                    btnclear.setVisibility(View.GONE);
                }
                Log.e("string",String.valueOf(s.length()));
            }
        };
    }

    /*click listener*/
    private View.OnClickListener OnclickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnback==v)
                {
                    dismiss();
                }
                if(btnclear==v)
                {
                    edtsearch.setText("");
                }
            }
        };
    }

    /*init*/
    private void init() {
        edtsearch = (MyEditText) findViewById(R.id.edt_search);
        btnback = (ImageView) findViewById(R.id.btn_back);
        btnclear = (ImageView) findViewById(R.id.btn_clear);
    }


    @Override
    protected void onStop() {
        super.onStop();

    }
}