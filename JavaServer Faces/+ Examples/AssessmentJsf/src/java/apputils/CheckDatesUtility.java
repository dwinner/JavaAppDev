package apputils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility for checking dates.
 *
 * @author Denis
 * @version demo 21-12-2012
 */
public class CheckDatesUtility
{
   /**
    * Check whether the input string is date in format yyyy-mm-dd hh:mm:ss
    *
    * @param stringToCheck String to check
    * @return true, if the input string is date in specified format
    */
   public static boolean isDateString(String stringToCheck)
   {
      Matcher dateMatcher = DATE_PATTERN.matcher(stringToCheck.trim());
      return dateMatcher.matches();
   }

   private CheckDatesUtility()
   {
   }

   private static final String DATE_REG_EXPR =
     "^(\\d{4})-(\\d{1,2})-(\\d{1,2})\\s+(\\d{1,2}):(\\d{1,2})(:\\d{1,2})?$";
   private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REG_EXPR);
}
