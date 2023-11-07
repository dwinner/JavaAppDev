import java.util.ArrayList;
public class ContactImpl implements Contact{
    private String firstName;
    private String lastName;
    private String title;
    private String organization;
    private ArrayList addresses = new ArrayList();
    
    public ContactImpl(){}
    public ContactImpl(String newFirstName, String newLastName,
      String newTitle, String newOrganization, ArrayList newAddresses){
        firstName = newFirstName;
        lastName = newLastName;
        title = newTitle;
        organization = newOrganization;
        if (newAddresses != null){ addresses = newAddresses; }
    }
    
    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
    public String getTitle(){ return title; }
    public String getOrganization(){ return organization; }
    public ArrayList getAddresses(){ return addresses; }
    
    public void setFirstName(String newFirstName){ firstName = newFirstName; }
    public void setLastName(String newLastName){ lastName = newLastName; }
    public void setTitle(String newTitle){ title = newTitle; }
    public void setOrganization(String newOrganization){ organization = newOrganization; }
    public void addAddress(Address address){
        if(!addresses.contains(address)){
            addresses.add(address);
        }
    }
    public void removeAddress(Address address){
        addresses.remove(address);
    }
    
    public boolean equals(Object o){
        if (!(o instanceof ContactImpl)){
            return false;
        }
        else{
            ContactImpl contact = (ContactImpl)o;
            if (firstName.equals(contact.firstName) &&
                lastName.equals(contact.lastName) &&
                organization.equals(contact.organization) &&
                title.equals(contact.title)){
                return true;
            }
            return false;
        }
    }
    
    public String toString(){
        return firstName + SPACE + lastName + EOL_STRING + addresses;
    }
}