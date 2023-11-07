import java.rmi.Remote;
import java.rmi.RemoteException;
public interface CallbackClient extends Remote{
    public void receiveProject(Project project) throws RemoteException;
}