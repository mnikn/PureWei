package com.mnikn.mylibrary.activity;

import android.os.Bundle;

import com.mnikn.mylibrary.R;
import com.mnikn.mylibrary.fragment.BaseFragment;

public abstract class SingleFragmentActivity extends BaseActivity {

    protected abstract BaseFragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(createFragment());
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_fragment;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }
}
