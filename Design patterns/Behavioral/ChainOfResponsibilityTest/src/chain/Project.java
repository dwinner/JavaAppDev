package chain;

import java.util.ArrayList;
import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: Denis
* Date: 25.08.12
* Time: 14:57
* To change this template use File | Settings | File Templates.
*/
public class Project implements ProjectItem
{
   public Project() { }

   public Project(String name, String details, Contact owner)
   {
      this.name = name;
      this.owner = owner;
      this.details = details;
   }

   public String getName() { return name; }
   @Override public String getDetails() { return details; }
   @Override public Contact getOwner() { return owner; }
   @Override public ProjectItem getParent() { return null; }
   @Override public List<ProjectItem> getProjectItems() { return projectItems; }

   public void setName(String newName) { name = newName; }
   public void setOwner(Contact newOwner) { owner = newOwner; }
   public void setDetails(String newDetails) { details = newDetails; }

   public void addProjectItem(ProjectItem element)
   {
      if (!projectItems.contains(element))
         projectItems.add(element);
   }

   public void removeProjectItem(ProjectItem element)
   {
      projectItems.remove(element);
   }

   @Override public String toString() { return name; }

   private String name;
   private Contact owner;
   private String details;
   private List<ProjectItem> projectItems = new ArrayList<>();
}
