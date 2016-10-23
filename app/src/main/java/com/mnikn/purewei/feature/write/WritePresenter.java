package com.mnikn.purewei.feature.write;

import android.content.Context;

import com.mnikn.library.presenter.Presenter;
import com.mnikn.purewei.support.net.RequestManager;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WritePresenter extends Presenter{

    private Context mContext;

    public WritePresenter(Context context) {
        mContext = context;
    }

    public void createWeibo(String content) {
        RequestManager.createWeibo(mContext, content);
    }
    public void createComment(String content, long id) {
        RequestManager.createComment(mContext,content,id);
    }

}
