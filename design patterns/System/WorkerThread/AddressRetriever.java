import java.rmi.Naming;
import java.rmi.RemoteException;
public class AddressRetriever implements RunnableTask{
    private Address address;
    private long addressID;
    private String url;
    
    public AddressRetriever(long newAddressID, String newUrl){
        addressID = newAddressID;
        url = newUrl;
    }
    
    public void execute(){
        try{
            ServerDataStore dataStore = (ServerDataStore)Naming.lookup(url);
            address = dataStore.retrieveAddress(addressID);
        }
        catch (Exception exc){
        }
    }
    
    public Address getAddress(){ return address; }
    public boolean isAddressAvailable(){ return (address == null) ? false : true; }
}