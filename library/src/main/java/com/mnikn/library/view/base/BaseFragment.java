package com.mnikn.library.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 * All fragment need to extends BaseFragment
 */
public abstract class BaseFragment<P extends Presenter> extends Fragment {

    private P mPresenter;
    private boolean mHasPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }
    @Override
    @SuppressWarnings("unchecked")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = onCreatePresenter();

        if(mPresenter != null){
            mHasPresenter = true;
            mPresenter.create();
            mPresenter.takeView(this);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mHasPresenter){
            mPresenter.dropView();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mHasPresenter){
            mPresenter.destroy();
        }
    }

    public boolean hasPresenter(){
        return mHasPresenter;
    }

    public P getPresenter(){
        return mPresenter;
    }

    @SuppressWarnings("unchecked")
    protected P onCreatePresenter() {
        return null;
    }

    protected abstract int getLayoutId();


}
