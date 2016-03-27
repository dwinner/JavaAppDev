package abstractfactory;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 23.07.12
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class USAddress extends Address
{
   private static final String COUNTRY = "UNITED STATES";
   private static final String COMMA = ",";

   @Override
   public String getCountry()
   {
      return COUNTRY;
   }

   @Override
   public String getFullAddress()
   {
      return getStreet() + EOL_STRING + getCity() + COMMA + SPACE + getRegion()
        + SPACE + getPostalCode() + EOL_STRING + COUNTRY + EOL_STRING;
   }
}
