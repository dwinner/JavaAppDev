package imlps;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 22.08.12
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */
public interface Contact extends Serializable
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
