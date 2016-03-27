package afactory;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 28.12.12
 * Time: 19:03
 * Абстрактный класс для телефонных номеров.
 */
public abstract class PhoneNumber
{
   protected PhoneNumber()
   {
   }

   protected PhoneNumber(long phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }

   public long getPhoneNumber()
   {
      return phoneNumber;
   }

   public void setPhoneNumber(String newValue)
   {
      try
      {
         phoneNumber = Long.parseLong(newValue);
      }
      catch (NumberFormatException nfEx)
      {
         throw new RuntimeException(nfEx);
      }
   }

   public void setPhoneNumber(long newValue)
   {
      phoneNumber = newValue;
   }

   public abstract String obtainCountryCode();

   private long phoneNumber;
}
