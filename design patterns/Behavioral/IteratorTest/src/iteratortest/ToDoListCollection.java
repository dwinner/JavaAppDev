package iteratortest;

public interface ToDoListCollection<T> extends Iterating<T>
{
   void add(T list);

   void remove(T list);

   int getNumberOfItems();
}
