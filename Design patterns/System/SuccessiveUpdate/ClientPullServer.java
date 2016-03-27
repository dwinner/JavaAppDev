import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
public interface ClientPullServer extends Remote{
    public Task getTask(String taskID, Date lastUpdate) throws RemoteException, UpdateException;
    public void updateTask(String taskID, Task updatedTask) throws RemoteException, UpdateException;
}