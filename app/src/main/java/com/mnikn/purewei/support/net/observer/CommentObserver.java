package com.mnikn.purewei.support.net.observer;

import android.content.ContentResolver;
import android.content.ContentValues;

import com.mnikn.library.view.net.NetPresenter;
import com.mnikn.library.view.net.INetView;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.mylibrary.util.ToastUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.UserEntity;
import com.mnikn.purewei.data.entity.WeiboCommentEntity;
import com.mnikn.purewei.support.Constant;
import com.mnikn.purewei.support.bean.CommentBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentObserver implements Observer<CommentBean>{

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
    public void onNext(CommentBean value) {
        ContentResolver resolver = mPresenter.getContext().getContentResolver();

        if(mType == Constant.REFRESH){
            resolver.delete(
                    WeiboContract.WeiboCommentEntry.CONTENT_URI,
                    WeiboContract.WeiboCommentEntry.COLUMN_WEIBO_ID + " = ?",
                    new String[]{NumberUtil.longToString(mWeiboId)});
        }

        //把bean转换成ContentValues,并插入到数据库中
        int size = value.comments.size();
        ContentValues[] commentValuesArray = new WeiboCommentEntity(value).toContentValuesArray();
        for(int i = 0;i < size; ++i){

            //先查询是否有这位用户信息,没有就插入数据
            long userId = value.comments.get(i).user.id;
            if(!com.mnikn.purewei.support.util.DataUtil.hasUserId(mPresenter.getContext(), userId)){
                resolver.insert(WeiboContract.UserEntry.CONTENT_URI,
                        new UserEntity(value.comments.get(i).user).toContentValues(Constant.USER_NORAML));
            }
        }
        resolver.bulkInsert(WeiboContract.WeiboCommentEntry.CONTENT_URI,commentValuesArray);
    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.makeToastShort(mPresenter.getContext(), e.getMessage());
        ((INetView) mPresenter.getView()).onLoadMoreFinish();
    }

    @Override
    public void onComplete() {
        switch (mType){
            case Constant.REFRESH:
                mPresenter.refreshFinish();
                break;
            case Constant.LOAD_MORE:
                mPresenter.loadMoreFinish();
                break;
        }
    }
}
