import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
public class ServerDataStoreImpl implements ServerDataStore{
    private static final String WORKER_SERVER_SERVICE_NAME = "workerThreadServer";
    
    public ServerDataStoreImpl(){
        try {
            UnicastRemoteObject.exportObject(this);
            Naming.rebind(WORKER_SERVER_SERVICE_NAME, this);
        }
        catch (Exception exc){
            System.err.println("Error using RMI to register the ServerDataStoreImpl " + exc);
        }
    }
    
    public Address retrieveAddress(long addressID){
        if (addressID == 5280L){
            return new AddressImpl("Fine Dining", "416 Chartres St.", "New Orleans", "LA", "51720");
        }
        else if (addressID == 2010L){
            return new AddressImpl("Mystic Yacht Club", "19 Imaginary Lane", "Mystic", "CT", "46802");
        }
        else{
            return new AddressImpl();
        }
    }
    public Contact retrieveContact(long contactID){
        if (contactID == 5280L){
            return new ContactImpl("Dwayne", "Dibley", "Accountant", "Virtucon");
        }
        else{
            return new ContactImpl();
        }
    }
    
}