package com.mnikn.mylibrary.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class DateUtil {

    public static String getShowDay(long time){
        Calendar current = Calendar.getInstance();

        Calendar record = Calendar.getInstance();
        record.setTimeInMillis(time);


        int currentYear = current.get(Calendar.YEAR);
        int currentMonth = current.get(Calendar.MONTH);
        int currentDay = current.get(Calendar.DAY_OF_MONTH);

        int recordYear = record.get(Calendar.YEAR);
        int recordMonth = record.get(Calendar.MONTH);
        int recordDay = record.get(Calendar.DAY_OF_MONTH);

        String showStr=null;

        if(currentYear == recordYear && currentMonth == recordMonth ){
            if(currentDay == recordDay){
                showStr = "今天" + record.get(Calendar.HOUR_OF_DAY) + ":" + record.get(Calendar.MINUTE);
            }
            else if(currentDay - 1 == recordDay){
                showStr = "昨天" + record.get(Calendar.HOUR_OF_DAY) + ":" + record.get(Calendar.MINUTE);
            }
            else{
                showStr = recordMonth + "月" +  recordDay + "日" +
                        record.get(Calendar.HOUR_OF_DAY) + ":" + record.get(Calendar.MINUTE);
            }
        }
        else{
            showStr = recordMonth + "月" +  recordDay + "日" +
                    record.get(Calendar.HOUR_OF_DAY) + ":" + record.get(Calendar.MINUTE);
        }
        return showStr;
    }

    public static long dateToLong(String date){
        return new Date(date).getTime();
    }
    public static String longToDate(long time){

        return new Date(time).toString();
    }
}
