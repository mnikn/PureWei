package com.mnikn.purewei.feature.write;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mnikn.mylibrary.mvp.presenter.IPresenter;
import com.mnikn.mylibrary.mvp.view.fragment.BaseFragment;
import com.mnikn.purewei.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteFragment extends BaseFragment {

    @BindView(R.id.edit_weibo) EditText mEditText;
    @BindView(R.id.imgBtn_at) ImageButton mButtonAt;
    @BindView(R.id.imgBtn_photo) ImageButton mButtonPhoto;
    @BindView(R.id.imgBtn_send) ImageButton mButtonSend;

    public static WriteFragment newInstance() {

        Bundle args = new Bundle();

        WriteFragment fragment = new WriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupViews(View parent) {
        ButterKnife.bind(this,parent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_write;
    }

    @Override
    public <P extends IPresenter> P getPresenter() {
        return null;
    }
}
