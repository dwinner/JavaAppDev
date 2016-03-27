package hopp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 24.08.12
 * Time: 8:08
 * To change this template use File | Settings | File Templates.
 */
public class Appointment implements Serializable
{
   public Appointment(String description, List<Contact> contacts, Location location, Date startDate, Date endDate)
   {
      this.description = description;
      this.contacts = contacts;
      this.location = location;
      this.startDate = startDate;
      this.endDate = endDate;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public List<Contact> getContacts()
   {
      return contacts;
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
      return "hopp.Appointment{" +
        "description='" + description + '\'' +
        ", contacts=" + contacts +
        ", location=" + location +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        '}';
   }

   private String description;
   private List<Contact> contacts;
   private Location location;
   private Date startDate;
   private Date endDate;
}
