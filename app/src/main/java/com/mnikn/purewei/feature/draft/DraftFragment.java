package com.mnikn.purewei.feature.draft;

import android.os.Bundle;

import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.support.adapter.RecyclerViewConfig;
import com.mnikn.library.view.common.RecyclerFragment;

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
    protected EasyRecyclerAdapter onCreateAdapter() {
        return null;
    }

    @Override
    protected RecyclerViewConfig.Builder onCreateRecyclerBuilder() {
        return null;
    }
}
