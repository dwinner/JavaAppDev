package proxy;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 23.08.12
 * Time: 11:25
 * To change this template use File | Settings | File Templates.
 */
public class FileLoader
{
   public static Object loadData(File inputFile)
   {
      Object returnValue = null;
      if (inputFile.exists())
      {
         if (inputFile.isFile())
         {
            try
            {
               try (ObjectInputStream readIn = new ObjectInputStream(new FileInputStream(inputFile)))
               {
                  returnValue = readIn.readObject();
               }
            }
            catch (FileNotFoundException fnfEx)
            {
               fnfEx.printStackTrace();
            }
            catch (IOException | ClassNotFoundException multiEx)
            {
               multiEx.printStackTrace();
            }
         }
         else
         {
            System.err.println(inputFile + " is a directory.");
         }
      }
      else
      {
         System.err.println("File " + inputFile + " does not exists.");
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

   private FileLoader() { }
}
