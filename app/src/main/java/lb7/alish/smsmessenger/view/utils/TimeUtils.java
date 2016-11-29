package lb7.alish.smsmessenger.view.utils;

import java.util.Calendar;

/**
 * Created by AliSh on 11/29/2016.
 */

public class TimeUtils {

    public static String getTime(long time) {
        Calendar currentCal = Calendar.getInstance();
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTimeInMillis(time);

        int dayOfYear = timeCal.get(Calendar.DAY_OF_YEAR);
        int hour = timeCal.get(Calendar.HOUR_OF_DAY);
        int minute = timeCal.get(Calendar.MINUTE);

        int currentDayOfYear = currentCal.get(Calendar.DAY_OF_YEAR);

        if (currentDayOfYear == dayOfYear) {
            String hourString = hour < 10 ? ("0" + hour) : ("" + hour);
            String minuteString = minute < 10 ? ("" + minute) : ("" + minute);
            return hourString + ":" + minuteString;
        } else {
            int dayOfMonth = timeCal.get(Calendar.DAY_OF_MONTH);
            int monthOfYear = timeCal.get(Calendar.MONTH);
            int year = timeCal.get(Calendar.YEAR);
            return year + "/" + monthOfYear + "/" + dayOfMonth;
        }
    }

    public static String getTimeForConversations(long time) {
        Calendar currentCal = Calendar.getInstance();
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTimeInMillis(time);

        int dayOfYear = timeCal.get(Calendar.DAY_OF_YEAR);
        int hour = timeCal.get(Calendar.HOUR_OF_DAY);
        int minute = timeCal.get(Calendar.MINUTE);
        int year = timeCal.get(Calendar.YEAR);

        int currentDayOfYear = currentCal.get(Calendar.DAY_OF_YEAR);
        int currentYear = currentCal.get(Calendar.YEAR);

        if (currentDayOfYear == dayOfYear) {
            String hourString = hour < 10 ? ("0" + hour) : ("" + hour);
            String minuteString = minute < 10 ? ("" + minute) : ("" + minute);
            return "Today , " + hourString + ":" + minuteString;
        } else if (year == currentYear) {
            int dayOfMonth = timeCal.get(Calendar.DAY_OF_MONTH);
            int monthOfYear = timeCal.get(Calendar.MONTH);
            String hourString = hour < 10 ? ("0" + hour) : ("" + hour);
            String minuteString = minute < 10 ? ("" + minute) : ("" + minute);
            return monthOfYear + "/" + dayOfMonth + " , " + hourString + ":" + minuteString;
        } else {
            int dayOfMonth = timeCal.get(Calendar.DAY_OF_MONTH);
            int monthOfYear = timeCal.get(Calendar.MONTH);
            String hourString = hour < 10 ? ("0" + hour) : ("" + hour);
            String minuteString = minute < 10 ? ("" + minute) : ("" + minute);
            return year + "/" + monthOfYear + "/" + dayOfMonth + " , " + hourString + ":" + minuteString;
        }
    }
}
