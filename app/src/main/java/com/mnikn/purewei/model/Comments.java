package com.mnikn.purewei.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class Comments {
    @SerializedName("comments")
    public List<Comment> comments;
}
