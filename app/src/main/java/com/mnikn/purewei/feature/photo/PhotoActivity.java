package com.mnikn.purewei.feature.photo;

import com.mnikn.library.view.BaseFragment;
import com.mnikn.library.view.BaseSingleFragmentActivity;

public class PhotoActivity extends BaseSingleFragmentActivity {
    
    @Override
    protected BaseFragment onCreateFragment() {
        return PhotoFragment.newInstance();
    }
}
