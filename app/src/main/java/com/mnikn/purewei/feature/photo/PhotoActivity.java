package com.mnikn.purewei.feature.photo;

import com.mnikn.library.view.base.BaseFragment;
import com.mnikn.library.view.base.BaseSingleFragmentActivity;
import com.mnikn.library.view.PhotoFragment;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

public class PhotoActivity extends BaseSingleFragmentActivity {
    
    @Override
    protected BaseFragment onCreateFragment() {
        return PhotoFragment.newInstance(getIntent().getStringExtra(WeiboViewHolder.EXTRA_PHOTO_URL));
    }
}
