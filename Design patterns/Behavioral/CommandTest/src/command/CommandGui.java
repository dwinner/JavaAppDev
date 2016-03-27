package command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author oracle_pr1
 */
public class CommandGui implements ActionListener, LocationEditor
{
   public CommandGui(UndoableCommand newCommand)
   {
      command = newCommand;
   }

   private void executeCommand()
   {
      command.execute();
      refreshDisplay();
   }

   private void refreshDisplay()
   {
      display.setText(appointment.toString());
   }

   private void undoCommand()
   {
      command.undo();
      refreshDisplay();
   }

   private void redoCommand()
   {
      command.redo();
      refreshDisplay();
   }

   private void exitApplication()
   {
      System.exit(0);
   }

   public void setAppointment(Appointment newAppointment)
   {
      appointment = newAppointment;
   }

   public void createGui()
   {
      mainFrame = new JFrame("Command Pattern Example");
      Container content = mainFrame.getContentPane();
      content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

      editorPanel = new JPanel();
      editorPanel.add(new JLabel("Location"));
      updatedLocation = new JTextField(20);
      editorPanel.add(updatedLocation);
      content.add(editorPanel);

      displayPanel = new JPanel();
      display = new JTextArea(10, 40);
      display.setEditable(false);
      displayPanel.add(display);
      content.add(displayPanel);

      controlPanel = new JPanel();
      update = new JButton("Update Location");
      undo = new JButton("Undo Location");
      redo = new JButton("Redo Location");
      exit = new JButton("Exit");
      controlPanel.add(update);
      controlPanel.add(undo);
      controlPanel.add(redo);
      controlPanel.add(exit);
      content.add(controlPanel);

      update.addActionListener(this);
      undo.addActionListener(this);
      redo.addActionListener(this);
      exit.addActionListener(this);
      refreshDisplay();
      mainFrame.addWindowListener(new WindowCloseManager());
      mainFrame.pack();
      mainFrame.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent actionEvent)
   {
      Object originator = actionEvent.getSource();
      if (originator == update)
      {
         executeCommand();
      }
      else if (originator == undo)
      {
         undoCommand();
      }
      else if (originator == redo)
      {
         redoCommand();
      }
      else if (originator == exit)
      {
         exitApplication();
      }
   }

   @Override
   public Location getNewLocation()
   {
      return new LocationImpl(updatedLocation.getText());
   }
   // -- Fields
   private JFrame mainFrame;
   private JTextArea display;
   private JTextField updatedLocation;
   private JButton update;
   private JButton undo;
   private JButton redo;
   private JButton exit;
   private JPanel controlPanel;
   private JPanel displayPanel;
   private JPanel editorPanel;
   private UndoableCommand command;
   private Appointment appointment;

   private class WindowCloseManager extends WindowAdapter
   {
      @Override
      public void windowClosing(WindowEvent windowEvent)
      {
         exitApplication();
      }
   }

}
