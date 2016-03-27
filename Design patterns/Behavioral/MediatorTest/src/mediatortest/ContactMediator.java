package mediatortest;

public interface ContactMediator
{
   void setContactDisplayPanel(ContactDisplayPanel aDisplayPanel);
   void setContactEditorPanel(ContactEditorPanel anEditorPanel);
   void setContactSelectorPanel(ContactSelectorPanel aSelectorPanel);
   void createContact(String aFirstName, String aLastName, String aTitle, String anOrganization);
   void updateContact(String aFirstName, String aLastName, String aTitle, String anOrganization);
   Contact[] getAllContacts();
   void selectContact(Contact<String> aContact);   
}
