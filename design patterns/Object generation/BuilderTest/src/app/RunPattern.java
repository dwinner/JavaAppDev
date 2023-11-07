package app;

import goffbuilder.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.07.12
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class RunPattern
{
   private static Calendar dateCreator = Calendar.getInstance();

   public static void main(String[] arguments)
   {
      Appointment appt = null;

      System.out.println("Example for the Builder pattern");
      System.out.println();
      System.out.println("This example demonstrates the use of the Builder");
      System.out.println("pattern to create goffbuilder.Appointment objects for the PIM.");
      System.out.println();

      System.out.println("Creating a goffbuilder.Scheduler for the example");
      Scheduler pimScheduler = new Scheduler();

      System.out.println("Creating an goffbuilder.AppointmentBuilder for the example.");
      System.out.println();
      AppointmentBuilder apptBuilder = new AppointmentBuilder();
      try
      {
         System.out.println("Creating a new goffbuilder.Appointment with an goffbuilder.AppointmentBuilder");
         appt = pimScheduler.createAppointment(apptBuilder,
                                               createDate(2066, 9, 22, 12, 30),
                                               null,
                                               "Trek conversion",
                                               new LocationImpl("Fargo, ND"),
                                               createAttendees(4));
         System.out.println("Successfully created an goffbuilder.Appointment.");
         System.out.println("goffbuilder.Appointment information");
         System.out.println(appt);
         System.out.println();
      }
      catch (InformationRequiredException ex)
      {
         printExceptions(ex);
      }

      System.out.println("Creating a goffbuilder.MeetingBuilder for the example");
      MeetingBuilder mtgBuilder = new MeetingBuilder();
      try
      {
         System.out.println("Creating a new goffbuilder.Appointment with a goffbuilder.MeetingBuilder");
         System.out.println("(notice that the same create arguments will produce");
         System.out.println(" an exception, since the goffbuilder.MeetingBuilder enforces a");
         System.out.println(" mandatory end date");
         appt = pimScheduler.createAppointment(mtgBuilder,
                                               createDate(2066, 9, 22, 12, 30),
                                               null,
                                               "Trek convention",
                                               new LocationImpl("Fargo, ND"),
                                               createAttendees(4));
         System.out.println("Successfully created an goffbuilder.Appointment.");
         System.out.println("goffbuilder.Appointment information:");
         System.out.println(appt);
         System.out.println();
      }
      catch (InformationRequiredException ex)
      {
         printExceptions(ex);
      }

      System.out.println("Creating a new goffbuilder.Appointment with a goffbuilder.MeetingBuilder");
      System.out.println("(This time, the goffbuilder.MeetingBuilder will provide an end date)");
      try
      {
         appt = pimScheduler.createAppointment(mtgBuilder,
                                               createDate(2002, 4, 1, 10, 00),
                                               createDate(2002, 4, 1, 11, 30),
                                               "OOO Meeting",
                                               new LocationImpl("Butte, MT"),
                                               createAttendees(2));
         System.out.println("Successfully created an goffbuilder.Appointment.");
         System.out.println("goffbuilder.Appointment information:");
         System.out.println(appt);
         System.out.println();
      }
      catch (InformationRequiredException ex)
      {
         printExceptions(ex);
      }
   }

   private static List<Contact> createAttendees(int numberToCreate)
   {
      List<Contact> group = new ArrayList<>();
      for (int i = 0; i < numberToCreate; i++)
         group.add(new ContactImpl("John", getLastName(i), "Employee (non-exempt)", "Yoyodyne Corporation"));
      return group;
   }

   private static String getLastName(int index)
   {
      String name = "";
      switch (index % 6)
      {
         case 0:
            name = "Worfin";
            break;
         case 1:
            name = "Smallberries";
            break;
         case 2:
            name = "Bigbootee";
            break;
         case 3:
            name = "Haugland";
            break;
         case 4:
            name = "Maassen";
            break;
         case 5:
            name = "Sterling";
            break;
         default: break;
      }
      return name;
   }

   private static Date createDate(int year, int month, int day, int hour, int minute)
   {
      dateCreator.set(year, month, day, hour, minute);
      return dateCreator.getTime();
   }

   private static void printExceptions(InformationRequiredException ex)
   {
      int statusCode = ex.getInformationRequired();

      System.out.println("Unable to create goffbuilder.Appointment: additional information is required");
      if ((statusCode & InformationRequiredException.START_DATE_REQUIRED) > 0)
         System.out.println("  A start date is required for this appointment to be complete");
      if ((statusCode & InformationRequiredException.END_DATE_REQUIRED) > 0)
         System.out.println("  An end date is required for this appointment to be complete");
      if ((statusCode & InformationRequiredException.DESCRIPTION_REQUIRED) > 0)
         System.out.println("  A description is required for this appointment to be complete");
      if ((statusCode & InformationRequiredException.ATTENDEE_REQUIRED) > 0)
         System.out.println("  At least one attendee is required for this appointment to be complete");
      if ((statusCode & InformationRequiredException.LOCATION_REQUIRED) > 0)
         System.out.println("  A location is required for this appointment to be complete");
      System.out.println();
   }
}
