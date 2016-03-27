package goff_bridge;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.08.12
 * Time: 17:10
 * To change this template use File | Settings | File Templates.
 */
public interface ListImpl
{
   void addItem(String item);
   void addItem(String item, int position);
   void removeItem(String item);
   int getNumberOfItems();
   String getItem(int index);
   boolean supportsOrdering();
}