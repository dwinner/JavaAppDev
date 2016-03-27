package afactory;

import static config.SymbolConstants.EOL;
import static config.SymbolConstants.SPACE;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 28.12.12
 * Time: 18:19
 * Базовый класс для фабричных объектов.
 */
public abstract class Address
{
   // <editor-fold defaultstate="collapsed" desc="Конструкторы">
   protected Address()
   {
   }

   protected Address(String street, String city, String region, String postalCode)
   {
      this.street = street;
      this.city = city;
      this.region = region;
      this.postalCode = postalCode;
   }
   // </editor-fold>

   // <editor-fold defaultstate="collapsed" desc="Свойства">
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

   public String getRegion()
   {
      return region;
   }

   public void setRegion(String region)
   {
      this.region = region;
   }

   public String getPostalCode()
   {
      return postalCode;
   }

   public void setPostalCode(String postalCode)
   {
      this.postalCode = postalCode;
   }
   // </editor-fold>

   public String obtainFullAddress()
   {
      return street + EOL + city + SPACE + EOL;
   }

   public abstract String obtainCountry();

   private String street;
   private String city;
   private String region;
   private String postalCode;
}
