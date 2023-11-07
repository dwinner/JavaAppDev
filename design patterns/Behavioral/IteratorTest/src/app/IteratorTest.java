package app;

import iteratortest.ListPrinter;
import iteratortest.ToDoListCollection;

import java.io.File;

public class IteratorTest
{
   public static void main(String[] args)
   {
      System.out.println("Example for the Iterator pattern");
      System.out.println(" This code sample demonstrates how an Iterator can enforce");
      System.out.println(" uniformity of processing for different collection types.");
      System.out.println(" In this case, there are two classes, ToDoListImpl and");
      System.out.println(" ToDoListCollectionImpl, that have different storage needs.");
      System.out.println(" ToDoListImpl uses an ArrayList to store its elements in");
      System.out.println(" ordered form. The ToDoListCollectionImpl uses a HashMap,");
      System.out.println(" since it must differentiate between ToDoListImpl objects by");
      System.out.println(" their String identifiers.");
      System.out.println();
      System.out.println("Although the two classes use different underlying collections,");
      System.out.println(" the ListPrinter class can use the Iterator produced by each");
      System.out.println(" to print out a set of list contents.");
      System.out.println();

      if (!(new File("data.ser").exists()))
      {
         DataCreator.serialize("data.ser");
      }
      @SuppressWarnings("unchecked")
      ToDoListCollection<ToDoList<String>> lists = (ToDoListCollection) (DataRetriever.deserializeData("data.ser"));

      System.out.println("Lists retrieved. Printing out contents using the Iterator");
      System.out.println();
      ListPrinter.printToDoListCollection(lists, System.out);
   }
}
