package com.mnikn.purewei.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class Timeline {
    @SerializedName("statuses")
    public List<Status> statuses;
}
