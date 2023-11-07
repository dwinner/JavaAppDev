package facade;

import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 21.08.12
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
public class Currency
{
   public char getCurrencySymbol()
   {
      return currencySymbol;
   }

   public void setCurrencySymbol(char currencySymbol)
   {
      this.currencySymbol = currencySymbol;
   }

   public NumberFormat getNumberFormat()
   {
      return numberFormat;
   }

   public void setNumberFormat(NumberFormat numberFormat)
   {
      this.numberFormat = numberFormat;
   }

   private char currencySymbol;
   private NumberFormat numberFormat;
}
