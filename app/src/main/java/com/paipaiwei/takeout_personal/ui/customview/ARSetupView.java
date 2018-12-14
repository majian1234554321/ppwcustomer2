package com.paipaiwei.takeout_personal.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.paipaiwei.takeout_personal.R;

public class ARSetupView extends RelativeLayout {

    public int maxValue = 9999;
    private TextView tv_count;

    public ARSetupView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.arsetupview, this);

        TextView tv_reduce = view.findViewById(R.id.tv_reduce);
        tv_count = view.findViewById(R.id.tv_count);
        TextView tv_add = view.findViewById(R.id.tv_add);


        tv_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(tv_count.getText().toString());
                value++;
                tv_count.setText(String.valueOf(value));

            }
        });


        tv_reduce.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(tv_count.getText().toString());

                if (value > 1) {
                    value--;
                    tv_count.setText(String.valueOf(value));
                }


            }
        });

    }


    public int getCount() {
        return Integer.parseInt(tv_count.getText().toString());
    }


    public ARSetupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ARSetupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);


    }
}
