package visitortest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project implements ProjectItem
{
   public Project()
   {
   }

   public Project(String name, String description)
   {
      this.name = name;
      this.description = description;
   }

   public String getName()
   {
      return name;
   }

   public String getDescription()
   {
      return description;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void setDescription(String description)
   {
      this.description = description;
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
   public void accept(ProjectVisitor aVisitor)
   {
      aVisitor.visitProject(this);
   }

   @Override
   public List<ProjectItem> getProjectItems()
   {
      return Collections.unmodifiableList(projectItems);
   }
   
   private String name;
   private String description;
   private List<ProjectItem> projectItems = new ArrayList<>();
}
