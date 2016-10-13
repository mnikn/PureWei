package com.mnikn.purewei.feature.user;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.mylibrary.adapter.EasyRecyclerCursorAdapter;
import com.mnikn.mylibrary.mvp.presenter.INetListPresenter;
import com.mnikn.mylibrary.mvp.view.fragment.NetRecyclerFragment;
import com.mnikn.mylibrary.widget.RecyclerViewDivider;
import com.mnikn.purewei.R;
import com.mnikn.purewei.model.UserModel;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.callback.UserLoaderCallback;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserFragment extends NetRecyclerFragment {

    private UserModel mUserModel;

    public static UserFragment newInstance() {

        Bundle args = new Bundle();
        
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserModel = getActivity().getIntent().getParcelableExtra(WeiboViewHolder.EXTRA_USER);
    }

    @Override
    public INetListPresenter getPresenter() {
        return new UserPresenter(getContext(),this);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        UserAdapter adapter = new UserAdapter(getContext(),mUserModel);
        adapter.setHasHeader(true);
        return adapter;
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
                new UserLoaderCallback(getContext(), (EasyRecyclerCursorAdapter) mAdapter,mUserModel.uid));
    }
}
