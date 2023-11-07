package abstractfactory;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 23.07.12
 * Time: 18:48
 * To change this template use File | Settings | File Templates.
 */
public interface AddressFactory
{
   Address createAddress();
   PhoneNumber createPhoneNumber();
}
