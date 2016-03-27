package mvctest;

public class Contact
{
   public Contact(ContactView newView)
   {
      firstName = "";
      lastName = "";
      title = "";
      organization = "";
      view = newView;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public String getOrganization()
   {
      return organization;
   }

   public void setOrganization(String organization)
   {
      this.organization = organization;
   }

   public void updateModel(String newFirstName, String newLastName, String newTitle, String newOrganization)
   {
      if (newFirstName != null && !newFirstName.isEmpty())
      {
         setFirstName(newFirstName);
      }
      if (newLastName != null && !newLastName.isEmpty())
      {
         setLastName(newLastName);
      }
      if (newTitle != null && !newTitle.isEmpty())
      {
         setTitle(newTitle);
      }
      if (newOrganization != null && !newOrganization.isEmpty())
      {
         setOrganization(newOrganization);
      }

      updateView();
   }

   private void updateView()
   {
      view.refreshContactView(firstName, lastName, title, organization);
   }
   
   private String firstName;
   private String lastName;
   private String title;
   private String organization;
   private ContactView view;
}
