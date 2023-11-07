package iteratortest;

public interface ToDoList<T> extends Iterating<T>
{
   void add(T item);

   void add(T item, int position);

   void remove(T item);

   int getNumberOfItems();

   T getListName();

   void setListName(T newListName);
}
