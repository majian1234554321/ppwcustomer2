package com.yjhh.common.utils;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration2 extends RecyclerView.ItemDecoration {
    int mSpace;


    String flag;


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = 0;
        outRect.right = 0;

        outRect.bottom = 0;


        if ("top".equals(flag)) {
            outRect.top = mSpace;
        } else {
            outRect.top = 0;
        }

    }

    public SpaceItemDecoration2(int space) {
        this.mSpace = space;
    }


    public SpaceItemDecoration2(int space, String flag) {
        this.mSpace = space;
        this.flag = flag;
    }

}
