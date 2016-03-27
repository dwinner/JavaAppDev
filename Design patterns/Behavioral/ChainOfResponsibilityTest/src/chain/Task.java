package chain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.08.12
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class Task implements ProjectItem
{
   public Task(ProjectItem newParent, String newName, String newDetails, Contact newOwner, boolean newPrimaryTask)
   {
      parent = newParent;
      name = newName;
      owner = newOwner;
      details = newDetails;
      primaryTask = newPrimaryTask;
   }

   public Task(ProjectItem newParent)
   {
      this(newParent, "", "", null, false);
   }

   @Override public Contact getOwner()
   {
      return owner == null ? parent.getOwner() : owner;
   }

   @Override public String getDetails()
   {
      return primaryTask ? details : parent.getDetails() + EOL_STRING + "\t" + details;
   }

   public String getName() { return name; }
   @Override public List<ProjectItem> getProjectItems() { return projectItems; }
   @Override public ProjectItem getParent() { return parent; }
   public boolean isPrimaryTask() { return primaryTask; }

   public void setName(String newName) { name = newName; }
   public void setOwner(Contact newOwner) { owner = newOwner; }
   public void setParent(ProjectItem newParent) { parent = newParent; }
   public void setPrimaryTask(boolean newPrimaryTask) { primaryTask = newPrimaryTask; }
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
   private List<ProjectItem> projectItems = new ArrayList<>();
   private Contact owner;
   private String details;
   private ProjectItem parent;
   private boolean primaryTask;
}
