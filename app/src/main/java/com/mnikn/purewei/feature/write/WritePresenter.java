package com.mnikn.purewei.feature.write;

import android.content.Context;

import com.mnikn.mylibrary.mvp.view.IView;
import com.mnikn.purewei.support.net.RequestManager;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WritePresenter implements IWritePresenter {

    private Context mContext;
    private IView mView;

    public WritePresenter(Context context,IView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void createWeibo(String content) {
        RequestManager.createWeibo(mContext, content);
    }

    @Override
    public void createComment(String content, long id) {
        RequestManager.createComment(mContext,content,id);
    }

    @Override
    public IView getView() {
        return mView;
    }
}
