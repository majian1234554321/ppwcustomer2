package com.paipaiwei.personal.common;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    public String args;

    public SpacesItemDecoration(int space, String args) {
        this.space = space;
        this.args = args;

    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {


        if (args != null) {

            if (args.contains("TOP")) {
                outRect.top = 0;
            }

            if (args.contains("BOTTOM")) {
                outRect.bottom = 0;
            }
            outRect.left = space;
            outRect.right = space;
        } else {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildAdapterPosition(view) == 0)
                outRect.top = space;
        }


    }
}


