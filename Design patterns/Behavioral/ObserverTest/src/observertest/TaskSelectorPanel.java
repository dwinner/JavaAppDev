package observertest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class TaskSelectorPanel extends JPanel implements ActionListener, TaskChangeObserver
{
   public TaskSelectorPanel(TaskChangeObservable newNotifier)
   {
      notifier = newNotifier;
      createGui();
   }

   @Override
   public void actionPerformed(ActionEvent actionEvent)
   {
      notifier.selectTask((Task) selector.getSelectedItem());
   }

   @Override
   public void taskAdded(Task aTask)
   {
      selector.addItem(aTask);
   }

   @Override
   public void taskChanged(Task aTask)
   {
   }

   @Override
   public void taskSelected(Task aTask)
   {
   }

   public void setNotifier(TaskChangeObservable newNotifier)
   {
      notifier = newNotifier;
   }

   private void createGui()
   {
      selector.addActionListener(this);
      add(selector);
   }
   
   private JComboBox<Task> selector = new JComboBox<>();
   private TaskChangeObservable notifier;
}
