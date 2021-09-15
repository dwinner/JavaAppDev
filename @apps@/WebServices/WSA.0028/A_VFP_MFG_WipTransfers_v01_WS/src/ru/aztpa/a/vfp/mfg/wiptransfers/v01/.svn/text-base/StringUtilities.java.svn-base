package ru.aztpa.a.vfp.mfg.wiptransfers.v01;

import java.util.regex.Pattern;

/**
 * Вспомогательный класс для обработки строк.
 *
 * @author jdeveloper@aztpa.ru
 * @version 1.0.0 17-07-2012
 */
public class StringUtilities
{
   /**
    * Проверка строки на пустое значение.
    *
    * @param toCheckString Строка для проверки
    * @return true, если строка пустая, false в противном случае
    */
   public static boolean isNullOrEmpty(String toCheckString)
   {
      return toCheckString == null || toCheckString.trim().isEmpty() ? true : false;
   }

   /**
    * Проверка того, что в строке фактически целое число
    *
    * @param toCheckString Строка для проверки
    * @return true, если в строке фактически целое число, false в противном случае
    */
   public static boolean isIntegerString(String toCheckString)
   {
      try
      {
         Integer.parseInt(toCheckString);
         return true;
      }
      catch (NumberFormatException e)
      {
         return false;
      }
   }

   /**
    * Проверка того, что в строке фактически число с плавающей точкой
    *
    * @param toCheckString Строка для проверки
    * @return true, если в строке фактически число с плавающей точкой, false в противном случае
    */
   public static boolean isDoubleString(String toCheckString)
   {
      try
      {
         Double.parseDouble(toCheckString);
         return true;
      }
      catch (NumberFormatException e)
      {
         return false;
      }
   }

   private StringUtilities()
   {
   }
   
   private final static String INTEGER_REG_EXP  = "[\\d]+";
   private final static Pattern INTEGER_PATTERN = Pattern.compile(INTEGER_REG_EXP);
   
   private final static String DOUBLE_REG_EXP = "[\\d]+(\\.[\\d]+)?";
   private final static Pattern DOUBLE_PATTERN = Pattern.compile(DOUBLE_REG_EXP);
}
