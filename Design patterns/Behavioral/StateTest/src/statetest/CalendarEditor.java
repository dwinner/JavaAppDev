package statetest;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalendarEditor
{
   public CalendarEditor(String appointmentFileName)
   {
      appointmentFile = new File(appointmentFileName);
      appointments = (List<Appointment>) FileLoader.loadData(appointmentFile);
      currentState = new CleanState();
   }

   public CalendarEditor()
   {
      this(DEFAULT_APPOINTMENT_FILE);
   }

   public void save()
   {
      currentState.save();
   }

   public void edit()
   {
      currentState.edit();
   }

   public List<Appointment> getAppointments()
   {
      return Collections.unmodifiableList(appointments);
   }

   public void addAppointment(Appointment appointment)
   {
      if (!appointments.contains(appointment))
      {
         appointments.add(appointment);
      }
   }

   public void removeAppointment(Appointment appointment)
   {
      appointments.remove(appointment);
   }
   
   private State currentState;
   private File appointmentFile;
   private List<Appointment> appointments = new ArrayList<>();
   private static final String DEFAULT_APPOINTMENT_FILE = "appointments.ser";

   private class DirtyState implements State
   {
      DirtyState(State nextState)
      {
         this.nextState = nextState;
      }

      @Override
      public void save()
      {
         FileLoader.storeData(appointmentFile, (Serializable) appointments);
         currentState = nextState;
      }

      @Override
      public void edit()
      {
      }
      
      private State nextState;
   }

   private class CleanState implements State
   {
      @Override
      public void save()
      {
      }

      @Override
      public void edit()
      {
         currentState = nextState;
      }
      
      private State nextState = new DirtyState(this);
   }

}
