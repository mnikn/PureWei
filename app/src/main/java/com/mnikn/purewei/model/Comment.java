package com.mnikn.purewei.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class Comment {

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
    public User user;
    @SerializedName("status")
    public Status status;
}
