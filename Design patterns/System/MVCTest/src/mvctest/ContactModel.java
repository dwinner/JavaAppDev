package mvctest;

import java.util.ArrayList;
import java.util.List;

public class ContactModel
{
   public ContactModel(ContactView view)
   {
      firstName = "";
      lastName = "";
      title = "";
      organization = "";
      if (view != null)
      {
         contactViews.add(view);
      }
   }

   public ContactModel()
   {
      this(null);
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
      if (!isEmptyString(newFirstName))
      {
         setFirstName(newFirstName);
      }
      if (!isEmptyString(newLastName))
      {
         setLastName(newLastName);
      }
      if (!isEmptyString(newTitle))
      {
         setTitle(newTitle);
      }
      if (!isEmptyString(newOrganization))
      {
         setOrganization(newOrganization);
      }
      updateView();
   }

   public void addContactView(ContactView view)
   {
      if (!contactViews.contains(view))
      {
         contactViews.add(view);
      }
   }

   public void removeContactView(ContactView view)
   {
      contactViews.remove(view);
   }

   private boolean isEmptyString(String input)
   {
      return input == null || input.isEmpty();
   }

   private void updateView()
   {
      for (ContactView contactView : contactViews)
      {
         contactView.refreshContactView(firstName, lastName, title, organization);
      }
   }
   
   private String firstName;
   private String lastName;
   private String title;
   private String organization;
   private List<ContactView> contactViews = new ArrayList<>();
}
