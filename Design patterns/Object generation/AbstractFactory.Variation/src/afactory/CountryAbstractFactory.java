package afactory;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 28.12.12
 * Time: 19:26
 * Интерфейс для абстрактных фабрик.
 */
public interface CountryAbstractFactory
{
   Address createAddress();

   PhoneNumber createPhoneNumber();

   /**
    * Реализация абстрактной фабрики объектов.
    */
   public static class CountryFactory
   {
      /**
       * Генератор фабрик для Франции
       *
       * @return Генератор фабрик для Франции
       */
      public static CountryAbstractFactory createFrenchCountry()
      {
         return new CountryAbstractFactory()
         {
            @Override
            public Address createAddress()
            {
               return AddressFactory.createFrenchAddress();
            }

            @Override
            public PhoneNumber createPhoneNumber()
            {
               return PhoneNumberFactory.createFrenchPhoneNumber();
            }
         };
      }

      /**
       * Генератор фабрик для Англии
       *
       * @return Генератор фабрик для Англии
       */
      public static CountryAbstractFactory createUSCountry()
      {
         return new CountryAbstractFactory()
         {
            @Override
            public Address createAddress()
            {
               return AddressFactory.createUSAddress();
            }

            @Override
            public PhoneNumber createPhoneNumber()
            {
               return PhoneNumberFactory.createUSPhoneNumber();
            }
         };
      }

      private CountryFactory()
      {
      }
   }
}
