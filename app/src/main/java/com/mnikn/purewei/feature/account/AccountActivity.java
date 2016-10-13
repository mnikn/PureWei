package com.mnikn.purewei.feature.account;

import com.mnikn.mylibrary.mvp.view.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.mvp.view.fragment.BaseFragment;

public class AccountActivity extends SingleFragmentActivity {

    @Override
    public BaseFragment getFragment() {
        return AccountFragment.newInstance();
    }
}
