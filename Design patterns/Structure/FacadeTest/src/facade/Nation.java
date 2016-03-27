package facade;

import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 21.08.12
 * Time: 10:29
 * To change this template use File | Settings | File Templates.
 */
public class Nation
{
   public Nation(char symbol, String name, String dialingPrefix, String propertyFileName, NumberFormat numberFormat)
   {
      this.symbol = symbol;
      this.name = name;
      this.dialingPrefix = dialingPrefix;
      this.propertyFileName = propertyFileName;
      this.numberFormat = numberFormat;
   }

   public char getSymbol()
   {
      return symbol;
   }

   public String getName()
   {
      return name;
   }

   public String getDialingPrefix()
   {
      return dialingPrefix;
   }

   public String getPropertyFileName()
   {
      return propertyFileName;
   }

   public NumberFormat getNumberFormat()
   {
      return numberFormat;
   }

   @Override public String toString() { return name; }

   private char symbol;
   private String name;
   private String dialingPrefix;
   private String propertyFileName;
   private NumberFormat numberFormat;
}
