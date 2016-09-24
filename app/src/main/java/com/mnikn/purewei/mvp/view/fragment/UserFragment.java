package com.mnikn.purewei.mvp.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.mylibrary.customview.RecyclerViewDivider;
import com.mnikn.mylibrary.fragment.BaseRecyclerFragment;
import com.mnikn.purewei.R;
import com.mnikn.purewei.mvp.presenter.IUserPresenter;
import com.mnikn.purewei.mvp.presenter.UserPresenter;
import com.mnikn.purewei.mvp.view.IUserView;
import com.mnikn.purewei.support.adapter.UserAdapter;
import com.mnikn.purewei.support.adapter.WeiboViewHolder;
import com.mnikn.purewei.support.callback.CursorLoaderCallback;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserFragment extends BaseRecyclerFragment implements IUserView {

    private static final int LOADER_USRE = 2;

    private long mUid;
    private UserAdapter mAdapter;
    private IUserPresenter mPresenter;


    public static UserFragment newInstance() {

        Bundle args = new Bundle();
        
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
    }

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    private void initVariables(){

        mUid = getActivity().getIntent().getLongExtra(WeiboViewHolder.EXTRA_UID,0);
        mAdapter = new UserAdapter(getContext());
        getActivity().getSupportLoaderManager().initLoader(
                LOADER_USRE,
                null,
                new CursorLoaderCallback(getContext(),mAdapter,mUid));
    }

    @Override
    public void setupViews(View parent) {

        RecyclerView recyclerView = getRecyclerView();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL, R.drawable.item_divider));
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void setupPresenter() {
        mPresenter = new UserPresenter(this);
    }
}
