package com.mnikn.purewei.feature.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.mylibrary.customview.RecyclerViewDivider;
import com.mnikn.mylibrary.fragment.BaseRecyclerFragment;
import com.mnikn.mylibrary.mvp.IListPresenter;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.callback.DetailLoaderCallback;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailViewFragment extends BaseRecyclerFragment implements IDetailView {

    private long mWeiboId;

    public static DetailViewFragment newInstance() {

        Bundle args = new Bundle();

        DetailViewFragment fragment = new DetailViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IListPresenter getPresenter() {
        return new DetailPresenter(getContext(),this,mWeiboId);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new DetailAdapter(getContext(),mWeiboId);
    }

    @Override
    public void setupViews(View parent) {
        getRecyclerView().addItemDecoration(new RecyclerViewDivider(
                getContext(),
                LinearLayout.VERTICAL));

        getActivity().getSupportLoaderManager().initLoader(
                Constant.LOADER_DETAIL,
                null,
                new DetailLoaderCallback(getContext(), (RecyclerCursorAdapter) mAdapter, mWeiboId));

        mPresenter.refresh();
    }

    @Override
    public void initVariables() {
        mWeiboId = getActivity().getIntent().getLongExtra(WeiboViewHolder.EXTRA_WEIBO_ID,0);
    }
}
