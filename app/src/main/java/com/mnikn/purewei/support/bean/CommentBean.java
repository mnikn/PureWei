package com.mnikn.purewei.support.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentBean {
    @SerializedName("comments")
    public List<CommentsBean> comments;
}
