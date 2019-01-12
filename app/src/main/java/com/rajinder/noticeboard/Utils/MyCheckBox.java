package com.rajinder.noticeboard.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by Rajinder on 4/20/2018
 */

@SuppressLint("AppCompatCustomView")
public class MyCheckBox extends CheckBox {

    public MyCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCheckBox(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat.ttf");
            setTypeface(tf);
        }
    }

}
