package com.rajinder.noticeboard.Utils.recycler_decorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class VerticalItemDecoration extends RecyclerView.ItemDecoration {

    private boolean verticalOrientation = true;
    private int space = 10;

    public VerticalItemDecoration(int value, boolean verticalOrientation) {
        this.space = value;
        this.verticalOrientation = verticalOrientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        //skip first item in the list
        if (parent.getChildAdapterPosition(view) != 0) {
            if (verticalOrientation) {
                outRect.set(space, 0, 0, 0);
            } else if (!verticalOrientation) {
                outRect.set(0, space, 0, 0);
            }
        }
        // adding margin after last item
     /*   if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount()-1) {
            outRect.set(0, 0, 0, space);
        }*/
    }
}