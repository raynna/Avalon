import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Avalon client
 * @Date - 15 Feb 2016
 */
public class Utils {

    /**
     * Gets the calander.
     *
     * @return the calander
     */
    public static Date getCalander() {
	return Calendar.getInstance().getTime();
    }

    /**
     * Gets the month.
     *
     * @param month the month
     * @return the month
     */
    public static String getMonth(int month) {
	return new DateFormatSymbols().getMonths()[month - 1];
    }

    /**
     * Gets the day.
     *
     * @return the day
     */
    public static String getDay() {
	String today = "";
	String suffixes[] = { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "st" };
	Date date = new Date();
	SimpleDateFormat formatDayOfMonth = new SimpleDateFormat("d");
	int day = Integer.parseInt(formatDayOfMonth.format(date));
	today = day + suffixes[day];
	return today;
    }

    /**
     * Gets the formated time short.
     *
     * @param seconds
     *            the seconds
     * @return the formated time short
     */
    public static String getFormatedTimeShort(long seconds) {
	long minutes = seconds / 60;
	long hours = minutes / 60;
	long days = hours / 24;
	minutes -= hours * 60;
	seconds -= (hours * 60 * 60) + (minutes * 60);
	hours -= days * 24;
	if (days > 0)
	    return days + "d:" + hours + "h:" + minutes + "m:" + seconds + "s";
	else if (hours > 0)
	    return hours + "h:" + minutes + "m:" + seconds + "s";
	else if (minutes > 0)
	    return minutes + "m:" + seconds + "s";
	return seconds + "s";
    }

    /**
     * Gets the current time.
     *
     * @return the current time
     */
    public static String getCurrentTime() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
	String time = sdf.format(date);
	return time;
    }
     
    

}
