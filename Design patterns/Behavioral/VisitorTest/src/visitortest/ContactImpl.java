package visitortest;

public class ContactImpl implements Contact
{
   public ContactImpl()
   {
   }

   public ContactImpl(String firstName, String lastName, String title, String organization)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.title = title;
      this.organization = organization;
   }

   @Override
   public String getFirstName()
   {
      return firstName;
   }

   @Override
   public String getLastName()
   {
      return lastName;
   }

   @Override
   public String getTitle()
   {
      return title;
   }

   @Override
   public String getOrganization()
   {
      return organization;
   }

   @Override
   public void setFirstName(String newFirstName)
   {
      firstName = newFirstName;
   }

   @Override
   public void setLastName(String newLastName)
   {
      lastName = newLastName;
   }

   @Override
   public void setTitle(String newTitle)
   {
      title = newTitle;
   }

   @Override
   public void setOrganization(String newOrganization)
   {
      organization = newOrganization;
   }

   @Override
   public String toString()
   {
      return firstName + SPACE + lastName;
   }
   
   private String firstName;
   private String lastName;
   private String title;
   private String organization;
}
