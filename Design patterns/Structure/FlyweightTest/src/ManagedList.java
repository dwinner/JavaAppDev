import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 22.08.12
 * Time: 14:50
 * To change this template use File | Settings | File Templates.
 */
public class ManagedList<T>
{
   public ManagedList() { }

   public ManagedList(Class<T> newClassType)
   {
      setClassType(newClassType);
   }

   public void setClassType(Class<T> newClassType)
   {
      classType = newClassType;
   }

   public void addItem(T item)
   {
      if (item != null && classType.isInstance(item))
         elements.add(item);
   }

   public void removeItem(T item)
   {
      elements.remove(item);
   }

   private List<T> elements = new ArrayList<>();
   private Class<T> classType;

   public List<T> getItems()
   {
      return elements;
   }
}
