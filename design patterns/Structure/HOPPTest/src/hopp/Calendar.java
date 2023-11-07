package hopp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 24.08.12
 * Time: 8:36
 * To change this template use File | Settings | File Templates.
 */
public interface Calendar extends Remote
{
   public String getHost() throws RemoteException;
   public List<Appointment> getAppointments(Date date) throws RemoteException;
   public void addAppointment(Appointment appointment, Date date) throws RemoteException;
}
