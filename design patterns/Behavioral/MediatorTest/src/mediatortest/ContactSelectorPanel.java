package mediatortest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ContactSelectorPanel extends JPanel implements ActionListener
{
   public ContactSelectorPanel()
   {
      createGui();
   }

   public ContactSelectorPanel(ContactMediator newMediator)
   {
      setContactMediator(newMediator);
      createGui();
   }

   public final void setContactMediator(ContactMediator newMediator)
   {
      mediator = newMediator;
   }

   @Override
   public void actionPerformed(ActionEvent actionEvent)
   {
      mediator.selectContact((Contact<?>) selector.getSelectedItem());
   }
   
   public void addContact(Contact<?> aContact)
   {
      selector.addItem(aContact);
      selector.setSelectedItem(aContact);
   }

   @SuppressWarnings("unchecked")
   private void createGui()
   {
      selector = new JComboBox(mediator.getAllContacts());
      selector.addActionListener(this);
      add(selector);
   }
   
   private ContactMediator mediator;
   private JComboBox<Contact<?>> selector;
}
