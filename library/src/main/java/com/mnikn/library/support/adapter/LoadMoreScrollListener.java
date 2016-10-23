package com.mnikn.library.support.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mnikn.library.view.net.NetPresenter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    private int mLastVisibleItem;
    private EasyRecyclerAdapter mAdapter;
    private NetPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;

    public LoadMoreScrollListener(EasyRecyclerAdapter adapter, NetPresenter presenter,
                                  LinearLayoutManager layoutManager) {
        mAdapter = adapter;
        mPresenter = presenter;
        mLayoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                mLastVisibleItem + 1 == mAdapter.getItemCount()) {
            if(!mPresenter.isLoading()){
                mPresenter.loadMore();
            }
        }
    }
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
    }
}
