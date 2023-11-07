package observertest;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TaskHistoryPanel extends JPanel implements TaskChangeObserver
{
   public TaskHistoryPanel()
   {
      createGui();
   }   

   @Override
   public void taskAdded(Task aTask)
   {
      displayRegion.append("Created task " + aTask + "\n");
   }

   @Override
   public void taskChanged(Task aTask)
   {
      displayRegion.append("Updated task " + aTask + "\n");
   }

   @Override
   public void taskSelected(Task aTask)
   {
      displayRegion.append("Selected task " + aTask + "\n");
   }
   
   private void createGui()
   {
      setLayout(new BorderLayout());
      displayRegion = new JTextArea(10, 40);
      displayRegion.setEditable(false);
      add(new JScrollPane(displayRegion));
   }
   
   private JTextArea displayRegion;
}
