package com.mnikn.purewei.support.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class TimelineBean {
    @SerializedName("has_unread")
    public long hasUnread;
    @SerializedName("statuses")
    public List<StatusesBean> statuses;
}
