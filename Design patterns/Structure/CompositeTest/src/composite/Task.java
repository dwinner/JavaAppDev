package composite;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 9:18
 * To change this template use File | Settings | File Templates.
 */
public class Task<T extends ProjectItem> implements ProjectItem
{
   public Task() { }

   public Task(String newName, String newDetails, Contact newOwner, double newTimeRequired)
   {
      name = newName;
      details = newDetails;
      owner = newOwner;
      timeRequired = newTimeRequired;
   }

   @Override
   public double getTimeRequired()
   {
      double totalTime = timeRequired;
      Iterator<T> items = projectItems.iterator();
      while (items.hasNext())
      {
         T item = items.next();
         totalTime += item.getTimeRequired();
      }
      return totalTime;
   }

   public String getName()
   {
      return name;
   }

   public String getDetails()
   {
      return details;
   }

   public List<T> getProjectItems()
   {
      return projectItems;
   }

   public Contact getOwner()
   {
      return owner;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void setDetails(String details)
   {
      this.details = details;
   }

   public void setOwner(Contact owner)
   {
      this.owner = owner;
   }

   public void setTimeRequired(double timeRequired)
   {
      this.timeRequired = timeRequired;
   }

   public void addProjectItem(T element)
   {
      if (!projectItems.contains(element))
         projectItems.add(element);
   }

   public void removeProjectItem(T element)
   {
      projectItems.remove(element);
   }

   private String name;
   private String details;
   private List<T> projectItems = new LinkedList<>();
   private Contact owner;
   private double timeRequired;
}
