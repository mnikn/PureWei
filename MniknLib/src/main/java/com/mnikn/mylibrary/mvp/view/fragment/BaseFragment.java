package com.mnikn.mylibrary.mvp.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.mylibrary.mvp.presenter.IPresenter;
import com.mnikn.mylibrary.mvp.view.IView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class BaseFragment<P extends IPresenter> extends Fragment
        implements IView {

    private P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        setupViews(view);
        mPresenter = getPresenter();
        return view;
    }


    protected abstract int getLayoutId();

}
