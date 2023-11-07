package abstractfactory;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 23.07.12
 * Time: 18:49
 * To change this template use File | Settings | File Templates.
 */
public class USAddressFactory implements AddressFactory
{
   @Override
   public Address createAddress()
   {
      return new USAddress();
   }

   @Override
   public PhoneNumber createPhoneNumber()
   {
      return new USPhoneNumber();
   }
}
