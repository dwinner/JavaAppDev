package ru.aztpa.a.vfp.mfg.wiptransfers.v01;

import java.util.regex.Pattern;

/**
 * ��������������� ����� ��� ��������� �����.
 *
 * @author jdeveloper@aztpa.ru
 * @version 1.0.0 17-07-2012
 */
public class StringUtilities
{
   /**
    * �������� ������ �� ������ ��������.
    *
    * @param toCheckString ������ ��� ��������
    * @return true, ���� ������ ������, false � ��������� ������
    */
   public static boolean isNullOrEmpty(String toCheckString)
   {
      return toCheckString == null || toCheckString.trim().isEmpty() ? true : false;
   }

   /**
    * �������� ����, ��� � ������ ���������� ����� �����
    *
    * @param toCheckString ������ ��� ��������
    * @return true, ���� � ������ ���������� ����� �����, false � ��������� ������
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
    * �������� ����, ��� � ������ ���������� ����� � ��������� ������
    *
    * @param toCheckString ������ ��� ��������
    * @return true, ���� � ������ ���������� ����� � ��������� ������, false � ��������� ������
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
