package observertest;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class ObserverGui
{
   public void createGui()
   {
      JFrame mainFrame = new JFrame("Observer Pattern Example");
      Container container = mainFrame.getContentPane();
      container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
      TaskChangeObservable observable = new TaskChangeObservable();
      TaskSelectorPanel select = new TaskSelectorPanel(observable);
      TaskHistoryPanel history = new TaskHistoryPanel();
      TaskEditorPanel edit = new TaskEditorPanel(observable);
      observable.addTaskChangeObserver(select);
      observable.addTaskChangeObserver(history);
      observable.addTaskChangeObserver(edit);
      observable.addTask(new Task());
      container.add(select);
      container.add(history);
      container.add(edit);
      mainFrame.addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosing(WindowEvent windowEvent)
         {
            System.exit(0);
         }
      });
      mainFrame.pack();
      mainFrame.setVisible(true);
   }
}
