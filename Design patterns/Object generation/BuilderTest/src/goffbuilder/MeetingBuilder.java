package goffbuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 25.07.12
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
public class MeetingBuilder extends AppointmentBuilder
{
   @Override
   public Appointment getAppointment() throws InformationRequiredException
   {
      try
      {
         super.getAppointment();
      }
      finally
      {
         if (appointment.getEndDate() == null)
            requiredElements += END_DATE_REQUIRED;
         if (requiredElements > 0)
            throw new InformationRequiredException(requiredElements);
      }
      return appointment;
   }
}
