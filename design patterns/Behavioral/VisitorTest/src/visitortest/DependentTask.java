package visitortest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DependentTask extends Task
{
   public DependentTask()
   {
   }

   public DependentTask(String newName, Contact newOwner, double newTimeRequired, double factor)
   {
      super(newName, newOwner, newTimeRequired);
      dependencyWeightingFactor = factor;
   }

   public List<Task> getDependentTasks()
   {
      return Collections.unmodifiableList(dependentTasks);
   }

   public double getDependencyWeightingFactor()
   {
      return dependencyWeightingFactor;
   }

   public void setDependencyWeightingFactor(double dependencyWeightingFactor)
   {
      this.dependencyWeightingFactor = dependencyWeightingFactor;
   }

   public void addDependentTask(Task aTask)
   {
      if (!dependentTasks.contains(aTask))
      {
         dependentTasks.add(aTask);
      }
   }

   public void removeDependentTask(Task aTask)
   {
      dependentTasks.remove(aTask);
   }

   @Override
   public void accept(ProjectVisitor aVisitor)
   {
      aVisitor.visitDependentTask(this);
   }
   
   private List<Task> dependentTasks = new ArrayList<>();
   private double dependencyWeightingFactor;
}
