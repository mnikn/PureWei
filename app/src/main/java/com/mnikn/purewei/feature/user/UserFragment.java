package com.mnikn.purewei.feature.user;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.mylibrary.customview.RecyclerViewDivider;
import com.mnikn.mylibrary.fragment.BaseRecyclerFragment;
import com.mnikn.mylibrary.mvp.IListPresenter;
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
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void initVariables() {
        mUid = getActivity().getIntent().getLongExtra(WeiboViewHolder.EXTRA_UID,0);
        getActivity().getSupportLoaderManager().initLoader(
                Constant.LOADER_USER,
                null,
                new UserLoaderCallback(getContext(), (RecyclerCursorAdapter) mAdapter, mUid));
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
    }
}
