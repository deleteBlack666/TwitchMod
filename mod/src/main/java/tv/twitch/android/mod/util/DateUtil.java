package tv.twitch.android.mod.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    private final static String API_DATE_SUBSECOND_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final static String API_DATE_FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";

    public static Date getStandardizeDateString(String str) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(API_DATE_FORMAT_ISO8601, Locale.US);
        simpleDateFormat.setTimeZone(timeZone);

        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(API_DATE_SUBSECOND_FORMAT, Locale.getDefault());
            simpleDateFormat2.setTimeZone(timeZone);

            try {
                return simpleDateFormat2.parse(str);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Source: https://commons.apache.org/proper/commons-lang/apidocs/src-html/org/apache/commons/lang3/time/DateUtils.html#line.190
     */
    public static boolean isSameDay(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        Calendar calDate1 = Calendar.getInstance();
        Calendar calDate2 = Calendar.getInstance();
        calDate1.setTime(date1);
        calDate2.setTime(date2);

        return calDate1.get(Calendar.ERA) == calDate2.get(Calendar.ERA) &&
                calDate1.get(Calendar.YEAR) == calDate2.get(Calendar.YEAR) &&
                calDate1.get(Calendar.DAY_OF_YEAR) == calDate2.get(Calendar.DAY_OF_YEAR);
    }
}
