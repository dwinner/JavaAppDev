package iteratortest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToDoListImpl implements ToDoList<String>
{   
   @Override
   public void add(String item)
   {
      if (!items.contains(item))
      {
         items.add(item);
      }
   }

   @Override
   public void add(String item, int position)
   {
      if (items.contains(item))
      {
         items.add(position, item);
      }
   }

   @Override
   public void remove(String item)
   {
      if (items.contains(item))
      {
         items.remove(items.indexOf(item));
      }
   }

   @Override
   public int getNumberOfItems()
   {
      return items.size();
   }

   @Override
   public String getListName()
   {
      return listName;
   }

   @Override
   public void setListName(String newListName)
   {
      listName = newListName;
   }

   @Override
   public Iterator<String> getIterator()
   {
      return items.iterator();
   }

   @Override
   public String toString()
   {
      return "ToDoListImpl{" + "listName=" + listName + ", items=" + items + '}';
   }
   private String listName;
   private List<String> items = new ArrayList<>();
}
