package goff_bridge;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.08.12
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class OrnamentedList extends BaseList
{
   @Override
   public String get(int index)
   {
      return itemType + " " + super.get(index);
   }

   public char getItemType()
   {
      return itemType;
   }

   public void setItemType(char newItemType)
   {
      if (newItemType > ' ')
         itemType = newItemType;
   }

   private char itemType;
}