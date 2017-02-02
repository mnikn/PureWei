package com.mnikn.purewei.data.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.mnikn.library.utils.Numbers;
import com.mnikn.purewei.App;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.model.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class PictureDao {

    public static List<Picture> getPictures(long statusId) {
        Cursor cursor = getPicturesCursor(statusId);
        List<Picture> pictures = new ArrayList<>();
        while (cursor.moveToNext()) {
            pictures.add(getPicture(cursor));
        }
        return pictures;
    }

    public static Cursor getPicturesCursor(long statusId) {
        Cursor cursor = App.getAppContext().getContentResolver().query(
                WeiboContract.PictureEntry.CONTENT_URI,
                null,
                WeiboContract.PictureEntry.COLUMN_STATUS_ID + " = ?",
                new String[]{Numbers.longToString(statusId)},
                null);
        return cursor;
    }

    public static Picture getPicture(Cursor cursor) {
        Picture picture = new Picture();
        picture.statusId = WeiboContract.PictureEntry.getStatusId(cursor);
        picture.thumbnailPic = WeiboContract.PictureEntry.getThumnbnailUrl(cursor);
        picture.middlePic = WeiboContract.PictureEntry.getMiddleUrl(cursor);
        picture.largePic = WeiboContract.PictureEntry.getLargeUrl(cursor);
        return picture;
    }

    public static void insertPicture(ContentValues values) {
        App.getAppContext().getContentResolver()
                .insert(WeiboContract.PictureEntry.CONTENT_URI, values);
    }

    public static void insertPicture(Picture picture) {
        insertPicture(toContentValues(picture));
    }

    public static void insertPictures(List<Picture> pictures) {
        ContentValues[] values = new ContentValues[pictures.size()];
        for (int i = 0;i < pictures.size();++i) {
            values[i] = toContentValues(pictures.get(i));
        }
        App.getAppContext().getContentResolver()
                .bulkInsert(WeiboContract.PictureEntry.CONTENT_URI,values);
    }

    public static void delete(long statusId) {
        App.getAppContext().getContentResolver()
                .delete(WeiboContract.PictureEntry.CONTENT_URI,
                        WeiboContract.PictureEntry.COLUMN_STATUS_ID + " = ?",
                        new String[]{Numbers.longToString(statusId)});
    }

    public static void clear() {
        App.getAppContext().getContentResolver()
                .delete(WeiboContract.PictureEntry.CONTENT_URI,
                        null,
                        null);
    }

    private static ContentValues toContentValues(Picture picture) {
        ContentValues values = new ContentValues();
        values.put(WeiboContract.PictureEntry.COLUMN_STATUS_ID, picture.statusId);
        values.put(WeiboContract.PictureEntry.COLUMN_THUMBNAIL_URL, picture.thumbnailPic);
        values.put(WeiboContract.PictureEntry.COLUMN_MIDDLE_URL, picture.thumbnailPic.replace("thumbnail", "bmiddle"));
        values.put(WeiboContract.PictureEntry.COLUMN_LARGE_URL, picture.thumbnailPic.replace("thumbnail", "large"));
        return values;
    }

}
