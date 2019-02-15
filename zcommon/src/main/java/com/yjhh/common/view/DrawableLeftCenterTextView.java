package com.yjhh.common.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class DrawableLeftCenterTextView extends TextView {





        public DrawableLeftCenterTextView(Context context, AttributeSet attrs,
                                      int defStyle) {
            super(context, attrs, defStyle);
        }

        public DrawableLeftCenterTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public DrawableLeftCenterTextView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Drawable[] drawables = getCompoundDrawables();
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = 0;
                drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
            super.onDraw(canvas);
        }
    }


