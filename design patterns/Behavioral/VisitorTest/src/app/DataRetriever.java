package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataRetriever
{
   public static Object deserializeData(String fileName)
   {
      Object returnValue = null;
      try
      {
         File inputFile = new File(fileName);
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
      catch (ClassNotFoundException | IOException exc)
      {
         exc.printStackTrace();
      }
      return returnValue;
   }

   private DataRetriever()
   {
   }
}
