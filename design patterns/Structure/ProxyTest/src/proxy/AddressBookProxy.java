package proxy;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 23.08.12
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */
public class AddressBookProxy implements AddressBook<Address>
{
   public AddressBookProxy(String fileName)
   {
      file = new File(fileName);
   }

   @Override
   public void add(Address address)
   {
      if (addressBook != null)
         addressBook.add(address);
      else if (!localAddresses.contains(address))
         localAddresses.add(address);
   }

   @Override
   public List<Address> getAllAddresses()
   {
      if (addressBook == null)
         open();
      return addressBook.getAllAddresses();
   }

   @Override
   public Address getAddress(String description)
   {
      if (!localAddresses.isEmpty())
      {
         Iterator<Address> addressIterator = localAddresses.iterator();
         while (addressIterator.hasNext())
         {
            Address address = addressIterator.next();
            if (address.getDescription().equalsIgnoreCase(description))
               return address;
         }
      }
      if (addressBook == null)
         open();
      return addressBook.getAddress(description);
   }

   @Override
   public void open()
   {
      if (addressBook == null)
         addressBook = new AddressBookImpl(file);
      Iterator<Address> addressIterator = localAddresses.iterator();
      while (addressIterator.hasNext())
      {
         addressBook.add(addressIterator.next());
      }
   }

   @Override
   public void save()
   {
      if (addressBook != null)
         addressBook.save();
      else if (!localAddresses.isEmpty())
      {
         open();
         addressBook.save();
      }
   }

   private File file;
   private AddressBookImpl addressBook;
   private List<Address> localAddresses = new ArrayList<>();
}
