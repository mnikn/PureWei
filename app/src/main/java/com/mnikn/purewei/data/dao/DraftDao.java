package com.mnikn.purewei.data.dao;

import android.content.ContentValues;

import com.mnikn.purewei.data.WeiboContract;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DraftDao {
    private int type;
    private long weiboId;
    private String content;

    public DraftDao(int type, String content){
        this.type = type;
        this.content = content;
    }
    public DraftDao(int type, long weiboId, String content){
        this.type = type;
        this.weiboId = weiboId;
        this.content = content;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.DraftEntry.COLUMN_TYPE,type);
        values.put(WeiboContract.DraftEntry.COLUMN_WEIBO_ID,weiboId);
        values.put(WeiboContract.DraftEntry.COLUMN_CONTENT,content);
        return values;
    }
}
