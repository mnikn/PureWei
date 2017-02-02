package com.mnikn.purewei.feature.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.support.adapter.data.CursorDataProvider;
import com.mnikn.library.support.adapter.divider.RecyclerDivider;
import com.mnikn.library.view.net.NetRecyclerFragment;
import com.mnikn.purewei.model.Status;
import com.mnikn.purewei.support.Constants;
import com.mnikn.purewei.support.callback.DetailLoaderCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends NetRecyclerFragment<DetailPresenter> {

    private Status model;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(
                Constants.LOADER_DETAIL,
                null,
                new DetailLoaderCallback(getContext(), getRecyclerAdapter(), model.id));

        getActivity().registerForContextMenu(getRecyclerView());
        getPresenter().refresh();

        getRecyclerBuilder().itemDecoration(new RecyclerDivider(getContext(), LinearLayout.VERTICAL));
    }

    @Override
    protected DetailPresenter onCreatePresenter() {
        return new DetailPresenter(getContext(),model.id);
    }

    @Override
    protected EasyRecyclerAdapter onCreateAdapter() {
        return new DetailAdapter(new CursorDataProvider(),getContext(),model);
    }
}
