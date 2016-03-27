package mvctest;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ContactDisplayView extends JPanel implements ContactView
{
   public ContactDisplayView()
   {
      createGui();
   }

   @Override
   public void refreshContactView(String firstName, String lastName, String title, String organization)
   {
      display.setText("UPDATED CONTACT:\nNEW VALUES:\n"
        + "\tName: " + firstName + " " + lastName + "\n"
        + "\tTitle: " + title + "\n" + "\tOrganization: " + organization);
   }

   private void createGui()
   {
      setLayout(new BorderLayout());
      display = new JTextArea(10, 40);
      display.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(display);
      add(scrollPane, BorderLayout.CENTER);
   }
   
   private JTextArea display;
}
