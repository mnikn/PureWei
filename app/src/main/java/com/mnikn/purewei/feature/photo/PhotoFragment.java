package com.mnikn.purewei.feature.photo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.mylibrary.fragment.BaseFragment;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends BaseFragment {

    @BindView(R.id.photo_view) PhotoView photoView;

    public static PhotoFragment newInstance() {

        Bundle args = new Bundle();

        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo,container,false);
    }

    @Override
    public void setupViews(View parent) {
        ButterKnife.bind(this,parent);

        String url = getActivity().getIntent().getStringExtra(WeiboViewHolder.EXTRA_PHOTO_URL);
        GlideUtil.setImage(getContext(),url,photoView);
    }
}
