package recipes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Пример стандартного процесса сериализации объектов.
 *
 * @author Denis
 */
public class SerializeExample
{
   public static void main(String[] args)
   {
      SerializeExample example = new SerializeExample();
      example.start();
   }

   private void start()
   {
      try
      {
         ProgramSettings settings = new ProgramSettings(new Point(10, 10), new Dimension(300, 200),
                                                        Color.blue,
                                                        "The title of the application");
         saveSettings(settings, "settings.bin");
         ProgramSettings loadedSettings = loadSettings("settings.bin");
         System.out.println("Are settings are equal? :" + loadedSettings.equals(settings));
      }
      catch (final FileNotFoundException ex)
      {
         Logger.getLogger(SerializeExample.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (IOException | ClassNotFoundException ex)
      {
         Logger.getLogger(SerializeExample.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    * Сохранение настроек приложения в сериализованном виде.
    *
    * @param settings Объект настроек
    * @param fileName Имя файла
    * @throws FileNotFoundException Если файл не найден
    * @throws IOException В процессе записи возникли ошибки
    */
   private void saveSettings(ProgramSettings settings, String fileName) throws FileNotFoundException,
                                                                               IOException
   {
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
      {
         oos.writeObject(settings);
      }
   }

   /**
    * Загрузка настроек.
    *
    * @param fileName Имя файла с байт-кодом сериализованного объекта
    * @return Объект настроек
    * @throws FileNotFoundException Если файл не найден
    * @throws IOException Если в процессе чтения возникла ошибка
    * @throws ClassNotFoundException Класс объекта не найден загрузчиком
    */
   private ProgramSettings loadSettings(String fileName) throws FileNotFoundException, IOException,
                                                                ClassNotFoundException
   {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
      {
         return (ProgramSettings) ois.readObject();
      }
   }

}
