package com.mnikn.library.support.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class RecyclerViewConfig {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.ItemDecoration mItemDecoration;
    private EasyRecyclerAdapter mRecyclerAdapter;

    private RecyclerViewConfig() {}

    private void bind(){
        if(mRecyclerView == null) throw new IllegalArgumentException("RecyclerView cannot be null!");
        if(mRecyclerAdapter != null){
            mRecyclerView.setAdapter(mRecyclerAdapter);
        }
        if(mLayoutManager != null){
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
        if(mItemDecoration != null){
            mRecyclerView.addItemDecoration(mItemDecoration);
        }
    }

    public static class Builder{

        private RecyclerView mRecyclerView;
        private RecyclerView.LayoutManager mLayoutManager;
        private RecyclerView.ItemDecoration mItemDecoration;
        private EasyRecyclerAdapter mRecyclerAdapter;

        public Builder() {}

        public RecyclerViewConfig build(){
            RecyclerViewConfig config = new RecyclerViewConfig();
            config.mRecyclerView = mRecyclerView;
            config.mLayoutManager = mLayoutManager;
            config.mItemDecoration = mItemDecoration;
            config.mRecyclerAdapter = mRecyclerAdapter;
            config.bind();
            return config;
        }

        public Builder recyclerView(RecyclerView recyclerView){
            mRecyclerView = recyclerView;
            return this;
        }

        public Builder recyclerAdapter(EasyRecyclerAdapter adapter){
            mRecyclerAdapter = adapter;
            return this;
        }

        public Builder layoutManager(RecyclerView.LayoutManager layoutManager){
            mLayoutManager = layoutManager;
            return this;
        }

        public Builder itemDecoration(RecyclerView.ItemDecoration itemDecoration){
            mItemDecoration = itemDecoration;
            return this;
        }
    }
}
