package com.mnikn.purewei.support.net.observer;

import android.content.ContentResolver;
import android.content.Context;

import com.mnikn.library.utils.ToastUtils;
import com.mnikn.library.view.net.INetView;
import com.mnikn.purewei.data.dao.PictureDao;
import com.mnikn.purewei.data.dao.StatusDao;
import com.mnikn.purewei.data.dao.UserDao;
import com.mnikn.purewei.model.Status;
import com.mnikn.purewei.support.Constants;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HotWeiboObserver implements Observer<List<Status>> {

    private Context mContext;
    private INetView mView;
    private int mRequestType;

    public HotWeiboObserver(Context context, INetView view, int requestType){
        mContext = context;
        mView = view;
        mRequestType = requestType;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(List<Status> value) {
        ContentResolver resolver = mContext.getContentResolver();

        //刷新时先删除数据
        if(mRequestType == Constants.REFRESH){
            StatusDao.clear();
            PictureDao.clear();
        }

        int size = value.size();
        for(int i = 0;i < size; ++i){
            PictureDao.insertPictures(value.get(i).pictures);
            value.get(i).user.type = Constants.USER_NORMAL;
            UserDao.insertUser(value.get(i).user);

            //若该微博为转发,就把原微博插入数据库
            if(value.get(i).retweetStatus != null){
                StatusDao.insertStatus(value.get(i).retweetStatus);
                PictureDao.insertPictures(value.get(i).retweetStatus.pictures);
            }
        }

        StatusDao.insertStatuses(value);
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.makeToastShort(mContext,e.getMessage());
        mView.onLoadMoreFinish();
    }

    @Override
    public void onComplete() {
        switch (mRequestType){
            case Constants.REFRESH:
                mView.onRefreshFinish();
                break;
            case Constants.LOAD_MORE:
                mView.onLoadMoreFinish();
                break;
            default:
                mView.onLoadMoreFinish();
        }
    }
}
