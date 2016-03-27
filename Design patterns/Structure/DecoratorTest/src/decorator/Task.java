package decorator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
public class Task implements ProjectItem
{
   public Task() { }

   public Task(String name, Contact owner, double timeRequired)
   {
      this.name = name;
      this.owner = owner;
      this.timeRequired = timeRequired;
   }

   public String getName()
   {
      return name;
   }

   public List<ProjectItem> getProjectItems()
   {
      return projectItems;
   }

   public Contact getOwner()
   {
      return owner;
   }

   @Override
   public double getTimeRequired()
   {
      double totalTime = timeRequired;
      Iterator<ProjectItem> items = projectItems.iterator();
      while (items.hasNext())
      {
         ProjectItem item = items.next();
         totalTime += item.getTimeRequired();
      }
      return totalTime;
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

   public void addProjectItem(ProjectItem element)
   {
      if (!projectItems.contains(element))
         projectItems.add(element);
   }

   public void removeProjectItem(ProjectItem element)
   {
      projectItems.remove(element);
   }

   @Override
   public String toString()
   {
      return "decorator.Task: " + name;
   }

   private String name;
   private List<ProjectItem> projectItems = new ArrayList<>();
   private Contact owner;
   private double timeRequired;
}
