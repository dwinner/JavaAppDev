import proxy.Address;
import proxy.AddressImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 23.08.12
 * Time: 12:28
 * To change this template use File | Settings | File Templates.
 */
public class DataCreator
{
   public static void main(String[] args)
   {
      String fileName = args.length == 1 ? args[0] : DEFAULT_FILE;
      serialize(fileName);
   }

   public static void serialize(String fileName)
   {
      try
      {
         serializaToFile(createData(), fileName);
      }
      catch (IOException ioEx)
      {
         ioEx.printStackTrace();
      }
   }

   private static void serializaToFile(Serializable data, String fileName)
     throws IOException
   {
      try (ObjectOutputStream serOut = new ObjectOutputStream(new FileOutputStream(fileName)))
      {
         serOut.writeObject(data);
      }
   }

   private static Serializable createData()
   {
      List<Address> items = new ArrayList<>();
      items.add(new AddressImpl("Home address", "1418 Appian Way", "Pleasantville", "NH", "27415", ""));
      items.add(new AddressImpl("Resort", "711 Casino Ave.", "Atlantic City", "NJ", "91720", ""));
      items.add(new AddressImpl("Vacation spot", "90 Ka'ahanau Cir.", "Haleiwa", "Hi", "41720", ""));
      return (Serializable) items;
   }

   private DataCreator() { }

   private static final String DEFAULT_FILE = "data.ser";
}
