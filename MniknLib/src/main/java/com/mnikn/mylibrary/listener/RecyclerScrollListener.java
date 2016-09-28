package com.mnikn.mylibrary.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mnikn.mylibrary.mvp.IListPresenter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class RecyclerScrollListener extends RecyclerView.OnScrollListener {

    private int mLastVisibleItem;
    private RecyclerView.Adapter mAdapter;
    private IListPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;

    public RecyclerScrollListener(RecyclerView.Adapter adapter, IListPresenter presenter,
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
