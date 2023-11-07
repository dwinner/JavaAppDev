package goffbuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.07.12
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class Appointment
{
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

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public List<? extends Contact> getAttendees()
   {
      return attendees;
   }

   public void setAttendees(List<Contact> newAttendees)
   {
      if (newAttendees != null)
         attendees = newAttendees;
   }

   public Location getLocation()
   {
      return location;
   }

   public void setLocation(Location location)
   {
      this.location = location;
   }

   public void addAttendee(Contact attendee)
   {
      if (!attendees.contains(attendee))
         attendees.add(attendee);
   }

   public void removeAttendee(Contact attendee)
   {
      attendees.remove(attendee);
   }

   @Override
   public String toString()
   {
      return "goffbuilder.Appointment{" +
        "startDate=" + startDate +
        ", endDate=" + endDate +
        ", description='" + description + '\'' +
        ", attendees=" + attendees +
        ", location=" + location +
        '}';
   }

   public static final String EOL_STRING = System.getProperty("line.separator");

   private Date startDate;
   private Date endDate;
   private String description;
   private List<Contact> attendees = new ArrayList<>();
   private Location location;
}
