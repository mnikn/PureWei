package com.mnikn.mylibrary.mvp.view;

import android.support.v7.widget.RecyclerView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IListView extends IView{

    RecyclerView.Adapter getAdapter();
}
