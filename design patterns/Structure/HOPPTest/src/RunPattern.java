import hopp.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 24.08.12
 * Time: 9:46
 * To change this template use File | Settings | File Templates.
 */
public class RunPattern
{
   private static Calendar dateCreator = Calendar.getInstance();

   public static void main(String[] arguments)
     throws RemoteException
   {
      System.out.println("Example for the HOPP pattern");
      System.out.println();
      System.out.println("This example will use RMI to demonstrate the HOPP pattern.");
      System.out.println(" In the sample, there will be two objects created, hopp.CalendarImpl");
      System.out.println(" and CalendarHOPP. The hopp.CalendarImpl object provides the true");
      System.out.println(" server-side implementation, while the CalendarHOPP would be");
      System.out.println(" a client or middle-tier representative. The CalendarHOPP will");
      System.out.println(" provide some functionality, in this case supplying the hostname");
      System.out.println(" in response to the getHost method.");
      System.out.println();
      System.out.println("Note: This example runs the rmiregistry, CalendarHOPP and hopp.CalendarImpl");
      System.out.println(" on the same machine.");
      System.out.println();

      /*try
      {
         Process p1 = Runtime.getRuntime().exec("rmic hopp.CalendarImpl");
         Process p2 = Runtime.getRuntime().exec("rmic hopp.CalendarHopp");
         p1.waitFor();
         p2.waitFor();
      }
      catch (IOException exc)
      {
         System.err.println("Unable to run rmic utility. Exiting application.");
         System.exit(1);
      }
      catch (InterruptedException exc)
      {
         System.err.println("Threading problems encountered while using the rmic utility.");
      }*/

      System.out.println("Starting the rmiregistry");
      System.out.println();
      Process rmiProcess = null;
      try
      {
         rmiProcess = Runtime.getRuntime().exec("rmiregistry");
         Thread.sleep(15000);
      }
      catch (IOException exc)
      {
         System.err.println("Unable to start the rmiregistry. Exiting application.");
         System.exit(1);
      }
      catch (InterruptedException exc)
      {
         System.err.println("Threading problems encountered when starting the rmiregistry.");
      }

      System.out.println("Creating the hopp.CalendarImpl object, which provides the server-side implementation.");
      System.out.println("(Note: If the hopp.CalendarImpl object does not have a file containing Appointments,");
      System.out.println("  this call will produce an error message. This will not affect the example.)");
      CalendarImpl remoteObject = new CalendarImpl();

      System.out.println();
      System.out.println("Creating the CalendarHOPP object, which provides client-side functionality.");
      CalendarHopp localObject = new CalendarHopp();

      System.out.println();
      System.out.println("Getting the hostname. The CalendarHOPP will handle this method locally.");
      System.out.println("Hostname is " + localObject.getHost());
      System.out.println();

      System.out.println("Creating and adding appointments. The CalendarHOPP will forward");
      System.out.println(" these calls to the hopp.CalendarImpl object.");
      Contact attendee = new ContactImpl("Jenny", "Yip", "Chief Java Expert", "MuchoJava LTD");
      ArrayList contacts = new ArrayList();
      contacts.add(attendee);
      Location place = new LocationImpl("Albuquerque, NM");
      localObject.addAppointment(new Appointment("Opening speeches at annual Java Guru's dinner",
                                                 contacts, place, createDate(2001, 4, 1, 16, 0),
                                                 createDate(2001, 4, 1, 18, 0)), createDate(2001, 4, 1, 0, 0));
      localObject.addAppointment(new Appointment("Java Guru post-dinner Cafe time",
                                                 contacts, place, createDate(2001, 4, 1, 19, 30),
                                                 createDate(2001, 4, 1, 21, 45)), createDate(2001, 4, 1, 0, 0));
      System.out.println("Appointments added.");
      System.out.println();

      System.out.println("Getting the Appointments for a date. The CalendarHOPP will forward");
      System.out.println(" this call to the hopp.CalendarImpl object.");
      System.out.println(localObject.getAppointments(createDate(2001, 4, 1, 0, 0)));
   }

   public static Date createDate(int year, int month, int day, int hour, int minute)
   {
      dateCreator.set(year, month, day, hour, minute);
      return dateCreator.getTime();
   }
}
