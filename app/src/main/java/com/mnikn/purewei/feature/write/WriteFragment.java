package com.mnikn.purewei.feature.write;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mnikn.mylibrary.mvp.view.fragment.BaseFragment;
import com.mnikn.purewei.R;
import com.mnikn.purewei.feature.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WriteFragment extends BaseFragment {

    @BindView(R.id.edit_weibo) EditText editText;
    @BindView(R.id.imgBtn_at) ImageButton imgBtnAt;
    @BindView(R.id.imgBtn_photo) ImageButton imgBtnPhoto;
    @BindView(R.id.imgBtn_send) ImageButton imgBtnSend;

    private IWritePresenter mPresenter;

    public static WriteFragment newInstance() {

        Bundle args = new Bundle();

        WriteFragment fragment = new WriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setupViews(View parent) {
        ButterKnife.bind(this, parent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_write;
    }

    @Override
    public IWritePresenter getPresenter() {
        mPresenter = new WritePresenter(getContext(),this);
        return mPresenter;
    }


    @OnClick(R.id.imgBtn_send)
    void onSendClick(){
        String content = editText.getText().toString();
        mPresenter.postWeibo(content);
        startActivity(new Intent(getContext(), HomeActivity.class));
    }
}
