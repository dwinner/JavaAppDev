import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
public interface Appointment extends Serializable{
    public static final String EOL_STRING = System.getProperty("line.separator");
    
    public Date getStartDate();
    public String getDescription();
    public ArrayList getAttendees();
    public Location getLocation();

    public void setDescription(String newDescription);
    public void setLocation(Location newLocation);
    public void setStartDate(Date newStartDate);
    public void setAttendees(ArrayList newAttendees);
    public void addAttendee(Contact attendee);
    public void removeAttendee(Contact attendee);
}