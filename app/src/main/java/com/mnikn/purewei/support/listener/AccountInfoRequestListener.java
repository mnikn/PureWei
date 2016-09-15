package com.mnikn.purewei.support.listener;

import android.content.Context;

import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.mvp.IHomeView;
import com.mnikn.purewei.mvp.model.UserModel;
import com.mnikn.purewei.support.bean.UserBean;
import com.mnikn.purewei.support.util.DataUtil;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class AccountInfoRequestListener implements RequestListener {

    private Context mContext;
    private IHomeView mView;

    public AccountInfoRequestListener(Context context,IHomeView view){
        mContext = context;
        mView = view;
    }

    @Override
    public void onComplete(String s) {
        UserModel model = new UserModel(DataUtil.jsonToBean(s,UserBean.class));
        mContext.getContentResolver().insert(WeiboContract.UserEntry.CONTENT_URI,model.toContentValues());
        mView.setUserView(model.getProfileImageUrl(), model.getUserName());
    }

    @Override
    public void onWeiboException(WeiboException e) {
        e.printStackTrace();
    }
}
