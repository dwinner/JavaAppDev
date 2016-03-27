import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 21.08.12
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
public class DataCreator
{
   public static void serialize(String fileName)
   {
      saveFrData();
      saveUsData();
      saveNlData();
   }

   private static void saveNlData()
   {
      Properties textSettings = new Properties();
      textSettings.setProperty(GUI_TITLE, "Facade Pattern voorbeeld");
      textSettings.setProperty(EXIT_CAPTION, "Exit");
      textSettings.setProperty(COUNTRY_LABEL, "Land");
      textSettings.setProperty(CURRENCY_LABEL, "Munt eenheid");
      textSettings.setProperty(PHONE_LABEL, "Telefoonnummer");
      try
      {
         textSettings.store(new FileOutputStream("dutch.properties"), "Dutch Settings");
      }
      catch (IOException ioEx)
      {
         System.err.println("Error storing settings to output");
         ioEx.printStackTrace();
      }
   }

   private static void saveUsData()
   {
      Properties textSettings = new Properties();
      textSettings.setProperty(GUI_TITLE, "Facade Pattern Demonstration");
      textSettings.setProperty(EXIT_CAPTION, "Exit");
      textSettings.setProperty(COUNTRY_LABEL, "Country");
      textSettings.setProperty(CURRENCY_LABEL, "facade.Currency");
      textSettings.setProperty(PHONE_LABEL, "Phone Number");
      try
      {
         textSettings.store(new FileOutputStream("us.properties"), "US Settings");
      }
      catch (IOException ioEx)
      {
         System.err.println("Error storing settings to output");
         ioEx.printStackTrace();
      }
   }

   private static void saveFrData()
   {
      Properties textSettings = new Properties();
      textSettings.setProperty(GUI_TITLE, "Demonstration du Pattern Facade");
      textSettings.setProperty(EXIT_CAPTION, "Sortir");
      textSettings.setProperty(COUNTRY_LABEL, "Pays");
      textSettings.setProperty(CURRENCY_LABEL, "Monnaie");
      textSettings.setProperty(PHONE_LABEL, "Numero de Telephone");
      try
      {
         textSettings.store(new FileOutputStream("french.properties"), "French Settings");
      }
      catch (IOException ioEx)
      {
         System.err.println("Error storing settings to output");
         ioEx.printStackTrace();
      }
   }

   private DataCreator() { }

   private static final String GUI_TITLE = "title";
   private static final String EXIT_CAPTION = "exit";
   private static final String COUNTRY_LABEL = "country";
   private static final String CURRENCY_LABEL = "currency";
   private static final String PHONE_LABEL = "phone";
}
