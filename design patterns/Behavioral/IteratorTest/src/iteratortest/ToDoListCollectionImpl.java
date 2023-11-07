package iteratortest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ToDoListCollectionImpl implements ToDoListCollection<ToDoList<String>>
{
   @Override
   public void add(ToDoList<String> list)
   {
      if (!lists.containsKey(list.getListName()))
      {
         lists.put(list.getListName(), list);
      }
   }

   @Override
   public void remove(ToDoList<String> list)
   {
      if (lists.containsKey(list.getListName()))
      {
         lists.remove(list.getListName());
      }
   }

   @Override
   public int getNumberOfItems()
   {
      return lists.size();
   }

   @Override
   public Iterator<ToDoList<String>> getIterator()
   {
      return lists.values().iterator();
   }

   @Override
   public String toString()
   {
      return getClass().toString();
   }
   private Map<String, ToDoList<String>> lists = new HashMap<>();
}
