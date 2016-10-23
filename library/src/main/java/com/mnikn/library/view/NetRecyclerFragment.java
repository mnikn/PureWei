package com.mnikn.library.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;

import com.mnikn.library.R;
import com.mnikn.library.presenter.NetPresenter;
import com.mnikn.library.utils.ViewUtils;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class NetRecyclerFragment<P extends NetPresenter> extends RecyclerFragment<P>
        implements INetView {

    private SwipeRefreshLayout mRefreshLayout;
    private ProgressBar mProgressBar;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(getRefreshViewId());
        mProgressBar = (ProgressBar) view.findViewById(getProgressBarId());

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().refresh();
            }
        });

        getPresenter().refresh();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_net_recycler;
    }
    @Override
    protected int getRecyclerViewId() {
        return R.id.recycler;
    }
    protected int getRefreshViewId(){
        return R.id.refresh;
    }
    protected int getProgressBarId(){
        return R.id.process;
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);
    }
    @Override
    public void onLoadMore() {
        ViewUtils.setGone(mProgressBar,false);
    }

    @Override
    public void onRefreshFinish() {
        mRefreshLayout.setRefreshing(false);
    }
    @Override
    public void onLoadMoreFinish() {
        mRefreshLayout.setRefreshing(false);
        ViewUtils.setGone(mProgressBar,true);
    }

    public SwipeRefreshLayout getRefreshLayout(){
        return mRefreshLayout;
    }
    public ProgressBar getProgressBar(){
        return mProgressBar;
    }
}
