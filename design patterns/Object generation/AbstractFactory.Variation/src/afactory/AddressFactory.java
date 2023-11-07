package afactory;

import static config.SymbolConstants.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 28.12.12
 * Time: 18:20
 * Генератор объектов-адресов.
 */
public class AddressFactory
{
   /**
    * Создание объекта французского адреса
    *
    * @return Объект французского адреса
    */
   public static Address createFrenchAddress()
   {
      return new Address()
      {
         @Override
         public String obtainCountry()
         {
            return "France";
         }

         @Override
         public String obtainFullAddress()
         {
            return String.format("%s%s%s%s%s%s%s%s", getStreet(), EOL, getPostalCode(), SPACE, getCity(), EOL,
                                 obtainCountry(), EOL);
         }
      };
   }

   /**
    * Создание объекта английского адреса
    *
    * @return Объект английского адреса
    */
   public static Address createUSAddress()
   {
      return new Address()
      {
         @Override
         public String obtainCountry()
         {
            return "United States";
         }

         @Override
         public String obtainFullAddress()
         {
            return String.format("%s%s%s%s%s%s%s%s%s%s%s", getStreet(), EOL, getCity(), COMMA, SPACE, getRegion(),
                                 SPACE, getPostalCode(), EOL, obtainCountry(), EOL);
         }
      };
   }

   private AddressFactory()
   {
   }
}
