import java.util.Enumeration;

/**
 * Объект Win32RegKey может применяться для извлечения и установки
 * значений, соответствующих ключу реестра Windows.
 * @version 1.00 1997-07-01
 * @author Cay Horstmann
 */
public class Win32RegKey
{
   public static final int HKEY_CLASSES_ROOT = 0x80000000;
   public static final int HKEY_CURRENT_USER = 0x80000001;
   public static final int HKEY_LOCAL_MACHINE = 0x80000002;
   public static final int HKEY_USERS = 0x80000003;
   public static final int HKEY_CURRENT_CONFIG = 0x80000005;
   public static final int HKEY_DYN_DATA = 0x80000006;

   static
   {
      System.loadLibrary("Win32RegKey");
   }

   private int root;
   private String path;

   /**
    * Создание объекта, представляющего ключ реестра.
    * @param theRoot Одно из следующих значений: HKEY_CLASSES_ROOT, HKEY_CURRENT_USER,
    *                  HKEY_LOCAL_MACHINE, HKEY_USERS, HKEY_CURRENT_CONFIG, HKEY_DYN_DATA
    * @param thePath Путь к ключу реестра.
    */
   public Win32RegKey(int theRoot, String thePath)
   {
      root = theRoot;
      path = thePath;
   }

   /**
    * Перечисление всех имен для пути, указанного в объекте.
    * @return Объект Enumeration, содержащий информацию об именах
    */
   public Enumeration<String> names()
   {
      return new Win32RegKeyEnumeration(root, path);
   }

   /**
    * Получение значения, связанного с именем.
    * @param name Имя.
    * @return Значение, соответствующее указанному имени
    */
   public native Object getValue(String name);

   /**
    * Связывание значения с именем.
    * @param name Имя
    * @param value Новое значение
    */
   public native void setValue(String name, Object value);
}

class Win32RegKeyEnumeration implements Enumeration<String>
{
   private int root;
   private String path;
   private int index = -1;
   private int hkey = 0;
   private int maxsize;
   private int count;

   Win32RegKeyEnumeration(int theRoot, String thePath)
   {
      root = theRoot;
      path = thePath;
   }

   @Override public native boolean hasMoreElements();
   @Override public native String nextElement();
}

class Win32RegKeyException extends RuntimeException
{
   public Win32RegKeyException()
   {
   }

   public Win32RegKeyException(String why)
   {
      super(why);
   }
}
