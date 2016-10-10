package com.mnikn.purewei.feature.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.mylibrary.adapter.EasyRecyclerCursorAdapter;
import com.mnikn.mylibrary.fragment.BaseRecyclerFragment;
import com.mnikn.mylibrary.mvp.IListPresenter;
import com.mnikn.mylibrary.widget.RecyclerViewDivider;
import com.mnikn.purewei.model.WeiboModel;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.callback.DetailLoaderCallback;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends BaseRecyclerFragment implements IDetailView {

    private WeiboModel model;

    public static DetailFragment newInstance() {

        Bundle args = new Bundle();

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariables() {
        model = getActivity().getIntent().getParcelableExtra(WeiboViewHolder.EXTRA_WEIBO_ID);
    }

    @Override
    public IListPresenter getPresenter() {
        return new DetailPresenter(getContext(),this,model.weiboId);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new DetailAdapter(getContext(),model);
    }



    @Override
    public void setupViews(View parent) {
        
        getRecyclerView().addItemDecoration(new RecyclerViewDivider(
                getContext(),
                LinearLayout.VERTICAL));

        getActivity().getSupportLoaderManager().initLoader(
                Constant.LOADER_DETAIL,
                null,
                new DetailLoaderCallback(getContext(), (EasyRecyclerCursorAdapter) mAdapter,model.weiboId));

        getActivity().registerForContextMenu(getRecyclerView());
    }

    @Override
    protected int getRecyclerViewId() {
        return 0;
    }
    @Override
    protected int getRefreshLayoutId() {
        return 0;
    }
    @Override
    protected int getLayoutId() {
        return 0;
    }


}
