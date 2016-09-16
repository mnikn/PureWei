package com.mnikn.purewei.support.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class TimelineBean {
    @SerializedName("hasvisible")
    private boolean hasvisible;
    @SerializedName("previous_cursor")
    private long previousCursor;
    @SerializedName("next_cursor")
    private long nextCursor;
    @SerializedName("total_number")
    private long totalNumber;
    @SerializedName("longerval")
    private long longerval;
    @SerializedName("uve_blank")
    private long uveBlank;
    @SerializedName("since_id")
    private long sinceId;
    @SerializedName("max_id")
    private long maxId;
    @SerializedName("has_unread")
    private long hasUnread;
    @SerializedName("statuses")
    private List<StatusesBean> statuses;
    @SerializedName("advertises")
    private List<?> advertises;
    @SerializedName("ad")
    private List<?> ad;

    public boolean isHasvisible() {
        return hasvisible;
    }

    public void setHasvisible(boolean hasvisible) {
        this.hasvisible = hasvisible;
    }

    public long getPreviousCursor() {
        return previousCursor;
    }

    public void setPreviousCursor(long previousCursor) {
        this.previousCursor = previousCursor;
    }

    public long getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(long nextCursor) {
        this.nextCursor = nextCursor;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public long getlongerval() {
        return longerval;
    }

    public void setlongerval(long longerval) {
        this.longerval = longerval;
    }

    public long getUveBlank() {
        return uveBlank;
    }

    public void setUveBlank(long uveBlank) {
        this.uveBlank = uveBlank;
    }

    public long getSinceId() {
        return sinceId;
    }

    public void setSinceId(long sinceId) {
        this.sinceId = sinceId;
    }

    public long getMaxId() {
        return maxId;
    }

    public void setMaxId(long maxId) {
        this.maxId = maxId;
    }

    public long getHasUnread() {
        return hasUnread;
    }

    public void setHasUnread(long hasUnread) {
        this.hasUnread = hasUnread;
    }

    public List<StatusesBean> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<StatusesBean> statuses) {
        this.statuses = statuses;
    }

    public List<?> getAdvertises() {
        return advertises;
    }

    public void setAdvertises(List<?> advertises) {
        this.advertises = advertises;
    }

    public List<?> getAd() {
        return ad;
    }

    public void setAd(List<?> ad) {
        this.ad = ad;
    }

    public static class StatusesBean {
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("id")
        private long id;
        @SerializedName("mid")
        private String mid;
        @SerializedName("idstr")
        private String idstr;
        @SerializedName("text")
        private String text;
        @SerializedName("source_allowclick")
        private long sourceAllowclick;
        @SerializedName("source_type")
        private long sourceType;
        @SerializedName("source")
        private String source;
        @SerializedName("favorited")
        private boolean favorited;
        @SerializedName("truncated")
        private boolean truncated;
        @SerializedName("in_reply_to_status_id")
        private String inReplyToStatusId;
        @SerializedName("in_reply_to_user_id")
        private String inReplyToUserId;
        @SerializedName("in_reply_to_screen_name")
        private String inReplyToScreenName;
        @SerializedName("geo")
        private Object geo;
        @SerializedName("user")
        private UserBean user;
        @SerializedName("retweeted_status")
        private RetweetedStatusBean retweetedStatus;
        @SerializedName("reposts_count")
        private long repostsCount;
        @SerializedName("comments_count")
        private long commentsCount;
        @SerializedName("attitudes_count")
        private long attitudesCount;
        @SerializedName("isLongText")
        private boolean isLongText;
        @SerializedName("mlevel")
        private long mlevel;
        @SerializedName("visible")
        private RetweetedStatusBean.VisibleBean visible;
        @SerializedName("biz_feature")
        private long bizFeature;
        @SerializedName("hasActionTypeCard")
        private long hasActionTypeCard;
        @SerializedName("rid")
        private String rid;
        @SerializedName("userType")
        private long userType;
        @SerializedName("cardid")
        private String cardid;
        @SerializedName("positive_recom_flag")
        private long positiveRecomFlag;
        @SerializedName("gif_ids")
        private String gifIds;
        @SerializedName("is_show_bulletin")
        private long isShowBulletin;
        @SerializedName("pic_urls")
        private List<?> picUrls;
        @SerializedName("darwin_tags")
        private List<?> darwlongags;
        @SerializedName("hot_weibo_tags")
        private List<?> hotWeiboTags;
        @SerializedName("text_tag_tips")
        private List<?> textTagTips;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public String getIdstr() {
            return idstr;
        }

        public void setIdstr(String idstr) {
            this.idstr = idstr;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public long getSourceAllowclick() {
            return sourceAllowclick;
        }

        public void setSourceAllowclick(long sourceAllowclick) {
            this.sourceAllowclick = sourceAllowclick;
        }

        public long getSourceType() {
            return sourceType;
        }

        public void setSourceType(long sourceType) {
            this.sourceType = sourceType;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public boolean isTruncated() {
            return truncated;
        }

        public void setTruncated(boolean truncated) {
            this.truncated = truncated;
        }

        public String getInReplyToStatusId() {
            return inReplyToStatusId;
        }

        public void setInReplyToStatusId(String inReplyToStatusId) {
            this.inReplyToStatusId = inReplyToStatusId;
        }

        public String getInReplyToUserId() {
            return inReplyToUserId;
        }

        public void setInReplyToUserId(String inReplyToUserId) {
            this.inReplyToUserId = inReplyToUserId;
        }

        public String getInReplyToScreenName() {
            return inReplyToScreenName;
        }

        public void setInReplyToScreenName(String inReplyToScreenName) {
            this.inReplyToScreenName = inReplyToScreenName;
        }

        public Object getGeo() {
            return geo;
        }

        public void setGeo(Object geo) {
            this.geo = geo;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public RetweetedStatusBean getRetweetedStatus() {
            return retweetedStatus;
        }

        public void setRetweetedStatus(RetweetedStatusBean retweetedStatus) {
            this.retweetedStatus = retweetedStatus;
        }

        public long getRepostsCount() {
            return repostsCount;
        }

        public void setRepostsCount(long repostsCount) {
            this.repostsCount = repostsCount;
        }

        public long getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(long commentsCount) {
            this.commentsCount = commentsCount;
        }

        public long getAttitudesCount() {
            return attitudesCount;
        }

        public void setAttitudesCount(long attitudesCount) {
            this.attitudesCount = attitudesCount;
        }

        public boolean isIsLongText() {
            return isLongText;
        }

        public void setIsLongText(boolean isLongText) {
            this.isLongText = isLongText;
        }

        public long getMlevel() {
            return mlevel;
        }

        public void setMlevel(long mlevel) {
            this.mlevel = mlevel;
        }

        public RetweetedStatusBean.VisibleBean getVisible() {
            return visible;
        }

        public void setVisible(RetweetedStatusBean.VisibleBean visible) {
            this.visible = visible;
        }

        public long getBizFeature() {
            return bizFeature;
        }

        public void setBizFeature(long bizFeature) {
            this.bizFeature = bizFeature;
        }

        public long getHasActionTypeCard() {
            return hasActionTypeCard;
        }

        public void setHasActionTypeCard(long hasActionTypeCard) {
            this.hasActionTypeCard = hasActionTypeCard;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public long getUserType() {
            return userType;
        }

        public void setUserType(long userType) {
            this.userType = userType;
        }

        public String getCardid() {
            return cardid;
        }

        public void setCardid(String cardid) {
            this.cardid = cardid;
        }

        public long getPositiveRecomFlag() {
            return positiveRecomFlag;
        }

        public void setPositiveRecomFlag(long positiveRecomFlag) {
            this.positiveRecomFlag = positiveRecomFlag;
        }

        public String getGifIds() {
            return gifIds;
        }

        public void setGifIds(String gifIds) {
            this.gifIds = gifIds;
        }

        public long getIsShowBulletin() {
            return isShowBulletin;
        }

        public void setIsShowBulletin(long isShowBulletin) {
            this.isShowBulletin = isShowBulletin;
        }

        public List<?> getPicUrls() {
            return picUrls;
        }

        public void setPicUrls(List<?> picUrls) {
            this.picUrls = picUrls;
        }

        public List<?> getDarwlongags() {
            return darwlongags;
        }

        public void setDarwlongags(List<?> darwlongags) {
            this.darwlongags = darwlongags;
        }

        public List<?> getHotWeiboTags() {
            return hotWeiboTags;
        }

        public void setHotWeiboTags(List<?> hotWeiboTags) {
            this.hotWeiboTags = hotWeiboTags;
        }

        public List<?> getTextTagTips() {
            return textTagTips;
        }

        public void setTextTagTips(List<?> textTagTips) {
            this.textTagTips = textTagTips;
        }

        public static class RetweetedStatusBean {
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("id")
            private long id;
            @SerializedName("mid")
            private String mid;
            @SerializedName("idstr")
            private String idstr;
            @SerializedName("text")
            private String text;
            @SerializedName("textLength")
            private long textLength;
            @SerializedName("source_allowclick")
            private long sourceAllowclick;
            @SerializedName("source_type")
            private long sourceType;
            @SerializedName("source")
            private String source;
            @SerializedName("favorited")
            private boolean favorited;
            @SerializedName("truncated")
            private boolean truncated;
            @SerializedName("in_reply_to_status_id")
            private String inReplyToStatusId;
            @SerializedName("in_reply_to_user_id")
            private String inReplyToUserId;
            @SerializedName("in_reply_to_screen_name")
            private String inReplyToScreenName;
            @SerializedName("thumbnail_pic")
            private String thumbnailPic;
            @SerializedName("bmiddle_pic")
            private String bmiddlePic;
            @SerializedName("original_pic")
            private String originalPic;
            @SerializedName("geo")
            private Object geo;
            @SerializedName("user")
            private UserBean user;
            @SerializedName("reposts_count")
            private long repostsCount;
            @SerializedName("comments_count")
            private long commentsCount;
            @SerializedName("attitudes_count")
            private long attitudesCount;
            @SerializedName("isLongText")
            private boolean isLongText;
            @SerializedName("mlevel")
            private long mlevel;
            @SerializedName("visible")
            private VisibleBean visible;
            @SerializedName("biz_feature")
            private long bizFeature;
            @SerializedName("hasActionTypeCard")
            private long hasActionTypeCard;
            @SerializedName("userType")
            private long userType;
            @SerializedName("cardid")
            private String cardid;
            @SerializedName("positive_recom_flag")
            private long positiveRecomFlag;
            @SerializedName("gif_ids")
            private String gifIds;
            @SerializedName("is_show_bulletin")
            private long isShowBulletin;
            @SerializedName("pic_urls")
            private List<PicUrlsBean> picUrls;
            @SerializedName("darwin_tags")
            private List<?> darwlongags;
            @SerializedName("hot_weibo_tags")
            private List<?> hotWeiboTags;
            @SerializedName("text_tag_tips")
            private List<?> textTagTips;

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getIdstr() {
                return idstr;
            }

            public void setIdstr(String idstr) {
                this.idstr = idstr;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public long getTextLength() {
                return textLength;
            }

            public void setTextLength(long textLength) {
                this.textLength = textLength;
            }

            public long getSourceAllowclick() {
                return sourceAllowclick;
            }

            public void setSourceAllowclick(long sourceAllowclick) {
                this.sourceAllowclick = sourceAllowclick;
            }

            public long getSourceType() {
                return sourceType;
            }

            public void setSourceType(long sourceType) {
                this.sourceType = sourceType;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public boolean isFavorited() {
                return favorited;
            }

            public void setFavorited(boolean favorited) {
                this.favorited = favorited;
            }

            public boolean isTruncated() {
                return truncated;
            }

            public void setTruncated(boolean truncated) {
                this.truncated = truncated;
            }

            public String getInReplyToStatusId() {
                return inReplyToStatusId;
            }

            public void setInReplyToStatusId(String inReplyToStatusId) {
                this.inReplyToStatusId = inReplyToStatusId;
            }

            public String getInReplyToUserId() {
                return inReplyToUserId;
            }

            public void setInReplyToUserId(String inReplyToUserId) {
                this.inReplyToUserId = inReplyToUserId;
            }

            public String getInReplyToScreenName() {
                return inReplyToScreenName;
            }

            public void setInReplyToScreenName(String inReplyToScreenName) {
                this.inReplyToScreenName = inReplyToScreenName;
            }

            public String getThumbnailPic() {
                return thumbnailPic;
            }

            public void setThumbnailPic(String thumbnailPic) {
                this.thumbnailPic = thumbnailPic;
            }

            public String getBmiddlePic() {
                return bmiddlePic;
            }

            public void setBmiddlePic(String bmiddlePic) {
                this.bmiddlePic = bmiddlePic;
            }

            public String getOriginalPic() {
                return originalPic;
            }

            public void setOriginalPic(String originalPic) {
                this.originalPic = originalPic;
            }

            public Object getGeo() {
                return geo;
            }

            public void setGeo(Object geo) {
                this.geo = geo;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public long getRepostsCount() {
                return repostsCount;
            }

            public void setRepostsCount(long repostsCount) {
                this.repostsCount = repostsCount;
            }

            public long getCommentsCount() {
                return commentsCount;
            }

            public void setCommentsCount(long commentsCount) {
                this.commentsCount = commentsCount;
            }

            public long getAttitudesCount() {
                return attitudesCount;
            }

            public void setAttitudesCount(long attitudesCount) {
                this.attitudesCount = attitudesCount;
            }

            public boolean isIsLongText() {
                return isLongText;
            }

            public void setIsLongText(boolean isLongText) {
                this.isLongText = isLongText;
            }

            public long getMlevel() {
                return mlevel;
            }

            public void setMlevel(long mlevel) {
                this.mlevel = mlevel;
            }

            public VisibleBean getVisible() {
                return visible;
            }

            public void setVisible(VisibleBean visible) {
                this.visible = visible;
            }

            public long getBizFeature() {
                return bizFeature;
            }

            public void setBizFeature(long bizFeature) {
                this.bizFeature = bizFeature;
            }

            public long getHasActionTypeCard() {
                return hasActionTypeCard;
            }

            public void setHasActionTypeCard(long hasActionTypeCard) {
                this.hasActionTypeCard = hasActionTypeCard;
            }

            public long getUserType() {
                return userType;
            }

            public void setUserType(long userType) {
                this.userType = userType;
            }

            public String getCardid() {
                return cardid;
            }

            public void setCardid(String cardid) {
                this.cardid = cardid;
            }

            public long getPositiveRecomFlag() {
                return positiveRecomFlag;
            }

            public void setPositiveRecomFlag(long positiveRecomFlag) {
                this.positiveRecomFlag = positiveRecomFlag;
            }

            public String getGifIds() {
                return gifIds;
            }

            public void setGifIds(String gifIds) {
                this.gifIds = gifIds;
            }

            public long getIsShowBulletin() {
                return isShowBulletin;
            }

            public void setIsShowBulletin(long isShowBulletin) {
                this.isShowBulletin = isShowBulletin;
            }

            public List<PicUrlsBean> getPicUrls() {
                return picUrls;
            }

            public void setPicUrls(List<PicUrlsBean> picUrls) {
                this.picUrls = picUrls;
            }

            public List<?> getDarwlongags() {
                return darwlongags;
            }

            public void setDarwlongags(List<?> darwlongags) {
                this.darwlongags = darwlongags;
            }

            public List<?> getHotWeiboTags() {
                return hotWeiboTags;
            }

            public void setHotWeiboTags(List<?> hotWeiboTags) {
                this.hotWeiboTags = hotWeiboTags;
            }

            public List<?> getTextTagTips() {
                return textTagTips;
            }

            public void setTextTagTips(List<?> textTagTips) {
                this.textTagTips = textTagTips;
            }

            public static class VisibleBean {
                @SerializedName("type")
                private long type;
                @SerializedName("list_id")
                private long listId;

                public long getType() {
                    return type;
                }

                public void setType(long type) {
                    this.type = type;
                }

                public long getListId() {
                    return listId;
                }

                public void setListId(long listId) {
                    this.listId = listId;
                }
            }

            public static class PicUrlsBean {
                @SerializedName("thumbnail_pic")
                private String thumbnailPic;

                public String getThumbnailPic() {
                    return thumbnailPic;
                }

                public void setThumbnailPic(String thumbnailPic) {
                    this.thumbnailPic = thumbnailPic;
                }
            }
        }
    }
}
