package com.mnikn.purewei.support.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/9/12 0012.
 */
public class UserBean {
    @SerializedName(value = "id",alternate = "uid")
    private long id;
    @SerializedName("idstr")
    private String idstr;
    @SerializedName("class")
    private long classX;
    @SerializedName("screen_name")
    private String screenName;
    @SerializedName("name")
    private String name;
    @SerializedName("province")
    private String province;
    @SerializedName("city")
    private String city;
    @SerializedName("location")
    private String location;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("profile_image_url")
    private String profileImageUrl;
    @SerializedName("cover_image")
    private String coverImage;
    @SerializedName("cover_image_phone")
    private String coverImagePhone;
    @SerializedName("profile_url")
    private String profileUrl;
    @SerializedName("domain")
    private String domain;
    @SerializedName("weihao")
    private String weihao;
    @SerializedName("gender")
    private String gender;
    @SerializedName("followers_count")
    private long followersCount;
    @SerializedName("friends_count")
    private long friendsCount;
    @SerializedName("pagefriends_count")
    private long pagefriendsCount;
    @SerializedName("statuses_count")
    private long statusesCount;
    @SerializedName("favourites_count")
    private long favouritesCount;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("following")
    private boolean following;
    @SerializedName("allow_all_act_msg")
    private boolean allowAllActMsg;
    @SerializedName("geo_enabled")
    private boolean geoEnabled;
    @SerializedName("verified")
    private boolean verified;
    @SerializedName("verified_type")
    private long verifiedType;
    @SerializedName("remark")
    private String remark;
    @SerializedName("ptype")
    private long ptype;
    @SerializedName("allow_all_comment")
    private boolean allowAllComment;
    @SerializedName("avatar_large")
    private String avatarLarge;
    @SerializedName("avatar_hd")
    private String avatarHd;
    @SerializedName("verified_reason")
    private String verifiedReason;
    @SerializedName("verified_trade")
    private String verifiedTrade;
    @SerializedName("verified_reason_url")
    private String verifiedReasonUrl;
    @SerializedName("verified_source")
    private String verifiedSource;
    @SerializedName("verified_source_url")
    private String verifiedSourceUrl;
    @SerializedName("follow_me")
    private boolean followMe;
    @SerializedName("online_status")
    private long onlineStatus;
    @SerializedName("bi_followers_count")
    private long biFollowersCount;
    @SerializedName("lang")
    private String lang;
    @SerializedName("star")
    private long star;
    @SerializedName("mbtype")
    private long mbtype;
    @SerializedName("mbrank")
    private long mbrank;
    @SerializedName("block_word")
    private long blockWord;
    @SerializedName("block_app")
    private long blockApp;
    @SerializedName("credit_score")
    private long creditScore;
    @SerializedName("user_ability")
    private long userAbility;
    @SerializedName("cardid")
    private String cardid;
    @SerializedName("urank")
    private long urank;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public long getClassX() {
        return classX;
    }

    public void setClassX(long classX) {
        this.classX = classX;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCoverImagePhone() {
        return coverImagePhone;
    }

    public void setCoverImagePhone(String coverImagePhone) {
        this.coverImagePhone = coverImagePhone;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getWeihao() {
        return weihao;
    }

    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(long followersCount) {
        this.followersCount = followersCount;
    }

    public long getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(long friendsCount) {
        this.friendsCount = friendsCount;
    }

    public long getPagefriendsCount() {
        return pagefriendsCount;
    }

    public void setPagefriendsCount(long pagefriendsCount) {
        this.pagefriendsCount = pagefriendsCount;
    }

    public long getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(long statusesCount) {
        this.statusesCount = statusesCount;
    }

    public long getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(long favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isAllowAllActMsg() {
        return allowAllActMsg;
    }

    public void setAllowAllActMsg(boolean allowAllActMsg) {
        this.allowAllActMsg = allowAllActMsg;
    }

    public boolean isGeoEnabled() {
        return geoEnabled;
    }

    public void setGeoEnabled(boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public long getVerifiedType() {
        return verifiedType;
    }

    public void setVerifiedType(long verifiedType) {
        this.verifiedType = verifiedType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getPtype() {
        return ptype;
    }

    public void setPtype(long ptype) {
        this.ptype = ptype;
    }

    public boolean isAllowAllComment() {
        return allowAllComment;
    }

    public void setAllowAllComment(boolean allowAllComment) {
        this.allowAllComment = allowAllComment;
    }

    public String getAvatarLarge() {
        return avatarLarge;
    }

    public void setAvatarLarge(String avatarLarge) {
        this.avatarLarge = avatarLarge;
    }

    public String getAvatarHd() {
        return avatarHd;
    }

    public void setAvatarHd(String avatarHd) {
        this.avatarHd = avatarHd;
    }

    public String getVerifiedReason() {
        return verifiedReason;
    }

    public void setVerifiedReason(String verifiedReason) {
        this.verifiedReason = verifiedReason;
    }

    public String getVerifiedTrade() {
        return verifiedTrade;
    }

    public void setVerifiedTrade(String verifiedTrade) {
        this.verifiedTrade = verifiedTrade;
    }

    public String getVerifiedReasonUrl() {
        return verifiedReasonUrl;
    }

    public void setVerifiedReasonUrl(String verifiedReasonUrl) {
        this.verifiedReasonUrl = verifiedReasonUrl;
    }

    public String getVerifiedSource() {
        return verifiedSource;
    }

    public void setVerifiedSource(String verifiedSource) {
        this.verifiedSource = verifiedSource;
    }

    public String getVerifiedSourceUrl() {
        return verifiedSourceUrl;
    }

    public void setVerifiedSourceUrl(String verifiedSourceUrl) {
        this.verifiedSourceUrl = verifiedSourceUrl;
    }

    public boolean isFollowMe() {
        return followMe;
    }

    public void setFollowMe(boolean followMe) {
        this.followMe = followMe;
    }

    public long getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(long onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public long getBiFollowersCount() {
        return biFollowersCount;
    }

    public void setBiFollowersCount(long biFollowersCount) {
        this.biFollowersCount = biFollowersCount;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public long getStar() {
        return star;
    }

    public void setStar(long star) {
        this.star = star;
    }

    public long getMbtype() {
        return mbtype;
    }

    public void setMbtype(long mbtype) {
        this.mbtype = mbtype;
    }

    public long getMbrank() {
        return mbrank;
    }

    public void setMbrank(long mbrank) {
        this.mbrank = mbrank;
    }

    public long getBlockWord() {
        return blockWord;
    }

    public void setBlockWord(long blockWord) {
        this.blockWord = blockWord;
    }

    public long getBlockApp() {
        return blockApp;
    }

    public void setBlockApp(long blockApp) {
        this.blockApp = blockApp;
    }

    public long getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(long creditScore) {
        this.creditScore = creditScore;
    }

    public long getUserAbility() {
        return userAbility;
    }

    public void setUserAbility(long userAbility) {
        this.userAbility = userAbility;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public long getUrank() {
        return urank;
    }

    public void setUrank(long urank) {
        this.urank = urank;
    }
}
