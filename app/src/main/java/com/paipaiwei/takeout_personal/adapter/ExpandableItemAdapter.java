package com.paipaiwei.takeout_personal.adapter;

import android.util.Log;
import android.view.View;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.paipaiwei.takeout_personal.R;


import java.util.List;

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_PERSON = 2;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.evaluatemanageadapter);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
        addItemType(TYPE_PERSON, R.layout.item_expandable_lv1);
    }


    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {

        Log.i("EvaluateManageAdapter", holder.getItemViewType() + "");

        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:

                break;
            case TYPE_LEVEL_1:

                break;
            case TYPE_PERSON:

                break;
        }
    }
}
