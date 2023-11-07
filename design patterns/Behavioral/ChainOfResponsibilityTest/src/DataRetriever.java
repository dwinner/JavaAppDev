import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.08.12
 * Time: 20:20
 * To change this template use File | Settings | File Templates.
 */
public class DataRetriever
{
   public static Object deserializeData(String fileName)
   {
      Object returnValue = null;
      File inputFile = new File(fileName);
      if (inputFile.exists() && inputFile.isFile())
      {
         try
         {
            try (ObjectInputStream readIn = new ObjectInputStream(new FileInputStream(fileName)))
            {
               returnValue = readIn.readObject();
            }
         }
         catch (FileNotFoundException fnfEx)
         {
            fnfEx.printStackTrace();
         }
         catch (ClassNotFoundException | IOException multiEx)
         {
            multiEx.printStackTrace();
         }
      }
      else
      {
         System.err.println("Unable to locate the file " + fileName);
      }
      return returnValue;
   }

   private DataRetriever() { }
}
