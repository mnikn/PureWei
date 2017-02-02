package com.mnikn.purewei.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class Picture {
    public long statusId;

    @SerializedName("thumbnail_pic")
    public String thumbnailPic;

    public String middlePic;
    public String largePic;
}
