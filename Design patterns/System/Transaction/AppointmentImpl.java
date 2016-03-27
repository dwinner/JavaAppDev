import java.util.ArrayList;
import java.util.Date;
public class AppointmentImpl implements Appointment{
    private Date startDate;
    private String description;
    private ArrayList attendees = new ArrayList();
    private Location location;
    
    public AppointmentImpl(String newDescription, ArrayList newAttendees,
      Location newLocation, Date newStartDate){
        description = newDescription;
        attendees = newAttendees;
        location = newLocation;
        startDate = newStartDate;
    }
    
    public Date getStartDate(){ return startDate; }
    public String getDescription(){ return description; }
    public ArrayList getAttendees(){ return attendees; }
    public Location getLocation(){ return location; }

    public void setDescription(String newDescription){ description = newDescription; }
    public void setLocation(Location newLocation){ location = newLocation; }
    public void setStartDate(Date newStartDate){ startDate = newStartDate; }
    public void setAttendees(ArrayList newAttendees){
        if (newAttendees != null){
            attendees = newAttendees;
        }
    }
    
    public void addAttendee(Contact attendee){
        if (!attendees.contains(attendee)){
            attendees.add(attendee);
        }
    }
    
    public void removeAttendee(Contact attendee){
        attendees.remove(attendee);
    }
    
    public int hashCode(){
        return description.hashCode() ^ startDate.hashCode();
    }
    
    public boolean equals(Object object){
        if (!(object instanceof AppointmentImpl)){
            return false;
        }
        if (object.hashCode() != hashCode()){
            return false;
        }
        return true;
    }
    
    public String toString(){
        return "  Description: " + description + EOL_STRING +
               "  Start Date: " + startDate + EOL_STRING +
               "  Location: " + location + EOL_STRING +
               "  Attendees: " + attendees;
    }
}