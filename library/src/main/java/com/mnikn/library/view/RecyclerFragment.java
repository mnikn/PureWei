package com.mnikn.library.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mnikn.library.R;
import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.support.adapter.RecyclerViewConfig;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class RecyclerFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private EasyRecyclerAdapter<?,?> mRecyclerAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(getRecyclerViewId());
        mRecyclerAdapter = onCreateAdapter();

        onCreateRecyclerBuilder()
                .recyclerView(mRecyclerView)
                .recyclerAdapter(mRecyclerAdapter)
                .build();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }

    public RecyclerView getRecyclerView(){
        return mRecyclerView;
    }

    public EasyRecyclerAdapter getRecyclerAdapter(){
        return mRecyclerAdapter;
    }

    /**
     * If you need to custom your own layout,you may have to override this function.
     */
    protected int getRecyclerViewId(){
        return R.id.recycler;
    }

    protected abstract EasyRecyclerAdapter onCreateAdapter();

    protected abstract RecyclerViewConfig.Builder onCreateRecyclerBuilder();
}
