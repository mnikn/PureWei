package com.mnikn.purewei.feature.photo;

import com.mnikn.mylibrary.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.fragment.BaseFragment;

public class PhotoActivity extends SingleFragmentActivity {

    @Override
    public BaseFragment getFragment() {
        return PhotoFragment.newInstance();
    }
}
