package com.mnikn.purewei.support.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class EmotionsBean {
    
    @SerializedName("phrase")
    public String phrase;
    @SerializedName("type")
    public String type;
    @SerializedName("url")
    public String url;
    @SerializedName("hot")
    public boolean hot;
    @SerializedName("common")
    public boolean common;
    @SerializedName("category")
    public String category;
    @SerializedName("icon")
    public String icon;
    @SerializedName("value")
    public String value;
    @SerializedName("picid")
    public String picid;
}
