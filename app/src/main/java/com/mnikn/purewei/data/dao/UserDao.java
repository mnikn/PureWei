package com.mnikn.purewei.data.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.mnikn.library.utils.Numbers;
import com.mnikn.purewei.App;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.model.User;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserDao {

    public static User getUser(long uid) {
        Cursor cursor = App.getAppContext().getContentResolver().query(
                WeiboContract.UserEntry.CONTENT_URI,
                null,
                WeiboContract.UserEntry.COLUMN_USER_ID + " = ?",
                new String[]{Numbers.longToString(uid)},
                null);
        cursor.moveToFirst();

        return getUser(cursor);
    }

    public static User getUser(Cursor cursor){
        if(cursor.isAfterLast()) return null;

        User user = new User();
        user.id = WeiboContract.UserEntry.getUserId(cursor);
        user.name = WeiboContract.UserEntry.getName(cursor);
        user.profileImageUrl = WeiboContract.UserEntry.getProfileImageUrl(cursor);
        user.avatarLarge = WeiboContract.UserEntry.getAvatarLargeUrl(cursor);
        user.avatarHd = WeiboContract.UserEntry.getAvatarHdUrl(cursor);
        user.coverImage = WeiboContract.UserEntry.getCoverUrl(cursor);
        user.description = WeiboContract.UserEntry.getDescription(cursor);
        user.followersCount = WeiboContract.UserEntry.getFollowerCount(cursor);
        user.friendsCount = WeiboContract.UserEntry.getFriendsCount(cursor);
        user.statusesCount = WeiboContract.UserEntry.getWeiboCount(cursor);
        user.following = WeiboContract.UserEntry.getFollowing(cursor);
        user.followMe = WeiboContract.UserEntry.getFollowMe(cursor);
        user.type = WeiboContract.UserEntry.getUserType(cursor);
        return user;
    }

    public static void insertUser(ContentValues values) {
        App.getAppContext().getContentResolver()
                .insert(WeiboContract.UserEntry.CONTENT_URI,values);
    }

    public static void insertUser(User user){
        ContentValues values = new ContentValues();
        values.put(WeiboContract.UserEntry.COLUMN_USER_ID,user.id);
        values.put(WeiboContract.UserEntry.COLUMN_NAME,user.name);
        values.put(WeiboContract.UserEntry.COLUMN_PROFILE_IMAGE_URL,user.profileImageUrl);
        values.put(WeiboContract.UserEntry.COLUMN_AVATAR_LARGE_URL, user.avatarLarge);
        values.put(WeiboContract.UserEntry.COLUMN_AVATAR_HD_URL, user.avatarHd);
        values.put(WeiboContract.UserEntry.COLUMN_COVER_URL, user.coverImage);
        values.put(WeiboContract.UserEntry.COLUMN_DESCRIPTION, user.description);
        values.put(WeiboContract.UserEntry.COLUMN_FOLLOWERS_COUNT, user.followersCount);
        values.put(WeiboContract.UserEntry.COLUMN_FRIENDS_COUNT, user.friendsCount);
        values.put(WeiboContract.UserEntry.COLUMN_STATUS_COUNT, user.statusesCount);
        values.put(WeiboContract.UserEntry.COLUMN_FOLLOWING, user.following);
        values.put(WeiboContract.UserEntry.COLUMN_FOLLOW_ME, user.followMe);
        values.put(WeiboContract.UserEntry.COLUMN_USER_TYPE, user.type);

        insertUser(values);
    }

    public static void delete(long id){
        App.getAppContext().getContentResolver()
                .delete(WeiboContract.UserEntry.CONTENT_URI,
                        WeiboContract.UserEntry.COLUMN_USER_ID + " = ?",
                        new String[]{Numbers.longToString(id)});
    }

    public static void clear(){
        App.getAppContext().getContentResolver()
                .delete(WeiboContract.StatusEntry.CONTENT_URI,
                        null,
                        null);
    }

}
