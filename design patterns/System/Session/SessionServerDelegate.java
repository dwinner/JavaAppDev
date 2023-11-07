import java.util.ArrayList;
import java.util.HashMap;
public class SessionServerDelegate{
    private static final long NO_SESSION_ID = 0;
    private static long nextSessionID = 1;
    private static ArrayList contacts = new ArrayList();
    private static ArrayList addresses = new ArrayList();
    private static HashMap editContacts = new HashMap();
    
    public static long addContact(Contact contact, long sessionID) throws SessionException{
        if (sessionID <= NO_SESSION_ID){
            sessionID = getSessionID();
        }
        if (contacts.indexOf(contact) != -1){
            if (!editContacts.containsValue(contact)){
                editContacts.put(new Long(sessionID), contact);
            }
            else{
                throw new SessionException("This contact is currently being edited by another user.",
                    SessionException.CONTACT_BEING_EDITED);
            }
        }
        else{
            contacts.add(contact);
            editContacts.put(new Long(sessionID), contact);
        }
        return sessionID;
    }
    
    public static long addAddress(Address address, long sessionID) throws SessionException{
        if (sessionID  <= NO_SESSION_ID){
            throw new SessionException("A valid session ID is required to add an address",
                SessionException.SESSION_ID_REQUIRED);
        }
        Contact contact = (Contact)editContacts.get(new Long(sessionID));
        if (contact == null){
            throw new SessionException("You must select a contact before adding an address",
                SessionException.CONTACT_SELECT_REQUIRED);
        }
        if (addresses.indexOf(address) == -1){
            addresses.add(address);
        }
        contact.addAddress(address);
        return sessionID;
    }
    
    public static long removeAddress(Address address, long sessionID) throws SessionException{
        if (sessionID  <= NO_SESSION_ID){
            throw new SessionException("A valid session ID is required to remove an address",
                SessionException.SESSION_ID_REQUIRED);
        }
        Contact contact = (Contact)editContacts.get(new Long(sessionID));
        if (contact == null){
            throw new SessionException("You must select a contact before removing an address",
                SessionException.CONTACT_SELECT_REQUIRED);
        }
        if (addresses.indexOf(address) == -1){
            throw new SessionException("There is no record of this address",
                SessionException.ADDRESS_DOES_NOT_EXIST);
        }
        contact.removeAddress(address);
        return sessionID;
    }
    
    public static long finalizeContact(long sessionID) throws SessionException{
        if (sessionID  <= NO_SESSION_ID){
            throw new SessionException("A valid session ID is required to finalize a contact",
                SessionException.SESSION_ID_REQUIRED);
        }
        Contact contact = (Contact)editContacts.get(new Long(sessionID));
        if (contact == null){
            throw new SessionException("You must select and edit a contact before committing changes",
                SessionException.CONTACT_SELECT_REQUIRED);
        }
        editContacts.remove(new Long(sessionID));
        return NO_SESSION_ID;
    }
    
    private static long getSessionID(){
        return nextSessionID++;
    }
    
    public static ArrayList getContacts(){ return contacts; }
    public static ArrayList getAddresses(){ return addresses; }
    public static ArrayList getEditContacts(){ return new ArrayList(editContacts.values()); }
}