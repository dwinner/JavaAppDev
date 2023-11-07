package templatemethodtest;

public abstract class ProjectItem
{
   public ProjectItem()
   {
   }

   public ProjectItem(String newName, String newDescription, double newRate)
   {
      name = newName;
      description = newDescription;
      rate = newRate;
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

   public double getRate()
   {
      return rate;
   }

   public void setRate(double rate)
   {
      this.rate = rate;
   }

   @Override
   public String toString()
   {
      return getName();
   }

   public abstract double getTimeRequired();

   public abstract double getMaterialCost();

   public final double getCostEstimate()
   {
      return getTimeRequired() * getRate() + getMaterialCost();
   }
   
   private String name;
   private String description;
   private double rate;
}
