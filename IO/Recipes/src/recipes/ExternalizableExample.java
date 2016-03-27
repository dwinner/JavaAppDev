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
 * Явная сериализация объектов.
 *
 * @author Denis
 */
public class ExternalizableExample
{
   public static void main(String[] args)
   {
      ExternalizableExample example = new ExternalizableExample();
      example.start();
   }

   private void start()
   {
      try
      {
         ExternalizableProgramSettings settings =
           new ExternalizableProgramSettings(new Point(10, 10), new Dimension(300, 200),
                                             Color.blue, "The title of the application");
         saveSettings(settings, "settingsExternalizable.bin");
         ExternalizableProgramSettings loadedSettings = loadSettings("settingsExternalizable.bin");
         System.out.println("Are settings equal? " + loadedSettings.equals(settings));
      }
      catch (final FileNotFoundException ex)
      {
         Logger.getLogger(ExternalizableExample.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (IOException | ClassNotFoundException ex)
      {
         Logger.getLogger(ExternalizableExample.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    * Сохранение настроек.
    *
    * @param settings Объект с настройками
    * @param fileName Имя файла для сохранения настроек
    * @throws FileNotFoundException
    * @throws IOException
    */
   private void saveSettings(ExternalizableProgramSettings settings, String fileName)
     throws FileNotFoundException, IOException
   {
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName)))
      {
         oos.writeObject(settings);
      }
   }

   /**
    * Загрузка настроек
    *
    * @param fileName Имя файла с настройками
    * @return Объект настроек
    * @throws FileNotFoundException
    * @throws IOException
    * @throws ClassNotFoundException
    */
   private ExternalizableProgramSettings loadSettings(String fileName) throws FileNotFoundException,
                                                                              IOException,
                                                                              ClassNotFoundException
   {
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
      {
         return (ExternalizableProgramSettings) ois.readObject();
      }
   }

}
