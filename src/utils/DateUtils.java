package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 牧心
 * @Date: 2020/09/07
 * @Description:
 */
public class DateUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getDateStamp() {
        return dateFormat.format(new Date());
    }
}
