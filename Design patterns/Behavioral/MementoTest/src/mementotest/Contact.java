package mementotest;

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
