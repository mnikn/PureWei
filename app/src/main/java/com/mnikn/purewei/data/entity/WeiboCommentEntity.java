package com.mnikn.purewei.data.entity;

import android.content.ContentValues;

import com.mnikn.mylibrary.util.DateUtil;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.support.bean.CommentBean;
import com.mnikn.purewei.support.bean.CommentsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboCommentEntity {
    public List<Long> weiboId = new ArrayList<>();
    public List<Long> commentId = new ArrayList<>();
    public List<Long> commentUserId = new ArrayList<>();
    public List<Long> commentTime = new ArrayList<>();
    public List<String> commentText = new ArrayList<>();
    public List<String> commentSource = new ArrayList<>();

    public WeiboCommentEntity() {}
    public WeiboCommentEntity(CommentBean bean){
        fromBean(bean);
    }

    private void fromBean(CommentBean bean){
        for(CommentsBean commentsBean : bean.comments){
            weiboId.add(commentsBean.status.id);
            commentId.add(commentsBean.id);
            commentUserId.add(commentsBean.user.id);
            commentTime.add(DateUtil.dateToLong(commentsBean.createdAt));
            commentText.add(commentsBean.text);
            commentSource.add(commentsBean.source);
        }
    }

    public ContentValues[] toContentValuesArray(){
        ContentValues[] valuesArray = new ContentValues[commentId.size()];

        for(int i = 0;i < commentId.size();++i){
            ContentValues values = new ContentValues();
            values.put(WeiboContract.WeiboCommentEntry.COLUMN_COMMENT_ID,commentId.get(i));
            values.put(WeiboContract.WeiboCommentEntry.COLUMN_WEIBO_ID,weiboId.get(i));
            values.put(WeiboContract.WeiboCommentEntry.COLUMN_COMMENT_USER_ID,commentUserId.get(i));
            values.put(WeiboContract.WeiboCommentEntry.COLUMN_COMMENT_TEXT,commentText.get(i));
            values.put(WeiboContract.WeiboCommentEntry.COLUMN_COMMENT_SOURCE,commentSource.get(i));
            values.put(WeiboContract.WeiboCommentEntry.COLUMN_COMMENT_TIME,commentTime.get(i));
            valuesArray[i] = values;
        }
        return valuesArray;
    }
}
