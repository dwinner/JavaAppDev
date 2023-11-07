package mediatortest;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class MediatorGui
{
   public void setMediator(ContactMediator mediator)
   {
      this.mediator = mediator;
   }

   public void createGui()
   {
      JFrame mainFrame = new JFrame("Mediator example");
      Container container = mainFrame.getContentPane();
      container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
      ContactSelectorPanel selectorPanel = new ContactSelectorPanel(mediator);
      ContactDisplayPanel displayPanel = new ContactDisplayPanel(mediator);
      ContactEditorPanel editorPanel = new ContactEditorPanel(mediator);
      container.add(selectorPanel);
      container.add(displayPanel);
      container.add(editorPanel);
      mediator.setContactSelectorPanel(selectorPanel);
      mediator.setContactDisplayPanel(displayPanel);
      mediator.setContactEditorPanel(editorPanel);
      mainFrame.addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosing(WindowEvent e)
         {
            System.exit(0);
         }
      });
      mainFrame.pack();
      mainFrame.setVisible(true);
   }
   
   private ContactMediator mediator;
}
