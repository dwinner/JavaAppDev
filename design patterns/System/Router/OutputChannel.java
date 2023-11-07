import java.rmi.Remote;
import java.rmi.RemoteException;
public interface OutputChannel extends Remote{
    public void sendMessage(Message message) throws RemoteException;
}