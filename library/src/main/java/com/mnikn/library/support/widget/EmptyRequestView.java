package com.mnikn.library.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mnikn.library.R;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class EmptyRequestView extends LinearLayout {

    private TextView mTxtContent;
    private Button mBtnRequest;
    private TypedArray mTypedArray;

    public EmptyRequestView(Context context) {
        super(context);
    }
    public EmptyRequestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTypedArray = context.obtainStyledAttributes(attrs,R.styleable.EmptyRequestView);
        setupView();
    }

    private void setupView(){

        mTxtContent = new TextView(getContext());
        mBtnRequest = new Button(getContext());

        String txtContent = mTypedArray.getString(R.styleable.EmptyRequestView_txt_content);
        String btnText = mTypedArray.getString(R.styleable.EmptyRequestView_btn_text);

        if(txtContent == null || txtContent.isEmpty()){
            txtContent = getContext().getResources().getString(R.string.error_default_txt_content);
        }
        if(btnText == null || btnText.isEmpty()){
            btnText = getContext().getResources().getString(R.string.error_default_btn_text);
        }
        mTxtContent.setText(txtContent);
        mBtnRequest.setText(btnText);

        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(mTxtContent,params);
        addView(mBtnRequest, params);

        mTypedArray.recycle();
    }

    public void setOnButtonClickListener(OnClickListener listener){
        mBtnRequest.setOnClickListener(listener);
    }

}
