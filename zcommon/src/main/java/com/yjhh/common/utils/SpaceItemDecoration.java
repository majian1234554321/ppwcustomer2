package com.yjhh.common.utils;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace;


    String flag;


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.right = mSpace;

        outRect.top = mSpace;


        if ("bottom".equals(flag)) {
            outRect.bottom = 0;
        } else {
            outRect.bottom = mSpace;
        }


    }

    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }


    public SpaceItemDecoration(int space, String flag) {
        this.mSpace = space;
        this.flag = flag;
    }

}
