package com.mnikn.library.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mnikn.library.R;
import com.mnikn.library.support.image.ImageDisplayUtils;
import com.mnikn.library.view.base.BaseFragment;

import java.io.File;

import uk.co.senab.photoview.PhotoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends BaseFragment {

    private PhotoView mPhotoView;

    public static PhotoFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);

        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public static PhotoFragment newInstance(File file) {
        Bundle args = new Bundle();
        args.putSerializable("file", file);

        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPhotoView = (PhotoView) view.findViewById(R.id.photo);

        String url = getArguments().getString("url");
        if(url != null){
            ImageDisplayUtils.displayFromNet(
                    getContext(),
                    url,
                    mPhotoView);
        }
        else{
            ImageDisplayUtils.displayFromFile(
                    getContext(),
                    (File) getArguments().getSerializable("file"),
                    mPhotoView);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_photo;
    }
}
