package com.mnikn.purewei.support.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentsBean {
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("id")
    public long id;
    @SerializedName("floor_number")
    public long floorNumber;
    @SerializedName("text")
    public String text;
    @SerializedName("source")
    public String source;
    @SerializedName("user")
    public UserBean user;
    @SerializedName("status")
    public StatusesBean status;
}
