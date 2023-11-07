import java.rmi.Remote;
import java.rmi.RemoteException;
public interface CallbackServer extends Remote{
    public void getProject(String projectID, String callbackMachine,
      String callbackObjectName) throws RemoteException;
}