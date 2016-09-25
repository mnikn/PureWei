package com.mnikn.purewei.support.listener;

import android.content.Context;

import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.feature.home.IHomeView;
import com.mnikn.purewei.support.bean.UserBean;
import com.mnikn.purewei.data.entity.UserEntity;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
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
        UserEntity entity = new UserEntity(DataUtil.jsonToBean(s,UserBean.class));
        mContext.getContentResolver().insert(WeiboContract.UserEntry.CONTENT_URI, entity.toContentValues());
        mView.setUserView(entity.profileImageUrl, entity.userName);
    }

    @Override
    public void onWeiboException(WeiboException e) {
        e.printStackTrace();
    }
}
