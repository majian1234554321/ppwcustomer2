package com.paipaiwei.personal.ui.customview.dropdownmenu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

/**
 * drawableLeft与文本一起居中显示
 */
public class DrawableCenterEditTextView extends EditText {

    private float textWidth;

    public DrawableCenterEditTextView(Context context, AttributeSet attrs,
                                      int defStyle) {
        super(context, attrs, defStyle);
    }

    public DrawableCenterEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableCenterEditTextView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {


                if (!TextUtils.isEmpty(getHint().toString())) {
                    textWidth = getPaint().measureText(getHint().toString());
                } else {
                    textWidth = getPaint().measureText(getText().toString());
                }


                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = 0;
                drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }

        super.onDraw(canvas);
    }
}

