package com.yjhh.common.base;

import android.app.ProgressDialog;
import android.content.Context;

public class WaitProgressDialog extends ProgressDialog {
    public WaitProgressDialog(Context context) {
        super(context);
    }

    public WaitProgressDialog(Context context, boolean isShow) {
        super(context);

    }
}
