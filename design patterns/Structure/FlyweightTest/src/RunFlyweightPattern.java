import flyweight.*;
import imlps.Address;
import imlps.AddressImpl;
import imlps.Contact;
import imlps.ContactImpl;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 22.08.12
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public class RunFlyweightPattern
{
   public static void main(String[] arguments)
     throws java.io.IOException
   {
      System.out.println("Example for the Flyweight pattern");
      System.out.println();
      System.out.println("In this sample, flyweight.State objects are shared between multiple");
      System.out.println(" parts of the PIM. Two lists, representing a imlps.Contact list");
      System.out.println(" and an imlps.Address Book, are used for the demonstration.");
      System.out.println(" The flyweight.State objects - flyweight.CleanState and flyweight.DirtyState - represent");
      System.out.println(" the Flyweight objects in this example.");
      System.out.println();

      System.out.println("Creating ManagedList objects to hold Contacts and Addresses");
      ManagedList contactList = new ManagedList(Contact.class);
      ManagedList addressList = new ManagedList(Address.class);
      System.out.println();

      System.out.println("Printing the flyweight.State for the application");
      printPIMState();
      System.out.println();

      System.out.println("Editing the imlps.Address and imlps.Contact lists");
      StateFactory.getCurrentState().edit(State.CONTACTS);
      StateFactory.getCurrentState().edit(State.ADDRESSES);
      contactList.addItem(new ContactImpl("f", "l", "t", "o"));
      addressList.addItem(new AddressImpl("d", "s", "c", "s", "t", "z"));
      System.out.println("Printing the flyweight.State for the application");
      printPIMState();
      System.out.println();

      System.out.println("Saving the imlps.Contact list");
      StateFactory.getCurrentState().save(new java.io.File("contacts.ser"), (Serializable) contactList.getItems(),
                                          State.CONTACTS);
      System.out.println("Printing the flyweight.State for the application");
      printPIMState();
      System.out.println();

      System.out.println("Saving the imlps.Address list");
      StateFactory.getCurrentState().save(new java.io.File("addresses.ser"), (Serializable) addressList.getItems(),
                                          State.ADDRESSES);
      System.out.println("Printing the flyweight.State for the application");
      printPIMState();
   }

   private static void printPIMState()
   {
      System.out.println("  Current flyweight.State of the PIM: " + StateFactory.getCurrentState().getClass());
      System.out.println("  Object ID: " + StateFactory.getCurrentState().hashCode());
      System.out.println();
   }
}
