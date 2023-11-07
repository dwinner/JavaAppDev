package command;

/**
 *
 * @author oracle_pr1
 */
public class ChangeLocationCommand implements UndoableCommand
{
   public Appointment getAppointment()
   {
      return appointment;
   }

   public void setAppointment(Appointment appointment)
   {
      this.appointment = appointment;
   }

   public void setEditor(LocationEditor editor)
   {
      this.editor = editor;
   }

   @Override
   public void undo()
   {
      appointment.setLocation(oldLocation);
   }

   @Override
   public void redo()
   {
      appointment.setLocation(newLocation);
   }

   @Override
   public void execute()
   {
      oldLocation = appointment.getLocation();
      newLocation = editor.getNewLocation();
      appointment.setLocation(newLocation);
   }

   public void setLocationEditor(LocationEditor locationEditor)
   {
      editor = locationEditor;
   }
   // -- Fields
   private Appointment appointment;
   private Location oldLocation;
   private Location newLocation;
   private LocationEditor editor;
}
