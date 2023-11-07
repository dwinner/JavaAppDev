package mvctest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ContactEditView extends JPanel implements ContactView
{
   public ContactEditView(ContactModel model, ContactEditController controller)
   {
      this.controller = controller;
      createGui();
   }

   public ContactEditView(ContactModel model)
   {
      controller = new ContactEditController(model, this);
      createGui();
   }
   
   @Override
   public void refreshContactView(String firstName, String lastName, String title, String organization)
   {
      firstNameField.setText(firstName);
      lastNameField.setText(lastName);
      titleField.setText(title);
      organizationField.setText(organization);
   }

   private void createGui()
   {
      updateButton = new JButton(UPDATE_BUTTON);
      exitButton = new JButton(EXIT_BUTTON);

      firstNameLabel = new JLabel(CONTACT_FIRST_NAME);
      lastNameLabel = new JLabel(CONTACT_LAST_NAME);
      titleLabel = new JLabel(CONTACT_TITLE);
      organizationLabel = new JLabel(CONTACT_ORG);

      firstNameField = new JTextField(FNAME_COL_WIDTH);
      lastNameField = new JTextField(LNAME_COL_WIDTH);
      titleField = new JTextField(TITLE_COL_WIDTH);
      organizationField = new JTextField(ORG_COL_WIDTH);

      JPanel editPanel = new JPanel();
      editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.X_AXIS));

      JPanel labelPanel = new JPanel();
      labelPanel.setLayout(new GridLayout(0, 1));

      labelPanel.add(firstNameLabel);
      labelPanel.add(lastNameLabel);
      labelPanel.add(titleLabel);
      labelPanel.add(organizationLabel);

      editPanel.add(labelPanel);

      JPanel fieldPanel = new JPanel();
      fieldPanel.setLayout(new GridLayout(0, 1));

      fieldPanel.add(firstNameField);
      fieldPanel.add(lastNameField);
      fieldPanel.add(titleField);
      fieldPanel.add(organizationField);

      editPanel.add(fieldPanel);

      JPanel controlPanel = new JPanel();
      controlPanel.add(updateButton);
      controlPanel.add(exitButton);
      updateButton.addActionListener(controller);
      exitButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      });
      
      setLayout(new BorderLayout());
      add(editPanel, BorderLayout.CENTER);
      add(controlPanel, BorderLayout.SOUTH);      
   }
   
   public Object getUpdateRef()
   {
      return updateButton;
   }
   
   public String getFirstName()
   {
      return firstNameField.getText();
   }
   
   public String getLastName()
   {
      return lastNameField.getText();
   }
   
   public String getTitle()
   {
      return titleField.getText();
   }
   
   public String getOrganization()
   {
      return organizationField.getText();
   }
   
   private ContactEditController controller;
   private JLabel firstNameLabel;
   private JLabel lastNameLabel;
   private JLabel titleLabel;
   private JLabel organizationLabel;
   private JTextField firstNameField;
   private JTextField lastNameField;
   private JTextField titleField;
   private JTextField organizationField;
   private JButton updateButton;
   private JButton exitButton;
   private static final String UPDATE_BUTTON = "Update";
   private static final String EXIT_BUTTON = "Exit";
   private static final String CONTACT_FIRST_NAME = "First Name  ";
   private static final String CONTACT_LAST_NAME = "Last Name  ";
   private static final String CONTACT_TITLE = "Title  ";
   private static final String CONTACT_ORG = "Organization  ";
   private static final int FNAME_COL_WIDTH = 25;
   private static final int LNAME_COL_WIDTH = 40;
   private static final int TITLE_COL_WIDTH = 25;
   private static final int ORG_COL_WIDTH = 40;   
}
