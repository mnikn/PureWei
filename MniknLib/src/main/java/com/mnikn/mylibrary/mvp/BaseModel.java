package com.mnikn.mylibrary.mvp;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Field;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class BaseModel implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    public static  <T extends BaseModel> T quickCreateFromParcel(T model,Parcel source){
        Field[] fields = model.getClass().getFields();
        for (Field field : fields) {
            int index = field.getType().getName().lastIndexOf(".") == -1 ? 0 : field.getType().getName().lastIndexOf(".") + 1;
            String type = field.getType().getName().substring(index);
            switch (type) {
                case "long":
                    field.setAccessible(true);
                    try {
                        field.setLong(model, source.readLong());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "String":
                    field.setAccessible(true);
                    try {
                        field.set(model,source.readString());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "boolean":
                    field.setAccessible(true);
                    try {
                        field.setBoolean(model,source.readByte() != 0);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        return model;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Field[] fields = getClass().getFields();
        for(Field field : fields){
            int index = field.getType().getName().lastIndexOf(".") == -1 ? 0 : field.getType().getName().lastIndexOf(".") + 1;
            String type = field.getType().getName().substring(index);
            switch (type){
                case "long":
                    field.setAccessible(true);
                    try {
                        dest.writeLong(field.getLong(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "String":
                    field.setAccessible(true);
                    try {
                        dest.writeString((String) field.get(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "boolean":
                    field.setAccessible(true);
                    try {
                        dest.writeByte((byte) (field.getBoolean(this) ? 1 : 0));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
