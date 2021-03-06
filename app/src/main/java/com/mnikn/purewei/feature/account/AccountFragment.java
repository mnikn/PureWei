package com.mnikn.purewei.feature.account;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.mylibrary.adapter.EasyRecyclerAdapter;
import com.mnikn.mylibrary.adapter.data.CursorDataProvider;
import com.mnikn.mylibrary.mvp.view.fragment.RecyclerFragment;
import com.mnikn.mylibrary.widget.RecyclerViewDivider;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.callback.AccountLoaderCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends RecyclerFragment {

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();

        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupViews(View parent) {

        mAdapter.setHasFooter(true);
        getRecyclerView().addItemDecoration(new RecyclerViewDivider(
                getContext(),
                LinearLayout.VERTICAL));

        getLoaderManager().initLoader(
                Constant.LOADER_ACCOUNT,
                null,
                new AccountLoaderCallback(getContext(),mAdapter));
    }

    @Override
    public AccountPresenter getPresenter() {
        return new AccountPresenter(getContext(),this);
    }

    @Override
    public EasyRecyclerAdapter getAdapter() {
        return new AccountAdapter(new CursorDataProvider(),getContext());
    }
}
