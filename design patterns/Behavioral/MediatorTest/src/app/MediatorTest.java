package app;

import mediatortest.Contact;
import mediatortest.ContactImpl;
import mediatortest.ContactMediatorImpl;
import mediatortest.MediatorGui;

public class MediatorTest
{  
   public static void main(String[] args)
   {
      System.out.println("Example for the Mediator pattern");
      System.out.println("In this demonstration, the ContactMediatorImpl class will");
      System.out.println(" coordinate updates between three controls in a GUI - the");
      System.out.println(" ContactDisplayPanel, the ContactEditorPanel, and the");
      System.out.println(" ContactSelectorPanel. As its name suggests, the Mediator");
      System.out.println(" mediates the activity between the elements of the GUI,");
      System.out.println(" translating method calls from one panel into the appropriate");
      System.out.println(" method calls on the other GUI components.");

      Contact<String> contact = new ContactImpl("", "", "", "");
      Contact<String> contact1 = new ContactImpl("Duke", "", "Java Advocate", "The Patterns Guild");
      ContactMediatorImpl mediator = new ContactMediatorImpl();
      mediator.addContact(contact);
      mediator.addContact(contact1);
      MediatorGui gui = new MediatorGui();
      gui.setMediator(mediator);
      gui.createGui();
   }
}
