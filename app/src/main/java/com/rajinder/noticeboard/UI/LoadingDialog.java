package com.rajinder.noticeboard.UI;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import com.rajinder.noticeboard.R;


public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
    }
}