package decorator;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 15:05
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
   public String getFirstName()
   {
      return firstName;
   }

   @Override
   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   @Override
   public String getLastName()
   {
      return lastName;
   }

   @Override
   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   @Override
   public String getTitle()
   {
      return title;
   }

   @Override
   public void setTitle(String title)
   {
      this.title = title;
   }

   @Override
   public String getOrganization()
   {
      return organization;
   }

   @Override
   public void setOrganization(String organization)
   {
      this.organization = organization;
   }

   @Override
   public String toString()
   {
      return "decorator.ContactImpl{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", title='" + title + '\'' +
        ", organization='" + organization + '\'' +
        '}';
   }

   private String firstName;
   private String lastName;
   private String title;
   private String organization;
}
