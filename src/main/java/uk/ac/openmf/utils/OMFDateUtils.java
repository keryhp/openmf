package uk.ac.openmf.utils;

import java.text.SimpleDateFormat;
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

	}
