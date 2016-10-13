package com.mnikn.purewei.feature.account;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.mylibrary.adapter.EasyRecyclerCursorAdapter;
import com.mnikn.mylibrary.mvp.view.fragment.RecyclerFragment;
import com.mnikn.mylibrary.widget.RecyclerViewDivider;
import com.mnikn.purewei.R;
import com.mnikn.purewei.support.callback.AccountLoaderCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends RecyclerFragment {

    private static int ACCOUNT_LOADER = 104;

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

        getRecyclerView().addItemDecoration(new RecyclerViewDivider(
                getContext(),
                LinearLayout.VERTICAL,
                R.drawable.item_divider));

        getLoaderManager().initLoader(
                ACCOUNT_LOADER,
                null,
                new AccountLoaderCallback(getContext(),(EasyRecyclerCursorAdapter) mAdapter));
    }

    @Override
    public AccountPresenter getPresenter() {
        return new AccountPresenter(getContext(),this);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new AccountAdapter(getContext());
    }
}
