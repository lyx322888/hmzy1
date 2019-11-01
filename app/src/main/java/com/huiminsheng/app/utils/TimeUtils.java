package com.huiminsheng.app.utils;

import java.text.SimpleDateFormat;

/**
 * Created by GuoMeng on 2017/7/27
 * Description:
 */

public class TimeUtils {

    /**
     * 时间格式
     * */
    private static final String[] TIME_TYPE = {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd hh:mm:ss",
            "yyyy-MM-dd",
            "HH:mm:ss",
            "hh:mm:ss",
            "HH:mm",
            "hh:mm",
            "dd/MM",
            "yyyy-MM-dd HH:mm",
    };

    private static TimeUtils timeUtils;

    private TimeUtils() {

    }

    public static TimeUtils getInstance() {
        if (timeUtils == null) {
            timeUtils = new TimeUtils();
        }
        return timeUtils;
    }

    /**
     * @param strTime  时间戳
     * @param timeGrow 是否将时间戳乘以1000
     * @param timeType 返回时间的格式
     */
    public String getTime(String strTime, boolean timeGrow, int timeType) {

        long longTime;

        if (timeGrow) {
            longTime = Long.parseLong(strTime) * 1000;
        } else {
            longTime = Long.parseLong(strTime);
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(getTimeType(timeType));
            return sdf.format(longTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * @param timeType 根据int值返回对应的时间类型
     */
    private String getTimeType(int timeType) {
        return TIME_TYPE[timeType];
    }


    /**
     * 已格式化的时间之间的天数差
     * */
    public String getDayRange(String maxTime, String minTime) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            java.util.Date maxDate = myFormatter.parse(maxTime);
            java.util.Date minDate = myFormatter.parse(minTime);
            day = (maxDate.getTime() - minDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

}
