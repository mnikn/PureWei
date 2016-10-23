package com.mnikn.purewei.feature.draft;

import android.os.Bundle;

import com.mnikn.library.support.adapter.RecyclerViewConfig;
import com.mnikn.library.view.RecyclerFragment;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DraftFragment extends RecyclerFragment {

    public static DraftFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DraftFragment fragment = new DraftFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected com.mnikn.library.support.adapter.EasyRecyclerAdapter onCreateAdapter() {
        return null;
    }

    @Override
    protected RecyclerViewConfig.Builder onCreateRecyclerBuilder() {
        return null;
    }
}
