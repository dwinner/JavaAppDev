package visitortest;

public interface ProjectVisitor
{
   void visitDependentTask(DependentTask dependentTask);
   void visitDeliverable(Deliverable deliverable);
   void visitTask(Task task);
   void visitProject(Project project);
}
