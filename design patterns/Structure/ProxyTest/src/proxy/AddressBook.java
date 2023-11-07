package proxy;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 23.08.12
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public interface AddressBook<T extends Address>
{
   void add(T address);
   List<T> getAllAddresses();
   T getAddress(String description);
   void open();
   void save();
}
