package com.mnikn.purewei.feature.detail;

import android.view.Menu;

import com.mnikn.mylibrary.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.fragment.BaseFragment;
import com.mnikn.purewei.R;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailActivity extends SingleFragmentActivity {

    @Override
    public BaseFragment getFragment() {
        return DetailViewFragment.newInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
