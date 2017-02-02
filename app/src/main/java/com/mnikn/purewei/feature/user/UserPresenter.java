package com.mnikn.purewei.feature.user;

import android.content.Context;
import android.preference.PreferenceManager;

import com.mnikn.library.view.net.NetPresenter;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constants;
import com.mnikn.purewei.support.net.RequestManager;
import com.mnikn.purewei.support.net.observer.StatusObserver;
import com.mnikn.purewei.support.net.service.WeiboService;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserPresenter extends NetPresenter<UserFragment> {

    private Observable homeWeiboObservale;

    public UserPresenter(Context context) {
        super(context);
    }

    @Override
    protected Observable request() {
        WeiboService service = RequestManager.sRetrofit.create(WeiboService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(getContext());
        int count = PreferenceManager.getDefaultSharedPreferences(getContext())
                .getInt("key_load_num",20);
        return service.getUserWeibo(token.getToken(),getPage(), count);
    }

    @Override
    protected Observer handleRequest() {
        if(getPage() == 1){
            return new StatusObserver(getContext(),getView(), Constants.REFRESH);
        }
        return new StatusObserver(getContext(),getView(), Constants.LOAD_MORE);
    }
}
