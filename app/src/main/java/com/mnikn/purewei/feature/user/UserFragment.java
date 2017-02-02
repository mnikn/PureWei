package com.mnikn.purewei.feature.user;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mnikn.library.support.adapter.EasyRecyclerAdapter;
import com.mnikn.library.support.adapter.data.CursorDataProvider;
import com.mnikn.library.support.adapter.divider.RecyclerDivider;
import com.mnikn.library.view.net.NetRecyclerFragment;
import com.mnikn.purewei.model.User;
import com.mnikn.purewei.support.Constants;
import com.mnikn.purewei.support.callback.UserLoaderCallback;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserFragment extends NetRecyclerFragment<UserPresenter> {

    private User mUserModel;

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
                Constants.LOADER_USER,
                null,
                new UserLoaderCallback(getContext(), getRecyclerAdapter(), mUserModel.id));
        getRecyclerAdapter().setHasHeader(true);

        getRecyclerBuilder()
                .itemDecoration(new RecyclerDivider(getContext(), LinearLayout.VERTICAL))
                .build();
    }


    @Override
    protected EasyRecyclerAdapter onCreateAdapter() {
        return new UserAdapter(new CursorDataProvider(),getContext(),mUserModel);
    }
}
