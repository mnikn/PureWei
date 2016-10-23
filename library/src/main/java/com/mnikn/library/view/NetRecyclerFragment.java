package com.mnikn.library.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;

import com.mnikn.library.R;
import com.mnikn.library.utils.ViewUtils;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class NetRecyclerFragment extends RecyclerFragment implements NetView{

    private SwipeRefreshLayout mRefreshLayout;
    private ProgressBar mProgressBar;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(getRefreshViewId());
        mProgressBar = (ProgressBar) view.findViewById(getProgressBarId());
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
    public void onLoadFinish() {
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
