package abstractfactory;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 23.07.12
 * Time: 17:25
 * To change this template use File | Settings | File Templates.
 */
public class FrenchAddress extends Address
{
   private static final String COUNTRY = "FRANCE";

   @Override
   public String getCountry()
   {
      return COUNTRY;
   }

   @Override
   public String getFullAddress()
   {
      return getStreet() + EOL_STRING + getPostalCode() + SPACE + getCity()
        + EOL_STRING + COUNTRY + EOL_STRING;
   }
}
