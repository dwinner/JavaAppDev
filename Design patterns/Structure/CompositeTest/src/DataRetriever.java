import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class DataRetriever
{
   public static Object deserializeData(String fileName)
   {
      Object returnValue = null;
      File inputFile = new File(fileName);

      try
      {
         if (inputFile.exists() && inputFile.isFile())
         {
            try (ObjectInputStream readIn = new ObjectInputStream(new FileInputStream(fileName)))
            {
               returnValue = readIn.readObject();
            }
         }
         else
         {
            System.err.println("Unable to locate the file " + fileName);
         }
      }
      catch (FileNotFoundException fnfEx)
      {
         fnfEx.printStackTrace();
      }
      catch (IOException | ClassNotFoundException exc)
      {
         exc.printStackTrace();
      }

      return returnValue;
   }

   private DataRetriever() { }
}
