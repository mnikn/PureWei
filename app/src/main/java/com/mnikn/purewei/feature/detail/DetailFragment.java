package com.mnikn.purewei.feature.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.mylibrary.adapter.EasyRecyclerAdapter;
import com.mnikn.mylibrary.adapter.data.CursorDataProvider;
import com.mnikn.mylibrary.mvp.presenter.INetListPresenter;
import com.mnikn.mylibrary.mvp.view.fragment.NetRecyclerFragment;
import com.mnikn.mylibrary.widget.RecyclerViewDivider;
import com.mnikn.purewei.model.WeiboModel;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.callback.DetailLoaderCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends NetRecyclerFragment {

    private WeiboModel model;

    public static DetailFragment newInstance() {

        Bundle args = new Bundle();

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = getActivity().getIntent().getParcelableExtra(DetailActivity.EXTRA_MODEL);
    }


    @Override
    public INetListPresenter getPresenter() {
        return new DetailPresenter(getContext(),this,model.weiboId);
    }

    @Override
    public EasyRecyclerAdapter getAdapter() {
        DetailAdapter adapter = new DetailAdapter(new CursorDataProvider(),getContext(),model);
        adapter.setHasHeader(true);
        return adapter;
    }

    @Override
    public void setupViews(View parent) {
        
        getRecyclerView().addItemDecoration(new RecyclerViewDivider(
                getContext(),
                LinearLayout.VERTICAL));

        getActivity().getSupportLoaderManager().initLoader(
                Constant.LOADER_DETAIL,
                null,
                new DetailLoaderCallback(getContext(),mAdapter,model.weiboId));

        getActivity().registerForContextMenu(getRecyclerView());

        mPresenter.refresh();
    }

}
