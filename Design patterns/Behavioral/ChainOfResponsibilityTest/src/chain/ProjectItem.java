package chain;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.08.12
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public interface ProjectItem extends Serializable
{
   ProjectItem getParent();
   Contact getOwner();
   String getDetails();
   List<ProjectItem> getProjectItems();

   String EOL_STRING = System.getProperty("line.separator");
}
