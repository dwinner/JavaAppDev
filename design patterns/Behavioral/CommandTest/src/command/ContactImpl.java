package command;

/**
 *
 * @author oracle_pr1
 */
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
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public String toString()
   {
      return "ContactImpl{" + "firstName=" + firstName
        + ", lastName=" + lastName
        + ", title=" + title
        + ", organization=" + organization + '}';
   }
   // -- Fields
   public static final String EOL_STRING = System.getProperty("line.separator");
   private String firstName;
   private String lastName;
   private String title;
   private String organization;
}
