package iteratortest;

import java.io.PrintStream;
import java.util.Iterator;

public class ListPrinter
{   

   public static <E> void printToDoListCollection(ToDoListCollection<E> lotsOfLists, PrintStream out)
   {
      Iterator<E> elements = lotsOfLists.getIterator();
      out.println("\"To Do\" List Collection:");
      while (elements.hasNext())
      {         
         printToDoList((ToDoList<?>) elements.next(), out);
      }
   }
   
   public static <E> void printToDoList(ToDoList<E> list, PrintStream output)
   {
      Iterator<E> elements = list.getIterator();
      output.println("  List - " + list + ":");
      while (elements.hasNext())
      {         
         output.println("\t" + elements.next());
      }
   }

   @SuppressWarnings("unchecked")
   public static <E> void printIteratingElement(Iterating<E> element, PrintStream output)
   {
      output.println("Printing the element " + element);
      Iterator<E> elements = element.getIterator();
      while (elements.hasNext())
      {
         E currentElement = elements.next();
         if (currentElement instanceof Iterating)
         {
            printIteratingElement((Iterating<E>) currentElement, output);
         }
         else
         {
            output.println(currentElement);
         }
      }
   }

   private ListPrinter()
   {
   }
}
