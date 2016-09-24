package com.mnikn.purewei.ui.activity;

import com.mnikn.mylibrary.fragment.BaseFragment;
import com.mnikn.purewei.support.base.SingleFragmentActivity;
import com.mnikn.purewei.ui.fragment.DetailFragment;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailActivity extends SingleFragmentActivity {

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    public BaseFragment getFragment() {
        return DetailFragment.newInstance();
    }
}
