package com.mnikn.purewei.feature.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.support.adapter.RecyclerViewConfig;
import com.mnikn.library.support.adapter.data.CursorDataProvider;
import com.mnikn.library.support.adapter.divider.HorizontalDivider;
import com.mnikn.library.view.NetRecyclerFragment;
import com.mnikn.purewei.model.UserModel;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.callback.UserLoaderCallback;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserFragment extends NetRecyclerFragment<UserPresenter> {

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
    protected UserPresenter onCreatePresenter() {
        return new UserPresenter(getContext());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(
                Constant.LOADER_USER,
                null,
                new UserLoaderCallback(getContext(),getRecyclerAdapter(), mUserModel.uid));
        getRecyclerAdapter().setHasHeader(true);
    }


    @Override
    protected EasyRecyclerAdapter onCreateAdapter() {
        return new UserAdapter(new CursorDataProvider(),getContext(),mUserModel);
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
