package com.mnikn.purewei.support.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentBean {
    @SerializedName("hasvisible")
    public boolean hasvisible;
    @SerializedName("previous_cursor")
    public long previousCursor;
    @SerializedName("next_cursor")
    public long nextCursor;
    @SerializedName("total_number")
    public long totalNumber;
    @SerializedName("since_id")
    public long sinceId;
    @SerializedName("max_id")
    public long maxId;
    @SerializedName("comments")
    public List<CommentsBean> comments;
    @SerializedName("marks")
    public List<?> marks;

    public static class CommentsBean {
        @SerializedName("created_at")
        public String createdAt;
        @SerializedName("id")
        public long id;
        @SerializedName("rootid")
        public long rootid;
        @SerializedName("floor_number")
        public long floorNumber;
        @SerializedName("text")
        public String text;
        @SerializedName("source_allowclick")
        public long sourceAllowclick;
        @SerializedName("source_type")
        public long sourceType;
        @SerializedName("source")
        public String source;
        @SerializedName("user")
        public UserBean user;
        @SerializedName("mid")
        public String mid;
        @SerializedName("idstr")
        public String idstr;
        @SerializedName("status")
        public StatusBean status;

        public static class UserBean {
            @SerializedName("id")
            public long id;
            @SerializedName("idstr")
            public String idstr;
            @SerializedName("class")
            public long classX;
            @SerializedName("screen_name")
            public String screenName;
            @SerializedName("name")
            public String name;
            @SerializedName("province")
            public String province;
            @SerializedName("city")
            public String city;
            @SerializedName("location")
            public String location;
            @SerializedName("description")
            public String description;
            @SerializedName("url")
            public String url;
            @SerializedName("profile_image_url")
            public String profileImageUrl;
            @SerializedName("cover_image_phone")
            public String coverImagePhone;
            @SerializedName("profile_url")
            public String profileUrl;
            @SerializedName("domain")
            public String domain;
            @SerializedName("weihao")
            public String weihao;
            @SerializedName("gender")
            public String gender;
            @SerializedName("followers_count")
            public long followersCount;
            @SerializedName("friends_count")
            public long friendsCount;
            @SerializedName("pagefriends_count")
            public long pagefriendsCount;
            @SerializedName("statuses_count")
            public long statusesCount;
            @SerializedName("favourites_count")
            public long favouritesCount;
            @SerializedName("created_at")
            public String createdAt;
            @SerializedName("following")
            public boolean following;
            @SerializedName("allow_all_act_msg")
            public boolean allowAllActMsg;
            @SerializedName("geo_enabled")
            public boolean geoEnabled;
            @SerializedName("verified")
            public boolean verified;
            @SerializedName("verified_type")
            public long verifiedType;
            @SerializedName("remark")
            public String remark;
            @SerializedName("ptype")
            public long ptype;
            @SerializedName("allow_all_comment")
            public boolean allowAllComment;
            @SerializedName("avatar_large")
            public String avatarLarge;
            @SerializedName("avatar_hd")
            public String avatarHd;
            @SerializedName("verified_reason")
            public String verifiedReason;
            @SerializedName("verified_trade")
            public String verifiedTrade;
            @SerializedName("verified_reason_url")
            public String verifiedReasonUrl;
            @SerializedName("verified_source")
            public String verifiedSource;
            @SerializedName("verified_source_url")
            public String verifiedSourceUrl;
            @SerializedName("verified_state")
            public long verifiedState;
            @SerializedName("verified_level")
            public long verifiedLevel;
            @SerializedName("verified_type_ext")
            public long verifiedTypeExt;
            @SerializedName("verified_reason_modified")
            public String verifiedReasonModified;
            @SerializedName("verified_contact_name")
            public String verifiedContactName;
            @SerializedName("verified_contact_email")
            public String verifiedContactEmail;
            @SerializedName("verified_contact_mobile")
            public String verifiedContactMobile;
            @SerializedName("follow_me")
            public boolean followMe;
            @SerializedName("online_status")
            public long onlineStatus;
            @SerializedName("bi_followers_count")
            public long biFollowersCount;
            @SerializedName("lang")
            public String lang;
            @SerializedName("star")
            public long star;
            @SerializedName("mbtype")
            public long mbtype;
            @SerializedName("mbrank")
            public long mbrank;
            @SerializedName("block_word")
            public long blockWord;
            @SerializedName("block_app")
            public long blockApp;
            @SerializedName("ability_tags")
            public String abilityTags;
            @SerializedName("credit_score")
            public long creditScore;
            @SerializedName("user_ability")
            public long userAbility;
            @SerializedName("urank")
            public long urank;
        }

        public static class StatusBean {
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
            @SerializedName("pic_video")
            public String picVideo;
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
            @SerializedName("annotations")
            public List<AnnotationsBean> annotations;
            @SerializedName("darwin_tags")
            public List<?> darwinTags;
            @SerializedName("hot_weibo_tags")
            public List<?> hotWeiboTags;
            @SerializedName("text_tag_tips")
            public List<?> textTagTips;

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

            public static class AnnotationsBean {
                @SerializedName("client_mblogid")
                public String clientMblogid;
            }
        }
    }
}
