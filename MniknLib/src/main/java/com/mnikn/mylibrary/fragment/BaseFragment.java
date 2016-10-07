package com.mnikn.mylibrary.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.mylibrary.mvp.IView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class BaseFragment extends Fragment implements IView{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        setupViews(view);
        return view;
    }

    protected abstract int getLayoutId();


}
