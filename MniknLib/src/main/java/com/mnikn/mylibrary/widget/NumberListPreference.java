package com.mnikn.mylibrary.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.widget.NumberPicker;

import com.mnikn.mylibrary.R;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class NumberListPreference extends DialogPreference {

    private final static Integer DEFAULT_VALUE = 0;

    private TypedArray mTypeArray;
    private AttributeSet mAttrs;
    private Context mContext;
    private NumberPicker mNumberPicker;

    public NumberListPreference(Context context) {
        this(context, null);
    }

    public NumberListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mAttrs = attrs;
        mTypeArray = context.obtainStyledAttributes(attrs,R.styleable.NumberListPreference);

        initNumberPicker();
        mTypeArray.recycle();
    }

    private void initNumberPicker(){
        mNumberPicker = new NumberPicker(mContext,mAttrs);
        mNumberPicker.setMinValue(mTypeArray.getInteger(R.styleable.NumberListPreference_minValue, DEFAULT_VALUE));
        mNumberPicker.setMaxValue(mTypeArray.getInteger(R.styleable.NumberListPreference_maxValue, DEFAULT_VALUE));
        mNumberPicker.setValue(mTypeArray.getInteger(R.styleable.NumberListPreference_value, mNumberPicker.getMinValue()));
    }


    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder);

        builder.setView(mNumberPicker);
    }

    public void setMaxValue(int value){
        mNumberPicker.setMaxValue(value);
    }

    public void setMinValue(int value){
        mNumberPicker.setMinValue(value);
    }

    public void setValue(int value){
        mNumberPicker.setValue(value);
    }



}
