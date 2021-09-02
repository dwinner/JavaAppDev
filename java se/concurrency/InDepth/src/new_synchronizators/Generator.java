package new_synchronizators;

/**
 * Created with IntelliJ IDEA. User: Denis Date: 18.08.12 Time: 10:38 Параметризованный интерфейс.
 *
 * @param <T> Тип для генератора
 */
public interface Generator<T>
{
   /**
    * Генерирование очередного объекта
    *
    * @return Новый экземпляр объекта
    */
   T next();

}
