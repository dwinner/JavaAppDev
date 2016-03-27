package composite;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 9:33
 * To change this template use File | Settings | File Templates.
 */
public class ContactImpl implements Contact
{
   public ContactImpl() { }

   public ContactImpl(String firstName, String lastName, String title, String organization)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.title = title;
      this.organization = organization;
   }

   @Override
   public String getFirstName() { return firstName; }

   @Override
   public String getLastName() { return lastName; }

   @Override
   public String getTitle() { return title; }

   @Override
   public String getOrganization() { return organization; }

   @Override
   public void setFirstName(String newFirstName) { firstName = newFirstName; }

   @Override
   public void setLastName(String newLastName) { lastName = newLastName; }

   @Override
   public void setTitle(String newTitle) { title = newTitle; }

   @Override
   public void setOrganization(String newOrganization) { organization = newOrganization; }

   @Override
   public String toString() { return firstName + SPACE + lastName; }

   private String firstName;
   private String lastName;
   private String title;
   private String organization;
}
