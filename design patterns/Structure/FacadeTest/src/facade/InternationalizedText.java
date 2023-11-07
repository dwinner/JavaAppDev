package facade;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 21.08.12
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class InternationalizedText
{
   public InternationalizedText(String fileName)
   {
      loadProperties(fileName);
   }

   public InternationalizedText()
   {
      this(DEFAULT_FILE_NAME);
   }

   public void setFileName(String newFileName)
   {
      if (newFileName != null)
         loadProperties(newFileName);
   }

   private void loadProperties(String fileName)
   {
      try
      {
         FileInputStream inputStream = new FileInputStream(fileName);
         textProperties.load(inputStream);
      }
      catch (FileNotFoundException fnfEx)
      {
         Logger.getLogger(
           InternationalizedText.class.getName()).log(Level.SEVERE, fnfEx.getLocalizedMessage());
      }
      catch (IOException ioEx)
      {
         throw new RuntimeException(ioEx);
      }
   }

   public String getProperty(String key)
   {
      return textProperties.getProperty(key);
   }

   public String getProperty(String key, String defaultValue)
   {
      return textProperties.getProperty(key, defaultValue);
   }

   private static final String DEFAULT_FILE_NAME = "";
   private Properties textProperties = new Properties();
}
