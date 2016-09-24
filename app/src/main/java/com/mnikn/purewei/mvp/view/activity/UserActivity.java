package com.mnikn.purewei.mvp.view.activity;

import android.view.Menu;
import android.view.MenuItem;

import com.mnikn.mylibrary.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.fragment.BaseFragment;
import com.mnikn.purewei.R;
import com.mnikn.purewei.mvp.view.fragment.UserFragment;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
