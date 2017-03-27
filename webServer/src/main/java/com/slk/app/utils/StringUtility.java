package com.slk.app.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class StringUtility {

	public static boolean isEmpty(String value){
		if(value==null || value.isEmpty()){
			return true;
		}
		return false;
	}
	
	  private StringUtility() {
	    }

	    public static boolean isNullOrEmpty( String txt ) {
	        if ( null == txt || txt.trim().length() <= 0 ) {
	            return true;
	        }    
	        return false;
	    }

	    public static boolean isValid(String text, String allowedCharacters) {
	    	if (null == text) return false;
	    	return text.matches("[" + allowedCharacters + "]+\\.?");
	    }

	    public static String safeString( String txt, String defaultValue ) {
	        return (isNullOrEmpty( txt ) ? defaultValue : txt);
	    }

	    public static String safeString( Object object, String defaultValue ) {
	    	return (null == object ) ? defaultValue : object.toString();
	    }

	    public static String safeTrim( String txt ) {
	        return (null == txt ? txt : txt.trim());
	    }

	    public static String safeTrim( String txt, String defaultValue  ) {
	        return (null == txt ? defaultValue : txt.trim());
	    }

	    public static Long toLong( String strNumber ) {
	        if ( null != strNumber ) {
	            return Long.parseLong( strNumber.trim() );
	        }    
	        return 0L;
	    }

	    public static Long toLong( String strNumber, Long defaultValue ) {
	        try {
	            return toLong( strNumber );
	        } catch ( NumberFormatException nfEx ) {
	            return defaultValue;
	        }
	    }
	    
	    /** 
	     * Takes input string and return an integer.  
	     * If input string is null it will return 0.  
	     * This method will throw an unchecked exception if input string is not a valid number
	     * 
	     * @param strNumber
	     * @return a number converted from input
	     */
	    public static Integer toInteger( String strNumber ) {
	        if ( null != strNumber ){
	            return Integer.parseInt( strNumber.trim() );            
	        }
	        return 0;
	    }

	    public static Integer toInteger( String strNumber, Integer defaultValue ) {
	        try {
	            return toInteger( strNumber );
	        } catch ( NumberFormatException nfEx ) {
	            return defaultValue;
	        }
	    }

	    public static Double toDouble( String strNumber, Double defaultValue ) {
	        try {
	            return Double.parseDouble( strNumber );
	        } catch ( NumberFormatException nfEx ) {
	            return defaultValue;
	        }
	    }

	    public static Boolean toBoolean(String strBoolean) {
	    	return toBoolean(strBoolean, false);
	    }

	    public static Boolean toBoolean(String strBoolean, Boolean defaultValue) {
	    	if (StringUtility.isNullOrEmpty(strBoolean))
	    		return defaultValue;
	    	
	 
	        return Boolean.parseBoolean(strBoolean);
	    }

	    public static Date toDate(String format, String strDate) {
	        if ( null == format || null == strDate) {
	        	return null;
	        }

	        try {
	        	return new SimpleDateFormat(format).parse(strDate);            
	        } catch(Exception ignored) {
	        	return null;
	        }
	    }

	    public static final String DF_yyyyMMddHHmmssSSSz = "yyyy/MM/dd HH:mm:ss.SSS z";
	    public static final String DF_MMddyyyy = "MM/dd/yyyy";
	    public static final String DF_ABS_yyyyMMddHHmmssSSSz = "yyyyMMddHHmmssSSSz";
	    public static String fromDate(String format, Date date) {
	        if ( null == format || null == date) {
	        	return null;
	        }

	        try {
	        	return new SimpleDateFormat(format).format(date);            
	        } catch(Exception ignored) {
	        	return null;
	        }
	    }

	    public static String getStackTrace( Throwable th ) {
	        StringWriter sw = new StringWriter();
	        th.printStackTrace( new PrintWriter( sw ) );
	        return sw.toString();
	    }

	    public static boolean isNumber( String str ) {
	        if ( null != str && str.matches( "\\d" ) ) {
	            return true;
	        }
	        return false;
	    }

	    public static char getLastChar( String str ) {
	        if ( !isNullOrEmpty( str ) ) {
	            return str.charAt( str.length() - 1 );
	        }
	        return 0;
	    }

	    public static List<String> createListArray( String... stringArray ) {
	      	List<String> returnList = null;
	        if ( stringArray.length > 0 ) {
	            returnList = new ArrayList<String>();
	            for ( int i = 0; i < stringArray.length; i++ ) {
	                returnList.add( stringArray[i] );
	            }
	        }
	        return returnList;
	    }

		public static void assertValid(String value, String errorMessage) {
			if (StringUtility.isNullOrEmpty(value))
				throw new IllegalArgumentException(errorMessage);
		}
	
}
