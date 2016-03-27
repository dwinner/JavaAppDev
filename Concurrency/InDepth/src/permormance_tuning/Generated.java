package permormance_tuning;

public class Generated
{
   /**
    * Заполнение существующего массива
    * @param <T> Тип массива
    * @param a Параметризованный массив
    * @param gen Генератор
    * @return Новый массив
    */
   public static <T> T[] array(T[] a, Generator<T> gen)
   {
      return new CollectionData<T>(gen, a.length).toArray(a);
   }

   /**
    * Создание нового массива
    * @param <T> Параметр типа
    * @param type Класс для обобщенного типа
    * @param gen Генератор
    * @param size Размер массива
    * @return Массив объектов по типу T
    */
   @SuppressWarnings("unchecked")
   public static <T> T[] array(Class<T> type, Generator<T> gen, int size)
   {
      T[] a = (T[]) java.lang.reflect.Array.newInstance(type, size);
      return new CollectionData<>(gen, size).toArray(a);
   }

}
