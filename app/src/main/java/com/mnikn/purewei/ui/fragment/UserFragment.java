package com.mnikn.purewei.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.mylibrary.customview.RecyclerViewDivider;
import com.mnikn.mylibrary.fragment.BaseFragment;
import com.mnikn.purewei.R;
import com.mnikn.purewei.mvp.IUserView;
import com.mnikn.purewei.mvp.presenter.IUserPresenter;
import com.mnikn.purewei.mvp.presenter.UserPresenter;
import com.mnikn.purewei.support.adapter.UserAdapter;
import com.mnikn.purewei.support.callback.CursorLoaderCallback;
import com.mnikn.purewei.ui.activity.HomeActivity;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserFragment extends BaseFragment implements IUserView {

    private static final int LOADER_USRE = 2;

    private RecyclerView rvUser;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);

        initVariables();
        setupViews(view);
        setupPresenter();

        return view;
    }

    private void initVariables(){
        mUid = getActivity().getIntent().getLongExtra(HomeActivity.EXTRA_UID,0);
        mAdapter = new UserAdapter(getContext());
        getActivity().getSupportLoaderManager().initLoader(
                LOADER_USRE,
                null,
                new CursorLoaderCallback(getContext(),mAdapter,mUid));
    }

    @Override
    public void setupViews(View parent) {
        rvUser = (RecyclerView) parent.findViewById(R.id.rv_user);
        rvUser.setLayoutManager(new LinearLayoutManager(getContext()));
        rvUser.addItemDecoration(new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL,R.drawable.item_divider));
        rvUser.setAdapter(mAdapter);
    }

    @Override
    public void setupPresenter() {
        mPresenter = new UserPresenter(this);
    }
}
