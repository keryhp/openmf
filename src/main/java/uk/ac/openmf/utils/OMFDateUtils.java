package uk.ac.openmf.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

	}
