package statetest;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Appointment implements Serializable
{   
   public Appointment(String reason,
                      List<Contact> contacts, Location location, Date startDate, Date endDate)
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

   public void setReason(String reason)
   {
      this.reason = reason;
   }

   public List<Contact> getContacts()
   {
      return Collections.unmodifiableList(contacts);
   }

   public void setContacts(List<Contact> contacts)
   {
      this.contacts = contacts;
   }

   public Location getLocation()
   {
      return location;
   }

   public void setLocation(Location location)
   {
      this.location = location;
   }

   public Date getStartDate()
   {
      return startDate;
   }

   public void setStartDate(Date startDate)
   {
      this.startDate = startDate;
   }

   public Date getEndDate()
   {
      return endDate;
   }

   public void setEndDate(Date endDate)
   {
      this.endDate = endDate;
   }

   @Override
   public String toString()
   {
      return "Appointment{" + "reason=" + reason + ", contacts=" + contacts
        + ", location=" + location + ", startDate=" + startDate + ", endDate=" + endDate + '}';
   }
   
   private String reason;
   private List<Contact> contacts;
   private Location location;
   private Date startDate;
   private Date endDate;
}
