package proxy;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 23.08.12
 * Time: 11:39
 * To change this template use File | Settings | File Templates.
 */
public class AddressBookImpl implements AddressBook<Address>
{
   public AddressBookImpl(File newFile)
   {
      file = newFile;
      open();
   }

   @Override
   public void add(Address address)
   {
      if (!addresses.contains(address))
         addresses.add(address);
   }

   @Override
   public List<Address> getAllAddresses()
   {
      return addresses;
   }

   @Override
   public Address getAddress(String description)
   {
      Iterator<Address> addressIterator = addresses.iterator();
      Address currentAddress = null;
      while (addressIterator.hasNext())
      {
         currentAddress = addressIterator.next();
         if (currentAddress.getDescription().equalsIgnoreCase(description))
            return currentAddress;
      }
      return null;
   }

   @Override
   public void open()
   {
      addresses = (List<Address>) FileLoader.loadData(file);
   }

   @Override
   public void save()
   {
      FileLoader.storeData(file, (Serializable) addresses);
   }

   private File file;
   private List<Address> addresses = new ArrayList<>();
}
