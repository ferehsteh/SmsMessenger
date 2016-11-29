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
        int hour = timeCal.get(Calendar.HOUR);
        int amOrPm = timeCal.get(Calendar.AM_PM);
        int minute = timeCal.get(Calendar.MINUTE);

        int currentDayOfYear = currentCal.get(Calendar.DAY_OF_YEAR);

        if (currentDayOfYear == dayOfYear) {
            String amOrPmString;
            if (amOrPm == 0) {
                amOrPmString = "am";
            } else {
                amOrPmString = "pm";
            }
            return hour + ":" + minute + " " + amOrPmString;
//        } else if (currentDayOfYear < dayOfYear + 6) {
//            int dayOfYear = timeCal.get(Calendar.DAY_OF_WEEK);
//            return
        } else {
            int dayOfMonth = timeCal.get(Calendar.DAY_OF_MONTH);
            int monthOfYear = timeCal.get(Calendar.MONTH);
            return monthOfYear + "/" + dayOfMonth;
        }

    }
}
