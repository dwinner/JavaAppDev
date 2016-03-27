package goff_factory;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 27.07.12
 * Time: 20:55
 * To change this template use File | Settings | File Templates.
 */
public class Contact implements Editable, Serializable
{
   @Override
   public ItemEditor getEditor()
   {
      return new ContactEditor();
   }

   private class ContactEditor implements ItemEditor, Serializable
   {
      @Override
      public JComponent getGUI()
      {
         if (panel == null)
         {
            panel = new JPanel();
            nameField = new JTextField(name);
            relationField = new JTextField(relationship);
            panel.setLayout(new GridLayout(2, 2));
            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Relationship:"));
            panel.add(relationField);
         }
         else
         {
            nameField.setText(name);
            relationField.setText(relationship);
         }
         return panel;
      }

      @Override
      public void commitChanges()
      {
         if (panel != null)
         {
            name = nameField.getText();
            relationship = relationField.getText();
         }
      }

      @Override
      public String toString()
      {
         return "ContactEditor{" +
           "panel=" + panel +
           ", nameField=" + nameField +
           ", relationField=" + relationField +
           '}';
      }

      private transient JPanel panel;
      private transient JTextField nameField;
      private transient JTextField relationField;
   }

   private String name;
   private String relationship;
}
