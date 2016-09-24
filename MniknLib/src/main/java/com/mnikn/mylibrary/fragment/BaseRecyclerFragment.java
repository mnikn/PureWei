package com.mnikn.mylibrary.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.mylibrary.R;
import com.mnikn.mylibrary.mvp.IListView;
import com.mnikn.mylibrary.util.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseRecyclerFragment extends BaseFragment implements IListView {

    public SwipeRefreshLayout refreshLayout;
    public RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getFragmentView(inflater,container,savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

        setupViews(view);
        setupPresenter();
        return view;
    }

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        ToastUtil.makeToastShort(getContext(), "正在刷新");
    }

    @Override
    public void onRefreshFinish() {
        refreshLayout.setRefreshing(false);
        ToastUtil.makeToastShort(getContext(), "刷新完成");
    }

    @Override
    public void onLoadMore() {
        refreshLayout.setRefreshing(true);
        ToastUtil.makeToastShort(getContext(), "正在加载");
    }

    @Override
    public void onLoadMoreFinish() {
        refreshLayout.setRefreshing(false);
        ToastUtil.makeToastShort(getContext(), "加载完成");
    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }

    public SwipeRefreshLayout getRefreshLayout(){
        return refreshLayout;
    }
}
