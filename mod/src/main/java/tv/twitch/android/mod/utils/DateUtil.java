package tv.twitch.android.mod.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            e.printStackTrace();
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
}
