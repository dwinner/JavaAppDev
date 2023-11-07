package goff_bridge;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.08.12
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class BaseList
{
   protected ListImpl implementor;

   public void setImplementor(ListImpl impl)
   {
      implementor = impl;
   }

   public void add(String item)
   {
      implementor.addItem(item);
   }

   public void add(String item, int position)
   {
      if (implementor.supportsOrdering())
         implementor.addItem(item, position);
   }

   public void remove(String item)
   {
      implementor.removeItem(item);
   }

   public String get(int index)
   {
      return implementor.getItem(index);
   }

   public int count()
   {
      return implementor.getNumberOfItems();
   }
}