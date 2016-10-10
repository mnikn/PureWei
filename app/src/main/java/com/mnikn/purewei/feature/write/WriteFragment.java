package com.mnikn.purewei.feature.write;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.mnikn.mylibrary.fragment.BaseFragment;
import com.mnikn.purewei.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteFragment extends BaseFragment {

    @BindView(R.id.edit) EditText mEditText;
    @BindView(R.id.toolbar) Toolbar mToolbar;

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
        ButterKnife.bind(this,parent);
    }
}
