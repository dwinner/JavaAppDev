package statetest;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StateGui implements ActionListener
{
   public StateGui(CalendarEditor calendarEditor)
   {
      editor = calendarEditor;
   }

   public void createGui()
   {
      mainFrame = new JFrame("State Pattern Example");
      Container content = mainFrame.getContentPane();
      content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

      editPanel = new JPanel();
      editPanel.setLayout(new BorderLayout());
      JTable appointmentTable = new JTable(new StateTableModel(
        editor.getAppointments().toArray(
          new Appointment[editor.getAppointments().size()])
        )
      );
      editPanel.add(new JScrollPane(appointmentTable));
      content.add(editPanel);

      controlPanel = new JPanel();
      save = new JButton("Save Appointments");
      exit = new JButton("Exit");
      controlPanel.add(save);
      controlPanel.add(exit);
      content.add(controlPanel);

      save.addActionListener(this);
      exit.addActionListener(this);

      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.pack();
      mainFrame.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent actionEvent)
   {
      Object originator = actionEvent.getSource();
      if (originator == save)
      {
         saveAppointments();
      }
      else if (originator == exit)
      {
         System.exit(0);
      }
   }

   private void saveAppointments()
   {
      editor.save();
   }
   
   private JFrame mainFrame;
   private JPanel controlPanel;
   private JPanel editPanel;
   private CalendarEditor editor;
   private JButton save;
   private JButton exit;

   private class StateTableModel extends AbstractTableModel
   {
      StateTableModel(Appointment[] appointments)
      {
         data = appointments;
      }

      @Override
      public int getRowCount()
      {
         return data.length;
      }

      @Override
      public int getColumnCount()
      {
         return columnNames.length;
      }

      @Override
      public Object getValueAt(int rowIndex, int columnIndex)
      {
         Object value = null;
         switch (columnIndex)
         {
            case 0:
               value = data[rowIndex].getReason();
               break;
            case 1:
               value = data[rowIndex].getContacts();
               break;
            case 2:
               value = data[rowIndex].getLocation();
               break;
            case 3:
               value = data[rowIndex].getStartDate();
               break;
            case 4:
               value = data[rowIndex].getEndDate();
               break;
            default:
               break;
         }
         return value;
      }

      @Override
      public String getColumnName(int columnIndex)
      {
         return columnNames[columnIndex];
      }

      @Override
      public boolean isCellEditable(int rowIndex, int columnIndex)
      {
         return (columnIndex == 0 || columnIndex == 2) ? true : false;
      }

      @Override
      public void setValueAt(Object aValue, int rowIndex, int columnIndex)
      {
         switch (columnIndex)
         {
            case 0:
               data[rowIndex].setReason((String) aValue);
               editor.edit();
               break;
            case 1:
               break;
            case 2:
               data[rowIndex].setLocation(new LocationImpl((String) aValue));
               editor.edit();
               break;
            case 3:
            case 4:
               break;
            default:
               break;
         }
      }
      
      private Appointment[] data;
      private final String[] columnNames =
      {
         "Appointment",
         "Contacts",
         "Location",
         "Start Date",
         "End Date"
      };
   }

}
