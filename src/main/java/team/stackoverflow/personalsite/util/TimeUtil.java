package team.stackoverflow.personalsite.util;

import java.time.LocalDateTime;

/**
 * @Author skyrocketing Hong
 * @Date 2020-07-02 002 10:45:31
 * @Version 1.0
 */
public class TimeUtil {
	public static String getDateTimeString() {
		LocalDateTime dateTime = LocalDateTime.now();
		return dateTime.toString().replace("T", " ");
	}
}
