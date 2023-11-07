package command;

import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author oracle_pr1
 */
public class Appointment
{
   public Appointment(String reason, Contact[] contacts, Location location, Date startDate, Date endDate)
   {
      this.reason = reason;
      this.contacts = contacts;
      this.location = location;
      this.startDate = startDate;
      this.endDate = endDate;
   }

   public String getReason()
   {
      return reason;
   }

   public Contact[] getContacts()
   {
      return Arrays.copyOf(contacts, contacts.length);
   }

   public Location getLocation()
   {
      return location;
   }

   public Date getStartDate()
   {
      return (Date) startDate.clone();
   }

   public Date getEndDate()
   {
      return (Date) endDate.clone();
   }

   public void setLocation(Location newLocation)
   {
      location = newLocation;
   }

   @Override
   public String toString()
   {
      return "Appointment{" + "reason=" + reason
        + ", contacts=" + contacts
        + ", location=" + location
        + ", startDate=" + startDate
        + ", endDate=" + endDate + '}';
   }
   // -- Fields
   private String reason;
   private Contact[] contacts;
   private Location location;
   private Date startDate;
   private Date endDate;
}
