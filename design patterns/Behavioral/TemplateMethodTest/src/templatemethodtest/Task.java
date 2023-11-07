package templatemethodtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task extends ProjectItem
{
   public Task()
   {
   }

   public Task(String newName, String newDescription, double newRate, double taskTimeRequired)
   {
      super(newName, newDescription, newRate);
      this.taskTimeRequired = taskTimeRequired;
   }

   public void setTaskTimeRequired(double taskTimeRequired)
   {
      this.taskTimeRequired = taskTimeRequired;
   }

   public double getTaskTimeRequired()
   {
      return taskTimeRequired;
   }
   
   

   public List<ProjectItem> getProjectItems()
   {
      return Collections.unmodifiableList(projectItems);
   }

   public void addProjectItem(ProjectItem aProjectItem)
   {
      if (!projectItems.contains(aProjectItem))
      {
         projectItems.add(aProjectItem);
      }
   }

   public void removeProjectItem(ProjectItem aProjectItem)
   {
      projectItems.remove(aProjectItem);
   }

   @Override
   public double getTimeRequired()  // FIXME: Лишний проход по коллекции для смежного метода getMaterialCost()
   {
      double totalTime = taskTimeRequired;
      for (ProjectItem projectItem : projectItems)
      {
         totalTime += projectItem.getTimeRequired();
      }
      return totalTime;
   }

   @Override
   public double getMaterialCost()  // FIXME: Лишний проход по коллекции для смежного метода getTimeRequired()
   {
      double totalCost = 0;
      for (ProjectItem projectItem : projectItems)
      {
         totalCost += projectItem.getMaterialCost();
      }
      return totalCost;
   }
   
   private List<ProjectItem> projectItems = new ArrayList<>();
   private double taskTimeRequired;
}
