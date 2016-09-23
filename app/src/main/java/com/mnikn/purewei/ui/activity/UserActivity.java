package com.mnikn.purewei.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mnikn.mylibrary.activity.BaseActivity;
import com.mnikn.purewei.R;
import com.mnikn.purewei.ui.fragment.UserFragment;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class UserActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        addFragment(UserFragment.newInstance());
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

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }
}
