package visitortest;

public class ProjectCostVisitor implements ProjectVisitor
{
   @Override
   public void visitDependentTask(DependentTask dependentTask)
   {
      double taskCost = dependentTask.getTimeRequired() * hourlyRate;
      taskCost *= dependentTask.getDependencyWeightingFactor();
      totalCost += taskCost;
   }

   @Override
   public void visitDeliverable(Deliverable deliverable)
   {
      totalCost += deliverable.getMaterialsCost() + deliverable.getProductionCost();
   }

   @Override
   public void visitTask(Task task)
   {
      totalCost += task.getTimeRequired() * hourlyRate;
   }

   @Override
   public void visitProject(Project project)
   {
   }

   public double getTotalCost()
   {
      return totalCost;
   }

   public double getHourlyRate()
   {
      return hourlyRate;
   }

   public void setHourlyRate(double rate)
   {
      hourlyRate = rate;
   }

   public void resetTotalCost()
   {
      totalCost = 0.0;
   }
   private double totalCost;
   private double hourlyRate;
}
