package new_synchronizators;

/**
 * Автоматическое создание Generator для класса с конструктором по умолчанию (без аргументов)
 *
 * @param <T> Тип для генератора
 * @author Denis 20.08.12 22:06
 */
public class BasicGenerator<T> implements Generator<T>
{
   public BasicGenerator(Class<T> type)
   {
      this.type = type;
   }

   @Override
   public T next()
   {
      try
      {  // Предполагается, что type является public-классом
         return type.newInstance();
      }
      catch (InstantiationException | IllegalAccessException e)
      {
         throw new RuntimeException(e);
      }
   }

   /**
    * Получение генератора по умолчанию для заданного типа
    *
    * @param type Тип
    * @param <T> Параметр типа
    * @return Обобщенный генератор
    */
   public static <T> Generator<T> create(Class<T> type)
   {
      return new BasicGenerator<T>(type);
   }

   private Class<T> type;
}
