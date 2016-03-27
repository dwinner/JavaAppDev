package visitortest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task implements ProjectItem
{
   public Task()
   {
   }

   public Task(String newName, Contact newOwner, double newTimeRequired)
   {
      name = newName;
      owner = newOwner;
      timeRequired = newTimeRequired;
   }

   public String getName()
   {
      return name;
   }

   public Contact getOwner()
   {
      return owner;
   }

   public double getTimeRequired()
   {
      return timeRequired;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void setOwner(Contact owner)
   {
      this.owner = owner;
   }

   public void setTimeRequired(double timeRequired)
   {
      this.timeRequired = timeRequired;
   }

   public void addProjectItem(ProjectItem projectItem)
   {
      if (!projectItems.contains(projectItem))
      {
         projectItems.add(projectItem);
      }
   }

   public void removeProjectItem(ProjectItem projectItem)
   {
      projectItems.remove(projectItem);
   }

   @Override
   public void accept(ProjectVisitor aVisitor)
   {
      aVisitor.visitTask(this);
   }

   @Override
   public List<ProjectItem> getProjectItems()
   {
      return Collections.unmodifiableList(projectItems);
   }
   
   private String name;
   private List<ProjectItem> projectItems = new ArrayList<>();
   private Contact owner;
   private double timeRequired;
}
