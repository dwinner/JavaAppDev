package hopp;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 24.08.12
 * Time: 9:08
 * To change this template use File | Settings | File Templates.
 */
public class CalendarHopp implements Calendar, Serializable
{
   public CalendarHopp(String host)
   {
      this.host = host;
      String url = PROTOCOL + host + REMOTE_SERVICE;
      try
      {
         calendar = (Calendar) Naming.lookup(url);
         Naming.rebind(HOPP_SERVICE, this);
      }
      catch (NotBoundException | MalformedURLException | RemoteException multiEx)
      {
         System.err.println("Error using RMI to look up the hopp.CalendarImpl or register the hopp.CalendarHopp " + multiEx);
      }
   }

   public CalendarHopp()
   {
      this(DEFAULT_HOST);
   }

   @Override
   public String getHost()
     throws RemoteException
   {
      return host;
   }

   @Override
   public List<Appointment> getAppointments(Date date)
     throws RemoteException
   {
      return calendar.getAppointments(date);
   }

   @Override
   public void addAppointment(Appointment appointment, Date date)
     throws RemoteException
   {
      calendar.addAppointment(appointment, date);
   }

   private Calendar calendar;
   private String host;
   private static final String PROTOCOL = "rmi://";
   private static final String REMOTE_SERVICE = "/calendarimpl";
   private static final String HOPP_SERVICE = "calendar";
   private static final String DEFAULT_HOST = "localhost";
}
