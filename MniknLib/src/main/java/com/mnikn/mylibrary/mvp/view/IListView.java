package com.mnikn.mylibrary.mvp.view;

import com.mnikn.mylibrary.adapter.EasyRecyclerAdapter;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public interface IListView extends IView{

    EasyRecyclerAdapter getAdapter();
}
