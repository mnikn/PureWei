package com.mnikn.mylibrary.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class RecyclerViewBuilder {

    private static RecyclerViewBuilder INSTANCE;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private RecyclerViewBuilder() {}

    public static RecyclerViewBuilder getInstance(){
        if(INSTANCE == null){
            INSTANCE = new RecyclerViewBuilder();
        }
        return INSTANCE;
    }

    public RecyclerViewBuilder bind(RecyclerView recyclerView,RecyclerView.Adapter adapter){
        mRecyclerView = recyclerView;
        mAdapter = adapter;
        mRecyclerView.setAdapter(mAdapter);
        return this;
    }

    public RecyclerViewBuilder recyclerViewDecor(RecyclerView.ItemDecoration itemDecoration){
        mRecyclerView.addItemDecoration(itemDecoration);
        return this;
    }

    public RecyclerViewBuilder layoutManager(RecyclerView.LayoutManager layoutManager){
        mRecyclerView.setLayoutManager(layoutManager);
        return this;
    }

    public RecyclerViewBuilder OnScroll(RecyclerView.OnScrollListener listener){
        mRecyclerView.addOnScrollListener(listener);
        return this;
    }


}
