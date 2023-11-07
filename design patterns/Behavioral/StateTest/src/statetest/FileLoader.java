package statetest;

import java.io.*;

public class FileLoader
{
   public static Object loadData(File inputFile)
   {
      Object returnValue = null;
      try
      {
         if (inputFile.exists())
         {
            if (inputFile.isFile())
            {
               try (ObjectInputStream readIn = new ObjectInputStream(new FileInputStream(inputFile)))
               {
                  returnValue = readIn.readObject();
               }
            }
            else
            {
               System.err.println(inputFile + " is a directory.");
            }
         }
         else
         {
            System.err.println("File " + inputFile + " does not exist.");
         }
      }      
      catch (IOException | ClassNotFoundException multiException)
      {
         multiException.printStackTrace();
      }
      return returnValue;
   }

   public static void storeData(File outputFile, Serializable data)
   {
      try
      {
         try (ObjectOutputStream writeOut = new ObjectOutputStream(new FileOutputStream(outputFile)))
         {
            writeOut.writeObject(data);
         }
      }
      catch (IOException ioEx)
      {
         ioEx.printStackTrace();
      }
   }

   private FileLoader()
   {
   }
}
