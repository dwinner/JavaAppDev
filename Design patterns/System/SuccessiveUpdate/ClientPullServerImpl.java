import java.util.Date;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
public class ClientPullServerImpl implements ClientPullServer{
    private static final String UPDATE_SERVER_SERVICE_NAME = "updateServer";
    public ClientPullServerImpl(){
        try {
            UnicastRemoteObject.exportObject(this);
            Naming.rebind(UPDATE_SERVER_SERVICE_NAME, this);
        }
        catch (Exception exc){
            System.err.println("Error using RMI to register the ClientPullServerImpl " + exc);
        }
    }
    
    public Task getTask(String taskID, Date lastUpdate) throws UpdateException{
        return UpdateServerDelegate.getTask(taskID, lastUpdate);
    }
    
    public void updateTask(String taskID, Task updatedTask) throws UpdateException{
        UpdateServerDelegate.updateTask(taskID, updatedTask);
    }
}