package com.mnikn.mylibrary.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DateUtil {

    public static String getShowDay(long time){
        Calendar current = Calendar.getInstance();

        Calendar record = Calendar.getInstance();
        record.setTimeInMillis(time);


        int currentYear = current.get(Calendar.YEAR);
        int currentMonth = current.get(Calendar.MONTH) + 1;
        int currentDay = current.get(Calendar.DAY_OF_MONTH);

        int recordYear = record.get(Calendar.YEAR);
        int recordMonth = record.get(Calendar.MONTH) + 1;
        int recordDay = record.get(Calendar.DAY_OF_MONTH);

        String showStr;
        int hour = record.get(Calendar.HOUR_OF_DAY),minute = record.get(Calendar.MINUTE);
        if(currentYear == recordYear && currentMonth == recordMonth ){

            if(currentDay == recordDay){
                showStr = "今天" + String.format("%02d",hour) + ":" + String.format("%02d",minute);
            }
            else if(currentDay - 1 == recordDay){
                showStr = "昨天" + String.format("%02d", hour) + ":" + String.format("%02d", minute);
            }
            else{
                showStr = recordMonth + "月" +  recordDay + "日" +
                        String.format("%02d", hour) + ":" + String.format("%02d", minute);
            }
        }
        else{
            showStr = recordMonth + "月" +  recordDay + "日" +
                    String.format("%02d", hour) + ":" + String.format("%02d", minute);
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
