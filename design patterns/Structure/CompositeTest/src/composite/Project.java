package composite;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 8:51
 * To change this template use File | Settings | File Templates.
 */
public class Project<T extends ProjectItem> implements ProjectItem
{
   public Project() { }

   public Project(String newName, String newDescription)
   {
      name = newName;
      description = newDescription;
   }

   @Override
   public double getTimeRequired()
   {
      double totalTime = 0;
      Iterator<T> items = projectItems.iterator();
      while (items.hasNext())
      {
         T item = items.next();
         totalTime += item.getTimeRequired();
      }
      return totalTime;
   }

   public String getName() { return name; }
   public String getDescription() { return description; }
   public List<T> getProjectItems() { return projectItems; }

   public void setName(String name) { this.name = name; }
   public void setDescription(String description) { this.description = description; }

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
   private String description;
   private List<T> projectItems = new LinkedList<>();
}
