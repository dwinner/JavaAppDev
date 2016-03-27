package visitortest;

import java.util.Collections;
import java.util.List;

public class Deliverable implements ProjectItem
{
   public Deliverable()
   {
   }

   public Deliverable(String name, String description, Contact owner, double materialsCost,
                      double productionCost)
   {
      this.name = name;
      this.description = description;
      this.owner = owner;
      this.materialsCost = materialsCost;
      this.productionCost = productionCost;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public Contact getOwner()
   {
      return owner;
   }

   public void setOwner(Contact owner)
   {
      this.owner = owner;
   }

   public double getMaterialsCost()
   {
      return materialsCost;
   }

   public void setMaterialsCost(double materialsCost)
   {
      this.materialsCost = materialsCost;
   }

   public double getProductionCost()
   {
      return productionCost;
   }

   public void setProductionCost(double productionCost)
   {
      this.productionCost = productionCost;
   }

   @Override
   public void accept(ProjectVisitor aVisitor)
   {
      aVisitor.visitDeliverable(this);
   }

   @Override
   public List<ProjectItem> getProjectItems()
   {
      return Collections.<ProjectItem>emptyList();
   }
   
   private String name;
   private String description;
   private Contact owner;
   private double materialsCost;
   private double productionCost;
}
