package observertest;

public interface TaskChangeObserver
{
   void taskAdded(Task aTask);

   void taskChanged(Task aTask);

   void taskSelected(Task aTask);
}
