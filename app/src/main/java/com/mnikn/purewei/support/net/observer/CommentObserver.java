package com.mnikn.purewei.support.net.observer;

import android.content.ContentResolver;

import com.mnikn.library.utils.Numbers;
import com.mnikn.library.utils.ToastUtils;
import com.mnikn.library.view.net.INetView;
import com.mnikn.library.view.net.NetPresenter;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.dao.CommentDao;
import com.mnikn.purewei.data.dao.UserDao;
import com.mnikn.purewei.model.Comments;
import com.mnikn.purewei.support.Constants;
import com.mnikn.purewei.support.util.DataUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentObserver implements Observer<Comments>{

    private NetPresenter mPresenter;
    private int mType;
    private long mWeiboId;

    public CommentObserver(NetPresenter presenter,int requestType,long weiboId){
        mPresenter = presenter;
        mType = requestType;
        mWeiboId = weiboId;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Comments value) {
        ContentResolver resolver = mPresenter.getContext().getContentResolver();

        if(mType == Constants.REFRESH){
            resolver.delete(
                    WeiboContract.CommentEntry.CONTENT_URI,
                    WeiboContract.CommentEntry.COLUMN_STATUS_ID + " = ?",
                    new String[]{Numbers.longToString(mWeiboId)});
        }

        //把bean转换成ContentValues,并插入到数据库中
        int size = value.comments.size();
        for(int i = 0;i < size; ++i){

            //先查询是否有这位用户信息,没有就插入数据
            long userId = value.comments.get(i).user.id;
            if(!DataUtil.hasUserId(mPresenter.getContext(), userId)){
                value.comments.get(i).user.type = Constants.USER_NORMAL;
                UserDao.insertUser(value.comments.get(i).user);
            }
        }
        CommentDao.insertComments(value.comments);
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.makeToastShort(mPresenter.getContext(), e.getMessage());
        ((INetView) mPresenter.getView()).onLoadMoreFinish();
    }

    @Override
    public void onComplete() {
        switch (mType){
            case Constants.REFRESH:
                mPresenter.refreshFinish();
                break;
            case Constants.LOAD_MORE:
                mPresenter.loadMoreFinish();
                break;
        }
    }
}
