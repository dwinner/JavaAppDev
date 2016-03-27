import java.util.Map;

/**
 * Загрузчик классов, который загружает классы из таблицы, где в роли ключей
 * выступают имена классов, а в роли значений - массивы байт-кодов.
 * @version 1.00 2007-11-02
 * @author Cay Horstmann
 */
public class MapClassLoader extends ClassLoader
{
   private Map<String, byte[]> classes;

   public MapClassLoader(Map<String, byte[]> classes)
   {
      this.classes = classes;
   }

   @Override
   protected Class<?> findClass(String name) throws ClassNotFoundException
   {
      byte[] classBytes = classes.get(name);
      if (classBytes == null)
         throw new ClassNotFoundException(name);
      Class<?> cl = defineClass(name, classBytes, 0, classBytes.length);
      if (cl == null)
         throw new ClassNotFoundException(name);
      return cl;
   }
}
