package com.mnikn.purewei.feature.account;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mnikn.mylibrary.mvp.presenter.IPresenter;
import com.mnikn.mylibrary.mvp.view.fragment.RecyclerFragment;

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
    public void setupViews(View parent) {

    }

    @Override
    public <P extends IPresenter> P getPresenter() {
        return null;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return null;
    }
}
