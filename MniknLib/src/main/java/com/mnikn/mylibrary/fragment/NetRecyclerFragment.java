package com.mnikn.mylibrary.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.mylibrary.R;
import com.mnikn.mylibrary.listener.RecyclerScrollListener;
import com.mnikn.mylibrary.mvp.presenter.INetListPresenter;
import com.mnikn.mylibrary.mvp.view.INetListView;
import com.mnikn.mylibrary.mvp.view.fragment.BaseFragment;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.mylibrary.util.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class NetRecyclerFragment extends BaseFragment implements INetListView {

    public SwipeRefreshLayout refreshLayout;
    public RecyclerView recyclerView;

    protected RecyclerView.Adapter mAdapter;
    protected INetListPresenter mPresenter;

    private boolean mIsDefaultLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();

        mAdapter = getAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        View view;
        if(NumberUtil.isZero(layoutId)){
            view = inflater.inflate(R.layout.fragment_recycler_net,container,false);
            mIsDefaultLayout = true;
        }
        else{
            view = inflater.inflate(layoutId,container,false);
            mIsDefaultLayout = false;
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = getPresenter();

        initViews(view);
        setupViews(view);
    }

    private void initViews(View parent){
        if(mIsDefaultLayout){
            recyclerView = (RecyclerView) parent.findViewById(R.id.recycler);
            refreshLayout = (SwipeRefreshLayout) parent.findViewById(R.id.refresh_layout);
        }
        else{
            recyclerView = (RecyclerView) parent.findViewById(getRecyclerViewId());
            refreshLayout = (SwipeRefreshLayout) parent.findViewById(getRefreshLayoutId());
        }

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new RecyclerScrollListener(
                mAdapter,
                mPresenter,
                manager));
        recyclerView.setAdapter(mAdapter);

        refreshLayout.setColorSchemeResources(android.R.color.holo_red_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_blue_dark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mPresenter.isLoading()) {
                    mPresenter.refresh();
                }
            }
        });


    }

    @Override
    public void onStop() {
        super.onStop();
        refreshLayout.setRefreshing(false);
        mPresenter.cancelLoading();
    }

    @Override
    public void onRefresh() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onRefreshFinish() {
        refreshLayout.setRefreshing(false);
        mPresenter.setIsLoading(false);
        recyclerView.scrollToPosition(0);
        if(getContext() != null){
            ToastUtil.makeToastShort(getContext(), "刷新完成");
        }
    }

    @Override
    public void onLoadMore() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void onLoadMoreFinish() {
        refreshLayout.setRefreshing(false);
        mPresenter.setIsLoading(false);
        if(getContext() != null){
            ToastUtil.makeToastShort(getContext(), "加载完成");
        }
    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }
    public SwipeRefreshLayout getRefreshLayout(){
        return refreshLayout;
    }

    protected abstract void initVariables();

    //If get id is zero will use default layout
    protected abstract int getRecyclerViewId();
    protected abstract int getRefreshLayoutId();



}
