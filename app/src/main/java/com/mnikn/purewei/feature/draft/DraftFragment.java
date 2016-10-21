package com.mnikn.purewei.feature.draft;

import android.os.Bundle;
import android.view.View;

import com.mnikn.mylibrary.adapter.EasyRecyclerAdapter;
import com.mnikn.mylibrary.mvp.view.fragment.RecyclerFragment;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DraftFragment extends RecyclerFragment{

    public static DraftFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DraftFragment fragment = new DraftFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public EasyRecyclerAdapter getAdapter() {
        return null;
    }

    @Override
    public void setupViews(View parent) {

    }

    @Override
    public DraftPresenter getPresenter() {
        return new DraftPresenter(getContext(),this);
    }
}
