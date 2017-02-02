package com.mnikn.purewei.support.net.observer;

import android.content.Context;
import android.util.Log;

import com.mnikn.library.utils.ToastUtils;
import com.mnikn.library.view.net.INetView;
import com.mnikn.purewei.data.dao.PictureDao;
import com.mnikn.purewei.data.dao.StatusDao;
import com.mnikn.purewei.data.dao.UserDao;
import com.mnikn.purewei.model.Picture;
import com.mnikn.purewei.model.Timeline;
import com.mnikn.purewei.support.Constants;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class StatusObserver implements Observer<Timeline> {

    private Context mContext;
    private INetView mView;
    private int mRequestType;

    public StatusObserver(Context context, INetView view, int requestType) {
        mContext = context;
        mView = view;
        mRequestType = requestType;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Timeline value) {

        //刷新时先删除数据
        if (mRequestType == Constants.REFRESH) {
            Log.d(StatusObserver.class.getSimpleName(), "refresh");
            StatusDao.clear();
            PictureDao.clear();
        }


        //把bean转换成ContentValues,并插入到数据库中
        int size = value.statuses.size();
        for (int i = 0; i < size; ++i) {
            for (Picture picture : value.statuses.get(i).pictures) {
                picture.statusId = value.statuses.get(i).id;
            }
            PictureDao.insertPictures(value.statuses.get(i).pictures);
            value.statuses.get(i).user.type = Constants.USER_NORMAL;
            UserDao.insertUser(value.statuses.get(i).user);

            //若该微博为转发,就把原微博插入数据库
            if (value.statuses.get(i).retweetStatus != null) {
                StatusDao.insertStatus(value.statuses.get(i).retweetStatus);
                PictureDao.insertPictures(value.statuses.get(i).retweetStatus.pictures);
            }
        }

        StatusDao.insertStatuses(value.statuses);
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.makeToastShort(mContext, e.getMessage());
        mView.onLoadMoreFinish();
    }

    @Override
    public void onComplete() {
        switch (mRequestType) {
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
