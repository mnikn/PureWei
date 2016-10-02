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
import com.mnikn.mylibrary.mvp.IListPresenter;
import com.mnikn.mylibrary.mvp.IListView;
import com.mnikn.mylibrary.util.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseRecyclerFragment extends BaseFragment implements IListView {

    public SwipeRefreshLayout refreshLayout;
    public RecyclerView recyclerView;

    protected RecyclerView.Adapter mAdapter;
    protected IListPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVariables();

        mAdapter = getAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return getFragmentView(inflater,container,savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = getPresenter();

        initViews(view);
        setupViews(view);
    }

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
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

    private void initViews(View parent){

        recyclerView = (RecyclerView) parent.findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new RecyclerScrollListener(
                mAdapter,
                mPresenter,
                manager));
        recyclerView.setAdapter(mAdapter);


        refreshLayout = (SwipeRefreshLayout) parent.findViewById(R.id.refresh_layout);
        refreshLayout.setColorSchemeResources(android.R.color.holo_red_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_blue_dark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!mPresenter.isLoading()){
                    mPresenter.refresh();
                }
            }
        });
    }

    public abstract void initVariables();
}
