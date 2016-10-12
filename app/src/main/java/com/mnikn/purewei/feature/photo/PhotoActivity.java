package com.mnikn.purewei.feature.photo;

import com.mnikn.mylibrary.mvp.view.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.mvp.view.fragment.BaseFragment;

public class PhotoActivity extends SingleFragmentActivity {

    @Override
    public BaseFragment getFragment() {
        return PhotoFragment.newInstance();
    }
}
