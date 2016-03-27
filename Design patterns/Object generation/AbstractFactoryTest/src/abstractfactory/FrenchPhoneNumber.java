package abstractfactory;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 23.07.12
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
public class FrenchPhoneNumber extends PhoneNumber
{
   private static final String COUNTRY_CODE = "33";
   private static final int NUMBER_LENGTH = 9;

   @Override
   public String getCountryCode()
   {
      return COUNTRY_CODE;
   }

   @Override
   public void setPhoneNumber(String newNumber)
   {
      if (newNumber.length() == NUMBER_LENGTH)
         super.setPhoneNumber(newNumber);
   }
}
