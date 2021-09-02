package recipes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Сохранение объектов в XML-формате.
 *
 * @author Denis
 */
public class XMLExample
{
   public static void main(String[] args)
   {
      XMLExample example = new XMLExample();
      example.start();
   }

   private void start()
   {
      ProgramSettings settings = new ProgramSettings(new Point(10, 10), new Dimension(300, 200), Color.blue,
                                                     "The title of the application");
      FileSystem fileSystem = FileSystems.getDefault();

      // Запись
      try (FileOutputStream fos = new FileOutputStream("settings.xml"))
      {
         try (XMLEncoder encoder = new XMLEncoder(fos))
         {
            encoder.setExceptionListener(new ExceptionListener()
            {
               @Override
               public void exceptionThrown(Exception e)
               {
                  System.out.println("Exception! : " + e.getLocalizedMessage());
               }

            });
            encoder.writeObject(settings);
         }
      }
      catch (FileNotFoundException fnfEx)
      {
         Logger.getLogger(XMLExample.class.getName()).log(Level.SEVERE, null, fnfEx);
      }
      catch (IOException ioEx)
      {
         Logger.getLogger(XMLExample.class.getName()).log(Level.SEVERE, null, ioEx);
      }

      // Чтение
      try (FileInputStream fis = new FileInputStream("settings.xml"))
      {
         try (XMLDecoder decoder = new XMLDecoder(fis))
         {
            ProgramSettings decodedSettings = (ProgramSettings) decoder.readObject();
            System.out.println("Is same? " + settings.equals(decodedSettings));
         }
      }
      catch (FileNotFoundException fnfEx)
      {
         Logger.getLogger(XMLExample.class.getName()).log(Level.SEVERE, null, fnfEx);
      }
      catch (IOException ioEx)
      {
         Logger.getLogger(XMLExample.class.getName()).log(Level.SEVERE, null, ioEx);
      }

      // Анализ
      Path file = fileSystem.getPath("settings.xml");
      try
      {
         List<String> xmlLines = Files.readAllLines(file, Charset.defaultCharset());
         for (String line : xmlLines)
         {
            System.out.println(line);
         }
      }
      catch (IOException ex)
      {
         Logger.getLogger(XMLExample.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

}
