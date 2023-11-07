package app;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            try (ObjectInputStream readStream = new ObjectInputStream(new FileInputStream(fileName)))
            {
               try
               {
                  returnValue = readStream.readObject();
               }
               catch (ClassNotFoundException ex)
               {
                  Logger.getLogger(DataRetriever.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
         }
         catch (FileNotFoundException ex)
         {
            Logger.getLogger(DataRetriever.class.getName()).log(Level.SEVERE, null, ex);
         }
         catch (IOException ex)
         {
            Logger.getLogger(DataRetriever.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      else
      {
         throw new RuntimeException("Unable to locate the file " + fileName);         
      }
      return returnValue;
   }

   private DataRetriever()
   {
   }
}
