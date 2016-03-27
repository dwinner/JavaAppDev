package mediatortest;

public interface Contact<T>
{
   T getFirstName();
   T getLastName();
   T getTitle();
   T getOrganization();
   
   void setFirstName(T newFirstName);
   void setLastName(T newLastName);
   void setTitle(T newTitle);
   void setOrganization(T newOrganization);
   
   String SPACE = " ";
}
