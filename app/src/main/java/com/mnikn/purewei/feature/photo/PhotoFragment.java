package com.mnikn.purewei.feature.photo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mnikn.library.view.BaseFragment;
import com.mnikn.purewei.R;
import com.mnikn.purewei.support.util.ImageDisplayUtil;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends BaseFragment {

    @BindView(R.id.photo) PhotoView photoView;

    public static PhotoFragment newInstance() {

        Bundle args = new Bundle();

        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        String url = getActivity().getIntent().getStringExtra(WeiboViewHolder.EXTRA_PHOTO_URL);
        if(url != null){
            ImageDisplayUtil.displayFromNet(getContext(), url, photoView);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_photo;
    }
}
