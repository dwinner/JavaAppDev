package imlps;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 22.08.12
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class AddressImpl implements Address
{
   public AddressImpl()
   {
   }

   public AddressImpl(String newType, String newDescription, String newStreet, String newCity, String newState,
                      String newZipCode)
   {
      type = newType;
      description = newDescription;
      street = newStreet;
      city = newCity;
      state = newState;
      zipCode = newZipCode;
   }

   @Override public String getType() { return type; }
   @Override public String getDescription() { return description; }
   @Override public String getStreet() { return street; }
   @Override public String getCity() { return city; }
   @Override public String getState() { return state; }
   @Override public String getZipCode() { return zipCode; }

   @Override public void setType(String newType) { type = newType; }
   @Override public void setDescription(String newDescription) { description = newDescription; }
   @Override public void setStreet(String newStreet) { street = newStreet; }
   @Override public void setCity(String newCity) { city = newCity; }
   @Override public void setState(String newState) { state = newState; }
   @Override public void setZipCode(String newZip) { zipCode = newZip; }

   @Override public String toString()
   {
      return "imlps.AddressImpl{" +
        "type='" + type + '\'' +
        ", description='" + description + '\'' +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", zipCode='" + zipCode + '\'' +
        '}';
   }

   public static final String HOME = "home";
   public static final String WORK = "work";

   private String type;
   private String description;
   private String street;
   private String city;
   private String state;
   private String zipCode;
}
