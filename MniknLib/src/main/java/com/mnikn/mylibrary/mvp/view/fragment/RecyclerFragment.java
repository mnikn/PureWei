package com.mnikn.mylibrary.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.mylibrary.R;
import com.mnikn.mylibrary.mvp.presenter.IListPresenter;
import com.mnikn.mylibrary.mvp.view.IListView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class RecyclerFragment <P extends IListPresenter> extends BaseFragment<P>
        implements IListView{

    public RecyclerView recyclerView;

    protected RecyclerView.Adapter mAdapter;
    protected P mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = getPresenter();

        initViews(view);

        setupViews(view);
    }

    private void initViews(View parent){

        mAdapter = getAdapter();

        recyclerView = (RecyclerView) parent.findViewById(R.id.recycler);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }


}
