package com.mnikn.purewei.support.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class TimelineBean {
    @SerializedName("total_number")
    public long totalNumber;
    @SerializedName("since_id")
    public long sinceId;
    @SerializedName("max_id")
    public long maxId;
    @SerializedName("has_unread")
    public long hasUnread;
    @SerializedName("statuses")
    public List<StatusesBean> statuses;

    public static class StatusesBean {
        @SerializedName("created_at")
        public String createdAt;
        @SerializedName("id")
        public long id;
        @SerializedName("mid")
        public String mid;
        @SerializedName("text")
        public String text;
        @SerializedName("source_allowclick")
        public long sourceAllowclick;
        @SerializedName("source_type")
        public long sourceType;
        @SerializedName("source")
        public String source;
        @SerializedName("favorited")
        public boolean favorited;
        @SerializedName("truncated")
        public boolean truncated;
        @SerializedName("user")
        public UserBean user;
        @SerializedName("retweeted_status")
        public RetweetedStatusBean retweetedStatus;
        @SerializedName("reposts_count")
        public long repostsCount;
        @SerializedName("comments_count")
        public long commentsCount;
        @SerializedName("attitudes_count")
        public long attitudesCount;
        @SerializedName("isLongText")
        public boolean isLongText;
        @SerializedName("mlevel")
        public long mlevel;
        @SerializedName("visible")
        public VisibleBean visible;
        @SerializedName("biz_feature")
        public long bizFeature;
        @SerializedName("hasActionTypeCard")
        public long hasActionTypeCard;
        @SerializedName("rid")
        public String rid;
        @SerializedName("userType")
        public long userType;
        @SerializedName("cardid")
        public String cardid;
        @SerializedName("positive_recom_flag")
        public long positiveRecomFlag;
        @SerializedName("gif_ids")
        public String gifIds;
        @SerializedName("is_show_bulletin")
        public long isShowBulletin;
        @SerializedName("pic_urls")
        public List<PicUrlsBean> picUrls;

        public static class RetweetedStatusBean {
            @SerializedName("created_at")
            public String createdAt;
            @SerializedName("id")
            public long id;
            @SerializedName("mid")
            public String mid;
            @SerializedName("idstr")
            public String idstr;
            @SerializedName("text")
            public String text;
            @SerializedName("textLength")
            public long textLength;
            @SerializedName("source_allowclick")
            public long sourceAllowclick;
            @SerializedName("source_type")
            public long sourceType;
            @SerializedName("source")
            public String source;
            @SerializedName("favorited")
            public boolean favorited;
            @SerializedName("truncated")
            public boolean truncated;
            @SerializedName("in_reply_to_status_id")
            public String inReplyToStatusId;
            @SerializedName("in_reply_to_user_id")
            public String inReplyToUserId;
            @SerializedName("in_reply_to_screen_name")
            public String inReplyToScreenName;
            @SerializedName("thumbnail_pic")
            public String thumbnailPic;
            @SerializedName("bmiddle_pic")
            public String bmiddlePic;
            @SerializedName("original_pic")
            public String originalPic;
            @SerializedName("geo")
            public Object geo;
            @SerializedName("user")
            public UserBean user;
            @SerializedName("reposts_count")
            public long repostsCount;
            @SerializedName("comments_count")
            public long commentsCount;
            @SerializedName("attitudes_count")
            public long attitudesCount;
            @SerializedName("isLongText")
            public boolean isLongText;
            @SerializedName("mlevel")
            public long mlevel;
            @SerializedName("visible")
            public VisibleBean visible;
            @SerializedName("biz_feature")
            public long bizFeature;
            @SerializedName("hasActionTypeCard")
            public long hasActionTypeCard;
            @SerializedName("userType")
            public long userType;
            @SerializedName("cardid")
            public String cardid;
            @SerializedName("positive_recom_flag")
            public long positiveRecomFlag;
            @SerializedName("gif_ids")
            public String gifIds;
            @SerializedName("is_show_bulletin")
            public long isShowBulletin;
            @SerializedName("pic_urls")
            public List<PicUrlsBean> picUrls;
            @SerializedName("darwin_tags")
            public List<?> darwlongags;
            @SerializedName("hot_weibo_tags")
            public List<?> hotWeiboTags;
            @SerializedName("text_tag_tips")
            public List<?> textTagTips;
        }

        public static class VisibleBean {
            @SerializedName("type")
            public long type;
            @SerializedName("list_id")
            public long listId;
        }

        public static class PicUrlsBean {
            @SerializedName("thumbnail_pic")
            public String thumbnailPic;
        }
    }
}
