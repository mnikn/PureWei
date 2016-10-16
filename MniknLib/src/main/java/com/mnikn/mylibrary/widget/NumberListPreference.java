package com.mnikn.mylibrary.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;

import com.mnikn.mylibrary.R;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class NumberListPreference extends DialogPreference {

    private TypedArray mTypeArray;
    private AttributeSet mAttrs;
    private Context mContext;
    private NumberPicker mNumberPicker;

    private int mNumber;

    public NumberListPreference(Context context) {
        this(context, null);
    }

    public NumberListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mAttrs = attrs;
        mTypeArray = context.obtainStyledAttributes(attrs,R.styleable.NumberListPreference);


    }

    @Override
    protected View onCreateDialogView() {
        mNumberPicker = new NumberPicker(mContext,mAttrs);
        mNumberPicker.setMinValue(mTypeArray.getInteger(R.styleable.NumberListPreference_minValue, 0));
        mNumberPicker.setMaxValue(mTypeArray.getInteger(R.styleable.NumberListPreference_maxValue, 100));

        mNumber = getPersistedInt(mNumberPicker.getMinValue());
        mNumberPicker.setValue(mTypeArray.getInteger(R.styleable.NumberListPreference_value,mNumber));

        mTypeArray.recycle();

        return mNumberPicker;
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        super.onPrepareDialogBuilder(builder);
        builder.setView(mNumberPicker);

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setValue(mNumberPicker.getValue());
                persistInt(mNumber);
                notifyChanged();
            }
        });
    }

    public void setMaxValue(int value){
        mNumberPicker.setMaxValue(value);
    }

    public void setMinValue(int value){
        mNumberPicker.setMinValue(value);
    }

    public void setValue(int value){
        final boolean changed = !(mNumber == value);
        if (changed) {
            mNumber = value;
            persistInt(value);
            notifyChanged();
        }
    }



}
