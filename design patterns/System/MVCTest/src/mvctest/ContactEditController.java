package mvctest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactEditController implements ActionListener
{
   public ContactEditController(ContactModel model, ContactEditView view)
   {
      this.model = model;
      this.view = view;
   }

   @Override
   public void actionPerformed(ActionEvent actionEvent)
   {
      Object source = actionEvent.getSource();
      if (source == view.getUpdateRef())
      {
         updateModel();
      }
   }

   private void updateModel()
   {
      String firstName = null;
      String lastName = null;
      if (isAlphabetic(view.getFirstName()))
      {
         firstName = view.getFirstName();
      }
      if (isAlphabetic(view.getLastName()))
      {
         lastName = view.getLastName();
      }
      model.updateModel(firstName, lastName, view.getTitle(), view.getOrganization());
   }

   private boolean isAlphabetic(String input)
   {
      char[] testChars =
      {
         '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
      };
      for (int i = 0; i < testChars.length; i++)
      {
         if (input.indexOf(testChars[i]) != -1)
         {
            return false;
         }
      }
      return true;
   }
   
   private ContactModel model;
   private ContactEditView view;
}
