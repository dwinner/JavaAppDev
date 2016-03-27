import java.rmi.Remote;
import java.rmi.RemoteException;
public interface SessionServer extends Remote{
    public long addContact(Contact contact, long sessionID) throws RemoteException, SessionException;
    public long addAddress(Address address, long sessionID) throws RemoteException, SessionException;
    public long removeAddress(Address address, long sessionID) throws RemoteException, SessionException;
    public long finalizeContact(long sessionID) throws RemoteException, SessionException;
}