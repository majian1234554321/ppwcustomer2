package com.paipaiwei.personal.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import com.paipaiwei.personal.R;

public class ToggleRadioButton extends androidx.appcompat.widget.AppCompatRadioButton {

    public ToggleRadioButton(Context context) {
        this(context, null);
    }

    public ToggleRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.radioButtonStyle);
    }

    public ToggleRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
        if (!isChecked()) {
            ((RadioGroup)getParent()).clearCheck();
        }
    }
}

