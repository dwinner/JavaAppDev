package goffbuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.07.12
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class InformationRequiredException extends Exception
{
   public InformationRequiredException(int itemsRequired)
   {
      informationRequired = itemsRequired;
   }

   public int getInformationRequired()
   {
      return informationRequired;
   }

   public static final int START_DATE_REQUIRED = 1;
   public static final int END_DATE_REQUIRED = 2;
   public static final int DESCRIPTION_REQUIRED = 4;
   public static final int ATTENDEE_REQUIRED = 8;
   public static final int LOCATION_REQUIRED = 16;

   private static final String MESSAGE = "goffbuilder.Appointment cannot be created because further information is required";
   private int informationRequired;
}
