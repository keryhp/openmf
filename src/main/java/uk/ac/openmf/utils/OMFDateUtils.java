package uk.ac.openmf.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * A utility class.
 *
 */
public final class OMFDateUtils {

	public static SimpleDateFormat formatter;

	static{
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public static long getTodaysDateTimeInMillis(){
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTimeInMillis();
	}

	public static String getDateFromTimestamp(long timestamp){
		return formatter.format(new Date(timestamp));
	}
	
	public static long getThisWeekStartDate(){
		// Get calendar set to current date and time
		Calendar cal = Calendar.getInstance();
		// Set the calendar to monday of the current week
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTimeInMillis();
	}
	
	public static long getPrevWeekStartDate(){
		// Get calendar set to current date and time
		Calendar cal = Calendar.getInstance();
		// Set the calendar to monday of the current week
		cal.add(Calendar.DATE, -7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTimeInMillis();
	}
}
