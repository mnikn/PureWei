package com.mnikn.purewei.feature.account;

import com.mnikn.library.view.BaseFragment;
import com.mnikn.library.view.BaseSingleFragmentActivity;

public class AccountActivity extends BaseSingleFragmentActivity {

    @Override
    protected BaseFragment onCreateFragment() {
        return AccountFragment.newInstance();
    }
}
