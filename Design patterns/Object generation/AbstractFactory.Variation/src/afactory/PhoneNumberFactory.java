package afactory;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 28.12.12
 * Time: 19:11
 * Генератор объектов для телефонных номеров.
 */
public class PhoneNumberFactory
{
   /**
    * Французский телефонный номер
    *
    * @return Французский телефонный номер
    */
   public static PhoneNumber createFrenchPhoneNumber()
   {
      return new PhoneNumber()
      {
         @Override
         public String obtainCountryCode()
         {
            return "33";
         }

         @Override
         public void setPhoneNumber(String newPhoneNumber)
         {
            if (newPhoneNumber.length() == NUMBER_LENGTH)
               super.setPhoneNumber(newPhoneNumber);
         }

         private static final short NUMBER_LENGTH = 9;
      };
   }

   /**
    * Английский телефонный номер
    *
    * @return Английский телефонный номер
    */
   public static PhoneNumber createUSPhoneNumber()
   {
      return new PhoneNumber()
      {
         @Override
         public String obtainCountryCode()
         {
            return "01";
         }

         @Override
         public void setPhoneNumber(String newPhoneNumber)
         {
            if (newPhoneNumber.length() == NUMBER_LENGTH)
               super.setPhoneNumber(newPhoneNumber);
         }

         private static final short NUMBER_LENGTH = 10;
      };
   }

   private PhoneNumberFactory()
   {
   }
}
