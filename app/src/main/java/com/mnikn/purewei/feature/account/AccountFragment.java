package com.mnikn.purewei.feature.account;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.support.adapter.RecyclerViewConfig;
import com.mnikn.library.support.adapter.data.CursorDataProvider;
import com.mnikn.library.support.adapter.divider.HorizontalDivider;
import com.mnikn.library.view.common.RecyclerFragment;
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getLoaderManager().initLoader(
                Constant.LOADER_ACCOUNT,
                null,
                new AccountLoaderCallback(getContext(), getRecyclerAdapter()));
        getRecyclerAdapter().setHasFooter(true);
    }

    @Override
    protected EasyRecyclerAdapter onCreateAdapter() {
        return new AccountAdapter(new CursorDataProvider(),getContext());
    }

    @Override
    protected RecyclerViewConfig.Builder onCreateRecyclerBuilder() {
        return new RecyclerViewConfig.Builder()
                .itemDecoration(new HorizontalDivider(
                        getContext(),
                        LinearLayout.VERTICAL))
                .layoutManager(new LinearLayoutManager(getContext()));
    }
}
