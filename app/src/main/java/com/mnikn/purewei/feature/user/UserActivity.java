package com.mnikn.purewei.feature.user;

import android.view.Menu;

import com.mnikn.library.view.base.BaseFragment;
import com.mnikn.library.view.base.BaseSingleFragmentActivity;
import com.mnikn.purewei.R;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserActivity extends BaseSingleFragmentActivity {

    @Override
    protected BaseFragment onCreateFragment() {
        return UserFragment.newInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }


}
