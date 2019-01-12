package com.rajinder.noticeboard.Utils.recycler_decorations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rajinder.noticeboard.R;


/**
 * Created by rajinder on 4/26/2018.
 */

public class SpaceDeividerDecoration extends RecyclerView.ItemDecoration {

    private boolean verticalOrientation = true;
    private int space = 10;
    private Drawable mDivider;

    public SpaceDeividerDecoration(Context context, int value) {
        this.space = value;
        mDivider = context.getResources().getDrawable(R.drawable.recycler_item_decoration);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        //skip first item in the list
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.set(0, space, 0, 0);
        }
        // adding margin after last item
        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.set(0, 0, 0, space);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

        }
    }
}

