package com.mnikn.purewei.mvp.view.activity;

import com.mnikn.mylibrary.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.fragment.BaseFragment;
import com.mnikn.purewei.mvp.view.fragment.DetailFragment;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailActivity extends SingleFragmentActivity {

    @Override
    public BaseFragment getFragment() {
        return DetailFragment.newInstance();
    }
}
