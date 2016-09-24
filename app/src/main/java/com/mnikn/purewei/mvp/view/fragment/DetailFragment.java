package com.mnikn.purewei.mvp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mnikn.mylibrary.fragment.BaseRecyclerFragment;
import com.mnikn.purewei.mvp.presenter.DetailPresenter;
import com.mnikn.purewei.mvp.presenter.IDetailPresenter;
import com.mnikn.purewei.mvp.view.IDetail;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends BaseRecyclerFragment implements IDetail {

    private IDetailPresenter mPresenter;

    public static DetailFragment newInstance() {

        Bundle args = new Bundle();

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupViews(View parent) {
        RecyclerView recyclerView = getRecyclerView();
    }

    @Override
    public void setupPresenter() {
        mPresenter = new DetailPresenter();
    }
}
