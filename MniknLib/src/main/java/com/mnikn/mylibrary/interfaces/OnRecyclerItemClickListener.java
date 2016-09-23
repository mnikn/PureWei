package com.mnikn.mylibrary.interfaces;

import android.view.View;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public interface OnRecyclerItemClickListener<T> {
    void onItemClick(View view,T data);
}
