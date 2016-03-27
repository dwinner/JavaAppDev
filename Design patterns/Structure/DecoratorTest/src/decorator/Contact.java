package decorator;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public interface Contact
{
   String getFirstName();
   String getLastName();
   String getTitle();
   String getOrganization();

   void setFirstName(String newFirstName);
   void setLastName(String newLastName);
   void setTitle(String newTitle);
   void setOrganization(String newOrganization);

   String SPACE = " ";
}
