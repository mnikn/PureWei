package com.mnikn.purewei.data;

import android.content.Context;

import com.mnikn.purewei.App;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboDataHelper {
    private static WeiboDataHelper INSTANCE;
    private Context mContext;

    private WeiboDataHelper() {
        mContext = App.getAppContext();
    }

    public static WeiboDataHelper getInstance(){
        if(INSTANCE == null){
            INSTANCE = new WeiboDataHelper();
        }
        return INSTANCE;
    }


}
