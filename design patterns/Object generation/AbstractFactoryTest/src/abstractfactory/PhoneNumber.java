package abstractfactory;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 23.07.12
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class PhoneNumber
{
   private String phoneNumber;

   public String getPhoneNumber()
   {
      return phoneNumber;
   }

   public void setPhoneNumber(String newNumber)
   {
      try
      {
         Long.parseLong(newNumber);
         phoneNumber = newNumber;
      }
      catch (NumberFormatException exc)
      {

      }
   }

   public abstract String getCountryCode();
}
