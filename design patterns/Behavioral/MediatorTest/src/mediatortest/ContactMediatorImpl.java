package mediatortest;

import java.util.ArrayList;
import java.util.List;

public class ContactMediatorImpl implements ContactMediator
{
   @Override
   public void setContactDisplayPanel(ContactDisplayPanel aDisplayPanel)
   {
      displayPanel = aDisplayPanel;
   }

   @Override
   public void setContactEditorPanel(ContactEditorPanel anEditorPanel)
   {
      editorPanel = anEditorPanel;
   }

   @Override
   public void setContactSelectorPanel(ContactSelectorPanel aSelectorPanel)
   {
      selectorPanel = aSelectorPanel;
   }

   @Override
   public void createContact(String aFirstName, String aLastName, String aTitle, String anOrganization)
   {
      Contact<String> newContact = new ContactImpl(aFirstName, aLastName, aTitle, anOrganization);
      addContact(newContact);
      selectorPanel.addContact(newContact);
      displayPanel.contactChanged(newContact);
   }

   @Override
   public void updateContact(String aFirstName, String aLastName, String aTitle, String anOrganization)
   {
      Contact<String> updateContact = contacts.get(contactIndex);
      if (updateContact != null)
      {
         updateContact.setFirstName(aFirstName);
         updateContact.setLastName(aLastName);
         updateContact.setTitle(aTitle);
         updateContact.setOrganization(anOrganization);
         displayPanel.contactChanged(updateContact);
      }
   }

   @Override
   public Contact[] getAllContacts()
   {
      return contacts.toArray(new Contact<?>[contacts.size()]);
   }

   @Override
   public void selectContact(Contact<String> aContact)
   {
      if (contacts.contains(aContact))
      {
         contactIndex = contacts.indexOf(aContact);
         displayPanel.contactChanged(aContact);
         editorPanel.setContactFields(aContact);
      }
   }

   public void addContact(Contact<String> newContact)
   {
      if (!contacts.contains(newContact))
      {
         contacts.add(newContact);
      }
   }
   
   private ContactDisplayPanel displayPanel;
   private ContactEditorPanel editorPanel;
   private ContactSelectorPanel selectorPanel;
   private List<Contact<String>> contacts = new ArrayList<>();
   private int contactIndex;
}
