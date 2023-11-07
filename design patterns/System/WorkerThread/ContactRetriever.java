import java.rmi.Naming;
import java.rmi.RemoteException;
public class ContactRetriever implements RunnableTask{
    private Contact contact;
    private long contactID;
    private String url;
    
    public ContactRetriever(long newContactID, String newUrl){
        contactID = newContactID;
        url = newUrl;
    }
    
    public void execute(){
        try{
            ServerDataStore dataStore = (ServerDataStore)Naming.lookup(url);
            contact = dataStore.retrieveContact(contactID);
        }
        catch (Exception exc){
        }
    }
    
    public Contact getContact(){ return contact; }
    public boolean isContactAvailable(){ return (contact == null) ? false : true; }
}