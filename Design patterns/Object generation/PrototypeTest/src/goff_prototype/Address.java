package goff_prototype;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 30.07.12
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public class Address implements Copyable
{
   public Address(String type, String street, String city, String state, String zipCode)
   {
      this.type = type;
      this.street = street;
      this.city = city;
      this.state = state;
      this.zipCode = zipCode;
   }

   public Address(String street, String city, String state, String zipCode)
   {
      this(WORK, street, city, state, zipCode);
   }

   public Address(String type)
   {
      this.type = type;
   }

   public Address() { }

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public String getStreet()
   {
      return street;
   }

   public void setStreet(String street)
   {
      this.street = street;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   public String getZipCode()
   {
      return zipCode;
   }

   public void setZipCode(String zipCode)
   {
      this.zipCode = zipCode;
   }

   @Override
   public Object copy()
   {
      return new Address(street, city, state, zipCode);
   }

   @Override
   public String toString()
   {
      return "goff_prototype.Address{" +
        "type='" + type + '\'' +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", zipCode='" + zipCode + '\'' +
        '}';
   }

   public static final String EOL_STRING = System.getProperty("line.separator");
   public static final String COMMA = ",";
   public static final String HOME = "home";
   public static final String WORK = "work";

   private String type;
   private String street;
   private String city;
   private String state;
   private String zipCode;
}
