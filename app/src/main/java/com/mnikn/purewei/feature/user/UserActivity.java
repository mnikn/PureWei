package com.mnikn.purewei.feature.user;

import android.view.Menu;

import com.mnikn.mylibrary.mvp.view.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.mvp.view.fragment.BaseFragment;
import com.mnikn.purewei.R;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserActivity extends SingleFragmentActivity {

    @Override
    public BaseFragment getFragment() {
        return UserFragment.newInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }


}
