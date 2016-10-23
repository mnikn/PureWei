package com.mnikn.library.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mnikn.library.R;
import com.mnikn.library.presenter.Presenter;
import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.support.adapter.RecyclerViewConfig;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class RecyclerFragment<P extends Presenter> extends BaseFragment<P> {

    private RecyclerView mRecyclerView;
    private EasyRecyclerAdapter<?,?> mRecyclerAdapter;
    private RecyclerViewConfig.Builder mBuilder;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(getRecyclerViewId());
        mRecyclerAdapter = onCreateAdapter();

        mBuilder = onCreateRecyclerBuilder()
                .recyclerView(mRecyclerView)
                .recyclerAdapter(mRecyclerAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBuilder.build();
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

    public RecyclerViewConfig.Builder getRecyclerBuilder(){
        return mBuilder;
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
