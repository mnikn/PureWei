package com.mnikn.purewei.feature.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.mylibrary.fragment.BaseRecyclerFragment;
import com.mnikn.mylibrary.mvp.IListPresenter;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.callback.DetailLoaderCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends BaseRecyclerFragment implements IDetail {

    public static final int LOADER_DETAIL = 3;

    public static DetailFragment newInstance() {

        Bundle args = new Bundle();

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IListPresenter getPresenter() {
        return new DetailPresenter();
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new DetailAdapter(getContext());
    }

    @Override
    public void setupViews(View parent) {
    }

    @Override
    public void initVariables() {
        getActivity().getSupportLoaderManager().initLoader(
                Constant.LOADER_DETAIL,
                null,
                new DetailLoaderCallback(getContext(),(RecyclerCursorAdapter) mAdapter));
    }
}
