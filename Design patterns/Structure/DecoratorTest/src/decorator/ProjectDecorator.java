package decorator;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
public abstract class ProjectDecorator implements ProjectItem
{
   @Override
   public double getTimeRequired()
   {
      return projectItem.getTimeRequired();
   }

   protected ProjectItem getProjectItem()
   {
      return projectItem;
   }

   public void setProjectItem(ProjectItem projectItem)
   {
      this.projectItem = projectItem;
   }

   private ProjectItem projectItem;
}
