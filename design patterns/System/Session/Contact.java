import java.io.Serializable;
import java.util.ArrayList;
public interface Contact extends Serializable{
    public static final String SPACE = " ";
    public static final String EOL_STRING = System.getProperty("line.separator");
    public String getFirstName();
    public String getLastName();
    public String getTitle();
    public String getOrganization();
    public ArrayList getAddresses();
    
    public void setFirstName(String newFirstName);
    public void setLastName(String newLastName);
    public void setTitle(String newTitle);
    public void setOrganization(String newOrganization);
    public void addAddress(Address address);
    public void removeAddress(Address address);
}