package com.yjhh.ppwcustomer.ui.customview;



import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.yjhh.ppwcustomer.R;
import com.yjhh.ppwcustomer.common.RxPhotoTool;


public class RxDialogChooseImage extends RxDialog {
    private RxDialogChooseImage.LayoutType mLayoutType;
    public TextView mTvCamera;
    private TextView mTvFile;
    private TextView mTvCancel;

    public RxDialogChooseImage(Activity context) {
        super(context);
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.initView(context);
    }

    public RxDialogChooseImage(Fragment fragment) {
        super(fragment.getContext());
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.initView(fragment);
    }

    public RxDialogChooseImage(Activity context, int themeResId) {
        super(context, themeResId);
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.initView(context);
    }

    public RxDialogChooseImage(Fragment fragment, int themeResId) {
        super(fragment.getContext(), themeResId);
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.initView(fragment);
    }

    public RxDialogChooseImage(Activity context, float alpha, int gravity) {
        super(context, alpha, gravity);
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.initView(context);
    }

    public RxDialogChooseImage(Fragment fragment, float alpha, int gravity) {
        super(fragment.getContext(), alpha, gravity);
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.initView(fragment);
    }

    public RxDialogChooseImage(Fragment fragment, RxDialogChooseImage.LayoutType layoutType) {
        super(fragment.getContext());
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.mLayoutType = layoutType;
        this.initView(fragment);
    }

    public RxDialogChooseImage(Activity context, RxDialogChooseImage.LayoutType layoutType) {
        super(context);
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.mLayoutType = layoutType;
        this.initView(context);
    }

    public RxDialogChooseImage(Activity context, int themeResId, RxDialogChooseImage.LayoutType layoutType) {
        super(context, themeResId);
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.mLayoutType = layoutType;
        this.initView(context);
    }

    public RxDialogChooseImage(Fragment fragment, int themeResId, RxDialogChooseImage.LayoutType layoutType) {
        super(fragment.getContext(), themeResId);
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.mLayoutType = layoutType;
        this.initView(fragment);
    }

    public RxDialogChooseImage(Activity context, float alpha, int gravity, RxDialogChooseImage.LayoutType layoutType) {
        super(context, alpha, gravity);
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.mLayoutType = layoutType;
        this.initView(context);
    }

    public RxDialogChooseImage(Fragment fragment, float alpha, int gravity, RxDialogChooseImage.LayoutType layoutType) {
        super(fragment.getContext(), alpha, gravity);
        this.mLayoutType = RxDialogChooseImage.LayoutType.TITLE;
        this.mLayoutType = layoutType;
        this.initView(fragment);
    }

    public TextView getFromCameraView() {
        return this.mTvCamera;
    }

    public TextView getFromFileView() {
        return this.mTvFile;
    }

    public TextView getCancelView() {
        return this.mTvCancel;
    }

    public RxDialogChooseImage.LayoutType getLayoutType() {
        return this.mLayoutType;
    }

    private void initView(final Activity activity) {
        View dialog_view = null;
        switch(this.mLayoutType) {
            case TITLE:
                dialog_view = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_picker_pictrue, (ViewGroup)null);
                break;
            case NO_TITLE:
                dialog_view = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_camero_show, (ViewGroup)null);
        }

        this.mTvCamera = (TextView)dialog_view.findViewById(R.id.tv_camera);
        this.mTvFile = (TextView)dialog_view.findViewById(R.id.tv_file);
        this.mTvCancel = (TextView)dialog_view.findViewById(R.id.tv_cancel);
        this.mTvCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                RxDialogChooseImage.this.cancel();
            }
        });
       /* this.mTvCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                RxPhotoTool.openCameraImage(activity);
                RxDialogChooseImage.this.cancel();
            }
        });
        this.mTvFile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                RxPhotoTool.openLocalImage(activity);
                RxDialogChooseImage.this.cancel();
            }
        });*/
        this.setContentView(dialog_view);
        this.mLayoutParams.gravity = 80;
    }

    private void initView(final Fragment fragment) {
        View dialog_view = null;
        switch(this.mLayoutType) {
            case TITLE:
                dialog_view = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_picker_pictrue, (ViewGroup)null);
                break;
            case NO_TITLE:
                dialog_view = LayoutInflater.from(this.getContext()).inflate(R.layout.dialog_camero_show, (ViewGroup)null);
        }

        this.mTvCamera = (TextView)dialog_view.findViewById(R.id.tv_camera);
        this.mTvFile = (TextView)dialog_view.findViewById(R.id.tv_file);
        this.mTvCancel = (TextView)dialog_view.findViewById(R.id.tv_cancel);
        this.mTvCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                RxDialogChooseImage.this.cancel();
            }
        });

        this.setContentView(dialog_view);
        this.mLayoutParams.gravity = 80;
    }

    public static enum LayoutType {
        TITLE,
        NO_TITLE;

        private LayoutType() {
        }
    }
}
