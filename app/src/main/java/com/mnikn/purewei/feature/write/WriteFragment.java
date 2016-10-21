package com.mnikn.purewei.feature.write;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mnikn.mylibrary.mvp.view.fragment.BaseFragment;
import com.mnikn.purewei.R;
import com.mnikn.purewei.feature.detail.DetailActivity;
import com.mnikn.purewei.feature.home.HomeActivity;
import com.mnikn.purewei.model.WeiboModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WriteFragment extends BaseFragment {

    private static final int CODE_PHOTO = 100;

    @BindView(R.id.edit_weibo) EditText edtWeibo;
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

    @OnClick(R.id.imgBtn_photo)
    void onPhotoClick(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.putExtra("return-data",true);
        getActivity().startActivityForResult(intent,CODE_PHOTO);
    }

    @OnClick(R.id.imgBtn_send)
    void onSendClick(){
        String content = edtWeibo.getText().toString();
        if(content.length() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("内容不能为空!");
            builder.show();
        }
        else{
            if(getActivity().getIntent().getIntExtra(WriteActivity.EXTRA_TYPE, 1) == 1){
                mPresenter.createWeibo(content);
                startActivity(new Intent(getContext(), HomeActivity.class));
            }
            else{
                WeiboModel model = getActivity().getIntent().getParcelableExtra(WriteActivity.EXTRA_WEIBO);
                mPresenter.createComment(content,model.weiboId);
                DetailActivity.startActivity(getContext(),model);
            }
        }

    }
}
