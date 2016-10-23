package com.mnikn.purewei.feature.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.support.adapter.RecyclerViewConfig;
import com.mnikn.library.support.adapter.data.CursorDataProvider;
import com.mnikn.library.support.adapter.divider.HorizontalDivider;
import com.mnikn.library.view.NetRecyclerFragment;
import com.mnikn.purewei.model.WeiboModel;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.callback.DetailLoaderCallback;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends NetRecyclerFragment<DetailPresenter> {

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(
                Constant.LOADER_DETAIL,
                null,
                new DetailLoaderCallback(getContext(),getRecyclerAdapter(),model.weiboId));

        getActivity().registerForContextMenu(getRecyclerView());
        getPresenter().refresh();
    }

    @Override
    protected DetailPresenter onCreatePresenter() {
        return new DetailPresenter(getContext(),model.weiboId);
    }

    @Override
    protected EasyRecyclerAdapter onCreateAdapter() {
        return new DetailAdapter(new CursorDataProvider(),getContext(),model);
    }

    @Override
    protected RecyclerViewConfig.Builder onCreateRecyclerBuilder() {
        return new RecyclerViewConfig.Builder()
                .itemDecoration(new HorizontalDivider(
                        getContext(),
                        LinearLayout.VERTICAL));
    }
}
