package composite;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 8:33
 * To change this template use File | Settings | File Templates.
 */
public class Deliverable implements ProjectItem
{
   public Deliverable() { }

   public Deliverable(String newName, String newDescription, Contact newOwner)
   {
      name = newName;
      description = newDescription;
      owner = newOwner;
   }

   @Override
   public double getTimeRequired()
   {
      return 0;
   }

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public String getDescription() { return description; }
   public void setDescription(String description) { this.description = description; }

   public Contact getOwner() { return owner; }
   public void setOwner(Contact owner) { this.owner = owner; }

   private String name;
   private String description;
   private Contact owner;
}
