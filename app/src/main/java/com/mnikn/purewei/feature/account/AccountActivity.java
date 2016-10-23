package com.mnikn.purewei.feature.account;

import com.mnikn.library.view.base.BaseFragment;
import com.mnikn.library.view.base.BaseSingleFragmentActivity;

public class AccountActivity extends BaseSingleFragmentActivity {

    @Override
    protected BaseFragment onCreateFragment() {
        return AccountFragment.newInstance();
    }
}
