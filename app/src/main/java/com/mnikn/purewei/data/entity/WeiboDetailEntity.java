package com.mnikn.purewei.data.entity;

import android.content.ContentValues;

import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.TimelineBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboDetailEntity {
    public long weiboId;
    public List<String> picsUrl = new ArrayList<>();

    public WeiboDetailEntity() {}
    public WeiboDetailEntity(TimelineBean bean,int position){
        fromBean(bean, position);
    }

    private void fromBean(TimelineBean bean,int position){
        TimelineBean.StatusesBean statusesBean = bean.statuses.get(position);
        weiboId = statusesBean.id;

        for(TimelineBean.StatusesBean.PicUrlsBean urlsBean : statusesBean.picUrls){
            picsUrl.add(urlsBean.thumbnailPic);
        }
    }

    public ContentValues[] toContentValuesArray(){
        ContentValues[] valuesArray = new ContentValues[picsUrl.size()];

        for(int i = 0;i < picsUrl.size();++i) {
            ContentValues values = new ContentValues();
            values.put(WeiboContract.WeiboDetailEntry.COLUMN_WEIBO_ID, weiboId);
            values.put(WeiboContract.WeiboDetailEntry.COLUMN_PICS_URL, picsUrl.get(i));
            valuesArray[i] = values;
        }
        return valuesArray;
    }
}
