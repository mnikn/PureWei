package com.mnikn.mylibrary.interfaces;

import android.view.View;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface OnRecyclerItemClickListener<T> {
    void onItemClick(View view,T data);
}
