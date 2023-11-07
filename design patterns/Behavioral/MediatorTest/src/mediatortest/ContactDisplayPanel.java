package mediatortest;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ContactDisplayPanel extends JPanel
{
   public ContactDisplayPanel()
   {
      createGui();
   }

   public ContactDisplayPanel(ContactMediator newMediator)
   {
      setContactMediator(newMediator);
      createGui();
   }

   private void createGui()
   {
      setLayout(new BorderLayout());
      displayRegion = new JTextArea(10, 40);
      displayRegion.setEditable(false);
      add(new JScrollPane(displayRegion));
   }
   
   public void contactChanged(Contact<String> aContact)
   {
      displayRegion.setText("Contact\n\tName: " + aContact.getLastName()
        + "\n\tTitle: " + aContact.getTitle()
        + "\n\tOrganization: " + aContact.getOrganization());
   }

   public final void setContactMediator(ContactMediator newMediator)
   {
      mediator = newMediator;
   }
   
   private ContactMediator mediator;
   private JTextArea displayRegion;
}
