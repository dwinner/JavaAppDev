package hopp;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
* Created with IntelliJ IDEA.
* User: oracle_pr1
* Date: 24.08.12
* Time: 8:40
* To change this template use File | Settings | File Templates.
*/
public class CalendarImpl implements hopp.Calendar
{
   public CalendarImpl(String fileName)
   {
      File inputFile = new File(fileName);
      appointmentCalendar = (Map<Long, List<Appointment>>) FileLoader.loadData(inputFile);
      if (appointmentCalendar == null)
         appointmentCalendar = new HashMap<>();
      try
      {
         UnicastRemoteObject.exportObject(this);
         Naming.rebind(REMOTE_SERVICE, this);
      }
      catch (RemoteException | MalformedURLException remoteEx)
      {
         if (remoteEx instanceof RemoteException)
            System.err.println("Error using RMI to register the hopp.CalendarImpl " + remoteEx);
         else
            System.err.println("Error using RMI for bad server name to register the hopp.CalendarImpl " + remoteEx);
      }
   }

   public CalendarImpl()
   {
      this(DEFAULT_FILE_NAME);
   }

   @Override
   public String getHost()
     throws RemoteException
   {
      return "";
   }

   @Override
   public List<Appointment> getAppointments(Date date)
     throws RemoteException
   {
      long appointmentKey = date.getTime();
      return appointmentCalendar.containsKey(appointmentKey) ? appointmentCalendar.get(appointmentKey) : null;
   }

   @Override
   public void addAppointment(Appointment appointment, Date date)
     throws RemoteException
   {
      long appointmentKey = date.getTime();
      if (appointmentCalendar.containsKey(appointmentKey))
      {
         List<Appointment> appointments = appointmentCalendar.get(appointmentKey);
         appointments.add(appointment);
      }
      else
      {
         List<Appointment> appointments = new ArrayList<>();
         appointments.add(appointment);
         appointmentCalendar.put(appointmentKey, appointments);
      }
   }

   private Map<Long, List<Appointment>> appointmentCalendar = new HashMap<>();
   private static final String REMOTE_SERVICE = "calendarimpl";
   private static final String DEFAULT_FILE_NAME = "calendar.ser";
}
