package proxy;

/**
* Created with IntelliJ IDEA.
* User: oracle_pr1
* Date: 23.08.12
* Time: 11:18
* To change this template use File | Settings | File Templates.
*/
public class AddressImpl implements Address
{
   public AddressImpl() { }

   public AddressImpl(String description, String street, String city, String state, String zipCode, String type)
   {
      this.description = description;
      this.street = street;
      this.city = city;
      this.state = state;
      this.zipCode = zipCode;
      this.type = type;
   }

   @Override
   public String getAddress()
   {
      return toString();
   }

   @Override
   public String toString()
   {
      return "proxy.AddressImpl{" +
        "type='" + type + '\'' +
        ", description='" + description + '\'' +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", zipCode='" + zipCode + '\'' +
        '}';
   }

   @Override
   public String getType()
   {
      return type;
   }

   @Override
   public void setType(String type)
   {
      this.type = type;
   }

   @Override
   public String getDescription()
   {
      return description;
   }

   @Override
   public void setDescription(String description)
   {
      this.description = description;
   }

   @Override
   public String getStreet()
   {
      return street;
   }

   @Override
   public void setStreet(String street)
   {
      this.street = street;
   }

   @Override
   public String getCity()
   {
      return city;
   }

   @Override
   public void setCity(String city)
   {
      this.city = city;
   }

   @Override
   public String getState()
   {
      return state;
   }

   @Override
   public void setState(String state)
   {
      this.state = state;
   }

   @Override
   public String getZipCode()
   {
      return zipCode;
   }

   @Override
   public void setZipCode(String zipCode)
   {
      this.zipCode = zipCode;
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
