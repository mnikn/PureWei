package com.mnikn.purewei.data.entity;

import android.content.ContentValues;

import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.PicUrlsBean;
import com.mnikn.purewei.support.bean.StatusesBean;
import com.mnikn.purewei.support.bean.TimelineBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboPicsEntity {
    public long weiboId;
    public List<String> thumbnailUrl = new ArrayList<>();
    public List<String> middleUrl = new ArrayList<>();
    public List<String> largeUrl = new ArrayList<>();

    public WeiboPicsEntity() {}
    public WeiboPicsEntity(TimelineBean bean, int position){
        fromBean(bean, position);
    }
    public WeiboPicsEntity(StatusesBean bean){
        fromBean(bean);
    }

    private void fromBean(TimelineBean bean,int position){
        fromBean(bean.statuses.get(position));
    }

    private void fromBean(StatusesBean bean){
        weiboId = bean.id;
        for(PicUrlsBean urlsBean : bean.picUrls){
            thumbnailUrl.add(urlsBean.thumbnailPic);
        }
    }

    public ContentValues[] toContentValuesArray(){
        ContentValues[] valuesArray = new ContentValues[thumbnailUrl.size()];

        for(int i = 0;i < thumbnailUrl.size();++i) {
            ContentValues values = new ContentValues();
            values.put(WeiboContract.WeiboPicsEntry.COLUMN_WEIBO_ID, weiboId);
            values.put(WeiboContract.WeiboPicsEntry.COLUMN_THUMBNAIL_URL,thumbnailUrl.get(i));
            values.put(WeiboContract.WeiboPicsEntry.COLUMN_MIDDLE_URL,thumbnailUrl.get(i).replace("thumbnail","bmiddle"));
            values.put(WeiboContract.WeiboPicsEntry.COLUMN_LARGE_URL,thumbnailUrl.get(i).replace("thumbnail","large"));
            valuesArray[i] = values;
        }
        return valuesArray;
    }
}
