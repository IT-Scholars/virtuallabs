package edu.fiu.cis.acrl.virtuallabs.server.tools.timezone;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeZoneTools {

	static public Calendar changeTimeZone(Calendar calendar, TimeZone timeZone) {
	
		Calendar changedCal = Calendar.getInstance();
		changedCal.setTimeZone(timeZone); 
		changedCal.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
		changedCal.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		changedCal.set(Calendar.DATE, calendar.get(Calendar.DATE));
		changedCal.set(Calendar.HOUR, calendar.get(Calendar.HOUR));
		changedCal.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		changedCal.set(Calendar.SECOND, calendar.get(Calendar.SECOND));
		changedCal.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND));
		changedCal.set(Calendar.AM_PM, calendar.get(Calendar.AM_PM));

		return changedCal;
		
	}
	
	/**
    *
    * This function removes the extra GMT??? and the space from the 
    * beginning of the the input parameter.
    */
	static public String getTimeZoneIdInJava( String timeZoneId ) {

		if (timeZoneId == null)
			return "";
		
	   String timeZoneIdInJava = timeZoneId;
       // making sure that the extra GMT??? is removed from the time zone id coming as input
       int spacePos = timeZoneId.indexOf(' ');
       if( spacePos != -1 )
           timeZoneIdInJava = timeZoneId.substring( spacePos + 1 );
       return timeZoneIdInJava;

   }


   /**
    *
    * returns false if the timeZoneId includes "GMT" at the beginning
    */
   static public boolean needTimeZoneOffsetStr( String timeZoneId ) {
     
		if (timeZoneId == null)
			return false;
		
       if( timeZoneId.length() < 3 )
           return true;

       String tempStr = timeZoneId.substring(0,3);
       if( tempStr.equals("GMT") )
           return false;

       return true;
   }


	/**
	 * 
	 * To make the list of available time zone ids more readable, this helper
	 * function returns a string with GMT??? format that indicates the offset of
	 * the time zone id passed to this function.
	 */
   static public String getTimeZoneOffsetStr(String timeZoneId) {

		if (timeZoneId == null)
			return "";
		
		TimeZone tz = TimeZone.getTimeZone(timeZoneId);
		String sign = "+";
		int offset = tz.getRawOffset();
		if (offset < 0) {
			
			sign = "-";
			offset = -(offset);
		
		}
		
		int min = offset / (1000 * 60);
		int hour = min / 60;
		min = min % 60;
		DecimalFormat formatter = new DecimalFormat("00");
		String tzStr = "GMT" + sign + formatter.format(hour) + ":"
		+ formatter.format(min);
		return tzStr;

	}

	/**
	 * 
	 * @return
	 */
	static public ArrayList<String> getAvailableTimeZoneIds() {
		
		// TimeZone tZone = java.util.SimpleTimeZone.getDefault();
		
		String[] tzids = TimeZone.getAvailableIDs();
		ArrayList<String> fixedTzids = new ArrayList<String>();
		
		// just an initial value which always needs to be changed for the first
		// time.
		int preOffset = -1000;
		for (String tzid : tzids) {

			TimeZone tz = TimeZone.getTimeZone(tzid);
			int offset = tz.getRawOffset();
			String tzStr = getTimeZoneOffsetStr(tzid);
		
			// Adding GMT??? before the time zone ids with the same offset
			if (offset != preOffset) {
		
				fixedTzids.add(tzStr);
				preOffset = offset;
			
			}
			
			tzStr += " " + tzid;
		
			fixedTzids.add(tzStr);
		
		}

		return fixedTzids;
		
	}

}
