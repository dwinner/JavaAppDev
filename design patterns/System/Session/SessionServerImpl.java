import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
public class SessionServerImpl implements SessionServer{
    private static final String SESSION_SERVER_SERVICE_NAME = "sessionServer";
    public SessionServerImpl(){
        try {
            UnicastRemoteObject.exportObject(this);
            Naming.rebind(SESSION_SERVER_SERVICE_NAME, this);
        }
        catch (Exception exc){
            System.err.println("Error using RMI to register the SessionServerImpl " + exc);
        }
    }
    
    public long addContact(Contact contact, long sessionID) throws SessionException{
        return SessionServerDelegate.addContact(contact, sessionID);
    }
    
    public long addAddress(Address address, long sessionID) throws SessionException{
        return SessionServerDelegate.addAddress(address, sessionID);
    }
    
    public long removeAddress(Address address, long sessionID) throws SessionException{
        return SessionServerDelegate.removeAddress(address, sessionID);
    }
    
    public long finalizeContact(long sessionID) throws SessionException{
        return SessionServerDelegate.finalizeContact(sessionID);
    }
}