import adapter.Contact;
import adapter.ContactAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 04.08.12
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
public class RunPattern
{
   public static void main(String[] arguments)
   {
      System.out.println("Example for the Adapter pattern");
      System.out.println();
      System.out.println("This example will demonstrate the Adapter by using the");
      System.out.println(" class adapter.ContactAdapter to translate from classes written");
      System.out.println(" in a foreign language (adapter.Chovnatlh and adapter.ChovnatlhImpl),");
      System.out.println(" enabling their code to satisfy the adapter.Contact interface.");
      System.out.println();

      System.out.println("Creating a new adapter.ContactAdapter. This will, by extension,");
      System.out.println(" create an instance of adapter.ChovnatlhImpl which will provide");
      System.out.println(" the underlying adapter.Contact implementation.");
      Contact contact = new ContactAdapter();
      System.out.println();

      System.out.println("adapter.ContactAdapter created. Setting contact data.");
      contact.setFirstName("Thomas");
      contact.setLastName("Williamson");
      contact.setTitle("Science Officer");
      contact.setOrganization("W3C");
      System.out.println();

      System.out.println("adapter.ContactAdapter data has been set. Printing out adapter.Contact data.");
      System.out.println();
      System.out.println(contact.toString());
   }
}
