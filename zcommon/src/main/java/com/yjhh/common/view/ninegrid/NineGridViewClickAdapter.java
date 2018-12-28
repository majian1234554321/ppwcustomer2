package com.yjhh.common.view.ninegrid;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import com.yjhh.common.view.fragments.PhotoFragment;

import java.util.List;


public class NineGridViewClickAdapter extends NineGridViewAdapter {

    private int statusHeight;

    public NineGridViewClickAdapter(Context context, List<String> imageInfo) {
        super(context, imageInfo);
        statusHeight = getStatusHeight(context);
    }

    public FragmentManager fragmentManager;

    public NineGridViewClickAdapter(Context context, List<String> imageInfo, FragmentManager fragmentManager) {
        super(context, imageInfo);
        statusHeight = getStatusHeight(context);
        this.fragmentManager = fragmentManager;
    }

    @Override
    protected void onImageItemClick(Context context, NineGridView nineGridView, int index, List<String> imageInfo) {


//        Intent intent = new Intent(context, ImagePreviewActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfo);
//        bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, index);
//        intent.putExtras(bundle);
//        context.startActivity(intent);
        //Toast.makeText(context, "index" + index, Toast.LENGTH_SHORT).show();

        PhotoFragment photoFragment = new PhotoFragment(imageInfo, index);
        photoFragment.show(fragmentManager,"TAG");


    }

    /**
     * 获得状态栏的高度
     */
    public int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
