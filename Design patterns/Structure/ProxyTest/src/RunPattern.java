import proxy.Address;
import proxy.AddressBookProxy;
import proxy.AddressImpl;

import java.util.List;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 23.08.12
 * Time: 12:40
 * To change this template use File | Settings | File Templates.
 */
public class RunPattern
{
   public static void main(String[] arguments)
   {
      System.out.println("Example for the Proxy pattern");
      System.out.println();
      System.out.println("This code will demonstrate the use of a Proxy to");
      System.out.println(" provide functionality in place of its underlying");
      System.out.println(" class.");
      System.out.println();

      System.out.println(" Initially, an proxy.AddressBookProxy object will provide");
      System.out.println(" address book support without requiring that the");
      System.out.println(" proxy.AddressBookImpl be created. This could potentially");
      System.out.println(" make the application run much faster, since the");
      System.out.println(" proxy.AddressBookImpl would need to read in all addresses");
      System.out.println(" from a file when it is first created.");
      System.out.println();

      if (!(new File("data.ser").exists()))
      {
         DataCreator.serialize("data.ser");
      }
      System.out.println("Creating the proxy.AddressBookProxy");
      AddressBookProxy proxy = new AddressBookProxy("data.ser");
      System.out.println("Adding entries to the proxy.AddressBookProxy");
      System.out.println("(this operation can be done by the Proxy, without");
      System.out.println(" creating an proxy.AddressBookImpl object)");
      proxy.add(new AddressImpl("Sun Education [CO]", "500 El Dorado Blvd.", "Broomfield", "CO", "80020", ""));
      proxy.add(new AddressImpl("Apple Inc.", "1 Infinite Loop", "Redwood City", "CA", "93741", ""));
      System.out.println("Addresses created. Retrieving an address");
      System.out.println("(since the address is stored by the Proxy, there is");
      System.out.println(" still no need to create an proxy.AddressBookImpl object)");
      System.out.println();
      System.out.println(proxy.getAddress("Sun Education [CO]").getAddress());
      System.out.println();

      System.out.println("So far, all operations have been handled by the Proxy,");
      System.out.println(" without any involvement from the proxy.AddressBookImpl.");
      System.out.println(" Now, a call to the method getAllAddresses will");
      System.out.println(" force instantiation of proxy.AddressBookImpl, and will");
      System.out.println(" retrieve ALL addresses that are stored.");
      System.out.println();

      List<Address> addresses = proxy.getAllAddresses();
      System.out.println("Addresses retrieved. Addresses currently stored:");
      System.out.println(addresses);
   }
}
