package mementotest;

public class AddressImpl implements Address
{
   public AddressImpl()
   {
   }

   public AddressImpl(String type, String description, String street, String city, String state,
                      String zipCode)
   {
      this.type = type;
      this.description = description;
      this.street = street;
      this.city = city;
      this.state = state;
      this.zipCode = zipCode;
   }

   @Override
   public String toString()
   {
      return "AddressImpl{" + "type=" + type + ", description=" + description
        + ", street=" + street + ", city=" + city
        + ", state=" + state + ", zipCode=" + zipCode + '}';
   }

   @Override
   public String getType()
   {
      return type;
   }

   @Override
   public String getDescription()
   {
      return description;
   }

   @Override
   public String getStreet()
   {
      return street;
   }

   @Override
   public String getCity()
   {
      return city;
   }

   @Override
   public String getState()
   {
      return state;
   }

   @Override
   public String getZipCode()
   {
      return zipCode;
   }

   @Override
   public void setType(String newType)
   {
      type = newType;
   }

   @Override
   public void setDescription(String newDescription)
   {
      description = newDescription;
   }

   @Override
   public void setStreet(String newStreet)
   {
      street = newStreet;
   }

   @Override
   public void setCity(String newCity)
   {
      city = newCity;
   }

   @Override
   public void setState(String newState)
   {
      state = newState;
   }

   @Override
   public void setZipCode(String newZip)
   {
      zipCode = newZip;
   }
   
   private String type;
   private String description;
   private String street;
   private String city;
   private String state;
   private String zipCode;
}
