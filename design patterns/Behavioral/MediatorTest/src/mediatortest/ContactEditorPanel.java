package mediatortest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ContactEditorPanel extends JPanel implements ActionListener
{
   public ContactEditorPanel()
   {
      createGui();
   }

   public ContactEditorPanel(ContactMediator newMediator)
   {
      setContactMediator(newMediator);
      createGui();
   }

   @Override
   public void actionPerformed(ActionEvent actionEvent)
   {
      Object source = actionEvent.getSource();
      if (source == createButton)
      {
         createContact();
      }
      else if (source == updateButton)
      {
         updateContact();
      }
   }
   
   public void setContactFields(Contact<String> aContact)
   {
      firstName.setText(aContact.getFirstName());
      lastName.setText(aContact.getLastName());
      title.setText(aContact.getTitle());
      organization.setText(aContact.getOrganization());
   }
   
   public final void setContactMediator(ContactMediator newMediator)
   {
      mediator = newMediator;
   }

   private void createGui()
   {
      setLayout(new BorderLayout());

      JPanel editor = new JPanel();
      editor.setLayout(new GridLayout(4, 2));
      editor.add(new JLabel("First Name: "));
      editor.add(firstName);
      editor.add(new JLabel("Last Name: "));
      editor.add(lastName);
      editor.add(new JLabel("Title: "));
      editor.add(title);
      editor.add(new JLabel("Organization: "));
      editor.add(organization);
      add(editor, BorderLayout.CENTER);

      JPanel control = new JPanel();
      createButton.addActionListener(this);
      updateButton.addActionListener(this);
      control.add(createButton);
      control.add(updateButton);
      add(control, BorderLayout.SOUTH);
   }

   private void createContact()
   {
      mediator.createContact(firstName.getText(), lastName.getText(), title.getText(), organization.getText());
   }

   private void updateContact()
   {
      mediator.updateContact(firstName.getText(), lastName.getText(), title.getText(), organization.getText());
   }   
   
   private ContactMediator mediator;
   private JTextField firstName = new JTextField(20);
   private JTextField lastName = new JTextField(20);
   private JTextField title = new JTextField(20);
   private JTextField organization = new JTextField(20);
   private JButton createButton = new JButton("Create Contact");
   private JButton updateButton = new JButton("Update Contact");
}
