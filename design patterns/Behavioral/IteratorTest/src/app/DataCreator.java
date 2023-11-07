package app;

import iteratortest.ToDoList;
import iteratortest.ToDoListCollection;
import iteratortest.ToDoListCollectionImpl;
import iteratortest.ToDoListImpl;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
         serializeToFile(createData(), fileName);
      }
      catch (FileNotFoundException ex)
      {
         Logger.getLogger(DataCreator.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (IOException ex)
      {
         Logger.getLogger(DataCreator.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private static Serializable createData()
   {
      ToDoListCollection<ToDoList<String>> data = new ToDoListCollectionImpl();
      ToDoList<String> listOne = new ToDoListImpl();
      ToDoList<String> listTwo = new ToDoListImpl();
      ToDoList<String> listThree = new ToDoListImpl();
      listOne.setListName("Daily Routine");
      listTwo.setListName("Programming hair washing procedure");
      listThree.setListName("Reading List");
      listOne.add("Get up (harder some days than others)");
      listOne.add("Brew cuppa Java");
      listOne.add("Read JVM Times");
      listTwo.add("Lather");
      listTwo.add("Rinse");
      listTwo.add("Repeat");
      listTwo.add("(eventually throw a TooMuchHairConditioner exception)");
      listThree.add("The complete annotated aphorisms of Duke");
      listThree.add("How green was my Java");
      listThree.add("URL, sweet URL");
      data.add(listOne);
      data.add(listTwo);
      data.add(listThree);
      return data;
   }

   private static void serializeToFile(Serializable data, String fileName) throws FileNotFoundException,
                                                                                  IOException
   {
      try (ObjectOutputStream serStream = new ObjectOutputStream(new FileOutputStream(fileName)))
      {
         serStream.writeObject(data);
      }
   }
   private static final String DEFAULT_FILE = "data.ser";
}
