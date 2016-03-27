package abstractfactory;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 23.07.12
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class FrenchAddressFactory implements AddressFactory
{
   @Override
   public Address createAddress()
   {
      return new FrenchAddress();
   }

   @Override
   public PhoneNumber createPhoneNumber()
   {
      return new FrenchPhoneNumber();
   }
}
