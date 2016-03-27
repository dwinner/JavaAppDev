package goffbuilder;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.07.12
 * Time: 18:50
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

   public static final String SPACE = " ";
}
