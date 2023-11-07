package goff_bridge;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.08.12
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class OrderedListImpl implements ListImpl
{
   @Override
   public void addItem(String item)
   {
      if (!items.contains(item))
         items.add(item);
   }

   @Override
   public void addItem(String item, int position)
   {
      if (!items.contains(item))
         items.add(position, item);
   }

   @Override
   public void removeItem(String item)
   {
      if (items.contains(item))
         items.remove(items.indexOf(item));
   }

   @Override
   public int getNumberOfItems()
   {
      return items.size();
   }

   @Override
   public String getItem(int index)
   {
      return (index >= 0 && index < items.size()) ? items.get(index) : null;
   }

   @Override
   public boolean supportsOrdering()
   {
      return true;
   }

   private List<String> items = new LinkedList<>();
}
