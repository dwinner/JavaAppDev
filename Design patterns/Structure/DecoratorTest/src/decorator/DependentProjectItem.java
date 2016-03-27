package decorator;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public class DependentProjectItem extends ProjectDecorator
{
   public DependentProjectItem() { }

   public DependentProjectItem(ProjectItem dependentItem)
   {
      this.dependentItem = dependentItem;
   }

   public ProjectItem getDependentItem()
   {
      return dependentItem;
   }

   public void setDependentItem(ProjectItem dependentItem)
   {
      this.dependentItem = dependentItem;
   }

   @Override
   public String toString()
   {
      return getProjectItem().toString() + EOL_STRING
        + "\tdecorator.ProjectItem dependent on: " + dependentItem;
   }

   private ProjectItem dependentItem;
}
