package com.mnikn.purewei.feature.user;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.mylibrary.adapter.EasyRecyclerCursorAdapter;
import com.mnikn.mylibrary.fragment.BaseRecyclerFragment;
import com.mnikn.mylibrary.mvp.IListPresenter;
import com.mnikn.mylibrary.widget.RecyclerViewDivider;
import com.mnikn.purewei.R;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.callback.UserLoaderCallback;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserFragment extends BaseRecyclerFragment implements IUserView {

    private long mUid;

    public static UserFragment newInstance() {

        Bundle args = new Bundle();
        
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariables() {
        mUid = getActivity().getIntent().getLongExtra(WeiboViewHolder.EXTRA_UID,0);
    }

    @Override
    public IListPresenter getPresenter() {
        return new UserPresenter(getContext(),this);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new UserAdapter(getContext());
    }

    @Override
    public void setupViews(View parent) {
        getRecyclerView().addItemDecoration(new RecyclerViewDivider(
                getContext(),
                LinearLayout.VERTICAL,
                R.drawable.item_divider));

        getActivity().getSupportLoaderManager().initLoader(
                Constant.LOADER_USER,
                null,
                new UserLoaderCallback(getContext(), (EasyRecyclerCursorAdapter) mAdapter, mUid));
    }

    @Override
    protected int getRecyclerViewId() {
        return 0;
    }
    @Override
    protected int getRefreshLayoutId() {
        return 0;
    }
    @Override
    protected int getLayoutId() {
        return 0;
    }
}
