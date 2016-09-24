package com.mnikn.purewei.support.base;

import android.os.Bundle;

import com.mnikn.mylibrary.activity.BaseActivity;
import com.mnikn.mylibrary.fragment.BaseFragment;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class SingleFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(getFragment());
    }

    public abstract BaseFragment getFragment();
}
