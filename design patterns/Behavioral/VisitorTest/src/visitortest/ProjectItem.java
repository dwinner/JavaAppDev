package visitortest;

import java.io.Serializable;
import java.util.List;

public interface ProjectItem extends Serializable
{
   void accept(ProjectVisitor aVisitor);
   List<ProjectItem> getProjectItems();
}
