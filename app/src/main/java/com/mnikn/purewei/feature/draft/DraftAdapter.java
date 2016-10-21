package com.mnikn.purewei.feature.draft;

import android.view.ViewGroup;

import com.mnikn.mylibrary.adapter.EasyRecyclerAdapter;
import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.adapter.data.CursorDataProvider;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DraftAdapter extends EasyRecyclerAdapter<CursorDataProvider,Object>{

    public DraftAdapter(CursorDataProvider dataProvider) {
        super(dataProvider);
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
