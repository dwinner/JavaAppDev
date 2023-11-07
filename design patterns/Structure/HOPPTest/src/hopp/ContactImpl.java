package hopp;

/**
* Created with IntelliJ IDEA.
* User: oracle_pr1
* Date: 24.08.12
* Time: 9:21
* To change this template use File | Settings | File Templates.
*/
public class ContactImpl implements Contact
{
   public ContactImpl() { }

   public ContactImpl(String newFirstName, String newLastName, String newOrganization, String newTitle)
   {
      firstName = newFirstName;
      lastName = newLastName;
      organization = newOrganization;
      title = newTitle;
   }

   @Override public String getFirstName() { return firstName; }
   @Override public void setFirstName(String newFirstName) { firstName = newFirstName; }

   @Override public String getLastName() { return lastName; }
   @Override public void setLastName(String newLastName) { lastName = newLastName; }

   @Override public String getTitle() { return title; }
   @Override public void setTitle(String newTitle) { title = newTitle; }

   @Override public String getOrganization() { return organization; }
   @Override public void setOrganization(String newOrganization) { organization = newOrganization; }

   @Override
   public String toString()
   {
      return "hopp.ContactImpl{" +
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
