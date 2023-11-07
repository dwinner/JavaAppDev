package observertest;

import java.util.ArrayList;
import java.util.List;

public class TaskChangeObservable
{
   public void addTaskChangeObserver(TaskChangeObserver observer)
   {
      if (!observers.contains(observer))
      {
         observers.add(observer);
      }
   }
   
   public void removeTaskChangeObserver(TaskChangeObserver observer)
   {
      observers.remove(observer);
   }
   
   public void selectTask(Task aTask)
   {
      for (TaskChangeObserver observer : observers)
      {
         observer.taskSelected(aTask);
      }
   }
   
   public void addTask(Task aTask)
   {
      for (TaskChangeObserver observer : observers)
      {
         observer.taskAdded(aTask);
      }
   }
   
   public void updateTask(Task aTask)
   {
      for (TaskChangeObserver observer : observers)
      {
         observer.taskChanged(aTask);
      }
   }
   
   private List<TaskChangeObserver> observers = new ArrayList<>();
}
