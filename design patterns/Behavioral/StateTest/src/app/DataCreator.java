package app;

import statetest.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataCreator
{
   public static void main(String[] args)
   {
      String fileName = args.length == 1 ? args[0] : DEFAULT_FILE;
      serialize(fileName);
   }

   public static void serialize(String fileName)
   {
      try
      {
         serializeToFile(createData(), fileName);
      }
      catch (IOException ex)
      {
         Logger.getLogger(DataCreator.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public static Date createDate(int year, int month, int day, int hour, int minute)
   {
      dateCreator.set(year, month, day, hour, minute);
      return dateCreator.getTime();
   }

   private static Serializable createData()
   {
      List<Appointment> appointments = new ArrayList<>();
      List<Contact> contacts = new ArrayList<>();
      contacts.add(new ContactImpl(
        "Test", "Subject", "Volunteer", "United Patterns Consortium"));
      Location location1 = new LocationImpl("Punxsutawney, PA");
      appointments.add(
        new Appointment("Slowpokes anonymous", contacts, location1,
                        createDate(2001, 1, 1, 12, 01), createDate(2001, 1, 1, 12, 02)));
      appointments.add(
        new Appointment("Java focus group", contacts, location1,
                        createDate(2001, 1, 1, 12, 30), createDate(2001, 1, 1, 14, 30)));
      appointments.add(
        new Appointment("Something else", contacts, location1,
                        createDate(2001, 1, 1, 12, 01), createDate(2001, 1, 1, 12, 02)));
      appointments.add(
        new Appointment("Yet another thingie", contacts, location1,
                        createDate(2001, 1, 1, 12, 01), createDate(2001, 1, 1, 12, 02)));
      return (Serializable) appointments;
   }

   private static void serializeToFile(Serializable content,
                                       String fileName) throws IOException
   {
      try (ObjectOutputStream serOut = new ObjectOutputStream(new FileOutputStream(fileName)))
      {
         serOut.writeObject(content);
      }
   }
   
   private static final String DEFAULT_FILE = "data.ser";
   private static Calendar dateCreator = Calendar.getInstance();
}
