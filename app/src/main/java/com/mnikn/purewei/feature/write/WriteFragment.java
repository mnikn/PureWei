package com.mnikn.purewei.feature.write;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mnikn.mylibrary.fragment.BaseFragment;
import com.mnikn.purewei.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteFragment extends BaseFragment {

    @BindView(R.id.edit) EditText mEditText;
    @BindView(R.id.imgBtn_at) ImageButton mButtonAt;
    @BindView(R.id.imgBtn_photo) ImageButton mButtonPhoto;
    @BindView(R.id.imgBtn_send) ImageButton mButtonSend;

    public static WriteFragment newInstance() {

        Bundle args = new Bundle();

        WriteFragment fragment = new WriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_write;
    }

    @Override
    public void setupViews(View parent) {
        ButterKnife.bind(this, parent);
    }
}
