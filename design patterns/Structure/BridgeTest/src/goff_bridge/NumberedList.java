package goff_bridge;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.08.12
 * Time: 17:38
 * To change this template use File | Settings | File Templates.
 */
public class NumberedList extends BaseList
{
   @Override
   public String get(int index)
   {
      return (index + 1) + ". " + super.get(index);
   }
}