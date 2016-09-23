package com.mnikn.purewei.support.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class UserBean {
    @SerializedName(value = "id",alternate = "uid")
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
    @SerializedName("cover_image")
    public String coverImage;
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
    @SerializedName("credit_score")
    public long creditScore;
    @SerializedName("user_ability")
    public long userAbility;
    @SerializedName("cardid")
    public String cardid;
    @SerializedName("urank")
    public long urank;
}
