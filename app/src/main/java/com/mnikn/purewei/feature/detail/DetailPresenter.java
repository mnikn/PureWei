package com.mnikn.purewei.feature.detail;

import android.content.Context;

import com.mnikn.library.view.net.NetPresenter;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.net.RequestManager;
import com.mnikn.purewei.support.net.observer.CommentObserver;
import com.mnikn.purewei.support.net.service.CommentService;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailPresenter extends NetPresenter<DetailFragment> {

    private long mWeiboId;
    private Observable commentObservable;

    public DetailPresenter(Context context,long weiboId) {
        super(context);
        mWeiboId = weiboId;
    }

    public long getWeiboId(){
        return mWeiboId;
    }

    @Override
    protected Observable request() {
        CommentService service = RequestManager.sRetrofit.create(CommentService.class);
        Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(getContext());
        return service.getComment(getPage(),token.getToken(),mWeiboId);
    }

    @Override
    protected Observer handleRequest() {
        if(getPage() == 1){
            return new CommentObserver(this,Constant.REFRESH,mWeiboId);
        }
        return new CommentObserver(this,Constant.LOAD_MORE,mWeiboId);
    }
}
