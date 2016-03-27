package facade;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 21.08.12
 * Time: 11:04
 * To change this template use File | Settings | File Templates.
 */
public class InternationalizationWizard
{
   public InternationalizationWizard()
   {
      nationMap = new HashMap<>();
      Nation[] nations =
        {
          new Nation('$', "US", "+1", "us.properties", NumberFormat.getInstance(Locale.US)),
          new Nation('f', "The Netherlands", "+31", "dutch.properties", NumberFormat.getInstance(Locale.GERMANY)),
          new Nation('f', "France", "+33", "french.properties", NumberFormat.getInstance(Locale.FRANCE))
        };
      for (int i = 0; i < nations.length; i++)
         nationMap.put(nations[i].getName(), nations[i]);
   }

   public Nation[] getNations()
   {
      return nationMap.values().toArray(new Nation[nationMap.values().size()]);
   }

   public void setNation(String name)
   {
      Nation nation = nationMap.get(name);
      if (nation != null)
      {
         currency.setCurrencySymbol(nation.getSymbol());
         currency.setNumberFormat(nation.getNumberFormat());
         PhoneNumber.setSelectedInterPrefix(nation.getDialingPrefix());
         propertyFile.setFileName(nation.getPropertyFileName());
      }
   }

   public Nation getNation(String name)
   {
      return nationMap.get(name);
   }

   public void setCurrencySymbol(char currencySymbol)
   {
      currency.setCurrencySymbol(currencySymbol);
   }

   public char getCurrencySymbol()
   {
      return currency.getCurrencySymbol();
   }

   public NumberFormat getNumberFormat()
   {
      return currency.getNumberFormat();
   }

   public String getPhonePrefix()
   {
      return PhoneNumber.getSelectedInterPrefix();
   }

   public String getProperty(String key)
   {
      return propertyFile.getProperty(key);
   }

   public String getProperty(String key, String defaultValue)
   {
      return propertyFile.getProperty(key, defaultValue);
   }

   private Map<String, Nation> nationMap;
   private Currency currency = new Currency();
   private InternationalizedText propertyFile = new InternationalizedText();
}
