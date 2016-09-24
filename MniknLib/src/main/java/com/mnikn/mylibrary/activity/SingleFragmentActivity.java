package com.mnikn.mylibrary.activity;

import android.os.Bundle;

import com.mnikn.mylibrary.R;
import com.mnikn.mylibrary.fragment.BaseFragment;

import butterknife.ButterKnife;

public abstract class SingleFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        ButterKnife.bind(this);

        addFragment(getFragment());
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    public abstract BaseFragment getFragment();
}
