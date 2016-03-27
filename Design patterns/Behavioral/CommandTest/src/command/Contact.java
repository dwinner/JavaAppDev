package command;

import java.io.Serializable;

/**
 *
 * @author oracle_pr1
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
