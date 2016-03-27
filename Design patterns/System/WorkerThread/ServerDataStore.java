import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ServerDataStore extends Remote{
    public Address retrieveAddress(long addressID) throws RemoteException;
    public Contact retrieveContact(long contactID) throws RemoteException;
}