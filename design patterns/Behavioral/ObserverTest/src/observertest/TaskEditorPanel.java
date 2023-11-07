package observertest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TaskEditorPanel extends JPanel implements ActionListener, TaskChangeObserver
{
   public TaskEditorPanel(TaskChangeObservable newNotifier)
   {
      notifier = newNotifier;
      createGui();
   }

   @Override
   public void actionPerformed(ActionEvent actionEvent)
   {
      Object source = actionEvent.getSource();
      if (source == addButton)
      {
         double timeRequired = 0.0;
         try
         {
            timeRequired = Double.parseDouble(taskTimeField.getText());
         }
         catch (NumberFormatException nfEx)
         {
         }
         notifier.addTask(new Task(taskNameField.getText(), taskNotesField.getText(), timeRequired));
      }
      else if (source == updateButton)
      {
         editTask.setName(taskNameField.getText());
         editTask.setNotes(taskNotesField.getText());
         try
         {
            editTask.setTimeRequired(Double.parseDouble(taskTimeField.getText()));
         }
         catch (NumberFormatException nfEx)
         {
         }
         notifier.updateTask(editTask);
      }
      else if (source == exitButton)
      {
         System.exit(0);
      }
   }

   @Override
   public void taskAdded(Task aTask)
   {
   }

   @Override
   public void taskChanged(Task aTask)
   {
   }

   @Override
   public void taskSelected(Task aTask)
   {
      editTask = aTask;
      taskNameField.setText(aTask.getName());
      taskNotesField.setText(aTask.getNotes());
      taskTimeField.setText("" + aTask.getTimeRequired());
   }

   public void setTaskChangeObservable(TaskChangeObservable newNotifier)
   {
      notifier = newNotifier;
   }

   private void createGui()
   {
      setLayout(new BorderLayout());
      editPanel = new JPanel();
      editPanel.setLayout(new GridLayout(3, 2));
      taskNameField = new JTextField(20);
      taskNotesField = new JTextField(20);
      taskTimeField = new JTextField(20);
      editPanel.add(new JLabel("Task Name"));
      editPanel.add(taskNameField);
      editPanel.add(new JLabel("Task Notes"));
      editPanel.add(taskNotesField);
      editPanel.add(new JLabel("Time Required"));
      editPanel.add(taskTimeField);

      controlPanel = new JPanel();
      addButton = new JButton("Add Task");
      updateButton = new JButton("Update Task");
      exitButton = new JButton("Exit");
      controlPanel.add(addButton);
      controlPanel.add(updateButton);
      controlPanel.add(exitButton);
      addButton.addActionListener(this);
      updateButton.addActionListener(this);
      exitButton.addActionListener(this);
      add(controlPanel, BorderLayout.SOUTH);
      add(editPanel, BorderLayout.CENTER);
   }
   
   private JPanel controlPanel;
   private JPanel editPanel;
   private JButton addButton;
   private JButton updateButton;
   private JButton exitButton;
   private JTextField taskNameField;
   private JTextField taskNotesField;
   private JTextField taskTimeField;
   private TaskChangeObservable notifier;
   private Task editTask;
}
