package goffbuilder;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.07.12
 * Time: 20:07
 * To change this template use File | Settings | File Templates.
 */
public class Scheduler
{
   public Appointment createAppointment(AppointmentBuilder builder,
                                            Date startDate,
                                            Date endDate,
                                            String description,
                                            Location location,
                                            List<Contact> attendees) throws InformationRequiredException
   {
      if (builder == null)
         builder = new AppointmentBuilder();
      builder.buildAppointment();
      builder.buildDates(startDate, endDate);
      builder.buildDescription(description);
      builder.buildAttendees(attendees);
      builder.buildLocation(location);
      return builder.getAppointment();
   }
}
