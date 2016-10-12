package com.mnikn.purewei.feature.write;

import android.view.Menu;

import com.mnikn.mylibrary.mvp.view.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.mvp.view.fragment.BaseFragment;
import com.mnikn.purewei.R;

public class WriteActivity extends SingleFragmentActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.write,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public BaseFragment getFragment() {
        return WriteFragment.newInstance();
    }
}
