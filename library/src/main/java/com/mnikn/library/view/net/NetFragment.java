package com.mnikn.library.view.net;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mnikn.library.view.base.BaseFragment;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class NetFragment extends BaseFragment<NetPresenter> implements INetView {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
