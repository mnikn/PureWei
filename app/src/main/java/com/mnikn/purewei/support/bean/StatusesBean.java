package com.mnikn.purewei.support.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class StatusesBean {
  @SerializedName("created_at")
  public String createdAt;
  @SerializedName("id")
  public long id;
  @SerializedName("text")
  public String text;
  @SerializedName("source")
  public String source;
  @SerializedName("favorited")
  public boolean favorited;
  @SerializedName("user")
  public UserBean user;
  @SerializedName("retweeted_status")
  public StatusesBean retweetedStatus;
  @SerializedName("reposts_count")
  public long repostsCount;
  @SerializedName("comments_count")
  public long commentsCount;
  @SerializedName("attitudes_count")
  public long attitudesCount;
  @SerializedName("isLongText")
  public boolean isLongText;
  @SerializedName("liked")
  public boolean liked;
  @SerializedName("pic_urls")
  public List<PicUrlsBean> picUrls;
}
