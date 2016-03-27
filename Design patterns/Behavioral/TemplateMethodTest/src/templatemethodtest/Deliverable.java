package templatemethodtest;

public class Deliverable extends ProjectItem
{
   public Deliverable()
   {
   }

   public Deliverable(String newName, String newDescription, double newRate, double materialCost,
                      double productionTime)
   {
      super(newName, newDescription, newRate);
      this.materialCost = materialCost;
      this.productionTime = productionTime;
   }

   public void setMaterialCost(double materialCost)
   {
      this.materialCost = materialCost;
   }

   public void setProductionTime(double productionTime)
   {
      this.productionTime = productionTime;
   }

   @Override
   public double getTimeRequired()
   {
      return productionTime;
   }

   @Override
   public double getMaterialCost()
   {
      return materialCost;
   }
   
   private double materialCost;
   private double productionTime;
}
