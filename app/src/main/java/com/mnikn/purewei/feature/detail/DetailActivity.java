package com.mnikn.purewei.feature.detail;

import com.mnikn.mylibrary.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.fragment.BaseFragment;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailActivity extends SingleFragmentActivity {

    @Override
    public BaseFragment getFragment() {
        return DetailFragment.newInstance();
    }
}
